import java.io.InputStream;
import java.util.Scanner;

public class SignIn {
    public static void main(String[] args) throws ClassNotFoundException {

        /*部分常用快捷键如下：
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
            for (int i = 0; i < user.length; i++) {
                if (UserName.equals(user[i].getUsername()) && UserPassword.equals(user[i].getPassword())) {
                    //System.out.println("登录成功！");


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
