package cn.wr.SpringBoot;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
public class TestXlsx
{
    public static void read(String fileName)
    {
        String[][] t1Array = new String[1500][12];
        try
        {
            InputStream input=new FileInputStream(fileName);  //建立输入流
            HSSFWorkbook wb=new HSSFWorkbook(input);//初始化
            HSSFSheet sheet=wb.getSheetAt(0);//获得第一个表单
            int rowLength=sheet.getLastRowNum()+1;
            HSSFRow hssfRow=sheet.getRow(0);//得到Excel工作表的行
            int colLength=hssfRow.getLastCellNum();//总列数
            HSSFCell hssfCell=hssfRow.getCell(0);//得到Excel指定单元格中的内容
            CellStyle cellStyle=hssfCell.getCellStyle();//得到单元格样式
            for(int i=1;i<rowLength;i++)  //去掉表头
            {
                HSSFRow row=sheet.getRow(i);//获取Excel工作表的每行
                for(int j=1;j<=colLength;j++)
                {
                    HSSFCell cell=row.getCell(j-1);//获取指定单元格
                    if(cell!=null)//将所有的需要读的Cell表格设置为String格式
                    {
                        cell.setCellType(CellType.STRING);
                    }
                    if(j>=0)
                    {
                        t1Array[i-1][j-1]=cell.getStringCellValue();
                    }
                }
                for(int k=0;k<colLength;k++)
                {
                    System.out.print(t1Array[i-1][k]+" ");//打印数组中的值
                }
                System.out.println();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException
    {
        read("C:\\Users\\Administrator\\Desktop\\11.xls");
    }
}
