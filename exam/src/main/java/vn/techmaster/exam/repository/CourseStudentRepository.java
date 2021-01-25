package vn.techmaster.exam.repository;

import java.util.List;
import java.util.Map;

import vn.techmaster.exam.model.Course;
import vn.techmaster.exam.model.CourseStudent;
import vn.techmaster.exam.model.Student;

public interface CourseStudentRepository {
    Map<String, List<Student>> listStudentByCourse();

    // Map<Course, List<CourseStudent>> listStudentByCourseSupport();
    
    //TODO: native query
    
    Double averageScoreByCourse(String courseName);

    // Map<Course, Double> averageScoreByCourseSupport();

    List<Student> listStudentStudyMathButNotMusic();

    
}
