import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class ReadProductExcel {

    //通过ID查找商品是否存在
    public Product getProductById(String id,InputStream in) {
        Product products[]= null;
        try {
            XSSFWorkbook xw = new XSSFWorkbook(in);
            XSSFSheet xs = xw.getSheetAt(0);
            products = new Product[xs.getLastRowNum()];
            //循环两次将读取商品表的所有信息
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                Product product=new Product();//每循环一次就把电子表格的一行的数据给对象赋值
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        product.setId(this.getValue(cell));//给username属性赋值
                    } else if (k == 1) {
                        product.setName(this.getValue(cell));//给password属性赋值
                    } else if (k == 2) {
                        product.setPrice(Float.valueOf(this.getValue(cell)));//给address属性赋值
                    } else if (k == 3) {
                        product.setDesc(this.getValue(cell));//给phone属性赋值
                    }
                }
                //如果ID相同则返回该商品
                if (id.equals(product.getId())){
                    return product;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //否则返回空，表示没有找到
        return null;
    }


    public Product[] getAllProduct(InputStream in) {
        Product products[]= null;
        try {
            XSSFWorkbook xw = new XSSFWorkbook(in);
            XSSFSheet xs = xw.getSheetAt(0);
            products = new Product[xs.getLastRowNum()];
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                Product product=new Product();//每循环一次就把电子表格的一行的数据给对象赋值
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        product.setId(this.getValue(cell));//给username属性赋值
                    } else if (k == 1) {
                        product.setName(this.getValue(cell));//给password属性赋值
                    } else if (k == 2) {
                        product.setPrice(Float.valueOf(this.getValue(cell)));//给address属性赋值
                    } else if (k == 3) {
                        product.setDesc(this.getValue(cell));//给phone属性赋值
                    }
                }
                //将所有找到的商品都返回给赋值给prosucts
                products[j-1] = product;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private String getValue(XSSFCell cell) {
        String value;
        CellType type = cell.getCellTypeEnum();

        switch (type) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            case NUMERIC:
                DecimalFormat df = new DecimalFormat("#");
                value = df.format(cell.getNumericCellValue());//format返回String类型的数据
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case ERROR:
                value = "非法字符";
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
}