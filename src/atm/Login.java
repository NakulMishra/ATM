package atm;
import java.sql.*;
import java.util.Scanner;
public class Login extends Register{
    static Scanner sc = new Scanner(System.in);
    public void login(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm?characterEncoding=utf8","root","root18");
            Statement stmt = con.createStatement();

            System.out.print("Enter your account number: ");long acno = sc.nextInt();
            System.out.print("Enter your pin: ");long pn = sc.nextInt();
            ResultSet rs = stmt.executeQuery("select * from atm.users where account_number =" + acno + " and pin =" + pn + "");
            String name = null;
            long balance = 0;
            int count = 0;
            while(rs.next()){
                name = rs.getString("name");
                balance = rs.getInt("balance");
                count++;
            }

            long added_amount = 0;
            long taken_amount = 0;
            if (count>0){
                System.out.println("Welcome to your account "+ name +"...\nChoose numbers for their respective operations.");

                while(true){
                    System.out.println("Choose 1 :- View Balance");
                    System.out.println("Choose 2 :- Withdraw Amount");
                    System.out.println("Choose 3 :- Deposit Amount");
                    System.out.println("Choose 4 :- Print Receipt");
                    System.out.println("Choose 5 :- Exit");

                    System.out.print("Enter your choice: ");int choice = sc.nextInt();
                    switch (choice){
                        case 1 :
                            System.out.println("Balance: " + balance);
                            break;
                        case 2 :
                            System.out.print("Enter amount to withdraw: ");taken_amount = sc.nextInt();
                            if(taken_amount>balance){
                                System.out.println("Insufficient balance!!!");
                            }
                            else{
                                balance = balance - taken_amount;
                                int bal = stmt.executeUpdate("update atm.users set balance=" + balance + " where account_number="+ acno);
                                System.out.println("Withdraw Successful..\n Current Balance: " + balance);
                            }
                            break;
                        case 3 :
                            System.out.print("Enter amount to deposit: ");added_amount = sc.nextInt();
                            balance = balance + added_amount;
                            int bal = stmt.executeUpdate("update atm.users set balance=" + balance + " where account_number="+ acno);
                            System.out.println("Deposit Successful..\n Current Balance: " + balance);
                            break;
                        case 4 :
                            System.out.println("Receipt Printed Successfully");
                            System.out.println("Your current balance is: " + balance);
                            System.out.println("Amount withdrawn: " + taken_amount);
                            System.out.println("Amount deposited: " + added_amount);
                            break;
                        case 5 :
                            System.out.println("Thank You... Visit again");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid Choice!!");
                            System.exit(0);
                            break;
                    }
                }
            }
            else{
                System.out.println("Wrong Pin.. Try again");
                System.exit(0);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
