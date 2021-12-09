package CS2020.assignment2;

import java.util.*;
import java.util.function.*;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.LocalDate;
import java.time.format.*;

import javax.swing.*;

import java.sql.*;

public class Utils
{


    public static HashMap<UUID, String> returnSongDurationAndTitleFormatted(ArrayList<Song> songList)
    {
        /*
         *  returns a sorted hashmap with all the songs in the songList
         *  key (UUID): SongId
         *  value (String): title (duration in formt min:sec)
         *
         *  example
         *  key: songId
         *  value: We are the champions (2:36)
         *
         *  uses iterator from ArrayList to add new entries to HashMap
         */

        HashMap<UUID, String> formattedSongs = new HashMap<>();

        Iterator<Song> songIter = songList.iterator();

        while(songIter.hasNext())
        {
            // create formatted String for value from
            // songIter.getTitle() and songIter.getDuration()
            // songIter.getDuration()/60 gives minutes, because int/int = int in java
            BiFunction<String, Integer, String> formatted = (title, duration) -> "" + title + "(" + duration/60 + ":" + duration%60 + ")";
            // add entry to HashMap
            Song a = songIter.next();
            formattedSongs.put(a.getSongID(), formatted.apply(a.getTitle(), a.getDuration()));
        }

        return formattedSongs;
    }

