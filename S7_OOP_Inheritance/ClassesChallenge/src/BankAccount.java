public class BankAccount {
    //fields
    private String accountNumber;
    private double accountBalance;
    private String customerName;
    private String email;
    private String phoneNumber;

    //constructor
    //same name as class, has no return type
    //this is an example of automatically, if not specified
//    public BankAccount() {
//        System.out.println("Empty constructor called");
//    }

    //constructor chaining
    //->one constructor explicitly call another overloaded constructor
    public BankAccount() {
        this("589636", 2.50, "Default name", "Default address", "Default phone");
        System.out.println("chained constructor called");
    }
    //second constructor with parameters
    public BankAccount(String accountNumber, double accountBalance, String customerName, String email, String phoneNumber){
        System.out.println("Account constructor with parameters called.");
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.customerName = customerName;
        email = email;
        phoneNumber = phoneNumber;
    }
    //third constructor automated
    public BankAccount(String customerName, String email, String phoneNumber) {
        this("999999", 100.55, customerName, email, phoneNumber);
//        this.customerName = customerName;
//        this.email = email;
//        this.phoneNumber = phoneNumber;
    }

    //methods
    public void depositFunds(double depositAmount) {
        accountBalance += depositAmount;
        System.out.println("Deposit of $" + depositAmount + " made.New balance is $ " + accountBalance);
    }

    public void withdrawalFunds(double withdrawalAmount) {
        if (accountBalance < 0) {
            System.out.println("Insufficient funds! You only have $" + accountBalance + " in your account.");
        } else {
            accountBalance -= withdrawalAmount;
            System.out.println("Withdrawal of $" + withdrawalAmount + " made.New balance is $ " + accountBalance);
        }
    }

    //getters and setters

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
