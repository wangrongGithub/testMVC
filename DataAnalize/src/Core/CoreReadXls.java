package Core;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;


public class CoreReadXls {


    public static Map<String,List<String>> getXlsxExcelData(File file,int colNum,int startRow,Map<String,Double>charater,Map<String,Integer>charater1) throws InvalidFormatException {
        InputStream is;
        Map<String,List<String>> map=new LinkedHashMap<>();
        try {
            is = new FileInputStream(file);
            Workbook xssfWorkbook = WorkbookFactory.create(is);
           // Sheet hssfSheet = workbook.getSheetAt(0);  //示意访问sheet
          //  XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
         //取每一个工作薄

for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
Sheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
if (xssfSheet == null) {
continue;
}
// 获取当前工作薄的每一行
for (int rowNum = startRow; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
    List<String> list=new ArrayList<>();
    map.put(""+rowNum,list);
Row xssfRow = xssfSheet.getRow(rowNum);
if (xssfRow != null) {
//这里需要注意 虽然是第一列数据 但是用这个输出的话得不到表格的数据而是返回 1.0 2.0 这样是数字 
    Cell one = xssfRow.getCell(0);
    for(int  j=0;j<colNum;j++)
    {
        if(!charater.containsKey("MaxCol"+j)||charater.get("MaxCol"+j)<Double.valueOf(xssfRow.getCell(j).toString()))
        {
            charater1.put("MaxCol"+j,((Double)(Double.valueOf(xssfRow.getCell(j).toString())*100)).intValue());
            charater.put("MaxCol"+j,Double.valueOf(xssfRow.getCell(j).toString()));
        }
        if(!charater.containsKey("MinCol"+j)||charater.get("MinCol"+j)>Double.valueOf(xssfRow.getCell(j).toString()))
        {
            charater1.put("MinCol"+j,((Double)(Double.valueOf(xssfRow.getCell(j).toString())*100)).intValue());
            charater.put("MinCol"+j,Double.valueOf(xssfRow.getCell(j).toString()));
        }
        list.add(xssfRow.getCell(j).toString());

    }




}
}
}
        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            return map;
        }

