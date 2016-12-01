import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.firebase.client.*;

/**
 * Created by AdamGary on 12/1/16.
 */
public class Authorization {

    private static boolean validLogin = false;
    private static Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/Candidates");

    public static void main(String args[]){

        JFrame loginFrame = new JFrame("Auditor Login"); // opens the starting frame
        loginFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        loginFrame.setSize(500,100);

        JPanel startPanel = new JPanel();
        JLabel emailLabel = new JLabel("Email:"); // GUI asks user to enter word that will be guessed
        JTextField emailField = new JTextField();
        emailField.setColumns(30);

        JLabel passWordLabel = new JLabel("Password:"); // GUI asks user to enter word that will be guessed
        JPasswordField passwordField = new JPasswordField();
        passwordField.setColumns(15);

        JButton startGameButton = new JButton("Login"); // button to start the game

        JLabel invalidEntryLabel = new JLabel(" ");

        startPanel.add(emailLabel);
        startPanel.add(emailField);
        startPanel.add(passWordLabel);
        startPanel.add(passwordField);
        startPanel.add(startGameButton);
        startPanel.add(invalidEntryLabel, BorderLayout.SOUTH);
        loginFrame.add(startPanel);
        loginFrame.setVisible(true);



        startGameButton.addActionListener(new ActionListener(){ // action listener for the new hangman GUI to be made for the new game
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = String.valueOf(passwordField.getPassword());
                validLogin = checkCredentials(email, password);
                if(validLogin){
                    loginFrame.setVisible(false);
                    CountyAuditorApp.main();
                }
            }
        });

        if(validLogin){
            loginFrame.setVisible(false);
        }

    }

    public static boolean checkCredentials(String email, String password){

        System.out.println("email: " + email);
        System.out.println("password: " + password);

        ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("valid login");

                validLogin = true;
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                System.out.println("invalid login");

                validLogin = false;
            }
        });

        return validLogin;
    }
}