    public static boolean checkIfBornOnWeekend(String dob)
    {
        /*
         * a function that determines if a date is on a weekend
         *
         * uses the time module of the java SDK
         */
        if(dob.length() < 11)
        {
            dob = "0" + dob;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate birthday = LocalDate.parse(dob, formatter);
        DayOfWeek day = DayOfWeek.of(birthday.get(ChronoField.DAY_OF_WEEK));

        switch(day)
        {
            case SATURDAY:
                return true;

            case SUNDAY:
                return true;

            default:
                return false;
        }
    }

    public static void createExampleArtists(JList<Artist> list)
    {
        /*
         *  create two artists MacMiller and Joji and
         *  add two songs each to their list of songs
         */
        Artist MacMiller = new Artist();

        MacMiller.setFirstName("Malcom James");
        MacMiller.setLastName("McCormick*");
        MacMiller.setDob("19 Jan 1992");
        MacMiller.setPlaceOfBirth("Pittsburgh, Pennsylvania");

        ArrayList<Song> Circles = new ArrayList();
        Circles.add(new Song(MacMiller.getArtistID(), "Circles", 170));
        Circles.add(new Song(MacMiller.getArtistID(), "Comlicated", 232));
        Circles.add(new Song(MacMiller.getArtistID(), "Blue World", 209));

        MacMiller.setSongs(Circles);

        // #---------------------------------------------------------------#

        Artist Joji = new Artist();

        Joji.setFirstName("George Kusunoki");
        Joji.setLastName("Miller*");
        Joji.setDob("18 Sep 1992");
        Joji.setPlaceOfBirth("Osaka, Japan");

        ArrayList<Song> Nectar = new ArrayList();
        Nectar.add(new Song(Joji.getArtistID(), "Ew", 207));
        Nectar.add(new Song(Joji.getArtistID(), "Modus", 207));
        Nectar.add(new Song(Joji.getArtistID(), "Tick Tock", 132));
        Nectar.add(new Song(Joji.getArtistID(), "Dayligth (with Diplo)", 163));
        Nectar.add(new Song(Joji.getArtistID(), "Upgrade", 89));
        Nectar.add(new Song(Joji.getArtistID(), "Gimme Love", 214));
        Nectar.add(new Song(Joji.getArtistID(), "Run", 195));
        Nectar.add(new Song(Joji.getArtistID(), "Sanctuary", 180));
        Nectar.add(new Song(Joji.getArtistID(), "High Hopes feat. Omar Appollo", 182));
        Nectar.add(new Song(Joji.getArtistID(), "Nitrous", 131));
        Nectar.add(new Song(Joji.getArtistID(), "Pretty Boy feat. Lil Yachty", 156));
        Nectar.add(new Song(Joji.getArtistID(), "Normal People feat. Rei Brown", 166));
        Nectar.add(new Song(Joji.getArtistID(), "Afterthought feat. Benee", 194));
        Nectar.add(new Song(Joji.getArtistID(), "777", 181));
        Nectar.add(new Song(Joji.getArtistID(), "Reanimator feat. Yves Tumor", 183));
        Nectar.add(new Song(Joji.getArtistID(), "Like You Do", 240));
        Nectar.add(new Song(Joji.getArtistID(), "Your Man", 163));

        Joji.setSongs(Nectar);

        DefaultListModel<Artist> l1 =(DefaultListModel) list.getModel();
        l1.addElement(MacMiller);
        l1.addElement(Joji);
        list.setModel(l1);
        // list.setBounds(100, 100, 75, 75);

    }

    public static Connection connectToDatabase()
    {
        /*
         *  a function that connects to the database in the resources folder
         *  sqlite-jdbc maintained by xerial
         */
        Connection dbConnect = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            dbConnect = DriverManager.getConnection("jdbc:sqlite:/home/notbroman/dev/java/uni/swing_music/roman.berger/resources/CS2020-assignment2.db");
            System.out.println("Connection Established");
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        return dbConnect;
    }

    public static void readArtistsAndSongsFromDatabase(JList<Artist> list)
    {
       // reads artists and songs from db
       // creates necessary objects
       // split name into fName and lName

        Connection dbConnectArtist = null;
        Connection dbConnectSong = null;
        Statement stmntArtist = null;
        Statement stmntSong = null;
        ResultSet artistResultSet = null;
        ResultSet songResultSet = null;
        // Statement stmnt = null;

        String artistQuery = "SELECT artistID, name, placeOfBirth, dob FROM Artist;";

        DefaultListModel<Artist> l1 = (DefaultListModel) list.getModel();

        try
        {
            dbConnectArtist = Utils.connectToDatabase();
            dbConnectSong = Utils.connectToDatabase();
            stmntArtist = dbConnectArtist.createStatement();
            stmntSong = dbConnectSong.createStatement();
            artistResultSet = stmntArtist.executeQuery(artistQuery);
            ArrayList<Artist> artistList = new ArrayList<>();
            // artistResultSet.next();

            while(artistResultSet.next())
            {
                // create artist and songs for artists
                String a = artistResultSet.getString("artistID").strip();
                // System.out.print("RowNo:" + artistResultSet.getRow() + "\t |");
                // System.out.println(a + "\t | length: " + a.length());

                Artist artist1 = new Artist(UUID.fromString(a));
                // get full name and split into fName and lName
                // ignore middle name
                String name = artistResultSet.getString("name");
                String[] splitName = name.split("\\s+");
                artist1.setFirstName(splitName[0]);
                artist1.setLastName(splitName[splitName.length - 1]);

                // set date of birth and place of birth
                artist1.setDob(artistResultSet.getString("dob"));
                artist1.setPlaceOfBirth(artistResultSet.getString("placeOfBirth"));

                // get resultset of artists songs
                String songQuery = "SELECT songID, artistID, title, duration FROM Song WHERE artistID = '"
                    + artistResultSet.getString("artistID") + "';";
                songResultSet = stmntSong.executeQuery(songQuery);

                // create ArrayList of Song and add songs to it
                ArrayList<Song> songList = new ArrayList<>();

                while(songResultSet.next())
                {
                    Song song1 = new Song();
                    song1.setSongID(UUID.fromString(songResultSet.getString("songID").strip()));
                    song1.setArtistID(UUID.fromString(artistResultSet.getString("artistID")));
                    String title = songResultSet.getString("title");
                    title.replace("\r\n?|\n", "");
                    song1.setTitle(songResultSet.getString("title").replace("\n", ""));
                    song1.setDuration(songResultSet.getInt("duration"));

                    songList.add(song1);
                }

                artist1.setSongs(songList);

                artistList.add(artist1);
                //System.out.println(artistResultSet.isClosed());
            }
            for(Artist a : artistList)
            {
                l1.addElement(a);

            }
            list.setModel(l1);
            // list.setBounds(100, 100, 75, 75);
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
           try
           {
                if(dbConnectArtist != null)
                {
                    dbConnectArtist.close();
                }
                if(dbConnectSong != null)
                {
                    dbConnectSong.close();
                }
           }
           catch(Exception e)
           {
               System.err.println(e.getMessage());
           }
        }
    }
}
