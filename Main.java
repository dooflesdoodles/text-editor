import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.swing.*;

public class Main extends JFrame {

    // Jcomponents
    JMenuBar menuBar        = new JMenuBar();
    JMenu fileMenu          = new JMenu("File");
    JMenuItem loadMenuItem  = new JMenuItem("Load");
    JMenuItem saveMenuItem  = new JMenuItem("Save");
    JMenuItem exitMenuItem  = new JMenuItem("Exit");

    JTextField textField    = new JTextField(20);
    JButton saveButton      = new JButton("Save");
    JButton loadButton      = new JButton("Load");
    JTextArea textArea      = new JTextArea(10, 50);

    public Main() {
        super("Text Editor");

        // Action listeners
        loadMenuItem.addActionListener(actionEvent -> {
            load(textField.getText());
        });

        saveMenuItem.addActionListener(actionEvent -> {
            save(textField.getText(), textArea.getText());
        });

        exitMenuItem.addActionListener(actionEvent -> {
            System.exit(0);
        });

        saveButton.addActionListener(actionEvent -> {
            save(textField.getText(), textArea.getText());
        });

        loadButton.addActionListener(actionEvent -> {
            load(textField.getText());
        });

        // Set content-pane & flow layout for the frame
        JScrollPane scrollableTextArea = new JScrollPane(textArea);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().setLayout(new FlowLayout(FlowLayout.LEADING));

        // Add components to menu
        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Add components to content-pane
        getContentPane().add(textField);
        getContentPane().add(saveButton);
        getContentPane().add(loadButton);
        getContentPane().add(scrollableTextArea);

        // Set Window
        setSize(593, 280);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void setTextAreaValue(String textAreaValue) {
        textArea.setText(textAreaValue);
    }

    // Load File
    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public void load(String textFieldValue){
        String pathToHelloWorldJava = textFieldValue;
        try {
            setTextAreaValue(readFileAsString(pathToHelloWorldJava));
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }
    }

    // Write File
    public void save(String textFieldValue, String textAreaValue){
        File file = new File(textFieldValue);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(textAreaValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Main main = new Main();
    }
}