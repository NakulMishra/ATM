package atm;
import java.util.Scanner;
public class ATM extends Login{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        ATM obj = new ATM();
        Login lobj = new Login();
        System.out.println("Welcome to my ATM Project...");
        System.out.println("Press 1 for Login\nPress 2 for Registration");
        int inp = sc.nextInt();
        switch(inp){
            case 1:
                obj.login();
                break;
            case 2:
                lobj.registration();
                break;
            default:
                System.out.println("Invalid Choice!!!\nTry again..");
                System.exit(0);
                break;
        }

    }
}
