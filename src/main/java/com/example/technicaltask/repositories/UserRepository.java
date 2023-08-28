package com.example.technicaltask.repositories;

import com.example.technicaltask.entities.Course;
import com.example.technicaltask.entities.User;
import com.example.technicaltask.entities.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUserType(UserType userType);

    Optional<User> findByIdAndUserType(long id, UserType userType);

    Page<User> findByCourseAndUserType(Course course, UserType userType, Pageable pageable);

    Page<User> findByGroupAndUserType(long group, UserType userType, Pageable pageable);

    Page<User> findByGroupAndCourse(long group, Course course, Pageable pageable);

    @Query("FROM User u where u.age >= :age and u.course = :course and u.userType = :userType")
    Page<User> findByAgeAndCourseAndUserType(@Param("age") int age, @Param("course") Course course, @Param("userType") UserType userType, Pageable pageable);
}
