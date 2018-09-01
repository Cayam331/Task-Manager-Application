package com.crud.tasks.dbService;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void testGetAllTasks(){
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1l, "test1", "test2"));
        when(dbService.getAllTasks()).thenReturn(taskList);
        //When
        List<Task> resultList = dbService.getAllTasks();
        //Then
        assertEquals(new Long(1l), resultList.get(0).getId());
        assertEquals("test2", resultList.get(0).getContent());
        assertEquals("test1", resultList.get(0).getTitle());
    }
    @Test
    public void testGettingOneTask(){
        //Given
        when(dbService.getTask(5l)).thenReturn(Optional.of(new Task(1l, "test1", "test2")));
        //When
        Optional<Task> result = dbService.getTask(5l);
        //Then
        Assert.assertEquals(new Long(1l), result.get().getId());
    }
    @Test
    public void testSavingTask(){
        //Given
        Task task = new Task(1l, "test1", "test2");
        when(dbService.saveTask(task)).thenReturn(task);
        //When
        Task result = dbService.saveTask(task);
        //Then
        assertEquals(result.getTitle(), "test1");
    }
}
