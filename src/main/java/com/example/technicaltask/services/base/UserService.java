package com.example.technicaltask.services.base;

import com.example.technicaltask.entities.User;
import com.example.technicaltask.entities.UserType;
import com.example.technicaltask.entities.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User createUser(UserDto userDto, UserType userType);

    User updateUser(long userId, UserDto userDto, UserType userType);

    void deleteUser(long userId, UserType userType);

    long countStudents();

    long countTeachers();

    long countCourses();

    Page<User> findStudentsForCourse(String course, Pageable pageable);

    Page<User> findStudentsForGroup(long group, Pageable pageable);

    Page<User> findStudentsForCourseOlderThan(int age, String course, Pageable pageable);

    Page<User> findAllForGroupAndCourse(long group, String course, Pageable pageable);


}
