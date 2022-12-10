import java.awt.*;
import javax.swing.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
class  editor extends JFrame implements ActionListener{

    JFrame f;

    JTextArea t;

    editor(){
        //initializing the frame with a title
        f = new JFrame("notepad");

        //setting the overall theme of the application
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch(Exception e){

        }

        //create the text component
        t = new JTextArea();

        //create the menubar
        JMenuBar menu = new JMenuBar();

        //file menu
        JMenu file = new JMenu("File");

        //menuitems for file menu
        JMenuItem m1 = new JMenuItem("New");
        JMenuItem m2 = new JMenuItem("Open");
        JMenuItem m3 = new JMenuItem("Save");
        JMenuItem m4 = new JMenuItem("Print");

        //adding actionlistener to the menuitems
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);

        file.add(m1);
        file.add(m2);
        file.add(m3);
        file.add(m4);

        JMenu edit = new JMenu("Edit");

        JMenuItem m5 = new JMenuItem("Cut");
        JMenuItem m6 = new JMenuItem("Copy");
        JMenuItem m7 = new JMenuItem("Paste");


        //adding actionlistener to the menuitems
        m5.addActionListener(this);
        m6.addActionListener(this);
        m7.addActionListener(this);

        //adding menuitems to edit menu
        edit.add(m5);
        edit.add(m6);
        edit.add(m7);

        //creating the close button
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(this);

        //adding the menus to the menubar
        menu.add(file);
        menu.add(edit);
        menu.add(close);

        //adding the menubar and the textarea to the frame
        f.setJMenuBar(menu);
        f.add(t);
        f.setSize(1600, 900);


        f.show();

    }






    //function for adding the functionality to each of th menuitems
    public void actionPerformed(ActionEvent e){
        //extracting the button pressed
        String s = e.getActionCommand();

        if(s.equals("New")){
            t.setText("");
        }
        else if(s.equals("Open")){

            //initialising the jfliechooser with desired directory
            JFileChooser j = new JFileChooser("D: ");

            //invoking the opendialogbox with an integer
            int r = j.showOpenDialog(null);

            if(r == JFileChooser.APPROVE_OPTION){

                //set the label to the path of the selected file location
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                //string to copy the data from the chosen file

                try {
                    String s1 = "", s2 = "";

                    //store the whole file in fr
                    FileReader fr = new FileReader(fi);
                    //buffers the fr variable character by character so that it can be stored in strings
                    BufferedReader br = new BufferedReader(fr);

                    //storing the first character inside s2
                    s2 = br.readLine();
                    //adding all the lines to  s2 one by one
                    while ((s1 = br.readLine()) != null){
                        s2 = s2 + '\n' + s1;
                    }
                    //setting the textarea to s2
                    t.setText(s2);
                }
                catch(Exception et){
                    JOptionPane.showMessageDialog(f, et.getMessage());
                }
            }



        }
        else if(s.equals("Save")){
            JFileChooser j = new JFileChooser("D: ");

            int r = j.showSaveDialog(null);
            if(r == JFileChooser.APPROVE_OPTION){
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try{
                    FileWriter fw = new FileWriter(fi, false);

                    BufferedWriter bw = new BufferedWriter(fw);

                    bw.write(t.getText());

                    //after writing is finished flush the stream
                    bw.flush();
                    bw.close();

                }
                catch(Exception et){
                    JOptionPane.showMessageDialog(f, et.getMessage());
                }
            }


        }

        else if(s.equals("Print")){
            try {
                t.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }

        else if(s.equals("Cut")){
            t.cut();
        }

        else if(s.equals("Copy")){
            t.copy();
        }

        else if(s.equals("Paste")){
            t.paste();
        }

        else if(s.equals("Close")){
            f.setVisible(false);
        }


    }


    public static void main(String args[]){
        editor e = new editor();
    }
}

