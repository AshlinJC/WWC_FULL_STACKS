import java.util.Scanner;

public class Bank {

    //BankAccount Class
    static class BankAccount {
        private String accountNumber;
        private String accountHolderName;
        private double balance;

        public BankAccount(String accountNumber, String accountHolderName, double balance) {
            this.accountNumber = accountNumber;
            this.accountHolderName = accountHolderName;
            this.balance = balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println(amount + " deposited.\nNew balance: " + balance);
            } else {
                System.out.println("Invalid deposit amount");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println(amount + " withdrawn.\nNew balance: " + balance);
            } else {
                System.out.println("Invalid withdrawal or insufficient balance");
            }
        }

        public void printDetails() {
            System.out.println("\n--- Account Details ---");
            System.out.println("Account Number : " + accountNumber);
            System.out.println("Account Holder : " + accountHolderName);
            System.out.println("Balance        : " + balance);
        }

        public double getBalance() {
            return balance;
        }

        protected void setBalance(double balance) {
            this.balance = balance;
        }
    }

    //SavingsAccount Class
    static class SavingsAccount extends BankAccount {
        private double interestRate;

        public SavingsAccount(String accountNumber, String accountHolderName, double balance, double interestRate) {
            super(accountNumber, accountHolderName, balance);
            this.interestRate = interestRate;
        }

        @Override
        public void withdraw(double amount) {
            if (amount <= getBalance()) {
                super.withdraw(amount);
            } else {
                System.out.println("Withdrawal denied! Cannot withdraw more than available balance.");
            }
        }

        public void applyInterest() {
            double interest = getBalance() * (interestRate / 100);
            setBalance(getBalance() + interest);
            System.out.println("Interest Applied: " + interest + ". New Balance: " + getBalance());
        }
    }

    // Main
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter account number:");
        String accNo = sc.nextLine();

        System.out.println("Enter account holder name:");
        String name = sc.nextLine();

        System.out.println("Enter initial balance:");
        double bal = sc.nextDouble();

        BankAccount acc = new BankAccount(accNo, name, bal);

        System.out.println("\n1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Print Details");
        System.out.println("4. Create Savings Account");
        System.out.println("Enter choice:");
        int ch = sc.nextInt();

        if (ch == 1) {
            System.out.println("Enter amount to deposit:");
            acc.deposit(sc.nextDouble());
        }
        else if (ch == 2) {
            System.out.println("Enter amount to withdraw:");
            acc.withdraw(sc.nextDouble());
        }
        else if (ch == 3) {
            acc.printDetails();
        }
        else if (ch == 4) {

            sc.nextLine();

            System.out.println("Enter Savings Account number:");
            String sAcc = sc.nextLine();

            System.out.println("Enter holder name:");
            String sName = sc.nextLine();

            System.out.println("Enter opening balance:");
            double sBal = sc.nextDouble();

            System.out.println("Enter interest rate:");
            double rate = sc.nextDouble();

            SavingsAccount sav = new SavingsAccount(sAcc, sName, sBal, rate);

            System.out.println("\n1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Apply Interest");
            System.out.println("4. Print Details");
            System.out.println("Enter choice:");
            int op = sc.nextInt();

            if (op == 1) {
                System.out.println("Amount:");
                sav.deposit(sc.nextDouble());
            }
            else if (op == 2) {
                System.out.println("Amount:");
                sav.withdraw(sc.nextDouble());
            }
            else if (op == 3) {
                sav.applyInterest();
            }
            else if (op == 4) {
                sav.printDetails();
            }
        }

        sc.close();
    }
}
