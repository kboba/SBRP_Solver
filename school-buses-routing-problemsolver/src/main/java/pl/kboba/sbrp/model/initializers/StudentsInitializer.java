package pl.kboba.sbrp.model.initializers;

import pl.kboba.sbrp.model.Student;

import java.util.ArrayList;
import java.util.List;

public final class StudentsInitializer {
    private StudentsInitializer (){

    }

    public static List<Student> basicStudentsInitialize() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(9, 12));
        students.add(new Student(9, 22));
        students.add(new Student(19, 12));
        students.add(new Student(19, 22));
        students.add(new Student(29, 16));
        students.add(new Student(29, 7));
        students.add(new Student(39, 22));
        students.add(new Student(39, 12));
        students.add(new Student(39, 32));
        students.add(new Student(29, 29));
        students.add(new Student(14, 25));
        students.add(new Student(16, 19));
        return students;
    }
}