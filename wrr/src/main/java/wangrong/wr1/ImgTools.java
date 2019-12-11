package wangrong.wr1;
import com.alibaba.druid.util.StringUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.*;

import java.util.Hashtable;

public class ImgTools {
    /**
     * 设置源图片为背景透明，并设置透明度
     * @param srcFile 源图片
     * @param desFile 目标文件
     * @param alpha 透明度
     * @param formatName 文件格式
     * @throws IOException
     */

    public static void transparentImage(String srcFile,String desFile,int alpha,String formatName) throws IOException{
        BufferedImage temp =  ImageIO.read(new File(srcFile));//取得图片
        transparentImage(temp, desFile, alpha, formatName);
    }
    /**
     * 设置源图片为背景透明，并设置透明度
     * @param srcImage 源图片
     * @param desFile 目标文件
     * @param alpha 透明度
     * @param formatName 文件格式
     * @throws IOException
     */
    public static void transparentImage(BufferedImage srcImage,
                                        String desFile, int alpha, String formatName) throws IOException {
        int imgHeight = srcImage.getHeight();//取得图片的长和宽
        int imgWidth = srcImage.getWidth();

        int c = srcImage.getRGB(3, 3);
        System.out.println(c);
        //防止越位
        if (alpha < 0) {
            alpha = 0;
        } else if (alpha > 10) {
            alpha = 10;
        }
        alpha=5;
        BufferedImage bi = new BufferedImage(imgWidth, imgHeight,
                BufferedImage.TYPE_4BYTE_ABGR);//新建一个类型支持透明的BufferedImage
        for(int i = 0; i < imgWidth; ++i)//把原图片的内容复制到新的图片，同时把背景设为透明
        {
            for(int j = 0; j < imgHeight; ++j)
            {
                System.out.println(srcImage.getRGB(i, j));
                //把背景设为透明
                if(srcImage.getRGB(i, j) == c)
                {
                    System.out.println(c);
                    bi.setRGB(i, j, c & 0x00ffffff);
                }
                //设置透明度
                else{
                    int rgb = srcImage.getRGB(i, j);
                    // rgb = ((alpha * 255 / 10) << 24) | (rgb & 0x00ffffff);
                    bi.setRGB(i, j, rgb);
                }
            }
        }
        ImageIO.write(bi, StringUtils.isEmpty(formatName)?"PNG":formatName, new File(desFile));
    }
    /**
     * 按照 宽高 比例压缩
     *
     * @param img
     * @param width
     * @param height
     * @param out
     * @throws IOException
     */
    public static void thumbnail_w_h(File img, int width, int height,
                                     OutputStream out) throws IOException {
        BufferedImage bi = ImageIO.read(img);
        double srcWidth = bi.getWidth(); // 源图宽度
        double srcHeight = bi.getHeight(); // 源图高度

        double scale = 1;

        if (width > 0) {
            scale = width / srcWidth;
        }
        if (height > 0) {
            scale = height / srcHeight;
        }
        if (width > 0 && height > 0) {
            scale = height / srcHeight < width / srcWidth ? height / srcHeight
                    : width / srcWidth;
        }

        thumbnail(img, (int) (srcWidth * scale), (int) (srcHeight * scale), out);

    }

    /**
     * 按照固定宽高原图压缩
     *
     * @param img
     * @param width
     * @param height
     * @param out
     * @throws IOException
     */
    public static void thumbnail(File img, int width, int height,
                                 OutputStream out) throws IOException {
        BufferedImage BI = ImageIO.read(img);
        Image image = BI.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        BufferedImage tag = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.setColor(Color.RED);
        g.drawImage(image, 0, 0, null); // 绘制处理后的图
        g.dispose();
        int c = tag.getRGB(3, 3);
        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_4BYTE_ABGR);//新建一个类型支持透明的BufferedImage
        for(int i = 0; i < width; ++i)//把原图片的内容复制到新的图片，同时把背景设为透明
        {
            for(int j = 0; j < height; ++j)
            {
                System.out.println(tag.getRGB(i, j));
                //把背景设为透明
                if(tag.getRGB(i, j) == c)
                {
                    System.out.println(c);
                    bi.setRGB(i, j, c & 0x00ffffff);
                }
                //设置透明度
                else{
                    int rgb = tag.getRGB(i, j);
                    // rgb = ((alpha * 255 / 10) << 24) | (rgb & 0x00ffffff);
                    bi.setRGB(i, j, rgb);
                }
            }
        }


