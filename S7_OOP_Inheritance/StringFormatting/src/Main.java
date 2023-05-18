public class Main {
    public static void main(String[] args) {
        //old type of formatting text
        String bulletIt = "Print a bulleted List:\n" +
                "\t\u2022 First Point\n" +
                "\t\t\u2022 Sub Point";
        System.out.println(bulletIt);

        //new type of formatting introduced in Java15 """..."""; -> textBlock, printf
        String textBlock = """
                Print a Bulleted list:
                     \u2022 First Point
                          \u2022 Sub Point""";
        System.out.println(textBlock);

        int age = 35;
        System.out.printf("Your age is %d%n", age);

        int yearOfBirth = 2023 - age;
        System.out.printf("Age = %d, Birth year = %d%n", age, yearOfBirth);
        System.out.printf("Your age is %f%n", (float) age);

        for(int i=1; i<=100000; i *=10){
            System.out.printf("Printing %6d %n", i);
        }
        String formattedString = String.format("your age is %d", age);
        System.out.println(formattedString);

        formattedString = "Your age is %d".formatted(age);
        System.out.println(formattedString);
    }
}