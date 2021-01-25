package vn.techmaster.exam;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import vn.techmaster.exam.model.Course;
import vn.techmaster.exam.model.CourseStudent;
import vn.techmaster.exam.model.Student;
import vn.techmaster.exam.repository.CourseRepository;
import vn.techmaster.exam.repository.ScoreRepository;
import vn.techmaster.exam.repository.CourseStudentRepositoryImpl;
import vn.techmaster.exam.repository.StudentRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

@DataJpaTest
@Sql({"/student.sql", "/course.sql"})
public class ScoreRepositoryTest {
    @Autowired ScoreRepository scoreRepo;
    @Autowired StudentRepository studentRepository;
    @Autowired CourseRepository courseRepository;

    Student bob, alice, tom;
    Course math, music, history;
    CourseStudent bobMath, bobMusic, bobHistory;
    CourseStudent aliceMath, aliceMusic, aliceHistory;
    CourseStudent tomMath, tomHistory;

    @BeforeEach
    public void setUp() {
        bob = studentRepository.findById(1L).get();
        alice = studentRepository.findById(2L).get();
        tom = studentRepository.findById(3L).get();

        math = courseRepository.findById(1L).get();
        music = courseRepository.findById(2L).get();
        history = courseRepository.findById(3L).get();

        bobMath = new CourseStudent(bob, math);
        bobMusic = new CourseStudent(bob, music);
        bobHistory = new CourseStudent(bob, history);

        aliceMath = new CourseStudent(alice, math);
        aliceMusic = new CourseStudent(alice, music);
        aliceHistory = new CourseStudent(alice, history);

        tomMath = new CourseStudent(tom, math);
        tomHistory = new CourseStudent(tom, history);

        bobMath.setGrade(7);
        bobMusic.setGrade(5);
        bobHistory.setGrade(8);

        aliceMath.setGrade(8);
        aliceMusic.setGrade(2);
        aliceHistory.setGrade(9);

        tomMath.setGrade(4);
        tomHistory.setGrade(10);

        scoreRepo.save(bobMath);
        scoreRepo.save(bobMusic);
        scoreRepo.save(bobHistory);
        scoreRepo.save(aliceMath);
        scoreRepo.save(aliceMusic);
        scoreRepo.save(aliceHistory);
        scoreRepo.save(tomMath);
        scoreRepo.save(tomHistory);

    }

    @AfterEach
    public void clear() {

    }

    @Test
    public void listStudentByCourseTest() {
        Map<String, List<Student>> result = scoreRepo.listStudentByCourse();
        result.entrySet().forEach(System.out::println);
        assertThat(result).containsKeys("math", "music", "history");
        assertThat(result.get("math")).contains(bob, alice, tom); //containsKeys("bob", "alice", "tom")
        
    }

    // @Test
    // public void listStudentByCourseSupportTest() {
    //     Map<Course, List<CourseStudent>> result = scoreRepo.listStudentByCourseSupport();
    //     result.entrySet().forEach(System.out::println);
    //     assertThat(result.keySet()).contains(math); //, music, history);
    // }

    @Test
    public void averageScoreByCourseTest() {
        Double mathAverage = scoreRepo.averageScoreByCourse("math");
        Double musicAverage = scoreRepo.averageScoreByCourse("music");
        assertThat(musicAverage).isEqualTo(3.5);
        assertThat(mathAverage).isEqualTo(6, Offset.offset(0.5));
    }

    // @Test
    // public void averageScoreByCourseSupportTest() {
    //     Map<Course, Double> result = scoreRepo.averageScoreByCourseSupport();
    //     result.entrySet().forEach(System.out::println);
    //     assertThat(result.get(music)).isEqualTo(3.5);
    // }

    @Test
    public void listStudentStudyMathButNotMusicTest() {
        List<Student> result = scoreRepo.listStudentStudyMathButNotMusic();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("tom");
    }
}
