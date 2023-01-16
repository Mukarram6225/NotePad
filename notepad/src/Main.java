import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;
import java.awt.event.*;
import javax.swing.text.*;
import java.awt.print.PrinterException;
import  java.io.*;
class notepad extends JFrame implements  ActionListener {
    JTextArea t;
    JFrame f;

    notepad() {
        // initialising the frame and textarea
        f = new JFrame("notepad");

        t = new JTextArea();

        //initialising menubar and all individual menus
        JMenuBar menu = new JMenuBar();

        JMenu file = new JMenu("file");

        JMenuItem f1 = new JMenuItem("new");
        JMenuItem f2 = new JMenuItem("open");
        JMenuItem f3 = new JMenuItem("save");
        JMenuItem f4 = new JMenuItem("print");
        // adding actionlistener to individual menuitems
        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);
        // adding menuitems to file menu
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);

        //creating the edit menu
        JMenu edit = new JMenu("edit");

        JMenuItem f5 = new JMenuItem("cut");
        JMenuItem f6 = new JMenuItem("copy");
        JMenuItem f7 = new JMenuItem("paste");

        // adding actionlistener to individual menuitems
        f5.addActionListener(this);
        f6.addActionListener(this);
        f7.addActionListener(this);

        // adding menuitems to edit menu
        edit.add(f5);
        edit.add(f6);
        edit.add(f7);

        JMenuItem close = new JMenuItem("close");
        close.addActionListener(this);

        menu.add(file);
        menu.add(edit);
        menu.add(close);

        f.setJMenuBar(menu);
        f.add(t);
        f.setSize(1000, 1000);
        f.show();

    }

    // funtionality implementation
    public void actionPerformed(ActionEvent e) {
        // getting the user clicked funtion in form of a string
        String s = e.getActionCommand();

        // proceesing the string s
        switch (s) {
            case "new":
                t.setText("");
                break;
            case "open":
                // creating object of jfilechooser and initialising it to a location in the computer
                JFileChooser j = new JFileChooser("D:");

                // initialising the opendialbox
                int r = j.showOpenDialog(null);
                // if the user select a file
                if (r == JFileChooser.APPROVE_OPTION) {

                    // extracting the absolute path of the selected file
                    File fi = new File(j.getSelectedFile().getAbsolutePath());

                    String s1, s2;
                    try {
                        //place pointer at the starting of the file
                        FileReader fr = new FileReader(fi);
                        //use bufferreader to read line by line
                        BufferedReader br = new BufferedReader(fr);

                        //storing the first line in s1
                        s1 = br.readLine();
                        //appending subsequent lines till end of file is reached
                        while ((s2 = br.readLine()) != null) {
                            s1 = s + "\n" + s2;
                        }
                        t.setText(s1);


                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(f, "operation cancelled");
                }

                break;
            case "save":
                // creating object of jfilechooser class and initialising it to a location in the computer
                JFileChooser ji = new JFileChooser("D:");

                // initialising the opendialbox
                int ri = ji.showOpenDialog(null);
                // if the user select a file
                if (ri == JFileChooser.APPROVE_OPTION) {

                    // extracting the absolute path of the selected file
                    File fi = new File(ji.getSelectedFile().getAbsolutePath());

                    try {
                        //place pointer at the starting of file
                        FileWriter fr = new FileWriter(fi);
                        //use bufferwriter to write line by line
                        BufferedWriter br = new BufferedWriter(fr);
                        br.write(t.getText());
                        br.flush();
                        br.close();


                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(f, "operation cancelled");
                }


                break;
            case "print":
                try {
                    t.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "cut":
                t.cut();
                break;
            case "copy":
                t.copy();
                break;
            case "paste":
                t.paste();
                break;
            case "close":
                f.setVisible(false);
                break;
        }
    }

}
public class Main {
    public static void main(String[] args) {
        notepad note = new notepad() ;
    }
}


