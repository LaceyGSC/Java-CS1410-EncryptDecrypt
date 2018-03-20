/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package decyrptingfinalltaylor;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Lacey
 */
public class KeepinItEasyGui extends JFrame implements ActionListener
{
//Attributes

    private File infile, outfile;
    private String theKey;
    private JTextField filepath;
    private JPanel toppart, nottop;
    private JFileChooser choice;
    private JButton pickfile, encrypt, decrypt;
    private char[] keya;
    private DataOutputStream dos;

//Constructors
    public KeepinItEasyGui(String title)
    {
        super(title);
        choice = new JFileChooser();


        toppart = new JPanel();
        pickfile = new JButton("Choose Original File");
        filepath = new JTextField();

        nottop = new JPanel();
        encrypt = new JButton("Encrypt");
        decrypt = new JButton("Decrypt");


        this.setSize(300, 200);
        this.setLocation(400, 100);
        this.setLayout(new GridLayout(2, 1));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        pickfile.setSize(50, 60);

        filepath.setPreferredSize(new Dimension(200, 30));

        toppart.add(pickfile);
        toppart.add(filepath);

        nottop.add(encrypt);
        nottop.add(decrypt);


        pickfile.addActionListener(this);
        encrypt.addActionListener(new Readit());
        decrypt.addActionListener(new UndoIt());

        this.add(toppart);
        this.add(nottop);


    }

    private void reverseStream(File file) throws IOException
    {
        dos = new DataOutputStream(new FileOutputStream(file.getPath()));
        InputFile list;
        list = new InputFile(infile.getPath());
        int countdown = 0;
        int what;

        while (!list.eof())
        {
            while (countdown < keya.length)
            {
                what = list.readChar();
                dos.write(((char)what - (char)keya[countdown]));
                System.out.println(((char)what - (char)keya[countdown]));
                countdown++;
            }
            countdown = 0;
        }

        dos.close();
        
        infile = null;
        filepath.setText("");

        System.out.println("Undid it");

    }

    private void createStream(File file) throws IOException
    {

//        dos = new DataOutputStream(new FileOutputStream(file.getPath()));
//        InputFile list;
//        list = new InputFile(infile.getPath());
//        int countdown = 0;
//        int what;
//
//        while (!list.eof())
//        {
//            while (countdown < keya.length)
//            {
//                what = (char)list.readChar();
//                System.out.println(what);
//                System.out.println(keya[countdown]);
//                System.out.println("");
//                //dos.write((char)what + (char) keya[countdown]);
//                countdown++;
//            }
//            countdown = 0;
//        }
//        dos.close();
//        
//        infile = null;
//        filepath.setText("");
//
//        System.out.println("Saved it");
        
        Tryout trial;
        trial = new Tryout("asdfasdf");
        trial.setVisible(true);

    }
    

//Constructors

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (choice == null)
        {
            choice = new JFileChooser("$HOME");
        }
        int returnVal = choice.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            filepath.setText(choice.getSelectedFile().getPath());
            infile = choice.getSelectedFile();
            theKey = JOptionPane.showInputDialog("Enter KeyPhrase");
            keya = new char[theKey.length()];
            keya = theKey.toCharArray();
        }
        else
        {
            filepath.setText("");
        }
    }

    private class Readit implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (infile != null)
            {
                if (choice == null)
                {
                    choice = new JFileChooser("$HOME");
                }
                int returnVal = choice.showSaveDialog(nottop);

                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    outfile = choice.getSelectedFile();
                    try
                    {
                        createStream(outfile);
                    }
                    catch (IOException w)
                    {
                        System.out.println("Went Wrong");
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Must Choose a File");

                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Must Choose a File");
            }
        }
    }

    private class UndoIt implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (infile != null)
            {
                if (choice == null)
                {
                    choice = new JFileChooser("$HOME");
                }
                int returnVal = choice.showSaveDialog(nottop);

                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    outfile = choice.getSelectedFile();
                    try
                    {
                        reverseStream(outfile);
                    }
                    catch (IOException w)
                    {
                        System.out.println("Went Wrong");
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Must Choose a File");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Must Choose a File");
            }
        }
    }
}
