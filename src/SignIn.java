import java.io.InputStream;
import java.util.Scanner;

public class SignIn {
    public static void main(String[] args) throws ClassNotFoundException {

        /*部分常用快捷键如下：
        1.快速打印内容：
         在要输出的内容后打上 .sout可以快速打出输出语句
         用psvm + 回车 可以快速打出mian语句


         2.快捷键：
         alt + inter 导包
         Ctrl + alt + L 使得代码自动变换为规范格式
         Ctrl + O  查看我们继承的类或者接口中的方法，以及我们要实现的方法
         Ctrl + 鼠标左键 进类中查看代码
         Alt + Insert    set/get; 构造方法;  toString; 重写方法
         Ctrl+Alt+T 将代码包在一个块中，例如try/catch  ;synchronized等
          */
        boolean bool = true;
        while (bool) {
            System.out.println("请输入你的名字：");
            Scanner sc = new Scanner(System.in);
            String UserName = sc.next();
            /*System.out.println("你输入的用户名为：" + UserName);*/

            System.out.println("请输入你的密码：");
            String UserPassword = sc.next();//阻塞函数next
            /*System.out.println("你输入的密码为：" + UserPassword);*/

            // File file = new File("C:\\Users\\lenovo\\IdeaProjects\\TestShop\\src\\UserData.xlsx");

            InputStream inuser = Class.forName("SignIn").getResourceAsStream("/UserData.xlsx");
            InputStream inproduct= Class.forName("SignIn").getResourceAsStream("/ProductData.xlsx");

            ReadUserExcel readExcel = new ReadUserExcel();
            User user[] = readExcel.readExcel(inuser);

            ReadProductExcel readProductExcel=new ReadProductExcel();
            Product product[]=readProductExcel.getAllProduct(inproduct);

            for (int i = 0; i < user.length; i++) {
                if (UserName.equals(user[i].getUsername()) && UserPassword.equals(user[i].getPassword())) {
                    //System.out.println("登录成功!"）;
                    for (int j=0;j<product.length;j++){
                        System.out.println("商品"+ (j+1) +"信息如下：");
                        System.out.print("商品ID" + product[j].getId()+"\t");
                        System.out.print("\t"+"名字："+product[j].getName());
                        System.out.print("\t"+"价格："+product[j].getPrice());
                        System.out.println("\t" + "描述："+product[j].getDesc());
                    }

                    int cout=0;
                    System.out.print("请输入你要寻找的商品ID：");
                    String id=sc.next();
                    inproduct=null;
                    inproduct= Class.forName("SignIn").getResourceAsStream("/ProductData.xlsx");

                    Product productes=readProductExcel.getProductById(id,inproduct);

                    Product products[] = new Product[3];
                    if (productes!=null) {
                        products[cout++]=productes;
                        System.out.println("该商品已加入购物车中");
                    }
                    else {
                        System.out.println("不存在该商品，请重新选择！");
                    }
                    bool = false;
                    break;
                } else {
                    if (i == (user.length - 1)) {
                        System.out.println("登陆失败！密码或者用户名错误，请重新输入！");
                    } else {
                        continue;
                    }
                }
            }
        }
    }
}
