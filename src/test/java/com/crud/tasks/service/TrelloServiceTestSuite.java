package com.crud.tasks.service;


import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {

    @InjectMocks
    TrelloService trelloService;

    @Mock
    AdminConfig adminConfig;

    @Mock
    TrelloClient trelloClient;

    @Mock
    SimpleEmailService simpleEmailService;

    @Test
    public void testFetchTrelloBoards(){
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("test","test1", trelloListDtoList));
        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtoList);
        //When
        List<TrelloBoardDto> resultList = trelloService.fetchTrelloBoards();
        //Then
        Assert.assertNotNull(resultList);
    }

    @Test
    public void testCreateTrelloCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto();
        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);
        //Then
        Assert.assertNotNull(result);
    }
}
