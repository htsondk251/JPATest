package vn.techmaster.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.exam.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}

