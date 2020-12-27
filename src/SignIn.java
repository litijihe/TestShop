import sun.security.util.Password;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class SignIn {
    public static void main(String[] args) throws ClassNotFoundException {

        /*
        alt + inter 导包
         Ctrl + alt + L 使得代码自动变换为规范格式
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

            InputStream in=Class.forName("SignIn").getResourceAsStream("/UserData.xlsx");
            ReadExcel readExcel = new ReadExcel();
            User user[] = readExcel.readExcel(in);
            for (int i = 0; i < user.length; i++) {
                if (UserName.equals(user[i].getUsername()) && UserPassword.equals(user[i].getPassword())) {
                    System.out.println("登录成功！");
                    bool = false;
                    break;
                } else {
                    if (i == (user.length - 1)) {
                        System.out.println("登陆失败！");
                    } else {
                        continue;
                    }
                }
            }
        }
    }
}
