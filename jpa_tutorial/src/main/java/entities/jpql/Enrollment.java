package entities.jpql;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY)//eager type would fetch courses everytime causing an n+1 query problem,
    @JoinColumn(name = "course_id")   //solutions are making it lazy, making this a one directional relationship or writing a fetch query
    private Course course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", enrollmentDate=" + enrollmentDate +
                '}';
    }
}

/*JPQL:
String jpql = """
                select s, e from Student s
                inner join s.enrollments e  --> that 'inner' is not necessary
                """;
this is the same thing -> select s, e from Student s, Enrollment e where s.id = e.student.id

TypedQuery<Object[]> result = em.createQuery(jpql, Object[].class);

        result.getResultList().forEach(objects -> {
        System.out.println("student: " + objects[0] + "  " + "enrollment: " + objects[1]);
        });
 select s, e from Student s left join s.enrollments e -> grabs all students including the ones who are not enrolled, also the enrollment data

 you could also use a dto to fetch the data in a more organized way, to avoid using Object[]:
 String jpql = """
                SELECT NEW entities.jpql.dto.EnrolledStudent(s, e) FROM Student s
                LEFT JOIN s.enrollments e
                """;
TypedQuery<EnrolledStudent> result = em.createQuery(jpql, EnrolledStudent.class);
result.getResultList().forEach(es -> IO.println(es.student() + " : " + es.enrollment()));

SELECT NEW entities.jpql.dto.CountForStudentEnrollments(s, (SELECT COUNT(e) FROM Enrollment e WHERE e.student.id = s.id)) FROM Student s

inner queries:
SELECT s FROM Student s WHERE (SELECT COUNT(e) FROM Enrollment e WHERE e.student.id = s.id) > 2

usually aggregates like COUNT, AVG, SUM we use a GROUP BY with it:
SELECT NEW entities.jpql.dto.CountForStudentEnrollments(s.name, count(s)) FROM Student s GROUP BY s.name
HAVING is a conditional that comes after GROUP BY
SELECT NEW entities.jpql.dto.CountForStudentEnrollments(s.name, count(s)) FROM Student s GROUP BY s.name HAVING s.name LIKE 'A%' ORDER BY s.name DESC
*/