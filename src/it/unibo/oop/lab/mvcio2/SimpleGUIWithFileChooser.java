package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame("SimpleGUIWithFileChooser");

    public SimpleGUIWithFileChooser(final Controller ctrl) {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);

        final JPanel mainPanel = new JPanel();
        final JTextArea textArea = new JTextArea();
        final JButton save = new JButton("Save");
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(textArea, BorderLayout.CENTER);
        mainPanel.add(save, BorderLayout.SOUTH);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                   ctrl.writeOnFile(textArea.getText());
            }
        });
        /*
         * 1) Add a JTextField and a button "Browse..." on the upper part of the
         * graphical interface.
         * Suggestion: use a second JPanel with a second BorderLayout, put the panel
         * in the North of the main panel, put the text field in the center of the
         * new panel and put the button in the line_end of the new panel.
         * 
         * 2) The JTextField should be non modifiable. And, should display the
         * current selected file.
         */
        final JTextField textField = new JTextField(ctrl.getPath());
        textField.setEditable(false);
        final JButton browse = new JButton("Browse...");
        final JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new BorderLayout());
        mainPanel.add(secondPanel, BorderLayout.NORTH);
        secondPanel.add(textField, BorderLayout.CENTER);
        secondPanel.add(browse, BorderLayout.LINE_END);
        /* 
         * 3) On press, the button should open a JFileChooser. The program should
         * use the method showSaveDialog() to display the file chooser, and if the
         * result is equal to JFileChooser.APPROVE_OPTION the program should set as
         * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
         * then the program should do nothing. Otherwise, a message dialog should be
         * shown telling the user that an error has occurred (use
         * JOptionPane.showMessageDialog()).
         * 
         * * 4) When in the controller a new File is set, also the graphical interface
         * must reflect such change. Suggestion: do not force the controller to
         * update the UI: in this example the UI knows when should be updated, so
         * try to keep things separated.
         */
        browse.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                final JFileChooser fileChooser = new JFileChooser("Choose where to save the file");
                fileChooser.setSelectedFile(ctrl.getCurrentFile());
                final int result = fileChooser.showSaveDialog(frame);
                switch (result) {
                case JFileChooser.APPROVE_OPTION: 
                    ctrl.setCurrentFile(fileChooser.getSelectedFile());
                    textField.setText(fileChooser.getSelectedFile().getPath());
                    break;
                case JFileChooser.CANCEL_OPTION:
                    break;
                default: 
                    JOptionPane.showMessageDialog(frame, result, "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    private void display() {
        frame.setVisible(true);
    }
    public static void main(final String... args) {
        new SimpleGUIWithFileChooser(new Controller()).display();
    }
}