        ImageIO.write(bi, "JPEG", out);
    }

    /**
     * 按照宽高裁剪
     *
     * @param srcImageFile
     * @param destWidth
     * @param destHeight
     * @param out
     */
    public static void cut_w_h(File srcImageFile, int destWidth,
                               int destHeight, OutputStream out) {
        cut_w_h(srcImageFile, 0, 0, destWidth, destHeight, out);
    }

    public static void cut_w_h(File srcImageFile, int x, int y, int destWidth,
                               int destHeight, OutputStream out) {
        try {
            Image img;
            ImageFilter cropFilter;
            // 读取源图像
            BufferedImage bi = ImageIO.read(srcImageFile);
            int srcWidth = bi.getWidth(); // 源图宽度
            int srcHeight = bi.getHeight(); // 源图高度

            if (srcWidth >= destWidth && srcHeight >= destHeight) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight,
                        Image.SCALE_DEFAULT);

                cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
                img = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(destWidth, destHeight,
                        BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null); // 绘制截取后的图
                g.dispose();
                // 输出为文件
                ImageIO.write(tag, "JPEG", out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }// #cut_w_h
//将小图片描绘在大图片上


    public static BufferedImage getImageGraphics(File imgs)
    {

        BufferedImage buffImg=null;
        try {
            InputStream is = new FileInputStream(imgs);
        /* 通过JPEG图象流创建JPEG数据流解码器 */
            JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
            //解码当前JPEG数据流，返回BufferedImage对象
             buffImg = jpegDecoder.decodeAsBufferedImage();

        }catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ImageFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return buffImg;
    }
    //将String描绘在大图片上
    public static void exportString(int x,int y,String  str,Color color,Font font,BufferedImage buffImg)
    {
        Graphics g = buffImg.getGraphics();
        g.setColor(color);
        g.setFont(font);


        g.drawString(str,x,y);

    }
    //将矩形描绘在大图片上
    public static void exportRect(int x1,int y1,int width,int height,int ArcX,int ArcY,Color color,BufferedImage buffImg)
    {
        Graphics g = buffImg.getGraphics();
        g.setColor(color);
        g.fillRoundRect(x1,y1,width,height,ArcX,ArcY);

    }
    //将小图片描绘在大图片上
    public static void exportImg(int x,int y,File headImg,BufferedImage buffImg){
        try {


            //创建你要附加的图象。
            //小图片的路径
            //得到画笔对象
            Graphics g = buffImg.getGraphics();
            ImageIcon imgIcon = new ImageIcon(headImg.getPath());

            //得到Image对象。
            Image img = imgIcon.getImage();

            //将小图片绘到大图片上。
            //5,300 .表示你的小图片在大图片上的位置。
            g.drawImage(img,x,y,null);
            g.dispose();


        }catch (ImageFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    //输出图片
    public static void outImg(String outPath,BufferedImage buffImg)
    {
        OutputStream os=null;

        try {
            os = new FileOutputStream(outPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
        try {
            en.encode(buffImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        File img = new File("d:\\1.jpg");
        FileOutputStream fos = new FileOutputStream("d:\\9.png");
        // ImgTools.thumbnail(img, 100, 100, fos);
        // ImgTools.cut_w_h(img, 230, 200, fos);
        ImgTools.main3("G:\\111\\imgs\\background.JPEG", "G:\\111\\imgs\\result1.JPEG");

    }
    public static void main3(String filePath,String outPath) throws IOException
    {
        BufferedImage bi1 = ImageIO.read(new File(filePath));

        // 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
        BufferedImage image = new BufferedImage(bi1.getWidth(), bi1.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        Rectangle2D.Double  shape = new Rectangle2D.Double (0, 0, bi1.getWidth(), bi1.getHeight()/2-10);
        Rectangle2D.Double  shape1 = new Rectangle2D.Double (0, bi1.getHeight()/2-10, bi1.getWidth()/2-10, 20);
        Rectangle2D.Double  shape2 = new Rectangle2D.Double (bi1.getWidth()/2+10, bi1.getHeight()/2-10, bi1.getWidth()/2-10, 20);
        Rectangle2D.Double  shape3 = new Rectangle2D.Double (0, bi1.getHeight()/2+10, bi1.getWidth(), bi1.getHeight()/2-10);
         //图片的裁剪
        Graphics2D g2 = image.createGraphics();
        image = g2.getDeviceConfiguration().createCompatibleImage(bi1.getWidth(), bi1.getHeight(), Transparency.TRANSLUCENT);
        g2 = image.createGraphics();
        g2.setComposite(AlphaComposite.Clear);
        g2.fill(new Rectangle(image.getWidth(), image.getHeight()));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1.0f));
        //g2.setClip(shape);
        g2.setClip(shape1);
        //g2.setClip(shape2);
        //g2.setClip(shape3);
        // 使用 setRenderingHint 设置抗锯齿
        g2.drawImage(bi1, 0, 0, null);
        g2.dispose();


        try {
            ImageIO.write(image, "JPEG", new File(outPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //将图片处理成背景透明圆形
    public static void main2(String filePath,String outPath) throws IOException
    {
        BufferedImage bi1 = ImageIO.read(new File(filePath));

        // 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
        BufferedImage image = new BufferedImage(bi1.getWidth(), bi1.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1
                .getHeight());

        Graphics2D g2 = image.createGraphics();
        image = g2.getDeviceConfiguration().createCompatibleImage(bi1.getWidth(), bi1.getHeight(), Transparency.TRANSLUCENT);
        g2 = image.createGraphics();
        g2.setComposite(AlphaComposite.Clear);
        g2.fill(new Rectangle(image.getWidth(), image.getHeight()));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1.0f));
        g2.setClip(shape);
        // 使用 setRenderingHint 设置抗锯齿
        g2.drawImage(bi1, 0, 0, null);
        g2.dispose();

        try {
            ImageIO.write(image, "PNG", new File(outPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//将内容生成二维码
    public static void Encode_QR_CODE(String context,String outPath,int width,int height) throws IOException, WriterException {
        String contents =context; // 二维码内容
       // int width =175; // 二维码图片宽度 300
       // int height = 175; // 二维码图片高度300

        String format = "jpg";// 二维码的图片格式 gif

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//      hints.put(EncodeHintType.MAX_SIZE, 350);//设置图片的最大值
//      hints.put(EncodeHintType.MIN_SIZE, 100);//设置图片的最小值
        hints.put(EncodeHintType.MARGIN, 1);//设置二维码边的空度，非负数

        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,//要编码的内容
                //编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,
                //Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,
                //MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E 1D,UPC/EAN extension,UPC_EAN_EXTENSION
                BarcodeFormat.QR_CODE,
                width, //条形码的宽度
                height, //条形码的高度
                hints);//生成条形码时的一些配置,此项可选

        // 生成二维码
        File outputFile = new File(outPath);//指定输出路径
        System.out.println("e:" + File.separator + "new-1.jpg");
        System.out.println("生成成功");
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
    }

    public static void main1(String[] args) throws IOException {
        ImgTools.transparentImage("G:\\111\\imgs\\task6.png","G:\\111\\imgs\\task9.png",5,"png");
    }

}