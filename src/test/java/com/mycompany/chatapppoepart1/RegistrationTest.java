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
public class RegistrationTest {

    public RegistrationTest() {
    }

    @Test
    public void testSetUserName() {
        Registration newUser = new Registration();
        newUser.setUserName("kyl_1");
        org.junit.jupiter.api.Assertions.assertEquals("kyl_1", newUser.getuserName()); // Test Data: "kyl_1"[1]
    }

    @Test
    public void testSetpassWord() {
        Registration newUser = new Registration();
       newUser.setpassWord("Ch&&sec@ke99!");
        org.junit.jupiter.api.Assertions.assertEquals("Ch&&sec@ke99!", newUser.getpassWord()); // Test Data: "Ch&&sec@ke99!"[2]
    }

    @Test
    public void testSetcellNumber() {
        Registration newUser = new Registration();
      newUser.setcellNumber("+27838968976");
        org.junit.jupiter.api.Assertions.assertEquals("+27838968976",newUser.getcellNumber()); // Test Data: "+27838968976"[2]
    }


    @Test
    public void testCheckuserName() {
        Registration newUser = new Registration();
        // Correctly formatted
        org.junit.jupiter.api.Assertions.assertTrue(newUser.checkuserName("kyl_1")); // System returns True
        // Incorrectly formatted
        org.junit.jupiter.api.Assertions.assertFalse(newUser.checkuserName("kyle!!!!!!!")); // System returns False
    }

    @Test
    public void testCheckPasswordComplexity() {
        Registration newUser = new Registration();
        // Meets complexity
        org.junit.jupiter.api.Assertions.assertTrue(newUser.checkPasswordComplexity("Ch&&sec@ke99!")); // System returns True
        // Does not meet complexity
        org.junit.jupiter.api.Assertions.assertFalse(newUser.checkPasswordComplexity("password")); // System returns False
    }

    @Test
    public void testCheckCellPhoneNumber() {
        Registration newUser = new Registration();
        // Correctly formatted
        org.junit.jupiter.api.Assertions.assertTrue(newUser.checkCellPhoneNumber("0838968976")); // System returns True
        // Incorrectly formatted
        org.junit.jupiter.api.Assertions.assertFalse(newUser.checkCellPhoneNumber("08966553")); // System returns False
    }
}
