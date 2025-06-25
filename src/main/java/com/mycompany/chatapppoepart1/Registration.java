/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppoepart1;
import javax.swing.JOptionPane;

/**
 *
 * @author lab_services_student
 */


public class Registration {
  
private String userName; 
private String passWord;
private String cellNumber;


// Getter methods to access private variables
public String getuserName(){
return userName;
}

public String getpassWord(){
  return passWord;
}

public String getcellNumber(){
       return cellNumber;
}

 // Setter methods to assign values to private variables
public void setUserName(String userName){
   this.userName = userName;     
}

public void setpassWord(String passWord){
   this.passWord = passWord;  
}

public void setcellNumber(String cellNumber){
   this.cellNumber = cellNumber; 
}


public void regUser(){
    
      // Greeting message and sentinel value should the user wish to exit at any point during registration
    JOptionPane.showMessageDialog(null, "Welcome to the chat app" 
                                       + "\n Should you wish to exit please enter ('exit') ");

     while(true){
         
     String userNameInput = JOptionPane.showInputDialog(null, "Please enter a valid userame that contains an underscore and has a maximum of 5 characters along" );
   
      // Check if the user wants to exit  
        if (userNameInput == null || userNameInput.equalsIgnoreCase("exit")) {  
            JOptionPane.showMessageDialog(null, "Exiting the application.");  
            return; // Exit the method if the user chooses to exit  
        }
     
     if (checkuserName(userNameInput)) {  
            setUserName(userNameInput); // Set username only if valid  
             break;
     }
     JOptionPane.showMessageDialog(null, "UserName is invalid, Please try again");
     }
      
   
     
  while(true){
  
  String cellNumberInput = JOptionPane.showInputDialog(null, "The cell phone number should contain the international country code followed by the number, which is no more than ten characters long.");
   
  // Checks if the user clicked "Cancel" or typed "exit" to terminate the registration process
   if (cellNumberInput == null || cellNumberInput.equalsIgnoreCase("exit")){
       JOptionPane.showMessageDialog(null, "Exiting the application, Goodbye.");
       return;
   }
  
   if (checkCellPhoneNumber(cellNumberInput)){
       setcellNumber(cellNumberInput);
       break;
   }else{
   // Notify if the cell number input was invalid  
        JOptionPane.showMessageDialog(null, "Invalid cell phone number. Please try again.");  
   
    }   
  }
  
 while(true){
       
   String passWordInput = JOptionPane.showInputDialog(null, "Please enter a password that is 8 or more characters, ensure that it has the following : "
                                                            +"\nContain a capital letter" 
                                                            +"\nContain a number."
                                                            +"\nContain a special character.");
   
   
   if (passWordInput == null || passWordInput.equalsIgnoreCase("exit")){
       JOptionPane.showMessageDialog(null,"Exiting the application, Goodbye.");
       return;
   }
   
   
   if(checkPasswordComplexity(passWordInput)){
       setpassWord(passWordInput);
       break;
   }
    JOptionPane.showMessageDialog(null, "Password doesn't meet the requirements, please try again");
   } 
  
  
   
}



public boolean checkuserName(String userName){
    
     boolean userIsValid = false;
     
     if (userName.length()==5 && userName.contains("_")){
    JOptionPane.showMessageDialog(null, "Username successfully captured.");
    userIsValid = true;
} else {
    JOptionPane.showMessageDialog(null, "Username is notcorrectly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
}    
     return userIsValid;
 }



 //Validates password using regular expression:
 //Must include lowercase, uppercase, digit, special character, and be at least 8 characters.
     
public boolean checkPasswordComplexity(String passWord){
    
 boolean passwordIsValid = false;
 
 
 // Regex to validate complexity
 String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$";

// Check the password against the regex
if (passWord.matches(passwordRegex)) {
    JOptionPane.showMessageDialog(null, "Password successfully captured.");
    passwordIsValid = true;
} else {
    JOptionPane.showMessageDialog(null, 
        "Password is not correctly formatted." +
        "\nMake sure it is at least 8 characters long and includes: " +
        "\n A capital letter" +
        "\n A number" +
        "\n A special character");
}
 return passwordIsValid;   
}
        


      //Validates cell phone number:
     // Must be exactly 10 digits, start with 0, and be numeric.
    // If valid, formats it to include South Africa's +27 code. 
 public boolean checkCellPhoneNumber(String cellNumber){
     
 boolean cellIsValid = false;
 
 if (cellNumber.length() == 10 && cellNumber.startsWith("0") && cellNumber.matches("\\d+")) {
        String formattedNumber = "+27" + cellNumber.substring(1);
        JOptionPane.showMessageDialog(null, "Cell number successfully captured as: " + formattedNumber);
        cellIsValid = true;
    } else {
     
        JOptionPane.showMessageDialog(null, "Invalid cell number. Please ensure it's 10 digits and starts with 0.");
    }
    return cellIsValid;
 }

}
