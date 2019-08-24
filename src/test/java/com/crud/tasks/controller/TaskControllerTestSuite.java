package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchEmptyTasksList() throws Exception {
        //Given
        List<Task> taskList = new ArrayList<>();
        List<TaskDto> taskDtos = new ArrayList<>();

        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskDtos);
        //When &Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
    @Test
    public void shouldGetAllTasks() throws Exception {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L,"Test","Test content"));
        tasks.add(new Task(2L,"Test2","Test2 content"));
        tasks.add(new Task(3L,"Test3","Test3 content"));

        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(new TaskDto(1L,"Test","Test content"));
        taskDtos.add(new TaskDto(2L,"Test2","Test2 content"));
        taskDtos.add(new TaskDto(3L,"Test3","Test3 content"));

        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].title", is("Test2")))
                .andExpect(jsonPath("$[2].content", is("Test3 content")));
    }
    @Test
    public void shouldGetTask() throws Exception {
        //given
                Task task = new Task(1L, "test_title", "test_content");
                TaskDto taskDto = new TaskDto(1L, "test_title", "test_content");
                //when(service.getTask(1L)).thenReturn(Optional.of(task));
                when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //when & then
        mockMvc.perform(get("/v1/task/getTask").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1L)))
                .andExpect(jsonPath("$.title", is("test_title")))
                .andExpect(jsonPath("$.content", is("test_content")));
    }
    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        Task task = new Task(1L,"Test","Test content");

        service.deleteTask(1L);
        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("taskId", "1"))
                .andExpect(status().isOk());
    }
    @Test
    public void shouldCreateTask() throws Exception{
        //Given
        Task task = new Task(1L,"Test","Test content");
        TaskDto taskDto = new TaskDto(1L,"Test","Test content");

        when(service.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(1L,"Test","Test content");
        TaskDto taskDto = new TaskDto(1L,"Test","Test content");

        when(service.saveTask(taskMapper.mapToTask(taskDto))).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test")))
                .andExpect(jsonPath("$.content", is("Test content")));
    }
}
