package vn.techmaster.exam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity("course_student")
@Table("course_student")
// @Data
@Getter
@Setter
@NoArgsConstructor
public class CourseStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    int grade;

    public void setGrade(int grade) {
        if (grade > 10 || grade < 0) {
            throw new IllegalArgumentException("score must between 0 and 10");
        }
        this.grade = grade;
    }

    public CourseStudent(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    
}
