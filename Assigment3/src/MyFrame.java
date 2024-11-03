
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener{
    
    JMenuBar menuBar;
    JMenu optionsMenu;
    JMenuItem timeItem;
    JMenuItem writeItem;
    JMenuItem colorItem;
    JMenuItem exitItem;
    JTextField dateItem;
    LocalDate today;
    Path textPath;
    
    MyFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setLayout(new FlowLayout());

        menuBar = new JMenuBar();

        optionsMenu = new JMenu("Options");

        timeItem = new JMenuItem("Time");
        writeItem = new JMenuItem("Write");
        colorItem = new JMenuItem("Color");
        exitItem = new JMenuItem("Exit");

        timeItem.addActionListener(this);
        writeItem.addActionListener(this);
        colorItem.addActionListener(this);
        exitItem.addActionListener(this);

        optionsMenu.add(timeItem);
        optionsMenu.add(writeItem);
        optionsMenu.add(colorItem);
        optionsMenu.add(exitItem);

        dateItem = new JTextField("");
        dateItem.setColumns(20);
        this.add(dateItem);

        menuBar.add(optionsMenu);

        this.setJMenuBar(menuBar);
         
        this.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==timeItem) {
            today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM, dd yyyy");
            
            String outputString = today.format(formatter);
            dateItem.setText(outputString);
        }
        if(e.getSource()==writeItem) {
            try {
                // Path to file
                textPath = Paths.get("log.txt");

                // Create file if it does not exist
                if (!Files.exists(textPath)){
                    Files.createFile(textPath);
                    System.out.println("log.txt created");
                }

                // Takes text from fields
                String uploadString = dateItem.getText();

                // Save to file
                Files.write(textPath, uploadString.getBytes(StandardCharsets.UTF_8));
            } catch (IOException a) {
                System.out.println("Error: " + a.getMessage());
            }
        }
        if(e.getSource()==colorItem) { // DONE
            // Random value between 16 and 50
            Random r = new Random();
            double random = (r.nextInt(32)+16);
            // Change button to Hue value
            colorItem.setText(random+"");
            // Convert double to float and change value into hundreths place decimal by dividing by 100
            float f = ((float)random) / 100;
            // Define hue by random number and set saturationg and brightness to max
            Color c = new Color(Color.HSBtoRGB(f, 1, 1));
            getContentPane().setBackground(c); // Hue 60(0.16) to 180(0.5)
        }
        if(e.getSource()==exitItem) { // DONE
            System.exit(0);
        }
    }
}
