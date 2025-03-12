package MinecraftVesions.pv1214;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import MinecraftVesions.pv1214.nomodsel.download;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class nomods {
    private String token; // Variable to store the token

    public nomods() {
        // Create a new frame for the "No Mods" window
        JFrame noModsFrame = new JFrame("No Mods Selected");
        noModsFrame.setTitle("Enter Token - Minecraft v1.21.4");
        noModsFrame.setSize(400, 150);
        noModsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add components for user input
        JLabel lblMessage = new JLabel("Please enter your code/token:", JLabel.CENTER);
        JTextField txtToken = new JTextField();
        JButton btnSubmit = new JButton("Submit");

        // Add action listener for the submit button
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                token = txtToken.getText(); // Get the text from the input field
                if (token.isEmpty()) {
                    JOptionPane.showMessageDialog(noModsFrame, "Token cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(noModsFrame, "Token saved: " + token, "Success", JOptionPane.INFORMATION_MESSAGE);
                    new download();
                    noModsFrame.dispose(); // Close the frame after saving the token
                }
            }
        });

        // Arrange components in the frame
        noModsFrame.setLayout(null);
        lblMessage.setBounds(50, 10, 300, 20);
        txtToken.setBounds(50, 40, 300, 25);
        btnSubmit.setBounds(150, 80, 100, 30);

        noModsFrame.add(lblMessage);
        noModsFrame.add(txtToken);
        noModsFrame.add(btnSubmit);

        // Make the frame visible
        noModsFrame.setVisible(true);
    }

    // Getter method for the token
    public String getToken() {
        return token;
    }
}
