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
    private JFrame mainFrame = new JFrame("Roman Berger:Assessment 2");


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
                case "About":
                    // create new frame for About popup
                    JFrame aboutFrame = new JFrame("About");
                    aboutFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    aboutFrame.setLayout(new BorderLayout());

                    Icon javaIcon = new ImageIcon( this.getClass().getResource( "/java_logo.jpg" ));
                    JLabel appInfo = new JLabel("Assignment 2 App v1.0.0", javaIcon, SwingConstants.LEFT);
                    JButton okButton = new JButton("Ok");
                    okButton.setActionCommand("OkButton");
                    okButton.addActionListener(new MenuActionListener());


                    aboutFrame.getContentPane().add(appInfo, BorderLayout.NORTH);
                    aboutFrame.getContentPane().add(okButton, BorderLayout.EAST);

                    // good size may be (300, 200)
                    aboutFrame.pack();
                    aboutFrame.setVisible(true);
                    break;

                case "manualData":
                    // find the JList<Artist> in the scrollpane
                    // addExampleArtists
                    // disable button afterwards
                    JComponent comp1 = (JComponent) e.getSource();
                    JFrame frm1 = (JFrame) SwingUtilities.getWindowAncestor(comp1);
                    BorderLayout bLayout = (BorderLayout) frm1.getContentPane().getLayout();
                    JScrollPane scrollPane = (JScrollPane) bLayout.getLayoutComponent(BorderLayout.CENTER);
                    JList<Artist> artistList = (JList) scrollPane.getViewport().getView();
                    Utils.createExampleArtists(artistList);
                    comp1.setEnabled(false);
                    break;

                case "databaseData":
                    // add data from database
                    JComponent comp2 = (JComponent) e.getSource();
                    JFrame frm2 = (JFrame) SwingUtilities.getWindowAncestor(comp2);
                    BorderLayout bLayout2 = (BorderLayout) frm2.getContentPane().getLayout();
                    JScrollPane scrollPane2 = (JScrollPane) bLayout2.getLayoutComponent(BorderLayout.CENTER);
                    JList<Artist> artistList2 = (JList) scrollPane2.getViewport().getView();
                    Utils.readArtistsAndSongsFromDatabase(artistList2);
                    comp2.setEnabled(false);
                    break;

                case "deleteData":
                    // delete selected data from list
                    break;

                case "OkButton":
                    // get frame in which button resides
                    // close frame
                    JComponent comp = (JComponent) e.getSource();
                    JFrame frm = (JFrame) SwingUtilities.getWindowAncestor(comp);
                    frm.dispose();
                    break;

                default:
                    System.out.println("event occured");
                    break;
            }
        }
    }

    public class ArtistListCellRenderer extends DefaultListCellRenderer
    {
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if(value instanceof Artist)
            {
                Artist artist = (Artist) value;
                setText("" + artist.getFirstName() + " " + artist.getLastName());
                // no tooltip
                // no icon
            }
            return this;
        }
    }

    public void constructGui()
    {
        /*
         *  construct the GUI window as in the instruction
         *
         *  construct a JFrame with title Roman Berger:assignment2
         *  using BorderLayout
         *
         *  north:
         *  menu bar with options:
         *  - About
         *  - Data
         *  - Export to CSV
         *
         *  West:
         *  - scrollable JList
         *
         *  East:
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
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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


        // create JList to add artists to
        // create JScrollPane with JList as Viewport
        DefaultListModel model = new DefaultListModel();
        JList<Artist> artistList = new JList<>(model);
        artistList.setCellRenderer(new ArtistListCellRenderer());
        artistList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane artistPane = new JScrollPane(artistList);
        artistPane.setVerticalScrollBar(artistPane.createVerticalScrollBar());
        artistPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // create JPanel to add 3 buttons to
        // attach to SOUTH of frame
        JPanel southPane = new JPanel();
        JButton manualData = new JButton("Add Data Manually");
        manualData.setActionCommand("manualData");
        JButton dbData = new JButton("Add Data From Database");
        dbData.setActionCommand("databaseData");
        JButton delData = new JButton("Delete Selected");
        delData.setActionCommand("deleteData");

        // add ActionListeners to buttons
        manualData.addActionListener(menuListener);
        dbData.addActionListener(menuListener);
        delData.addActionListener(menuListener);

        southPane.add(manualData, BorderLayout.EAST);
        southPane.add(dbData, BorderLayout.CENTER);
        southPane.add(delData, BorderLayout.WEST);

        // create JPanel to add JPanel and textArea to
        // attach to EAST of frame
        JPanel eastPane = new JPanel();
        eastPane.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea("Test", 10, 20);
        JScrollPane scrollText = new JScrollPane(textArea);

        // create JPanel for the the data fields above the TextField
        JPanel dataPane = new JPanel();
        dataPane.setLayout(new GridLayout(3, 2, 10, 5));

        JLabel dobLabel = new JLabel("Date of Birth:");
        JLabel pobLabel = new JLabel("Place of Birth:");
        JLabel weekendLabel = new JLabel("Born on Weekend:");

        JTextField dobField = new JTextField(6);
        JTextField pobField = new JTextField(6);
        JTextField weekendField = new JTextField(6);

        //fill JPanel for data
        dataPane.add(dobLabel);
        dataPane.add(dobField);
        dataPane.add(pobLabel);
        dataPane.add(pobField);
        dataPane.add(weekendLabel);
        dataPane.add(weekendField);

        // add dataPanel and TextField to Panel
        eastPane.add(dataPane, BorderLayout.NORTH);
        eastPane.add(scrollText, BorderLayout.CENTER);

        this.mainFrame.setSize(800, 600);
        // settings of menubar
        menubar1.setBorderPainted(true);
        // add components to BorderLayout
        this.mainFrame.getContentPane().add(menubar1, BorderLayout.NORTH);
        this.mainFrame.getContentPane().add(artistPane, BorderLayout.CENTER);
        this.mainFrame.getContentPane().add(eastPane, BorderLayout.EAST);
        this.mainFrame.getContentPane().add(southPane, BorderLayout.SOUTH);
        // make frame visible - last operation
        this.mainFrame.setVisible(true);


    }
}