 // 获
}


 public static void getXlsExcelData(File file) {
InputStream is;
        try {
            is = new FileInputStream(file);
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
// 获取每一个工作薄
for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
if (hssfSheet == null) {
continue;
}
// 获取当前工作薄的每一行
for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
HSSFRow hssfRow = hssfSheet.getRow(rowNum);
if (hssfRow != null) {
//第一列数据
HSSFCell one = hssfRow.getCell(0);
System.out.println("第"+rowNum+"行"+"第1列"+hssfRow.getCell(0));
//第二列数据
HSSFCell two = hssfRow.getCell(1);
System.out.println("第"+rowNum+"行"+"第2列"+hssfRow.getCell(1));
//第三列数据
HSSFCell three = hssfRow.getCell(2);
System.out.println("第"+rowNum+"行"+"第3列"+hssfRow.getCell(2));

}
}
}
        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

}
    public static void main1(String[] args) throws IOException, SimpleException {
        String path = "E:/";
        String fileName = "被保险人员清单(新增)04";
        String fileType = "xlsx";
        List<InsuraceExcelBean> list = new ArrayList<>();
        String title[] = {"axAvg","axStand","axMin","axMax","ayAvg","ayStand","ayMin","ayMax","azAvg","azStand","azMin","azMax"};
//        createExcel("E:/被保险人员清单(新增).xlsx","sheet1",fileType,title);

        writer(path, fileName, fileType,list,title);
    }

    @SuppressWarnings("resource")
    public static void writer(String path, String fileName,String fileType,List<InsuraceExcelBean> list,String titleRow[]) throws IOException, SimpleException {
        Workbook wb = null;
        String excelPath = path+File.separator+fileName+"."+fileType;
        File file = new File(excelPath);

        Sheet sheet =null;
        //创建工作文档对象
        if (!file.exists()) {
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook();

            } else if(fileType.equals("xlsx")) {

                wb = new XSSFWorkbook();
            } else {
                throw new SimpleException("文件格式不正确");
            }
            //创建sheet对象
            sheet = (Sheet) wb.createSheet("sheet1");
            OutputStream outputStream = new FileOutputStream(excelPath);
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();

        } else {
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook();

            } else if(fileType.equals("xlsx")) {
                wb = new XSSFWorkbook();

            } else {
                throw new SimpleException("文件格式不正确");
            }
        }
        //创建sheet对象
        if (sheet==null) {
            sheet = (Sheet) wb.createSheet("sheet1");
        }

        //添加表头
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        row.setHeight((short) 540);
        cell.setCellValue("被保险人员清单");    //创建第一行

        CellStyle style = wb.createCellStyle(); // 样式对象
        // 设置单元格的背景颜色为淡蓝色
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);

        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直
        style.setAlignment(CellStyle.ALIGN_CENTER);// 水平
        style.setWrapText(true);// 指定当单元格内容显示不下时自动换行

        cell.setCellStyle(style); // 样式，居中

        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 280);
        style.setFont(font);
        // 单元格合并
        // 四个参数分别是：起始行，起始列，结束行，结束列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));
        sheet.autoSizeColumn(5200);

        row = sheet.createRow(1);    //创建第二行
        for(int i = 0;i < titleRow.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(titleRow[i]);
            cell.setCellStyle(style); // 样式，居中
            sheet.setColumnWidth(i, 20 * 256);
        }
        row.setHeight((short) 540);

        //循环写入行数据
        for (int i = 0; i < list.size(); i++) {
            row = (Row) sheet.createRow(i+2);
            row.setHeight((short) 500);
            row.createCell(0).setCellValue(( list.get(i)).getAxAvg());
            row.createCell(1).setCellValue(( list.get(i)).getAxStand());
            row.createCell(2).setCellValue(( list.get(i)).getAxMin());
            row.createCell(3).setCellValue(( list.get(i)).getAxMax());
            row.createCell(4).setCellValue(( list.get(i)).getAyAvg());
            row.createCell(5).setCellValue(( list.get(i)).getAyStand());
            row.createCell(6).setCellValue(( list.get(i)).getAyMin());
            row.createCell(7).setCellValue(( list.get(i)).getAyMax());
            row.createCell(8).setCellValue(( list.get(i)).getAzAvg());
            row.createCell(9).setCellValue(( list.get(i)).getAzStand());
            row.createCell(10).setCellValue(( list.get(i)).getAzMin());
            row.createCell(11).setCellValue(( list.get(i)).getAzMax());

        }

        //创建文件流
        OutputStream stream = new FileOutputStream(excelPath);
        //写入数据
        wb.write(stream);
        //关闭文件流
        stream.close();
    }

    public static void main(String arfs[]) throws InvalidFormatException, IOException, SimpleException {
        Map<String,Double>charactor=new HashMap<>();  Map<String,Integer>charactor1=new HashMap<>();
        Map<String,List<String>> map=CoreReadXls.getXlsxExcelData(new File("D:\\ORINIGNDATA.xls"),14,2,charactor,charactor1);

        Set<String> set=map.keySet();
        List<InsuraceExcelBean> beans=new ArrayList<>();
        for(String str:set)
        {
            List<String> list=map.get(str);
           System.out.println(list);
            {
                InsuraceExcelBean bean=new InsuraceExcelBean();
                bean.setAxMax(list.get(0));
                bean.setAxMin(list.get(1));
                bean.setAxAvg(list.get(2));
                bean.setAxStand(list.get(3));
                bean.setAyMax(list.get(4));
                bean.setAyMin(list.get(5));
                bean.setAyAvg(list.get(6));
                bean.setAyStand(list.get(7));
                bean.setAzMax(list.get(8));
                bean.setAzMin(list.get(9));
                bean.setAzAvg(list.get(10));
                bean.setAzStand(list.get(11));
                bean.setSpeed(list.get(12));
                bean.setTag(list.get(13));
                beans.add(bean);

            }

        }
        System.out.println(charactor);
        Double[] subs=new Double[charactor.size()/2];
        Double[] mins=new Double[charactor.size()/2];
        Integer[] subsI=new Integer[charactor.size()/2];
        Integer[] minsI=new Integer[charactor.size()/2];
        //区间划分,每个简单的划分五个区间啊
        List<Integer[][]> qujians=new ArrayList<>();//表示每一个标签用户的数据啊
        for(int i=0;i<charactor1.get("MaxCol"+(charactor.size()/2-1))/100;i++)
        {
            qujians.add(new Integer[charactor.size()/2][5]);//每一个被分成5个区间
        }


        for(int  j=0;j<charactor.size()/2;j++)
        {

            minsI[j]=charactor1.get("MinCol"+j);
            subsI[j]=charactor1.get("MaxCol"+j)-charactor1.get("MinCol"+j);
            System.out.println(minsI[j]+"   "+subsI[j]);
        }
        for(String str:set)
        {
            List<String> list=map.get(str);
            {

                InsuraceExcelBean bean=new InsuraceExcelBean();
                bean.setAxMax(list.get(0));
                bean.setAxMin(list.get(1));
                bean.setAxAvg(list.get(2));
                bean.setAxStand(list.get(3));
                bean.setAyMax(list.get(4));
                bean.setAyMin(list.get(5));
                bean.setAyAvg(list.get(6));
                bean.setAyStand(list.get(7));
                bean.setAzMax(list.get(8));
                bean.setAzMin(list.get(9));
                bean.setAzAvg(list.get(10));
                bean.setAzStand(list.get(11));
                bean.setSpeed(list.get(12));
                bean.setTag(list.get(13));
                String tag=list.get(13);
                //转化成Integer；是第几个用户啊
                Integer[][] qujian=qujians.get(Integer.valueOf(tag)-1);//获取到标签的数据
                for(int i=0;i<list.size();i++)
                {
                    Integer qjSize=(subsI[i]+4)/5;
                    //获取下标
                    Integer index=((((Double)(Double.valueOf(list.get(i).toString())*100)).intValue()-minsI[i])-1)/qjSize;
                    if(qujian[i][index]==null)
                        qujian[i][index]=0;

                    qujian[i][index]++;
                    System.out.println(((Double)(Double.valueOf(list.get(i).toString())*100)).intValue()+"  "+qjSize+"  "+qujian[i][index]+"  "+index);


                }
                beans.add(bean);

            }





        }
        Integer[] rows=new Integer[13];
        for(int i=0;i<4;i++)
        {
            //每一个被分成5个区间
            Integer[][] qujian=qujians.get(i);
            for(int j=0;j<13;j++)
            {
                System.out.println(i+":"+j+" "+(qujian[j][0]==null?qujian[j][0]=0:qujian[j][0])+" "+(qujian[j][1]==null?qujian[j][1]=0:qujian[j][1])+" "+(qujian[j][2]==null?qujian[j][2]=0:qujian[j][2])+" "+(qujian[j][3]==null?qujian[j][3]=0:qujian[j][3])+" "+(qujian[j][4]==null?qujian[j][4]=0:qujian[j][4]));
                if(rows[j]==null)
                    rows[j]=0;
                rows[j]=qujian[j][0]+qujian[j][1]+qujian[j][2]+qujian[j][3]+qujian[j][4]+rows[j];
            }
        }
for(int i=0;i<13;i++)
    System.out.println(rows[i]);
        System.out.println("size real"+set.size());
    }
    public static void main3(String arfs[]) throws InvalidFormatException, IOException, SimpleException {
        Map<String,Double>charactor=new LinkedHashMap<>();Map<String,Integer>charactor1=new HashMap<>();
        Map<String,List<String>> map=CoreReadXls.getXlsxExcelData(new File("D:\\otian.xls"),14,2,charactor,charactor1);
        Set<String> set=map.keySet();
        List<String> ax=new ArrayList<>();
        List<String> ay=new ArrayList<>();
        List<String> az=new ArrayList<>();
        List<InsuraceExcelBean> beans=new ArrayList<>();
        for(String str:set)
        {
            List<String> list=map.get(str);
            if(list.get(0).equals("ax"))
            {//计算
                Double avgAx=CoreComputing.getAvg(ax,0,ax.size());
                Double SAx=CoreComputing.getStandardDeviation(ax,0,ax.size(),avgAx);
                Double minAx=CoreComputing.getMin(ax,0,ax.size());
                Double maxAx=CoreComputing.getMax(ax,0,ax.size());
                Double avgAy=CoreComputing.getAvg(ay,0,ay.size());
                Double SAy= CoreComputing.getStandardDeviation(ay,0,ay.size(),avgAy);
                Double minAy=CoreComputing.getMin(ay,0,ay.size());
                Double maxAy=CoreComputing.getMax(ay,0,ay.size());
                Double avgAz=CoreComputing.getAvg(az,0,az.size());
                Double SAz= CoreComputing.getStandardDeviation(az,0,az.size(),avgAz);
                Double minAz=CoreComputing.getMin(az,0,az.size());
                Double maxAz=CoreComputing.getMax(az,0,az.size());
                InsuraceExcelBean bean=new InsuraceExcelBean();
                bean.setAxAvg(avgAx.toString());
                bean.setAxStand(SAx.toString());
                bean.setAxMax(maxAx.toString());
                bean.setAxMin(minAx.toString());
                bean.setAyAvg(avgAy.toString());
                bean.setAyStand(SAy.toString());
                bean.setAyMax(maxAy.toString());
                bean.setAyMin(minAy.toString());
                bean.setAzAvg(avgAz.toString());
                bean.setAzStand(SAz.toString());
                bean.setAzMax(maxAz.toString());
                bean.setAzMin(minAz.toString());
                beans.add(bean);

            }
            else {
                ax.add(list.get(0));
                ay.add(list.get(1));
                az.add(list.get(2));
            }
            System.out.println();
        }
        String path = "E:/";
        String fileName = "被保险人员清单(新增)04";
        String fileType = "xlsx";

        String title[] = {"axAvg","axStand","axMin","axMax","ayAvg","ayStand","ayMin","ayMax","azAvg","azStand","azMin","azMax"};
//        createExcel("E:/被保险人员清单(新增).xlsx","sheet1",fileType,title);

        writer(path, fileName, fileType,beans,title);

    }
}



