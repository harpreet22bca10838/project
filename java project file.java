interface GradeCalculator {
    double calculateGrade();
}

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

class Student extends Person implements GradeCalculator {
    String studentId;
    Course course;
    static int studentCount = 0;

    Student(String name, int age, String studentId, Course course) {
        super(name, age);
        this.studentId = studentId;
        this.course = course;
        studentCount++;
    }

    Student(String name, int age, String studentId) {
        this(name, age, studentId, null);
    }

    static void displayStudentCount() {
        System.out.println("Total Students: " + studentCount);
    }

    @Override
    public double calculateGrade() {
        if (course != null) {
            return course.getMarks() / 10.0;
        }
        return 0.0;
    }

    void displayStudentInfo() {
        displayInfo();
        System.out.println("Student ID: " + studentId);
        if (course != null) {
            course.displayCourseInfo();
            System.out.println("Grade: " + calculateGrade());
        }
    }
}

class GraduateStudent extends Student {
    String thesisTopic;

    GraduateStudent(String name, int age, String studentId, Course course, String thesisTopic) {
        super(name, age, studentId, course);
        this.thesisTopic = thesisTopic;
    }

    @Override
    void displayStudentInfo() {
        super.displayStudentInfo();
        System.out.println("Thesis Topic: " + thesisTopic);
    }
}

class Course {
    String courseName;
    int marks;

    Course(String courseName, int marks) {
        this.courseName = courseName;
        this.marks = marks;
    }

    int getMarks() {
        return marks;
    }

    void displayCourseInfo() {
        System.out.println("Course: " + courseName);
        System.out.println("Marks: " + marks);
    }
}

public class StudentInformationSystem {
    public static void main(String[] args) {
        Course course1 = new Course("Mathematics", 85);
        Student student1 = new Student("Alice", 20, "S001", course1);
        GraduateStudent gradStudent = new GraduateStudent("Bob", 24, "S002", new Course("Physics", 92), "Quantum Computing");

        student1.displayStudentInfo();
        System.out.println();
        gradStudent.displayStudentInfo();
        System.out.println();

        Student.displayStudentCount();
    }
}
