package wangrong.wr1;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import java.text.SimpleDateFormat;
import java.util.Date;
public class Test {
    public static String main5(String[] args) throws Exception {
        try {



            BufferedImage bimage=ImgTools.getImageGraphics(new File("G:\\111\\imgs\\background1.jpg"));
             ImgTools.exportRect(0,0,503,57,0,0,new Color(63,63,63),bimage);
             ImgTools.exportString(163,35,"时 间 典 当 行",new Color(52,186,92),new Font("微软雅黑",Font.BOLD,23),bimage);

            //处理用户头像
            File img1 = new File("G:\\111\\imgs\\userhead.jpg");
            FileOutputStream fos1 = new FileOutputStream("G:\\111\\imgs\\userhead1.png");
            ImgTools.thumbnail_w_h(img1, 95, 95, fos1);
            ImgTools.main2("G:\\111\\imgs\\userhead1.png","G:\\111\\imgs\\userhead2.png");
            ImgTools.exportImg(195,86,new File("G:\\111\\imgs\\userhead2.png"),bimage);

            ImgTools.exportRect(174,192,152,60,10,10,new Color(110,190,89),bimage);
            ImgTools.exportString(226,225,"组队",new Color(255,255,255),new Font("微软雅黑",Font.BOLD,25),bimage);

            ImgTools.exportRect(2,256,499,50,0,0,new Color(209,197,85),bimage);
            ImgTools.exportImg(121,262,new File("G:\\111\\imgs\\task1.png"),bimage);

            ImgTools.exportString(165,285,"任务名称：火锅优惠劵",new Color(0,0,0),new Font("微软雅黑",Font.BOLD,20),bimage);

            //两个小人物
            ImgTools.exportImg(83,373,new File("G:\\111\\imgs\\left.png"),bimage);
            ImgTools.exportImg(358,373,new File("G:\\111\\imgs\\right.png"),bimage);
            //生成二维码并进行描绘
            ImgTools.Encode_QR_CODE("yrueshg","G:\\111\\imgs\\taskQR.png",175,175);
            ImgTools.transparentImage("G:\\111\\imgs\\taskQR.png","G:\\111\\imgs\\taskQR1.png",5,"png");
             ImgTools.exportImg(161,307,new File("G:\\111\\imgs\\taskQR1.png"),bimage);
            ImgTools.exportString(226,498,"扫一扫",new Color(255,255,255),new Font("微软雅黑",Font.BOLD,20),bimage);


            ImgTools.exportRect(0,669,503,45,0,0,new Color(210,183,105,100),bimage);
            ImgTools.exportRect(0,725,503,45,0,0,new Color(210,183,105,100),bimage);

            Date date =new Date(System.currentTimeMillis());
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            df.format(date).toString();

            ImgTools.exportString(36,698,"开始时间:"+ df.format(date).toString(),new Color(255,255,255),new Font("微软雅黑",Font.BOLD,20),bimage);
            ImgTools.exportString(36,749,"结束时间:"+ df.format(new Date(System.currentTimeMillis()+1000000)).toString(),new Color(255,255,255),new Font("微软雅黑",Font.BOLD,20),bimage);
            ImgTools.outImg("G:\\111\\imgs\\result6.jpg",bimage);

            return "G:\\111\\imgs\\result6.jpg";


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return "";
    }

}
