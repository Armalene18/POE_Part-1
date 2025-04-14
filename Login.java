import javax.swing.JOptionPane;
import java.util.regex.*;

public class Login {
    private String username;
    private String password;
    private String cellPhoneNumber;

    public Login() {
        // Default constructor
    }

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])[A-Za-z\\d@#$%^&+=]{8,}$";
        return Pattern.matches(regex, password);
    }

    public boolean checkCellPhoneNumber(String phoneNumber) {
        String regex = "^\\+27\\d{9}$";
        return Pattern.matches(regex, phoneNumber);
    }

    public String registerUser(String username, String password, String phoneNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted. It must contain an underscore and be no more than five characters.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted. It must contain at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell phone number is incorrectly formatted or does not contain the international code (+27).";
        }

        this.username = username;
        this.password = password;
        this.cellPhoneNumber = phoneNumber;
        return "Registration successful!";
    }

    public String loginUser(String enteredUsername, String enteredPassword) {
        if (enteredUsername.equals(this.username) && enteredPassword.equals(this.password)) {
            return "Welcome " + enteredUsername + ", it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }

    public static void main(String[] args) {
        Login user = new Login();

        // User registration
        String username = JOptionPane.showInputDialog("Enter a username (must contain '_' and be max 5 characters):");
        String password = JOptionPane.showInputDialog("Enter a password (min 8 characters, one capital letter, one number, one special char):");
        String phoneNumber = JOptionPane.showInputDialog("Enter your cell phone number (e.g. +27831234567):");

        String registrationResult = user.registerUser(username, password, phoneNumber);
        JOptionPane.showMessageDialog(null, registrationResult);

        if (registrationResult.equals("Registration successful!")) {
            // User login
            String enteredUsername = JOptionPane.showInputDialog("Login - Enter your username:");
            String enteredPassword = JOptionPane.showInputDialog("Login - Enter your password:");

            String loginResult = user.loginUser(enteredUsername, enteredPassword);
            JOptionPane.showMessageDialog(null, loginResult);
        }
    }
}
