package entities.jpql.dto;

import entities.jpql.Enrollment;
import entities.jpql.Student;

public record EnrolledStudent(Student student, Enrollment enrollment) {
}
