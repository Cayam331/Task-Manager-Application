package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {
    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto();
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        Assert.assertEquals(null, task.getId());
        Assert.assertEquals(null, task.getContent());
        Assert.assertEquals(null, task.getTitle());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "test1", "test2");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertEquals(new Long(1L), taskDto.getId());
        Assert.assertEquals("test2", taskDto.getContent());
        Assert.assertEquals("test1", taskDto.getTitle());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task = new Task(1L, "test1", "test2");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        //When
        List<TaskDto> resultList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        Assert.assertEquals(new Long(1L), resultList.get(0).getId());
        Assert.assertEquals("test2", resultList.get(0).getContent());
        Assert.assertEquals("test1", resultList.get(0).getTitle());
    }
}
