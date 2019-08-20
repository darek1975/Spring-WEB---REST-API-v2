package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> testList = new ArrayList<>();
        testList.add(new TrelloListDto("1", "TestList", false));
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(new TrelloBoardDto("1", "TestBoard", testList));
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        //Then
        assertEquals("TestBoard", trelloBoards.get(0).getName());
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("TestList", trelloBoards.get(0).getLists().get(0).getName());
        assertEquals(1, trelloBoards.size());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> testList = new ArrayList<>();
        testList.add(new TrelloList("1", "TestList", false));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "TestBoard", testList));
        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals("TestBoard", trelloBoardsDto.get(0).getName());
        assertEquals("1", trelloBoardsDto.get(0).getId());
        assertEquals("TestList", trelloBoardsDto.get(0).getLists().get(0).getName());
        assertEquals(1, trelloBoardsDto.size());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> testListDto = new ArrayList<>();
        testListDto.add(new TrelloListDto("1", "Test 1", false));
        testListDto.add(new TrelloListDto("2","Test 2", false));
        //When
        List<TrelloList> testLists = trelloMapper.mapToLists(testListDto);
        //Then
        assertEquals(2, testLists.size());
        assertEquals("1",testLists.get(0).getId());
        assertEquals("Test 2", testLists.get(1).getName());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> testList = new ArrayList<>();
        testList.add(new TrelloList("1", "Test 1", false));
        testList.add(new TrelloList("2","Test 2", false));
        //When
        List<TrelloListDto> testListsDto = trelloMapper.mapToListsDto(testList);
        //Then
        assertEquals(2, testListsDto.size());
        assertEquals("1",testListsDto.get(0).getId());
        assertEquals("Test 2", testListsDto.get(1).getName());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("TestCard","Testing","test","1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("TestCard", trelloCard.getName());
        assertEquals("Testing", trelloCard.getDescription());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("TestCard","Testing","test","1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("1", trelloCardDto.getListId());
        assertEquals("test", trelloCardDto.getPos());
    }
}
