//У створеному класі визначити приватні поля для
//зберігання указаних даних, конструктори для створення об’єктів
//та відкриті методи setValue(), getValue(), toString() для доступу до
//полів об’єкту.
//2. В основному класі програми визначити методи, що створюють
//масив об'єктів. Задати критерії вибору даних та вивести ці дані на
//консоль. Для кожного критерію створити окремий метод.


//Student: id, Прізвище, Ім'я, По батькові, Дата народження, Адреса,
//Телефон, Факультет, Курс, Група.
//Скласти масив об'єктів. Вивести:
//a) список студентів заданого факультету;
//b) список студентів, які народились після заданого року;
//c) список навчальної групи

package student;

import java.time.LocalDate;

public class Student {
    private int id;
    private int course;
    private String surname;
    private String name;
    private String middleName;
    private LocalDate birthDay;
    private String address;
    private String phoneNumber;
    private String faculty;
    private String group;

    /**
     * Constructs a new Student with the specified details.
     *
     * @param id the student's ID
     * @param course the student's course number
     * @param surname the student's surname
     * @param name the student's first name
     * @param middleName the student's middle name
     * @param birthDay the student's birth date
     * @param address the student's address
     * @param phoneNumber the student's phone number
     * @param faculty the student's faculty
     * @param group the student's group
     */
    public Student(int id, int course, String surname, String name, String middleName, LocalDate birthDay,
                   String address, String phoneNumber, String faculty, String group) {
        this.id = id;
        this.course = course;
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.birthDay = birthDay;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.faculty = faculty;
        this.group = group;
    }

    /**
     * @return a string representation of the student
     */
    public String toString(){
        return "Студент{" +
                "id=" + id +
                "; курс=" + course +
                "; прізвище=" + surname +
                "; ім'я=" + name +
                "; по батькові=" + middleName +
                "; дата народження=" + birthDay +
                "; адреса=" + address +
                "; номер телефону=" + phoneNumber +
                "; факультет=" + faculty +
                "; група=" + group + "}";

    }

    /**
     * @param faculty prints the student's full name if their faculty matches the specified faculty.
     */
    public void studentByFaculty(String faculty){
        if (faculty.equals(this.faculty)){
            System.out.printf("%s %s %s\n", getSurname(), getName(), getMiddleName());
        }
    }

    /**
     * @param year prints the student's full name if they were born after the specified year.
     */
    public void printStudentBornAfter(int year){
        if (this.birthDay.getYear() > year){
            System.out.printf("%s %s %s\n", surname, name, middleName);
        }
    }

    /**
     * @param group prints the student's full name if their group matches the specified group.
     */
    public void printStudentInGroup(String group){
        if (group.equals(this.group)){
            System.out.printf("%s %s %s\n", surname, name, middleName);
        }
    }

    public int getId() {
        return id;
    }

    public int getCourse() {
        return course;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getGroup() {
        return group;
    }


    public void setId(int id) {
        if (id <= 0){
            System.out.println("Ви ввели неправильне ID\n");
            return;
        }
        this.id = id;
    }

    public void setCourse(int course) {
        if (course <= 0){
            System.out.println("Ви ввели невірний курс курс");
            return;
        }
        this.course = course;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
