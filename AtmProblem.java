import java.util.Scanner;


class Account implements Runnable
{
    private static double balance = 1000;
    public void getBalance()
    {
        System.out.println(Thread.currentThread().getName() + " : You have total " + balance + " .");
    }
    public  void diductBalance(double amt)
    {
        this.balance = this.balance - amt;
    }
    public void dipositValue(double amt)
    {
        this.balance += amt;
    }
    public void withdraw(double amt)
    {
        if(balance >= amt)
        {
            this.diductBalance(amt);
            System.out.println(Thread.currentThread().getName() + " : Successfully withdraw money.");
            this.getBalance();
        }
        else{
            System.out.println(Thread.currentThread().getName() + " : unable to withdraw amount ");
            this.getBalance();
        }
    }
    public void run()
    {
        this.getBalance();
    }
}
class Transaction implements Runnable 
{
    Scanner sc = new Scanner(System.in);
    Account ac = new Account();
    private boolean flag = true;
    public void BWithdraw() throws InterruptedException
    {
        Thread t1 = new Thread(this , "Father");
        Thread t2 = new Thread(this , "Son");
        t1.start();
        t1.join();
        t2.start();
        t2.join();
    }
    public void run()
    {
        this.withdrawAmt();

    }
    public synchronized void withdrawAmt()
    {
            if(Thread.currentThread().getName() == "Father")
            {
                if(flag == true)
                {
                    System.out.println("Son on waiting stage now.");
                }
                
                System.out.println("Father :- How much money you want to withdraw.");
                flag = false;
            }
            else{
                if(flag)
                {
                    System.out.println("Father on waiting stage now.");
                }
                System.out.println("Son :- How much money you want to withdraw.");
            }
            double amt = sc.nextDouble();
            ac.withdraw(amt);
    }
}

public class AtmProblem 
{
    public static void main(String[] args) throws Exception{
        
       
            Scanner sc = new Scanner(System.in);
            Account ac = new Account();
            Transaction t = new Transaction();
            double amt;
            while(true)
            {
            System.out.println("press 1: Father and Son both are withdraw amount Asynchronously.");
            System.out.println("press 2: Father and Son both are read amount Asynchronously.");
            System.out.println("press 3: Father withdraw amount independently.");
            System.out.println("press 4: son withdraw amount independently.");
            System.out.println("press 5: Father deposite amount independently.");
            System.out.println("press 6: Son deposite amount independently.");
            System.out.println("press 7: Father read amount independently.");
            System.out.println("press 8: Son read amount independently.");

            System.out.println("Press 9: exit");
            System.out.println("please enter your choice :- ");

            int choice =sc.nextInt();

            switch(choice)
                {
                    case 1: t.BWithdraw();
                        break;
                    case 2:
                            Thread t3 = new Thread(new Account());
                            Thread t4 = new Thread(new Account());
                            t3.setName("Father");
                            t4.setName("Son");
                            t3.start();
                            t4.start();
                            break;
                    case 3:
                    case 4:
                        System.out.println("How much you want to withdraw: ");
                        amt = sc.nextDouble();
                        ac.withdraw(amt);
                        break;
                    case 5:
                    case 6:
                        System.out.println("How much you deposite :- ");
                        amt = sc.nextDouble();
                        ac.dipositValue(amt);
                        break;
                    case 7:
                    case 8:
                        ac.getBalance();
                        break;
                    case 9:
                            System.exit(0);
                    default:
                        System.out.println("In valid choice please make currect it.");
               }
            }
            
        }
        
    
}