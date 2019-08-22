package com.crud.tasks.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloBadgesTest {
    @Test
    public void testGetVotes() {
        //Given
        Trello trello = new Trello(1,1);
        AttachmentsByType attachments = new AttachmentsByType(trello);
        Badges badges = new Badges(1,attachments);
        //When
        //Then
        assertEquals(1,badges.getVotes());
        assertEquals(1,badges.getAttachmentsByType().getTrello().getBoard());
    }

   }
