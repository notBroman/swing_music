package CS2020.assignment2;

import java.util.*;

// GUI imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI
{
    /*
     *  has a custom ActionListener to do something on action taken
     */


    static class MenuActionListener implements ActionListener {
        /*
         *  adapted the example found at:
         *  https://www.tutorialspoint.com/swingexamples/creating_menu_bar.htm
         */

        @Override
        public void actionPerformed(ActionEvent e)
        {
            String event = e.getActionCommand();

            switch(event)
            {
                default:
                    System.out.println("event occured");
            }
        }
    }

    public static void constructGui()
    {
        /*
         *  construct the GUI window as in the instruction
         *
         *  construct a JFrame with title Roman Berger:assignment2
         *
         *  north:
         *  menu bar with options:
         *  - About
         *  - Data
         *  - Export to CSV
         *
         *  center:
         *  East:
         *  - scrollable JList
         *  West:
         *  - Fields with DoB, PlaceOfBirth, BornOnWeekend
         *  - JList of Songs
         *
         *  south:
         *  three buttons
         *  - Add Data Manually
         *  - Add Date From Database
         *  - Delete Selected
         */

        // Create the frame with the title <name>:assignment 2
        JFrame mainFrame = new JFrame("Roman Berger:Assignment 2");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Create the menubar
        JMenuBar menubar1 = new JMenuBar();

        // Create menu items to add to menu
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setPreferredSize(new Dimension(30, 20));
        aboutMenuItem.setActionCommand("About");

        JMenuItem dataMenuItem = new JMenuItem("Data");
        dataMenuItem.setPreferredSize(new Dimension(30, 20));
        dataMenuItem.setActionCommand("Data");

        JMenuItem exportMenuItem = new JMenuItem("Export As CSV");
        exportMenuItem.setPreferredSize(new Dimension(650, 20));
        exportMenuItem.setActionCommand("Export");

        // create and attach actionListeners to the menuItems
        MenuActionListener menuListener = new MenuActionListener();

        aboutMenuItem.addActionListener(menuListener);
        dataMenuItem.addActionListener(menuListener);
        exportMenuItem.addActionListener(menuListener);

        // add MenuItems to menubar
        menubar1.add(aboutMenuItem);
        menubar1.add(dataMenuItem);
        menubar1.add(exportMenuItem);



        mainFrame.setSize(800, 600);
        // settings of menubar
        menubar1.setBorderPainted(true);
        // add menubar to frame
        mainFrame.setJMenuBar(menubar1);
        // make frame visible - last operation
        mainFrame.setVisible(true);


    }
}
