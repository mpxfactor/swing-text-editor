package com.mpxfactor;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextEditor extends JFrame implements ActionListener {

    JTextArea textArea;

    JScrollPane scrollPane;

    JSpinner fontSizeSpinner;

    JLabel fontLabel;

    JButton setForegroundColorButton;
    JButton setBackgroundColorButton;
    JButton setCaretColorButton;

    JButton setAllToDefaultButton;

    JComboBox fontBox;

    JPanel buttonsPanel;

    JPanel fontPanel;

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem openItem;
    JMenuItem saveItem;
    JMenuItem exitItem;

    TextEditor(){
        this.setTitle("Simple Java Text Editor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10,10));
        this.setSize(800,700);
        this.setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 25));

        //default colors
        textArea.setBackground(new Color(255,255,255));
        textArea.setForeground(new Color(0));
        textArea.setCaretColor(new Color(0));

        textArea.setCursor(new Cursor(Cursor.TEXT_CURSOR));
//        textArea.setOpaque(true);

        /*--------------------------------------------------------------------------------------------------------------------*/
        /*-----------------------FONT COLOR-----------------------------*/
        setForegroundColorButton = new JButton("Font Color");
        setForegroundColorButton.setForeground(new Color(0x454040));
        setForegroundColorButton.setFont(new Font(null,Font.BOLD, 15));
        setForegroundColorButton.addActionListener(this);
        setForegroundColorButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        /*----------------------------------------------------*/

        /*------------------------BACKGROUND COLOR----------------------------*/
        setBackgroundColorButton = new JButton("Background Color");
        setBackgroundColorButton.setForeground(new Color(0x454040));
        setBackgroundColorButton.setFont(new Font(null,Font.BOLD, 15));
        setBackgroundColorButton.addActionListener(this);
        setBackgroundColorButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        /*----------------------------------------------------*/

        /*------------------------CARET COLOR----------------------------*/
        setCaretColorButton = new JButton("Caret Color");
        setCaretColorButton.setForeground(new Color(0x454040));
        setCaretColorButton.setFont(new Font(null,Font.BOLD, 15));
        setCaretColorButton.addActionListener(this);
        setCaretColorButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        /*----------------------------------------------------*/

        /*------------------------DEFAULT BUTTON----------------------------*/
        setAllToDefaultButton = new JButton("DEFAULT");
        setAllToDefaultButton.setForeground(new Color(0x454040));
        setAllToDefaultButton.setFont(new Font(null,Font.BOLD, 15));
        setAllToDefaultButton.addActionListener(this);
        setAllToDefaultButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        /*----------------------------------------------------*/

        /*--------------------------------------------------------------------------------------------------------------------*/

        /*---------------Font Panel--------------------*/
        fontPanel = new JPanel();
        fontPanel.setLayout(new BorderLayout(5,10));

        scrollPane = new JScrollPane(textArea);
        scrollPane.setSize(500,500);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        fontLabel = new JLabel("Font : ");
        fontLabel.setForeground(new Color(0x454040));
        fontLabel.setFont(new Font(null,Font.BOLD, 10));

        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(new Dimension(50, 15));
        fontSizeSpinner.setForeground(new Color(0x454040));
        fontSizeSpinner.setFont(new Font(null,Font.BOLD, 15));
        fontSizeSpinner.setValue(25);
        fontSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getFamily(), textArea.getFont().getStyle(), (int) fontSizeSpinner.getValue()));
            }
        });

        fontPanel.add(fontLabel, BorderLayout.WEST);
        fontPanel.add(fontSizeSpinner);

        /*---------------Font Panel--------------------*/

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontBox = new JComboBox(fonts);
        fontBox.setSelectedItem("Arial");
        fontBox.setEditable(true);
        fontBox.setFont(new Font(null,Font.BOLD, 15));
        fontBox.addActionListener(this);

        /*----buttonsPanel----*/
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,1, 10,10));

        buttonsPanel.add(fontPanel);

        buttonsPanel.add(fontBox);

        buttonsPanel.add(setForegroundColorButton);
        buttonsPanel.add(setBackgroundColorButton);
        buttonsPanel.add(setCaretColorButton);
        buttonsPanel.add(setAllToDefaultButton);
        /*----buttonsPanel----*/

        /*--------------------------------------------------------------------------------------------------------------------*/
        /*-------------------------------------------------------------*/
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        /*-------------------------------------------------------------*/

        /*--------------------------------------------------------------------------------------------------------------------*/

        this.setJMenuBar(menuBar);
        this.add(buttonsPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JColorChooser colorChooser = new JColorChooser();
        if (e.getSource() == setForegroundColorButton) {
            Color color = colorChooser.showDialog(null, "Choose a color for fonts", new Color(0));
            textArea.setForeground(color);
            setForegroundColorButton.setBackground(color);
        }
        if(e.getSource() == setBackgroundColorButton){
            Color color = colorChooser.showDialog(null, "Choose a color for background", new Color(255,255,255));
            textArea.setBackground(color);
            setBackgroundColorButton.setBackground(color);
        }
        if(e.getSource() == setCaretColorButton){
            Color color = colorChooser.showDialog(null, "Choose a color for caret", new Color(0));
            textArea.setCaretColor(color);
            setCaretColorButton.setBackground(color);

        }
        if(e.getSource() == setAllToDefaultButton){
            setBackgroundColorButton.setBackground(null);
            setForegroundColorButton.setBackground(null);
            setCaretColorButton.setBackground(null);

            textArea.setBackground(new Color(255,255,255));
            textArea.setForeground(new Color(0));
            textArea.setCaretColor(new Color(0));
            textArea.setFont(new Font("Arial", Font.PLAIN, 25));
            fontSizeSpinner.setValue(25);
            fontBox.setSelectedItem("Arial");
        }

        if(e.getSource() == fontBox){
            textArea.setFont(new Font((String)fontBox.getSelectedItem(), Font.PLAIN, (int) fontSizeSpinner.getValue()));
        }

        if(e.getSource() == openItem){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("/home/mpxfactor"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            fileChooser.setFileFilter(filter);

            int response = fileChooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                Scanner fileIn = null;

                try {
                    fileIn = new Scanner(file);
                    if(file.isFile()){
                        while(fileIn.hasNextLine()){
                            String line = fileIn.nextLine() + "\n";
                            textArea.append(line);
                        }
                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            }

        }

        if(e.getSource() == saveItem){
            JFileChooser fileChooser= new JFileChooser();
            fileChooser.setCurrentDirectory(new File("/home/mpxfactor"));

            int response = fileChooser.showSaveDialog(null);

            if(response == JFileChooser.APPROVE_OPTION){
                File file;
                PrintWriter fileOut = null;

                file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try {
                    fileOut = new PrintWriter(file);
                    fileOut.println(textArea.getText());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } finally {
                    fileOut.close();
                }
            }


        }

        if(e.getSource() == exitItem){
            this.setVisible(false);
            this.dispose();
        }
    }

}
