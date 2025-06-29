/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapppoepart1;

import javax.swing.JOptionPane;

/**
 *
 * @author lab_services_student
 */
public class ChatAppPOEPart1 {

    public static void main(String[] args) {
        
    //  Create the registration object
        Registration newUser = new Registration();

        //  Register the user
        newUser.regUser();
        
        // Add this check before proceeding:
    if (newUser.getuserName() == null || newUser.getpassWord() == null || newUser.getcellNumber() == null) {
       JOptionPane.showMessageDialog(null, "User exited the registration process. Ending program.");
        return; // Stops the whole app right here
    }

        // Create login object using registered user
        Login userLogin = new Login(newUser);

        //  Try to log in
        userLogin.returnLoginStatus();  
        
        
        Message msg = new Message();  // Create instance of Message class
        
        Message.loadMessages();


        msg.showWelcomeMessage();

        boolean running = true;

        while (running) {
            msg.showMenuAndSetChoice(); // Display menu and set user's choice
            
            String choice = msg.getMenuChoice();

            if (choice == null) {
                // User cancelled or closed input dialog
                JOptionPane.showMessageDialog(null, "No option selected. Exiting.");
                break;
            }

            switch (choice) {
                case "1":
                    // Validate recipient cell number before sending
                    if (msg.checkRecipientCell()) {
                        msg.CheckMessageID();  // Generate & validate message ID
                        msg.MessageHandling(); // Handle sending message
                        msg.checkMessageHash();// Create message hash
                        msg.sentMessage();     // Show options after message sent
                    }
                    break;

                case "2":
                    // Show recently sent messages
                    msg.printMessages(1); // Starting numbering at 1
                    break;

                case "3":
                    // Quit the application
                    JOptionPane.showMessageDialog(null, "Thank you for using Quick Chat. Goodbye!");
                    running = false;
                    break;
                case "4":
                // Display sender & recipient of all sent messages
                MessageTest.displaySenderAndRecipient(Message.getSentMessages());
                break;

               case "5":
                   // Display the longest sent message
                   MessageTest.displayLongestMessage(Message.getSentMessages());
                   break;

               case "6":
                   // Search message by ID
                   String messageID = JOptionPane.showInputDialog("Enter Message ID:");
                   MessageTest.searchByMessageID(Message.getStoredMessages(), messageID);
                   break;

               case "7":
                   // Search messages sent to a recipient
                   String recipientNumber = JOptionPane.showInputDialog("Enter Recipient Number:");
                   MessageTest.searchMessagesToRecipient(Message.getStoredMessages(), recipientNumber);
                   break;

               case "8":
                   // Delete message by hash
                   String messageHash = JOptionPane.showInputDialog("Enter Message Hash:");
                   MessageTest.deleteMessageByHash(Message.getStoredMessages(), messageHash);
                   // Save after deletion
                   break;

               case "9":
                   // Show full report
                   MessageTest.displayFullReport(Message.getSentMessages());
                   break;

               case "10":
                   // Clear all sent messages
                   MessageTest.clearAllMessages(Message.getSentMessages());
                   // Save after clearing
                 
                   break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option selected. Please try again.");
            }
        }
    }
            
    }
    


//Reference list
