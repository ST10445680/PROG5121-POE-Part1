/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppoepart1;

/**
 *
 * @author lab_services_student
 */
import javax.swing.JOptionPane;

public class Login {

    private Registration regUser; // This connects to the Registration class

    public Login(Registration regUser) {
        this.regUser = regUser;
    }

  
    // Asks for user input and compares to registration info
    public boolean loginUser() {
        boolean isValidUser = false;
        
        // Loop until correct credentials are entered
        while(true){
        
        //Prompting the user for input
        String inputUserName = JOptionPane.showInputDialog("Enter your username:");
        String inputPassword = JOptionPane.showInputDialog("Enter your password:");
        
         // Check if entered credentials match those stored in regUser
        if(inputUserName.equals(regUser.getuserName()) &&
               inputPassword.equals(regUser.getpassWord())){
        isValidUser= true;
        JOptionPane.showMessageDialog(null,"Welcome " +inputUserName+ " it is great to see you again.");
          break;// Exit the loop after successful login
          
        }else{  
        JOptionPane.showMessageDialog(null, "Incorrect credentials, please try again");  // Show error message for incorrect credentials   
        }
        
        }
         
      return isValidUser;  
    }

    
    //Displays login status
    public void returnLoginStatus() {
        if (loginUser()) {
              // Display success message if login is successful
            JOptionPane.showMessageDialog(null,"Login successful. Welcome!"); 
        } else {
   
            JOptionPane.showMessageDialog(null, "Login failed. Please check your details and try again.");
        }
    }
}