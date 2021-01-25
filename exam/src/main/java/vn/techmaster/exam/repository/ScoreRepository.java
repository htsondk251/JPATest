package vn.techmaster.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.techmaster.exam.model.CourseAverageScore;
import vn.techmaster.exam.model.CourseStudent;

public interface ScoreRepository extends JpaRepository<CourseStudent, Long>, CourseStudentRepository {
    


    
}
