/**
 * Клас представляє обчислення перших N чисел Люка.
 * */
public class LukeNumbers {
    int number;
    int firstNum;
    int secondNum;

    /**
     * Конструктор створює об'єкт для обчислення перших N чисел Люка.
     *
     * @param number кількість перших чисел Люка.
     */
    public LukeNumbers(int number) {
        this.number = number;
        this.firstNum = 2;
        this.secondNum = 1;
    }

    /**
     * Виводить на екран перші N чисел Люка та їхню суму.
     */
    public void printInfo() {
        int num1 = this.firstNum, num2 = this.secondNum, num3;

        System.out.printf("%d перших чисел Люка:\n", this.number);

        for (int i = 0; i < this.number; i++) {
            System.out.printf("[%d] ", num1);
            num3 = num1 + num2;
            num1 = num2;
            num2 = num3;
        }

        System.out.printf("\nЇхня сума: %d", getSum());
    }

    /**
     * Встановлює нову кількість чисел Люка для обчислення.
     *
     * @param num кількість перших чисел Люка.
     */
    public void setNumber(int num){
        this.number = num;
    }

    /**
     * Обчислює суму перших N чисел Люка.
     *
     * @return сума перших N чисел Люка.
     */
    public int getSum() {
        int num1 = this.firstNum, num2 = this.secondNum, num3;
        int sum = num1;

        for (int i = 0; i < this.number - 1; i++) {
            num3 = num1 + num2;
            sum += num2;
            num1 = num2;
            num2 = num3;
        }
        return sum;
    }

    /**
     * Обчислює суму перших N чисел Люка.
     *
     * @param num кількість чисел Люка для обчислення.
     * @return сума перших N чисел Люка.
     */
    public int getSum(int num) {
        int num1 = this.firstNum, num2 = this.secondNum, num3;
        int sum = num1;

        for (int i = 0; i < num - 1; i++) {
            num3 = num1 + num2;
            sum += num2;
            num1 = num2;
            num2 = num3;
        }
        return sum;
    }
}