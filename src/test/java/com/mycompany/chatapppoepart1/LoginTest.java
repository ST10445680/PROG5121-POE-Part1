/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapppoepart1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lab_services_student
 */
public class LoginTest {
    

    public LoginTest() {
    }

    @Test
    public void testLoginUser() {
        Registration newUser = new Registration();
        newUser.setUserName("kyl_1");
        newUser.setpassWord("Ch&&sec@ke99!");
        Login login = new Login(newUser);

        // Simulate correct credentials
        // Here, you would mock JOptionPane for input/output in a real test
        // For demonstration, we assume correct credentials are entered
        boolean result = login.loginUser();
        org.junit.jupiter.api.Assertions.assertTrue(result); // Login Successful, system returns True[3]
    }

    @Test
    public void testReturnLoginStatus() {
        Registration newUser = new Registration();
        newUser.setUserName("kyl_1");
        newUser.setpassWord("Ch&&sec@ke99!");
        Login login = new Login(newUser);

       //check that login status is true for correct credentials
        boolean loginStatus = login.loginUser();
        org.junit.jupiter.api.Assertions.assertEquals(true, loginStatus); // System returns True for success[3]
    }
}

    

