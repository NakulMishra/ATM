package atm;
import java.sql.*;
import java.util.Scanner;
public class Register {
    static Scanner sc = new Scanner(System.in);
    public void registration(){
        try{
            System.out.println("Welcome to registration page...");
            System.out.print("Enter your name: ");String name = sc.nextLine();
            System.out.print("Enter your 9 digit account number: ");int account_number = sc.nextInt();
            System.out.print("Enter your pin: ");int pin = sc.nextInt();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm?characterEncoding=utf8","root","root18");
            String sql = "insert into atm.users"+"(account_number, pin, name)"+" values (?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, account_number);
            stmt.setInt(2, pin);
            stmt.setString(3, name);

            int res = stmt.executeUpdate();

            if (res > 0){
                System.out.println("User Registration Successful...");
            }
            else{
                System.out.println("User Registration Unsuccessful...");
            }
            System.exit(0);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
