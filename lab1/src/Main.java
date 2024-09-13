
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LukeNumbers num1 = new LukeNumbers(7);

        System.out.print("\nВведіть кількість перших чисел Люка для обчислення їх суми: ");
        int n = scanner.nextInt();
        num1.setNumber(n);
        num1.printInfo();


//        num1.printInfo();
//        System.out.println(num1.getSum());
    }
}