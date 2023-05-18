public class Animal {
    protected String type; //let any class that is a subclass, access this field. This is conditional encapsulation.
    private String size;
    private double weight;
    public Animal(){
    }
    public Animal(String type, String size, double weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }
    @Override
    public String toString() {
        return "Animal{" +
                "type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", weight=" + weight +
                '}';
    }
    //methods
    public void move (String speed){
        System.out.println(type + " moves " + speed);
    }
    public void makeNoise(){
        System.out.println(type + " makes some kind of noise");
    }
}
