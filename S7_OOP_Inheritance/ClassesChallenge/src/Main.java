public class Main {
    public static void main(String[] args) {
        //no constructor defined-> only getter and setters are in place
        //java calls default empty constructor to create the object
//        BankAccount account = new BankAccount();
//        account.setAccountBalance(1000.0);
//        account.setAccountNumber("123456789");
//        account.setCustomerName("John Smith");
//        account.setEmail("john.smith@hotmail.com");
//        account.setPhoneNumber("456321");
//        System.out.println("account balance: " + account.getAccountBalance() +
//                " ,account number: " + account.getAccountNumber() +
//        " ,customer name: " + account.getCustomerName());

        //2nd constructor
//        BankAccount account = new BankAccount("123456789", 500, "Bob","bob@email.com", "0800-963852");

        //chained constructor
        BankAccount account = new BankAccount();
        System.out.println(account.getAccountNumber());
        System.out.println(account.getAccountBalance());

        account.depositFunds(500);
        account.withdrawalFunds(1500);
        account.withdrawalFunds(500);
        account.withdrawalFunds(54.36);

        //test code for 3rd constructor
        BankAccount timAccount = new BankAccount("Tim", "tim@email.com", "456789");

        System.out.println("account no.: " + timAccount.getAccountNumber() + " ; name " + timAccount.getCustomerName());
    }
}