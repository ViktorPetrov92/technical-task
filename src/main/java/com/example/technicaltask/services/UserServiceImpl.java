package com.example.technicaltask.services;

import com.example.technicaltask.entities.Course;
import com.example.technicaltask.entities.User;
import com.example.technicaltask.entities.UserType;
import com.example.technicaltask.entities.dto.UserDto;
import com.example.technicaltask.exceptions.InvalidInputException;
import com.example.technicaltask.repositories.UserRepository;
import com.example.technicaltask.services.base.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Environment environment;

    @Override
    public User createUser(UserDto userDto, UserType userType) {
        validateUserDto(userDto);
        return populateUser(userDto, userType);
    }

    @Override
    public User updateUser(long userId, UserDto userDto, UserType userType) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return populateUser(userDto, userType);
        } else {
            throw new InvalidInputException(environment.getProperty("invalid.id"));
        }
    }

    @Override
    public void deleteUser(long userId, UserType userType) {
        Optional<User> optionalUser = userRepository.findByIdAndUserType(userId, userType);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
        } else {
            throw new InvalidInputException(environment.getProperty("invalid.id"));
        }
    }

    @Override
    public long countStudents() {
        return userRepository.findByUserType(UserType.STUDENT).size();
    }

    @Override
    public long countTeachers() {
        return userRepository.findByUserType(UserType.TEACHER).size();
    }

    @Override
    public long countCourses() {
        return Course.values().length;
    }

    @Override
    public Page<User> findStudentsForCourse(String course, Pageable pageable) {
        validateCourse(course);
        return userRepository.findByCourseAndUserType(Course.valueOf(course.toUpperCase()), UserType.STUDENT, pageable);
    }


    @Override
    public Page<User> findStudentsForGroup(long group, Pageable pageable) {
        return userRepository.findByGroupAndUserType(group, UserType.STUDENT, pageable);
    }

    @Override
    public Page<User> findStudentsForCourseOlderThan(int age, String course, Pageable pageable) {
        validateCourse(course);

        return userRepository.findByAgeAndCourseAndUserType(age, Course.valueOf(course.toUpperCase()), UserType.STUDENT, pageable);
    }

    @Override
    public Page<User> findAllForGroupAndCourse(long group, String course, Pageable pageable) {
        validateCourse(course);

        return userRepository.findByGroupAndCourse(group, Course.valueOf(course.toUpperCase()), pageable);
    }

    private void validateUserDto(UserDto userDto) {
        if (userDto.getName().isBlank()) {
            throw new InvalidInputException(environment.getProperty("invalid.name"));
        }
        if (userDto.getAge() <= 0 || userDto.getAge() > 100) {
            throw new InvalidInputException(environment.getProperty("invalid.age"));
        }
        validateCourse(userDto.getCourse());
    }

    private void validateCourse(String course) {
        if (course.isBlank()) {
            throw new InvalidInputException(environment.getProperty("invalid.course"));
        }

        try {
            Course.valueOf(course.toUpperCase());
        } catch (IllegalArgumentException iae) {
            throw new InvalidInputException(environment.getProperty("invalid.course"));
        }
    }


    private User populateUser(UserDto userDto, UserType userType) {
        User user = new User();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setGroup(userDto.getGroup());
        user.setCourse(Course.valueOf(userDto.getCourse().toUpperCase()));
        user.setUserType(userType);
        userRepository.save(user);

        return user;
    }
}
