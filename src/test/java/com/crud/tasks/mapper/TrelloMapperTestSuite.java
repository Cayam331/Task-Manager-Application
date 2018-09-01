package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite{

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToboards() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("5", "test22", true);
        List<TrelloListDto> testTrelloListDto = new ArrayList<>();
        testTrelloListDto.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("test1", "5", testTrelloListDto);
        List<TrelloBoardDto> testTrelloBoardDto = new ArrayList<>();
        testTrelloBoardDto.add(trelloBoardDto);
        //When
        List<TrelloBoard> resultTrelloBoard = trelloMapper.mapToBoards(testTrelloBoardDto);
        //Then
        Assert.assertEquals("5", resultTrelloBoard.get(0).getId());
        Assert.assertEquals("test1", resultTrelloBoard.get(0).getName());
        Assert.assertEquals("5", resultTrelloBoard.get(0).getLists().get(0).getId());
        Assert.assertEquals("test22", resultTrelloBoard.get(0).getLists().get(0).getName());
        Assert.assertEquals(true, resultTrelloBoard.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList trelloList = new TrelloList("5", "test22", true);
        List<TrelloList> testTrelloList = new ArrayList<>();
        testTrelloList.add(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("test1", "5", testTrelloList);
        List<TrelloBoard> testTrelloBoard = new ArrayList<>();
        testTrelloBoard.add(trelloBoard);
        //When
        List<TrelloBoardDto> resultTrelloBoardDto = trelloMapper.mapToBoardsDto(testTrelloBoard);
        //Then
        Assert.assertEquals("5", resultTrelloBoardDto.get(0).getId());
        Assert.assertEquals("test1", resultTrelloBoardDto.get(0).getName());
        Assert.assertEquals("5", resultTrelloBoardDto.get(0).getLists().get(0).getId());
        Assert.assertEquals("test22", resultTrelloBoardDto.get(0).getLists().get(0).getName());
        Assert.assertEquals(true, resultTrelloBoardDto.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapTocard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test1", "test2", "test3", "test4");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assert.assertEquals("test1", trelloCard.getName());
        Assert.assertEquals("test2", trelloCard.getDescription());
        Assert.assertEquals("test3", trelloCard.getPos());
        Assert.assertEquals("test4", trelloCard.getListId());
    }

    @Test
    public void testMapTocardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test1", "test2", "test3", "test4");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        Assert.assertEquals("test1", trelloCardDto.getName());
        Assert.assertEquals("test2", trelloCardDto.getDescription());
        Assert.assertEquals("test3", trelloCardDto.getPos());
        Assert.assertEquals("test4", trelloCardDto.getListId());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("55", "test", false);
        List<TrelloListDto> testTrelloListDto = new ArrayList<>();
        testTrelloListDto.add(trelloListDto);
        //When
        List<TrelloList> resultList= trelloMapper.mapToList(testTrelloListDto);
        //Then
        Assert.assertEquals("55", resultList.get(0).getId());
        Assert.assertEquals("test", resultList.get(0).getName());
        Assert.assertEquals(false, resultList.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList = new TrelloList("55", "test", false);
        List<TrelloList> testTrelloList = new ArrayList<>();
        testTrelloList.add(trelloList);
        //When
        List<TrelloListDto> resultList= trelloMapper.mapToListDto(testTrelloList);
        //Then
        Assert.assertEquals("55", resultList.get(0).getId());
        Assert.assertEquals("test", resultList.get(0).getName());
        Assert.assertEquals(false, resultList.get(0).isClosed());
    }

}


