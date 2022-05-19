package GUI;

import services.Calculate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;

//creating the buton, file selection page and the action listener for the button
public class MyFrame extends JFrame implements ActionListener {
    long months=0;
    JButton button;

    public MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        button = new JButton("Select File");
        button.addActionListener(this);

        this.add(button);
        this.pack();
        this.setVisible(true);
    }



    @Override
    public void actionPerformed( ActionEvent e) {

        if (e.getSource() == button) {

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File(".")); //sets current directory

            int response = fileChooser.showOpenDialog(null); //select file to open
            //int response = fileChooser.showSaveDialog(null); //select file to save

            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
              //  FileRead fileRead = new FileRead();
                System.out.println(file);
                Calculate calculate = new Calculate();
                try {
                    calculate.convertStringToDate(file.toString());
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                try {
                    new JTableExamples(calculate.getEmployees(file.toString()));


                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

            }
        }

    }
}
