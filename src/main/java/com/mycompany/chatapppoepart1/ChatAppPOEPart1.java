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
            
    }
}

//Reference list
