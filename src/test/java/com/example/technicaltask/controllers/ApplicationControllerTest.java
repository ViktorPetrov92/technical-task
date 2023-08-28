package com.example.technicaltask.controllers;

import com.example.technicaltask.entities.dto.UserDto;
import com.example.technicaltask.services.base.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ApplicationController.class)
public class ApplicationControllerTest {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;

    @Test
    public void getStudentsShouldReturnOk() throws Exception {
        String uri = "/api/students";
        mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Student count is:")));
    }

    @Test
    public void getTeachersShouldReturnOk() throws Exception {
        String uri = "/api/teachers";
        mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Teacher count is:")));
    }

    @Test
    public void createStudentShouldReturnOk() throws Exception {
        String uri = "/api/students";
        UserDto userDto = new UserDto();
        userDto.setAge(18);
        userDto.setName("TestUser");
        userDto.setGroup(2);
        userDto.setCourse("Secondary");

        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(userDto);

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(requestJson)
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Student created")));
    }

    @Test
    public void createTeacherShouldReturnOk() throws Exception {
        String uri = "/api/teachers";
        UserDto userDto = new UserDto();
        userDto.setAge(18);
        userDto.setName("TestUser");
        userDto.setGroup(2);
        userDto.setCourse("Secondary");

        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(userDto);

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(requestJson)
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(content().string(containsString("Teacher created")));
    }
}
