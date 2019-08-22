package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTestSuite {
    @InjectMocks
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloValidator trelloValidatorMock;


    @Test
    public void validateTrelloBoards() {

        //Given

        TrelloList trelloList1 = new TrelloList("1", "tost1", false);
        TrelloList trelloList2 = new TrelloList("2", "tost2", false);
        List<TrelloList> trelloListList = new ArrayList<>();
        trelloListList.add(trelloList1);
        trelloListList.add(trelloList2);

        TrelloBoard trelloBoard1 = new TrelloBoard("3", "test", trelloListList);
        TrelloBoard trelloBoard2 = new TrelloBoard("4", "tost4", trelloListList);
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard1);
        trelloBoardList.add(trelloBoard2);

        TrelloCard trelloCard = new TrelloCard("test", "nowa", "pos", "1");

        //When
        List<TrelloBoard> test = trelloValidator.validateTrelloBoards(trelloBoardList);
        trelloValidatorMock.validateCard(trelloCard);

        //Then
        assertEquals(2, trelloBoardList.size());
        assertEquals(1, test.size());

        verify(trelloValidatorMock,times(1)).validateCard(trelloCard);

    }
}
