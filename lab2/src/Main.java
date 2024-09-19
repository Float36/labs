import student.Student;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Student> students = new ArrayList<>();
        students.add(new Student(1, 2, "Стащишин", "Юрій", "Іванович",
                LocalDate.of(2003, 6, 15), "Львів, вул. Січових Стрільців, 1",
                "0987654321", "Комп'ютерні науки", "ОІ-24"));

        students.add(new Student(2, 3, "Іваненко", "Іван", "Олександрович",
                LocalDate.of(2001, 12, 10), "Київ, вул. Шевченка, 10",
                "0671234567", "Економіка", "Е-32"));

        students.add(new Student(3, 1, "Петренко", "Петро", "Петрович",
                LocalDate.of(2002, 3, 20), "Львів, вул. Личаківська, 7",
                "0936543210", "Комп'ютерні науки", "ОІ-24"));

        students.add(new Student(4, 4, "Коваленко", "Марія", "Михайлівна",
                LocalDate.of(2000, 9, 5), "Одеса, вул. Дерибасівська, 15",
                "0509876543", "Математика", "М-21"));

        students.add(new Student(5, 2, "Захарченко", "Олег", "Вікторович",
                LocalDate.of(2003, 7, 19), "Харків, вул. Сумська, 22",
                "0734567890", "Комп'ютерні науки", "ОІ-24"));

        System.out.println("\nВсі студенти:");
        for (Student stud : students){
            System.out.println(stud.toString());
        }

        System.out.print("\nВведіть факультет з якого ви хотіли б дізнатися всіх студентів: ");
        String faculty = scanner.nextLine();
        System.out.printf("\nСтуденти з факультету %s:\n", faculty);
        for (Student stud : students){
            stud.studentByFaculty(faculty);
        }

        int year;
        while (true) {
            System.out.print("\nВведіть рік, щоб побачити всіх студентів, які народжені після цього року: ");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
                break;
            } else {
                System.out.println("Помилка: Введіть ціле число.");
                scanner.next();
            }
        }
        System.out.printf("\nСтуденти що народжені після %d року:\n", year);
        for (Student stud : students){
            stud.printStudentBornAfter(year);
        }
        scanner.nextLine();

        System.out.print("\nВведіть групу з якої ви хотіли б дізнатися всіх студентів: ");
        String group = scanner.nextLine();
        System.out.printf("\nСтуденти групи %s:\n", group);
        for (Student stud : students){
            stud.printStudentInGroup(group);
        }

        scanner.close();
    }
}