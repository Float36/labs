/**
*The class represents the calculation of the first N numbers of Luke.
 */
public class LukeNumbers {
    int number;
    int firstNum;
    int secondNum;

    /**
     * The constructor creates an object to calculate the first N numbers of Luke.
     *
     * @param number the number of Luke's first numbers.
     */
    public LukeNumbers(int number) {
        this.number = number;
        this.firstNum = 2;
        this.secondNum = 1;
    }

    /**
     * Prints the first N numbers of Luke and their sum.
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
     * Sets the new number of Luke numbers to calculate.
     *
     * @param num is the number of Luke's first numbers.
     */
    public void setNumber(int num){
        this.number = num;
    }

    /**
     * Calculates the sum of the first N numbers of Luke.
     *
     * @return the sum of Luke's first N numbers.
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
     * Calculates the sum of the first N numbers of Luke.
     *
     * @param num the number of Luke numbers to be calculated.
     * @return the sum of Luke's first N numbers.
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