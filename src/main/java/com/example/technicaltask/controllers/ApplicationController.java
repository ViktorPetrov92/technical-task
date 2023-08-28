package com.example.technicaltask.controllers;

import com.example.technicaltask.entities.User;
import com.example.technicaltask.entities.UserType;
import com.example.technicaltask.entities.dto.UserDto;
import com.example.technicaltask.exceptions.InvalidInputException;
import com.example.technicaltask.services.base.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApplicationController {

    private final UserService userService;

    @Operation(summary = "Count students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found x students count")})
    @GetMapping("/students")
    public ResponseEntity<String> countStudents() {
        return new ResponseEntity<>("Student count is: " + userService.countStudents(), HttpStatus.OK);
    }

    @Operation(summary = "Create a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student created"),
            @ApiResponse(responseCode = "400", description = "Invalid dto supplied")})
    @PostMapping("/students")
    public ResponseEntity<String> createStudent(@RequestBody UserDto userDto) {
        try {
            userService.createUser(userDto, UserType.STUDENT);
        } catch (InvalidInputException iie) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iie.getMessage());
        }
        return new ResponseEntity<>("Student created", HttpStatus.OK);
    }

    @Operation(summary = "Update a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied")})
    @PutMapping("/students/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable("id") long userId, @Validated @RequestBody UserDto userDto) {
        try {
            userService.updateUser(userId, userDto, UserType.STUDENT);
        } catch (InvalidInputException iie) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iie.getMessage());
        }
        return new ResponseEntity<>("Student updated", HttpStatus.OK);
    }

    @Operation(summary = "Delete a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student removed"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied")})
    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") long userId) {
        try {
            userService.deleteUser(userId, UserType.STUDENT);
        } catch (InvalidInputException iie) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iie.getMessage());
        }
        return new ResponseEntity<>("Student removed", HttpStatus.OK);
    }

    @Operation(summary = "Get all existing students for a course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of students for this course"),
            @ApiResponse(responseCode = "400", description = "Invalid course supplied")})
    @GetMapping("/students/{course}")
    public Page<User> getStudentsByCourse(@PathVariable("course") String course, @PageableDefault Pageable pageable) {
        try {
            return userService.findStudentsForCourse(course, pageable);
        } catch (InvalidInputException iie) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iie.getMessage());
        }
    }

    @Operation(summary = "Get all existing students for a group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of students for this group"),
            @ApiResponse(responseCode = "400", description = "Invalid group supplied")})
    @GetMapping("/students/group/{group}")
    public Page<User> getStudentsByGroup(@PathVariable("group") long group, @PageableDefault Pageable pageable) {
        try {
            return userService.findStudentsForGroup(group, pageable);
        } catch (InvalidInputException iie) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iie.getMessage());
        }
    }

    @Operation(summary = "Get all existing students for a group and older than specified age")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of students for this group and older than specified age"),
            @ApiResponse(responseCode = "400", description = "Invalid course or age supplied")})
    @GetMapping("/students/{course}/{age}")
    public Page<User> getStudentsForCourseAndOlderThan(@PathVariable("course") String course, @PathVariable("age") int age, @PageableDefault Pageable pageable) {
        try {
            return userService.findStudentsForCourseOlderThan(age, course, pageable);
        } catch (InvalidInputException iie) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iie.getMessage());
        }
    }

    @Operation(summary = "Get all existing students and teachers based for a group and a course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of students and teachers for this group and course"),
            @ApiResponse(responseCode = "400", description = "Invalid group or course supplied")})
    @GetMapping("/group/{group}/course/{course}")
    public Page<User> getAllForGroupAndCourse(@PathVariable("group") long group, @PathVariable("course") String course, @PageableDefault Pageable pageable) {
        try {
            return userService.findAllForGroupAndCourse(group, course, pageable);
        } catch (InvalidInputException iie) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iie.getMessage());
        }
    }

    @Operation(summary = "Count teachers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found x teachers count")})
    @GetMapping("/teachers")
    public ResponseEntity<String> countTeachers() {
        return new ResponseEntity<>("Teacher count is: " + userService.countStudents(), HttpStatus.OK);
    }

    @Operation(summary = "Create a teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher created"),
            @ApiResponse(responseCode = "400", description = "Invalid dto supplied")})
    @PostMapping("/teachers")
    public ResponseEntity<String> createTeacher(@RequestBody UserDto userDto) {
        try {
            userService.createUser(userDto, UserType.TEACHER);
        } catch (InvalidInputException iie) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iie.getMessage());
        }
        return new ResponseEntity<>("Teacher created", HttpStatus.OK);
    }


    @Operation(summary = "Update a teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher updated"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied")})
    @PutMapping("/teachers/{id}")
    public ResponseEntity<String> updateTeacher(@PathVariable("id") long userId, @Validated @RequestBody UserDto userDto) {
        try {
            userService.updateUser(userId, userDto, UserType.TEACHER);
        } catch (InvalidInputException iie) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iie.getMessage());
        }
        return new ResponseEntity<>("Teacher updated", HttpStatus.OK);
    }

    @Operation(summary = "Remove a teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher removed"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied")})
    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable("id") long userId) {
        try {
            userService.deleteUser(userId, UserType.TEACHER);
        } catch (InvalidInputException iie) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iie.getMessage());
        }
        return new ResponseEntity<>("Teacher removed", HttpStatus.OK);
    }

}
