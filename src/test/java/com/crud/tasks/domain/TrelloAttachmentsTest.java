package com.crud.tasks.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloAttachmentsTest {
    @Test
    public void testGetTrello() {
        //Given
        Trello trello = new Trello(1,1);
        AttachmentsByType attachments = new AttachmentsByType(trello);
        //When
        //Then
        assertEquals(1,attachments.getTrello().getBoard());
        assertEquals(1,attachments.getTrello().getCard());
    }
}
