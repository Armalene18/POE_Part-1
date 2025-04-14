import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void testValidUsername() {
        Login login = new Login();
        assertTrue(login.checkUserName("us_1")); // valid
    }

    @Test
    public void testInvalidUsername_NoUnderscore() {
        Login login = new Login();
        assertFalse(login.checkUserName("user1")); // missing underscore
    }

    @Test
    public void testInvalidUsername_TooLong() {
        Login login = new Login();
        assertFalse(login.checkUserName("user_12")); // more than 5 chars
    }

    @Test
    public void testValidPassword() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Pass123@")); // valid
    }

    @Test
    public void testInvalidPassword_NoCapital() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("pass123@")); // missing capital
    }

    @Test
    public void testInvalidPassword_NoDigit() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("Password@")); // missing digit
    }

    @Test
    public void testInvalidPassword_NoSpecialChar() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("Password123")); // missing special char
    }

    @Test
    public void testValidPhoneNumber() {
        Login login = new Login();
        assertTrue(login.checkCellPhoneNumber("+27831234567")); // valid SA number
    }

    @Test
    public void testInvalidPhoneNumber_WrongFormat() {
        Login login = new Login();
        assertFalse(login.checkCellPhoneNumber("0831234567")); // no +27
    }

    @Test
    public void testRegisterUser_ValidInputs() {
        Login login = new Login();
        String result = login.registerUser("us_1", "Pass123@", "+27831234567");
        assertEquals("Registration successful!", result);
    }

    @Test
    public void testRegisterUser_InvalidUsername() {
        Login login = new Login();
        String result = login.registerUser("user123", "Pass123@", "+27831234567");
        assertTrue(result.contains("Username is not correctly formatted"));
    }

    @Test
    public void testRegisterUser_InvalidPassword() {
        Login login = new Login();
        String result = login.registerUser("us_1", "password", "+27831234567");
        assertTrue(result.contains("Password is not correctly formatted"));
    }

    @Test
    public void testRegisterUser_InvalidPhone() {
        Login login = new Login();
        String result = login.registerUser("us_1", "Pass123@", "0831234567");
        assertTrue(result.contains("Cell phone number is incorrectly formatted"),
                "The error message should contain 'Cell phone number is incorrectly formatted'. Actual message: " + result);
    
    }

    @Test
    public void testLoginUser_Success() {
        Login login = new Login();
        login.registerUser("us_1", "Pass123@", "+27831234567");
        String loginMessage = login.loginUser("us_1", "Pass123@");
        assertTrue(loginMessage.contains("Welcome"));
    }

    @Test
    public void testLoginUser_Failure() {
        Login login = new Login();
        login.registerUser("us_1", "Pass123@", "+27831234567");
        String loginMessage = login.loginUser("us_1", "WrongPass@");
        assertEquals("Username or password incorrect, please try again.", loginMessage);
    }
}
