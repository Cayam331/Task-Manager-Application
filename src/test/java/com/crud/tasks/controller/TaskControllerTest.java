package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
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

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchTasks() throws Exception {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "test", "test");
        Task task1= new Task(2L, "test", "test");
        taskList.add(task);
        taskList.add(task1);

        List<TaskDto> taskDtoList = new ArrayList<>();
        TaskDto taskDto = new TaskDto(1L, "test", "test22");
        TaskDto taskDto1 = new TaskDto(2L, "test332", "testing");
        taskDtoList.add(taskDto);
        taskDtoList.add(taskDto1);

        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);
        when(dbService.getAllTasks()).thenReturn(taskList);

        //When&Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].content", is("test22")))
                .andExpect(jsonPath("$[1].title", is("test332")));
    }
    @Test
    public void shouldFetchTask() throws Exception {
        //Given
        Task task = new Task( 1l, "test11", "test22");
        TaskDto taskDto = new TaskDto(1l,"test", "test33");

        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(dbService.getTask(task.getId())).thenReturn(Optional.ofNullable(task));

        //When&Then
        mockMvc.perform(get("/v1/task/getTask").param("taskId","1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("test")));
    }

    @Test
    public void shouldUpdateTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1l,"test", "test");
        Task task = new Task( 1l, "test", "test");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When&Then
        mockMvc.perform(put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        Task task = new Task( 1l, "test", "test");
        when(dbService.getTask(task.getId())).thenReturn(Optional.ofNullable(task));

        //When&Then
        mockMvc.perform(delete("/v1/task/deleteTask").param("taskId","1").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldSaveTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1l,"test", "test");
        Task task = new Task(1l, "test", "test");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(any(Task.class))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When&Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}