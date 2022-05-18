package GUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
//Creating the table

public class JTableExamples {
        // frame
        JFrame f;
        // Table
        JTable j;

        // Constructor
        JTableExamples(ArrayList<String[]> obj)
        {
            // Frame initialization
            f = new JFrame();

            // Frame Title
            f.setTitle("JTable Example");

            // Data to be displayed in the JTable
            String [][] data= new String[obj.size()][obj.get(0).length];
            for(int i=0; i< obj.size();i++){
                data[i][0] = obj.get(i)[0];
                data[i][1] = obj.get(i)[1];
                data[i][2] = obj.get(i)[2];
                data[i][3] = obj.get(i)[3];
            }


//                        {
//                            obj.get(0)[0], obj.get(0)[1], obj.get(0)[2], obj.get(0)[3]
//                        },
//                    {
//                            obj.get(1)[0], obj.get(1)[1], obj.get(1)[2], obj.get(1)[3]
//                    }
//
//                    };

            // Column Names
            String[] columnNames = { "EMployee 1 ID","EMployee 2 ID","Project ID","days worked together"};

            // Initializing the JTable
            j = new JTable(data, columnNames);
            j.setBounds(30, 40, 200, 300);

            // adding it to JScrollPane
            JScrollPane sp = new JScrollPane(j);
            f.add(sp);
            // Frame Size
            f.setSize(500, 200);
            // Frame Visible = true
            f.setVisible(true);
        }
}
