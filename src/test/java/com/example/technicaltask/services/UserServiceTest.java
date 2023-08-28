package com.example.technicaltask.services;

import com.example.technicaltask.entities.Course;
import com.example.technicaltask.entities.User;
import com.example.technicaltask.entities.UserType;
import com.example.technicaltask.entities.dto.UserDto;
import com.example.technicaltask.exceptions.InvalidInputException;
import com.example.technicaltask.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserServiceImpl mockService;
    @Mock
    UserRepository mockRepository;

    @Mock
    Environment environment;
    @Mock
    Pageable pageable;
    private UserDto userDto;

    @BeforeEach
    public void prepare(){
        userDto = new UserDto();
        userDto.setAge(18);
        userDto.setName("TestUser");
        userDto.setGroup(2);
        userDto.setCourse("Secondary");
    }

    @Test
    public void createUser(){
        User user = mockService.createUser(userDto, UserType.STUDENT);

        Mockito.verify(mockRepository,
                Mockito.times(1)).save(user);
    }
    @Test
    public void updateUser(){
        User user = new User(1,"name",18,1, Course.MAIN,UserType.TEACHER);
        when(mockRepository.findById(1L)).thenReturn(Optional.of(user));

        User userUpdate = mockService.updateUser(1,userDto,UserType.STUDENT);

        Mockito.verify(mockRepository, Mockito.times(1)).save(userUpdate);

       assertEquals(userUpdate.getName(),"TestUser");
       assertEquals(userUpdate.getGroup(),2);
       assertEquals(userUpdate.getCourse(),Course.SECONDARY);
    }
    @Test
    public void deleteUser(){
        User user = new User(1,"name",18,1, Course.MAIN,UserType.TEACHER);
        when(mockRepository.findByIdAndUserType(1L,UserType.TEACHER)).thenReturn(Optional.of(user));

        mockService.deleteUser(1,UserType.TEACHER);
        Mockito.verify(mockRepository,
                Mockito.times(1)).delete(user);

        assertThrows(InvalidInputException.class,
                () -> mockService.deleteUser(2,UserType.TEACHER));
    }

    @Test
    public void createUser_ShouldThrowException_whenDtoIsInvalid(){
        when(environment.getProperty("invalid.course")).thenReturn("Invalid course");
        when(environment.getProperty("invalid.age")).thenReturn("Invalid age");
        when(environment.getProperty("invalid.name")).thenReturn("Invalid name");
        when(environment.getProperty("invalid.id")).thenReturn("Invalid id");

        try {
            userDto.setCourse("test");
            mockService.createUser(userDto, UserType.STUDENT);
        }catch (InvalidInputException iie){
            assertEquals(environment.getProperty("invalid.course"),iie.getMessage());
        }
        try {
            userDto.setAge(-1);
            mockService.createUser(userDto, UserType.STUDENT);
        }catch (InvalidInputException iie){
            assertEquals(environment.getProperty("invalid.age"),iie.getMessage());
        }
        try {
            userDto.setName("    ");
            mockService.createUser(userDto, UserType.STUDENT);
        }catch (InvalidInputException iie){
            assertEquals(environment.getProperty("invalid.name"),iie.getMessage());
        }
        try {
            mockService.updateUser(-1,userDto, UserType.STUDENT);
        }catch (InvalidInputException iie){
            assertEquals(environment.getProperty("invalid.id"),iie.getMessage());
        }
    }

    @Test
    public void countStudents(){
        User user = new User(1,"name",18,1, Course.MAIN,UserType.STUDENT);
        when(mockRepository.findByUserType(UserType.STUDENT)).thenReturn(List.of(user));

        assertEquals(1,mockService.countStudents());
    }

    @Test
    public void countTeachers(){
        User user = new User(1,"name",18,1, Course.MAIN,UserType.TEACHER);
        when(mockRepository.findByUserType(UserType.TEACHER)).thenReturn(List.of(user));

        assertEquals(1,mockService.countTeachers());
    }

    @Test
    public void findStudentsForCourse(){

        User user = new User(1,"name",18,1, Course.MAIN,UserType.STUDENT);
        Page<User> users = new PageImpl<>(List.of(user));
        when(mockRepository.findByCourseAndUserType(Course.MAIN,UserType.STUDENT,pageable)).thenReturn(users);

        assertEquals(users,mockService.findStudentsForCourse("main",pageable));
    }

    @Test
    public void findStudentsForGroup(){

        User user = new User(1,"name",18,1, Course.MAIN,UserType.STUDENT);
        Page<User> users = new PageImpl<>(List.of(user));
        when(mockRepository.findByGroupAndUserType(1,UserType.STUDENT,pageable)).thenReturn(users);

        assertEquals(users,mockService.findStudentsForGroup(1L,pageable));
    }
    @Test
    public void findStudentsForCourseOlderThan(){

        User user = new User(1,"name",18,1, Course.MAIN,UserType.STUDENT);
        Page<User> users = new PageImpl<>(List.of(user));
        when(mockRepository.findByAgeAndCourseAndUserType(15,Course.MAIN,UserType.STUDENT,pageable)).thenReturn(users);

        assertEquals(users,mockService.findStudentsForCourseOlderThan(15,"main",pageable));
    }
    @Test
    public void findAllForGroupAndCourse(){

        User student = new User(1,"name",18,1, Course.MAIN,UserType.STUDENT);
        User teacher = new User(1,"name",18,1, Course.MAIN,UserType.TEACHER);
        Page<User> users = new PageImpl<>(List.of(student,teacher));
        when(mockRepository.findByGroupAndCourse(1,Course.MAIN,pageable)).thenReturn(users);

        assertEquals(users,mockService.findAllForGroupAndCourse(1,"main",pageable));
    }
}
