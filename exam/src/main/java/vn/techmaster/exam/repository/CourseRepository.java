package vn.techmaster.exam.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.techmaster.exam.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "SELECT cs.course, avg(grade) FROM CourseStudent cs group by cs.course", nativeQuery = true)
    // List<Post> getTop2Posts();
    Map<Course, Double> averageScoreByCourseSupport(String courseName);
}
