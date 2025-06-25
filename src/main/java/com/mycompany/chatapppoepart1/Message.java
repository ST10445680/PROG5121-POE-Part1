/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppoepart1;
import javax.swing.JOptionPane;
import java.util.Random;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;    
import java.util.List;


/**
 *
 * @author lab_services_student
 */
public class Message {
   

// Array declarations  
private static ArrayList<Message> sentMessages = new ArrayList<>();  
private static ArrayList<Message> disregardedMessages = new ArrayList<>();  
private static ArrayList<Message> storedMessages = new ArrayList<>();  
private static ArrayList<String> messageHashes = new ArrayList<>();  
private static ArrayList<String> messageIDs = new ArrayList<>();   

public static List<Message> getSentMessages() {
    return sentMessages;
}

public static List<Message> getStoredMessages() {
    return storedMessages;
}


private String flag;

public void setFlag(String flag) {
    this.flag = flag;
}
    
//Declaring variables   
private String messageID;
private int i;
private String messageInput;
private String messageHash;
private int messageCount;
int messageLength ;
private String recipientCellNumber;
private static final String FILE_PATH = "messages.json";
private ObjectMapper mapper = new ObjectMapper();

private String menuChoice;  // Added declaration here — you had used menuChoice before declaring it
 
//Getters and setters      

public String getMenuChoice(){
    return menuChoice;
}

public int getMessageCount(){
    return messageCount;
}

public String getMessageInput(){
    return messageInput;
}

public int getMessageLength(){
    return messageLength;
}

public String getMessageID(){
    return messageID;
}

public String getRecipientCellNumber(){
    return recipientCellNumber;
}

public String getMessageHash(){
    return messageHash;
}

public int getI(){
    return i;
}

public void setMenuChoice(String menuChoice){
   this.menuChoice = menuChoice;     
}

public void setMessageCount(int messageCount) {
    this.messageCount = messageCount;
}

public void setMessageInput(String messageInput) {
    this.messageInput = messageInput;
}

public void setMessageLength(int messageLength) {
    this.messageLength = messageLength;
}

public void setMessageID(String messageID) {
    this.messageID = messageID;
}

public void setRecipientCellNumber(String recipientCellNumber) {
    this.recipientCellNumber = recipientCellNumber;
}

public void setMessageHash(String messageHash) {
    this.messageHash = messageHash;
}

public void setI(int i) {
    this.i = i;
}


//welcome message
public void showWelcomeMessage() { 
    JOptionPane.showMessageDialog(null,"Welcome to quick chat!");
}

//Menu that displays at startup to prompt user selection      
public void showMenuAndSetChoice() {
    do {
         menuChoice = JOptionPane.showInputDialog(null,"\nPlease select one of the following:"  
                                    +"\n1.Send messages"  
                                    +"\n2.Show recently sent messages"  
                                    +"\n3.Quit"  
                                    +"\n4.Display sender & recipient of all sent messages"  
                                    +"\n5.Display the longest sent message"  
                                    +"\n6.Search message by ID"  
                                    +"\n7.Search messages sent to a recipient"  
                                    +"\n8.Delete message by hash"  
                                    +"\n9.Show full report of all sent messages"  
                                    +"\n10.Clear all sent messages"); 

        switch (menuChoice) {
            case "1":  
                    MessageHandling();  
                    break;  
                case "2":  
                    printMessages(1);  
                    break;  
                case "3":  
                    JOptionPane.showMessageDialog(null, "Goodbye!");  
                    break;  
                case "4":  
                    MessageTest.displaySenderAndRecipient(sentMessages);  
                    break;  
                case "5":  
                    MessageTest.displayLongestMessage(sentMessages);  
                    break;  
                case "6":  
                     MessageTest.searchByMessageID(storedMessages, JOptionPane.showInputDialog("Enter Message ID:"));  
                    break;  
                case "7":  
                    MessageTest.searchMessagesToRecipient(storedMessages, JOptionPane.showInputDialog("Enter Recipient Number:"));  
                    break;  
                case "8":  
                  MessageTest.deleteMessageByHash(storedMessages, JOptionPane.showInputDialog("Enter Message Hash:"));  
                this.storeMessage(this); // Save after deletion  
                    break;  
                case "9":  
                    MessageTest.displayFullReport(sentMessages);  
                    break;  
                case "10":  
                    MessageTest.clearAllMessages(sentMessages);  
                  
                this.storeMessage(this); // Save after clearing  
                    break;  
                default:  
                    JOptionPane.showMessageDialog(null, "Invalid choice.");  
        }

    } while (!"3".equals(menuChoice));
}


//Method for handling sending of messages
public void MessageHandling() {
    
    // Prompt the user for the number of messages they want to send
    int messageCount = Integer.parseInt(JOptionPane.showInputDialog(null, "How many messages do you want to send?"));

    // Set the max allowed message length
    messageLength = 250;

    // Counter to track how many valid messages have been successfully sent
    int successfulMessages = 0;

    // Keep prompting until the desired number of valid messages are sent
    while (successfulMessages < messageCount) {

        // Ask the user to enter the message
        messageInput = JOptionPane.showInputDialog(null, "Please enter your message");

        // If the user cancels input (null), show message and skip iteration
        if (messageInput == null) {
            JOptionPane.showMessageDialog(null, "Message cancelled.");
            continue;
        }

        // Check if the message is too long
        if (messageInput.length() >= messageLength) {
            JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters");
            continue; // Don't count this as a successful attempt
        }

        // Ask the user for a valid recipient phone number
        if (!checkRecipientCell()) {
            continue; // If the number is invalid, skip this attempt
        }

        // Generate a unique message ID
        CheckMessageID();

        // Generate a message hash for security/integrity
        checkMessageHash();

        // Show confirmation that the message was sent
        JOptionPane.showMessageDialog(null, "Message sent!");

        // Display the details of the message to the user
        JOptionPane.showMessageDialog(null, "The message details are:" +
                "\n*****************" +
                "\nMessageID: " + messageID +
                "\nMessage hash: " + messageHash +
                "\nRecipient: " + recipientCellNumber +
                "\nMessage: " + messageInput);

        // Store the message in the message list
        storeMessage(this);

        // Increment the counter only after a valid message is sent
        successfulMessages++;
    }
}

 

//Validation of messageID  
public boolean CheckMessageID(){
    
    //While loop that runs methods while user selection isn't 3 (quit)
    while(menuChoice != null && !menuChoice.equals("3")){
        Random rand = new Random();
        // Generates a random 10-digit number by starting from 1000000000L (the smallest 10-digit number)
        // and adding a random value between 0 and 8999999999L. Casting to long ensures no decimals,
        messageID = String.valueOf(1000000000L + (long)(rand.nextDouble() * 9000000000L));
        break;  // To avoid infinite loop
    }
      
    boolean validMessageID;
    
    //Checking if messageID length is 10 digits, you had messageID == 10 which compares string to int (wrong)
    if(messageID != null && messageID.length() == 10){
        validMessageID = true;
    }
    else{
        validMessageID = false;
    }
    
    return validMessageID;
}
  
  
//Validation of recipients cell number
public boolean checkRecipientCell(){
      
    // Asking for and storing recipient information, must assign it to recipientCellNumber
    recipientCellNumber = JOptionPane.showInputDialog(null,"Please enter the recipient's cell number"+"\nPlease note that it should not be longer than 10 digits and it should contain an international code");

    //Ensure that the number is not more than 10 digits long and contains an international code
    boolean recipientCellIsValid = false;
 
    if (recipientCellNumber != null && recipientCellNumber.length() == 10 && recipientCellNumber.startsWith("0") && recipientCellNumber.matches("\\d+")) {
        String formattedNumber = "+27" + recipientCellNumber.substring(1);
        JOptionPane.showMessageDialog(null, "Cell number successfully captured as: " + formattedNumber);
        recipientCellIsValid = true;
    } else {
     
        JOptionPane.showMessageDialog(null, "Invalid cell number. Please ensure it's 10 digits and starts with 0.");
    }
    return recipientCellIsValid;
}
  
  
//Method to create and check messageHash.
public void checkMessageHash(){

    if(messageID == null || messageInput == null) {
        JOptionPane.showMessageDialog(null, "Message ID or message input is null. Cannot create hash.");
        return;
    }

    // Extract the first two digits from the message ID string 
    String firstTwoDigits = messageID.substring(0, 2);

    // Remove leading/trailing spaces from the message and split it into words by spaces
    String[] words = messageInput.trim().split("\\s+");

    // Get the first word from the array (at index 0)
    String firstWord = words[0];

    // Get the last word from the array (at index length-1)
    String lastWord = words[words.length - 1];

    //Formatting messageHash - you used "i" but it's not set anywhere, just keep it
    this.messageHash = (firstTwoDigits + ":" + i + ":" + firstWord + lastWord).toUpperCase();

    //Displaying messageHash
    JOptionPane.showMessageDialog(null,"The message hash is: " +messageHash);

}
  
  
//Displaying options for the user once the message is completed
public void sentMessage(){
      
    String choice = JOptionPane.showInputDialog(null, "Please choose one of the following:"
                                + "\n1.Send message" 
                                + "\n2.Disregard message" 
                                + "\n3.Store message to send later");
  
    //You mentioned wanting a case statement or do-while; left as is for you to expand
}
  
//Displaying a list of messages sent throughout the program
public void printMessages(int i) {
    // First ensure we have latest data
    loadMessages();
    
    if (sentMessages.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No messages found.");
        return;
    }
    
    StringBuilder output = new StringBuilder();
    int count = i;
    for (Message msg : sentMessages) {
        output.append(count++).append(". ").append(msg.getMessageInput()).append("\n");
    }
    JOptionPane.showMessageDialog(null, output.toString());
}


  
//Method that returns the total number of messages
public int returnTotalMessages(){

    int numberOfMessagesSent = 0;
   
    int i = 0;

    //Honestly this needs to run while program is running, maybe a do-while loop is more suitable
    while(menuChoice != null && !menuChoice.equals("3"))
    {
        i++;
        break;  // To avoid infinite loop
    }
    //Display of the total number of messages sent
    JOptionPane.showMessageDialog(null,"The total number of messages sent are " + i);

    return i;
}
  

    



//JSON file storage
// Method to store a message into the JSON file
public void storeMessage(Message message) {
    try {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("messages.json");
        
        // Always read current messages from file first
        List<Message> allMessages = file.exists() ? 
            mapper.readValue(file, new TypeReference<List<Message>>() {}) : 
            new ArrayList<>();
            
        // Add the new message
        allMessages.add(message);
        
        // Save to file
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, allMessages);
        
        // Update the static lists
        sentMessages = new ArrayList<>(allMessages);
        storedMessages = new ArrayList<>(allMessages);
        
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error saving message: " + e.getMessage());
    }
}

