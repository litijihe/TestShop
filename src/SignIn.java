import java.util.Scanner;

public class SignIn {
    public static void main(String[] args) {
        System.out.println("请输入你的名字：");
        Scanner sc=new Scanner(System.in);
        String UserName=sc.next();
        System.out.println("你输入的用户名为：" + UserName);

        System.out.println("请输入你的密码：");
        String UserPassword=sc.next();
        System.out.println("你输入的密码为：" + UserPassword);
    }
}
