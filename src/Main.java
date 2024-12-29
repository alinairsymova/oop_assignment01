import java.util.*;

class Person {
    protected String name;
    protected String surname;
    protected int age;
    protected boolean gender;

    public Person(String name, String surname, int age, boolean gender) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        String genderString = gender ? "Male" : "Female";
        return "Hi, I am " + name + " " + surname + ", a " + age + "-year-old " + genderString + ".";
    }
}

class Student extends Person {
    private static int idCounter = 1;
    private final int studentID;
    private final List<Integer> grades;

    public Student(String name, String surname, int age, boolean gender) {
        super(name, surname, age, gender);
        this.studentID = idCounter++;
        this.grades = new ArrayList<>();
    }

    public void addGrade(int grade) {
        if (grade >= 0 && grade <= 100) {
            grades.add(grade);
        } else {
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        }
    }

    public double calculateGPA() {
        if (grades.isEmpty()) return 0.0;
        int total = 0;
        for (int grade : grades) {
            total += grade;
        }
        return total / (double) grades.size();
    }

    @Override
    public String toString() {
        return super.toString() + " I am a student with ID " + studentID + ".";
    }
}

class Teacher extends Person {
    private final String subject;
    private final int yearsOfExperience;
    private int salary;

    public Teacher(String name, String surname, int age, boolean gender, String subject, int yearsOfExperience, int salary) {
        super(name, surname, age, gender);
        this.subject = subject;
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
    }

    public void giveRaise(double percentage) {
        if (percentage < 0) {
            throw new IllegalArgumentException("Raise percentage must be positive.");
        }
        salary += (int) (salary * (percentage / 100));
    }

    @Override
    public String toString() {
        return super.toString() + " I teach " + subject + ".";
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public int getSalary() {
        return salary;
    }
}

class School {
    private final List<Person> members;

    public School() {
        members = new ArrayList<>();
    }

    public void addMember(Person person) {
        members.add(person);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Person member : members) {
            result.append(member).append("\n");
        }
        return result.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        School school = new School();

        Student harry = new Student("Harry", "Potter", 21, true);
        harry.addGrade(78);
        harry.addGrade(89);
        harry.addGrade(99);
        harry.addGrade(51);

        Student ron = new Student("Ron", "Weasley", 20, true);
        ron.addGrade(78);
        ron.addGrade(89);
        ron.addGrade(75);
        ron.addGrade(63);
        ron.addGrade(82);

        Student hermione = new Student("Hermione", "Granger", 19, false);
        hermione.addGrade(100);
        hermione.addGrade(99);
        hermione.addGrade(95);
        hermione.addGrade(98);
        hermione.addGrade(100);
        hermione.addGrade(96);
        hermione.addGrade(97);

        Teacher snape = new Teacher("Severus", "Snape", 51, true, "Math", 7, 800000);
        Teacher dumbledore = new Teacher("Albus", "Dumbledore", 71, true, "Philosophy", 25, 1500000);
        Teacher mcgonagall = new Teacher("Minerva", "McGonagall", 62, false, "Sociology", 17, 1100000);

        school.addMember(harry);
        school.addMember(ron);
        school.addMember(hermione);
        school.addMember(snape);
        school.addMember(dumbledore);
        school.addMember(mcgonagall);

        if (dumbledore.getYearsOfExperience() > 10) dumbledore.giveRaise(10);
        if (mcgonagall.getYearsOfExperience() > 10) mcgonagall.giveRaise(10);

        System.out.println(school);
        System.out.println("Harry's GPA: " + harry.calculateGPA());
        System.out.println("Ron's GPA: " + ron.calculateGPA());
        System.out.println("Hermione's GPA: " + hermione.calculateGPA());
    }
}
