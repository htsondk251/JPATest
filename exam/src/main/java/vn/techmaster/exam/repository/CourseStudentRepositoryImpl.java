package vn.techmaster.exam.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import vn.techmaster.exam.model.Course;
import vn.techmaster.exam.model.CourseStudent;
import vn.techmaster.exam.model.Student;

public class CourseStudentRepositoryImpl implements CourseStudentRepository {
    @Autowired ScoreRepository courseStudentRepository;

    @Override
    public Map<String, List<Student>> listStudentByCourse() {
        Map<Course, List<CourseStudent>> cCS = courseStudentRepository.findAll()
            .stream().collect(Collectors.groupingBy(CourseStudent::getCourse));
        
        Map<String, List<Student>> result = new HashMap<>();
        
        for (Map.Entry<Course, List<CourseStudent>> e : cCS.entrySet()) {
            List<Student> students = e.getValue().stream().map(cs -> cs.getStudent()).collect(Collectors.toList());
            result.put(e.getKey().getName(), students);
        }
        return result;

        
        // Map<String, List<Student>> result = courseStudentRepository.findAll().stream()
        //     .collect(Collectors.collectingAndThen(Collectors.groupingBy(CourseStudent::getCourse), Collectors.toMap(Course::getName, CourseStudent::getStudent)));
        
    }

    // @Override
    // public Map<Course, List<CourseStudent>> listStudentByCourseSupport() {
    //     return courseStudentRepository.findAll()
    //         .stream().collect(Collectors.groupingBy(CourseStudent::getCourse));
    // }

    @Override
    public Double averageScoreByCourse(String courseName) {
        Map<Course, Double> courseDouble = courseStudentRepository.findAll().stream()
            .collect(Collectors.groupingBy(CourseStudent::getCourse, Collectors.averagingInt(CourseStudent::getGrade)));
        
        return courseDouble.entrySet().stream().filter(cd -> courseName.equals(cd.getKey().getName())).findFirst().get().getValue();
    }


    // public Map<Course, Double> averageScoreByCourseSupport() {
    //     return courseStudentRepository.findAll().stream()
    //         .collect(Collectors.groupingBy(CourseStudent::getCourse, Collectors.averagingInt(CourseStudent::getGrade)));
    // }

    @Override
    public List<Student> listStudentStudyMathButNotMusic() {
        // TODO Auto-generated method stub
        return null;
    }


    
}
