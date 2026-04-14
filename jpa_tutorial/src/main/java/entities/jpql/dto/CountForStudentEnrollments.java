package entities.jpql.dto;

import entities.jpql.Student;

public record CountForStudentEnrollments(Student student, Long count) {
}