public static void loadMessages() {
    try {
        File file = new File("messages.json");
        if (file.exists()) {
            ObjectMapper mapper = new ObjectMapper();
            List<Message> loadedMessages = 
                mapper.readValue(file, new TypeReference<List<Message>>() {});
            
            sentMessages.clear();
            storedMessages.clear();
            sentMessages.addAll(loadedMessages);
            storedMessages.addAll(loadedMessages);
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error loading messages: " + e.getMessage());
    }
}



}  

//Reference list: Reference listcodingtechroom, 2025. Java Regex: Validate Phone Numbers Using Regular Expressions - CodingTechRoom. [online]Available at: <https://codingtechroom.com/tutorial/java-java-regex-validate-phone-numbers> [Accessed 24 May 2025].
//Farrell, J., 2022. Java Programming. 10th ed. S.L.: Cengage Learning.
//geeksforgeeks, 2016. Split() String method in Java with examples. [online]Available at: <https://www.geeksforgeeks.org/split-string-java-examples/> [Accessed 23 May 2025].
//geeksforgeeks, 2017. Substring in Java. [online]Available at: <https://www.geeksforgeeks.org/substring-in-java/> [Accessed 21 May 2025].
//OpenAI, 2022. ChatGPT. [online]Available at: <https://chatgpt.com/> [Accessed 25 May 2025].
