package com.mycompany.chatapppoepart1;

import javax.swing.JOptionPane;
import java.util.List;

public class MessageTest {

    //Method to display the sender and recipient of a message
    public static void displaySenderAndRecipient(List<Message> sentMessages) {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No sent messages.");
        }
        StringBuilder sb = new StringBuilder("Sender (you) and Recipient:\n");
        for (Message msg : sentMessages) {
            sb.append("Sender: you, Recipient: ").append(msg.getRecipientCellNumber())
              .append(", Message: ").append(msg.getMessageInput()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    //Method to finnd and display the longest message
    public static void displayLongestMessage(List<Message> sentMessages) {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No sent messages.");
            return;
        }
        Message longestMsg = sentMessages.get(0);
        for (Message msg : sentMessages) {
            if (msg.getMessageInput().length() > longestMsg.getMessageInput().length()) {
                longestMsg = msg;
            }
        }
        JOptionPane.showMessageDialog(null, "Longest message:\n" + longestMsg.getMessageInput());
    }

    //Method to locate a message using message ID
    public static void searchByMessageID(List<Message> storedMessages, String messageID) {
        boolean found = false;
        for (Message msg : storedMessages) {
            if (msg.getMessageID().equals(messageID)) {
                JOptionPane.showMessageDialog(null,
                    "Recipient: " + msg.getRecipientCellNumber() +
                    "\nMessage: " + msg.getMessageInput());
                found = true;
                break;
            }
        }
        if (!found)
            JOptionPane.showMessageDialog(null, "Message ID not found.");
    }

    
    //Method to search for a message using recipient 
    public static void searchMessagesToRecipient(List<Message> storedMessages, String recipientNumber) {
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for (Message msg : storedMessages) {
            if (msg.getRecipientCellNumber().equals(recipientNumber)) {
                sb.append("Message ID: ").append(msg.getMessageID()).append(", Message: ").append(msg.getMessageInput()).append("\n");
                found = true;
            }
        }
        if (found)
            JOptionPane.showMessageDialog(null, "Messages to " + recipientNumber + ":\n" + sb.toString());
        else
            JOptionPane.showMessageDialog(null, "No messages found for recipient " + recipientNumber);
    }

    //Method to delete message using the Message hash
    public static void deleteMessageByHash(List<Message> storedMessages, String messageHash) {
        boolean deleted = false;
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).getMessageHash().equals(messageHash)) {
                storedMessages.remove(i);
                JOptionPane.showMessageDialog(null, "Message deleted successfully.");
                deleted = true;
                break;
            }
        }
        if (!deleted)
            JOptionPane.showMessageDialog(null, "No message found with that hash.");
    }

    //Method to display full report
    public static void displayFullReport(List<Message> sentMessages) {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No sent messages.");
            return;
        }
        StringBuilder sb = new StringBuilder("Full Sent Messages Report:\n");
        for (Message msg : sentMessages) {
            sb.append("MessageID: ").append(msg.getMessageID()).append("\n")
              .append("Recipient: ").append(msg.getRecipientCellNumber()).append("\n")
              .append("Message: ").append(msg.getMessageInput()).append("\n")
              .append("Hash: ").append(msg.getMessageHash()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    //Method to clear al messages stored in json file
    public static void clearAllMessages(List<Message> sentMessages) {
        sentMessages.clear();
        JOptionPane.showMessageDialog(null, "All sent messages have been cleared.");
    }
    
}

//Reference list 
//Academy, N., 2020. Single-Dimensional Arrays in Java (Part 1). [online]Available at:https://www.youtube.com/watch?v=kWJHzambtNo&list=PLBlnK6fEyqRiraym3T703apTvEZLaSVtJ [Accessed 23 June 2025].

//GeeksforGeeks, 2016. Arrays in Java. [online]Available at:https://www.geeksforgeeks.org/java/arrays-in-java/ [Accessed 22 June 2025].

//OpenAI, 2022. ChatGPT. [online]Available at: <https://chatgpt.com/> [Accessed 24 June 2025].

//Volodymyr Portianko, 2020. Append() Method in Java: StringBuilder and StringBuffer. [online]Available at:https://codegym.cc/groups/posts/append-method-in-java [Accessed 22 June 2025].

//W3Schools, 2019. Java ArrayList. [online] W3schools.com. Available at:https://www.w3schools.com/java/java_arraylist.asp [Accessed 20 June 2025].

