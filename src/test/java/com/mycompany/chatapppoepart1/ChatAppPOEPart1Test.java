package com.mycompany.chatapppoepart1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ChatAppPOEPart1Test {
    
    
private String flag;

public void setFlag(String flag) {
    this.flag = flag;
}

    private List<Message> messages;

    public ChatAppPOEPart1Test() {
        // Setup sample data for tests
        messages = new ArrayList<>();
        Message msg1 = new Message();
        msg1.setMessageID("1234567890");
        msg1.setRecipientCellNumber("+2783456789");
        msg1.setMessageInput("Did you get the cake?");
        msg1.setMessageHash("12:0:Did cake");
        msg1.setFlag("Sent");
        messages.add(msg1);

        Message msg2 = new Message();
        msg2.setMessageID("0987654321");
        msg2.setRecipientCellNumber("+27834456789");
        msg2.setMessageInput("Where are you? You are late! I have asked you to be on time.");
        msg2.setMessageHash("09:0:WhereYouare?");
        msg2.setFlag("Stored");
        messages.add(msg2);

        Message msg3 = new Message();
        msg3.setMessageID("1122334455");
        msg3.setRecipientCellNumber("+278344567");
        msg3.setMessageInput("Yoohooo, I am at your gate.");
        msg3.setMessageHash("11:0:Yoohoo,");
        msg3.setFlag("Disregard");
        messages.add(msg3);
    }

    @Test
    public void testDisplayLongestMessage() {
        Message longestMsg = null;
        int maxLength = 0;
        for (Message msg : messages) {
            if (msg.getMessageInput().length() > maxLength) {
                maxLength = msg.getMessageInput().length();
                longestMsg = msg;
            }
        }
        assertNotNull(longestMsg);
        assertEquals("Where are you? You are late! I have asked you to be on time.", longestMsg.getMessageInput());
    }

    @Test
    public void testSearchByMessageID_Found() {
        String targetID = "0987654321";
        Message foundMsg = null;
        for (Message msg : messages) {
            if (msg.getMessageID().equals(targetID)) {
                foundMsg = msg;
            }
        }
        assertNotNull(foundMsg);
        assertEquals("+27834456789", foundMsg.getRecipientCellNumber());
    }

    @Test
    public void testSearchByMessageID_NotFound() {
        String targetID = "0000000000";
        Message foundMsg = null;
        for (Message msg : messages) {
            if (msg.getMessageID().equals(targetID)) {
                foundMsg = msg;
            }
        }
        assertNull(foundMsg);
    }

    @Test
    public void testSearchMessagesToRecipient() {
        String recipient = "+27834456789";
        List<Message> results = new ArrayList<>();
        for (Message msg : messages) {
            if (msg.getRecipientCellNumber().equals(recipient)) {
                results.add(msg);
            }
        }
        assertFalse(results.isEmpty());
        assertEquals("+27834456789", results.get(0).getRecipientCellNumber());
    }

    @Test
    public void testDeleteMessageByHash() {
        String hashToDelete = "09:0:WhereYouare?";
        Message toRemove = null;
        for (Message msg : messages) {
            if (msg.getMessageHash().equals(hashToDelete)) {
                toRemove = msg;
            }
        }
        assertNotNull(toRemove);
        
        int sizeBefore = messages.size();
        boolean removed = false;
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getMessageHash().equals(hashToDelete)) {
                messages.remove(i);
                removed = true;
                break;
            }
        }
        assertTrue(removed);
        assertEquals(sizeBefore - 1, messages.size());

        for (Message msg : messages) {
            assertNotEquals(hashToDelete, msg.getMessageHash());
        }
        // Confirm not found after removal
        boolean stillExists = messages.stream()
            .anyMatch(m -> m.getMessageHash().equals(hashToDelete));
        assertFalse(stillExists);
    }

    @Test
    public void testDisplayFullReport() {
        String report = "";
        report += "Full Sent Messages Report:\n";
        for (Message msg : messages) {
            report += "ID: " + msg.getMessageID() + "\n"
                    + "Recipient: " + msg.getRecipientCellNumber() + "\n"
                    + "Message: " + msg.getMessageInput() + "\n"
                    + "Hash: " + msg.getMessageHash() + "\n\n";
        }
        assertTrue(report.contains("ID: 1234567890"));
        assertTrue(report.contains("Message: Did you get the cake?"));
    }

    @Test
    public void testClearAllMessages() {
        messages.clear();
        assertTrue(messages.isEmpty());
    }
}