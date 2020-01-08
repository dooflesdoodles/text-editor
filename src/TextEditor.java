import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.swing.*;

public class TextEditor extends JFrame {

    // Jcomponents (menue)
    JMenuBar menuBar            = new JMenuBar();
    JMenu fileMenu              = new JMenu("File");
    JMenuItem loadMenuItem      = new JMenuItem("Load");
    JMenuItem saveMenuItem      = new JMenuItem("Save");
    JMenuItem exitMenuItem      = new JMenuItem("Exit");

    JTextField fileSelector     = new JTextField(20);
    JButton saveButton          = new JButton("Save");
    JButton loadButton          = new JButton("Load");
    JTextArea editorTextArea    = new JTextArea(10, 50);

    public TextEditor() {
        super("Text Editor"); // Window title

        // Action listeners
        loadMenuItem.addActionListener(actionEvent -> {
	    load(getFileSelectorValue() );
        });

        saveMenuItem.addActionListener(actionEvent -> {
	    save(fileSelector.getText(), getEditorTextAreaValue() );
        });

        exitMenuItem.addActionListener(actionEvent -> {
            System.exit(0);
        });

        saveButton.addActionListener(actionEvent -> {
	    save(fileSelector.getText(), getEditorTextAreaValue() );
        });

        loadButton.addActionListener(actionEvent -> {
	    load(getFileSelectorValue() );
        });

        // Set content-pane & flow layout for the frame
        JScrollPane scrollableTextArea = new JScrollPane(editorTextArea);
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
        getContentPane().add(fileSelector);
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

    // Getter & setter
    public void setEditorTextAreaValue(String editorTextAreaValue) {
        editorTextArea.setText(editorTextAreaValue);
    }
		
    public String getEditorTextAreaValue() {
	return editorTextArea.getText();
    }

    public String getFileSelectorValue() {
        return fileSelector.getText();
    }
		
    // Load File
    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public void load(String fileSelectorValue){
        String pathToFile = fileSelectorValue;
        try {
            setEditorTextAreaValue(readFileAsString(pathToFile));
            editorTextArea.setCaretPosition(0);
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }
    }

    // Write File
    public void save(String fileSelectorValue, String editorTextAreaValue){
        File file = new File(fileSelectorValue);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(editorTextAreaValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
