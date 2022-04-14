import annotation.ShikiAnnotation;

public class Test {

    public static void main(String[] args) {
        System.out.println("Hello Shiki!");
        Test t = new Test();

        ShikiAnnotation annotation = Test.class.getAnnotation(ShikiAnnotation.class);
        System.out.println("author: " + annotation.author());
        System.out.println("date: " + annotation.date());
        System.out.println("description: " + annotation.description());

    }
}
