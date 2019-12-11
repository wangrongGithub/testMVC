package Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class CoreReadXls {


    public static void getXlsxExcelData(File file)
    {
        InputStream is;
        try {
            is = new FileInputStream(file);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
//取每一个工作薄
           for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
           XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
           if (xssfSheet == null) {
               continue;
           }
           // 获取当前工作薄的每一行
           for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
               XSSFRow xssfRow = xssfSheet.getRow(rowNum);
               if (xssfRow != null) {
             //这里需要注意 虽然是第一列数据 但是用这个输出的话得不到表格的数据而是返回 1.0 2.0 这样是数字 
                   XSSFCell one = xssfRow.getCell(0);
                   System.out.println("第"+rowNum+"行"+"第1列"+xssfRow.getCell(0));//这样才能输出表格内的内容
                   //第二列数据
                   XSSFCell two = xssfRow.getCell(1);
                   System.out.println("第"+rowNum+"行"+"第2列"+xssfRow.getCell(1));
                   //第三列数据
                   XSSFCell three = xssfRow.getCell(2);
                   System.out.println("第"+rowNum+"行"+"第3列"+xssfRow.getCell(2));
                  


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
    public static void main(String arfs[])
    {
        ExcelFile.getXlsxExcelData(new File("D:\\test.xlsx"));

                       System.out.println("----------------------------------");
        ExcelFile.getXlsExcelData(new File("D:\\test.xls"));
    }

}



