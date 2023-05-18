public class Dog extends Animal {
    //fields specific to Dog only
    private String earShape;
    private String tailShape;

    //Constructors
    public Dog() {
        super("Mutt", "big", 50.00);
    }

    //constructor chaining within class -> this ();
    public Dog(String type, double weight) {
        this(type, weight, "Perky", "Curled");
    }

    //using parent constructor -> super ();
    public Dog(String type, double weight, String earShape, String tailShape) {
        super(type, weight < 15 ? "small" : (weight < 35 ? " medium" : "large"), weight);
        this.earShape = earShape;
        this.tailShape = tailShape;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "earShape='" + earShape + '\'' +
                ", tailShape='" + tailShape + '\'' +
                "} " + super.toString();
    }

    public void makeNoise() {
        if (type == "Wolf") {
            System.out.println("Ow wooo! ");
        }
        bark();
        System.out.println();
    }

    @Override
    public void move(String speed) {
        super.move(speed);
        //System.out.println("Dogs walk, run and wag their tail.");
        if (speed == "slow") {
            walk();
            wagTail();
        } else {
            run();
            bark();
        }
        System.out.println();
    }

    //these private methods will be used only in this class
    private void bark() {
        System.out.println("Woof! ");
    }

    private void run() {
        System.out.println("Dog running ");
    }

    private void walk() {
        System.out.println("Dog walking ");
    }

    private void wagTail() {
        System.out.println("Tail wagging ");
    }
}
