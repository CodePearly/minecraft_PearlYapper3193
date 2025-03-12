package MinecraftVesions.pv1214.nomodsel;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import MinecraftVesions.pv1214.nomods;

public class download {

    private JFrame frame;
    private String filelocation; // Variable to store the save location
    private final String fileUrl = "https://codeload.github.com/CodePearly/minecraft_PearlYapper3193/zip/refs/tags/1.21.4-Offline?token="; // Default URL

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    download window = new download();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public download() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("File Downloader");
        frame.getContentPane().setLayout(null);

        JLabel lblInstruction = new JLabel("Click 'Download' to begin:");
        lblInstruction.setBounds(10, 10, 300, 25);
        frame.getContentPane().add(lblInstruction);

        JButton downloadButton = new JButton("Download");
        downloadButton.setBounds(150, 60, 120, 30);
        frame.getContentPane().add(downloadButton);

        JLabel statusLabel = new JLabel("");
        statusLabel.setBounds(10, 100, 400, 25);
        frame.getContentPane().add(statusLabel);

        // Add action listener for the "Download" button
        downloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Prompt the user to select the save location
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose Save Location");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int userSelection = fileChooser.showSaveDialog(frame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    filelocation = fileChooser.getSelectedFile().getAbsolutePath();

                    try {
                        statusLabel.setText("Downloading...");
                        HttpURLConnection connection = (HttpURLConnection) new URL(fileUrl + token).openConnection();
                        connection.setRequestMethod("GET");

                        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
                        BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                        FileOutputStream out = new FileOutputStream(filelocation + "/" + fileName);

                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = in.read(buffer, 0, 1024)) != -1) {
                            out.write(buffer, 0, bytesRead);
                        }

                        in.close();
                        out.close();
                        statusLabel.setText("Download completed: " + filelocation + "/" + fileName);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Error downloading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    statusLabel.setText("Download cancelled.");
                }
            }
        });
    }
}
