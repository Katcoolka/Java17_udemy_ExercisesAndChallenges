public class Customer {
    //fields
    private String name;
    private double creditLimit;
    private String emailAddress;

    //CONSTRUCTORS:
    //First constructor -> create a constructor for all three fields which should assign the arguments directly to the instance fields.
    public Customer(String name, double creditLimit, String emailAddress) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.emailAddress = emailAddress;
        System.out.println("The first constructor called.");
    }

    //Second constructor ->create a no args constructor that calls another constructor, passing some literal values for each argument.
    public Customer(){
        this("Default name", "Default email");
        System.out.println("The second constructor called.");
    }

    //Third constructor -> create a constructor with just the name and email parameters, which also calls another constructor.
    public Customer(String name, String emailAddress) {
            this(name, 1000, emailAddress);
        System.out.println("The third constructor called.");
    }

    //getters
    public String getName() {
        return name;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
