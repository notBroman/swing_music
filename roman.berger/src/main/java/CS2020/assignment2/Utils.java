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
    /**
     *  @author Roman Berger
     *  @version v1.0.0
     *
     *  A class of utility functions used throughout the implementation of the assessment
     *
     */

    /**
     *  @param
     *  takes an arrayList of Songs
     *
     *  @return
     *  this function returns a Hasmap where the key is the UUID of the song and the value
     *  is a string stating the title of the song and the duration
     *
     *  @see
     *  Song.java for the implementation of the Song type
     *  <https://docs.oracle.com/javase/7/docs/api/java/util/UUID.html>
     *  for the documentation of the UUID class
     */
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


    /**
     *  @param
     *  takes a string describing a date
     *  the format of the date has to be DD-MMM-YYYY
     *
     *  @return
     *  returns a boolean if the specified day was on a weekend
     *  yes: true
     *  no: false
     *
     */
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

    /**
     *  @param
     *  takes a java swing JList and adds example artists and sorts them in alphabetical order of their
     *  first names
     *
     *  the artist added are Joji and MacMiller
     *  Jojis songs are from the album Nectar
     *  MacMillers songs are from the album Cirlces
     *
     *  @return
     *  this function returns nothing it just changes the contents of the given parameter
     *
     *  @see
     *  the JList documentation
     *  <https://docs.oracle.com/javase/7/docs/api/javax/swing/JList.html>
     *
     */
    public static void createExampleArtists(JList<Artist> list)
    {
        /*
         *  create two artists MacMiller and Joji and
         *  add two songs each to their list of songs
         */
        Artist MacMiller = new Artist();

        MacMiller.setFirstName("Mac");
        MacMiller.setLastName("Miller*");
        MacMiller.setDob("19 Jan 1992");
        MacMiller.setPlaceOfBirth("Pittsburgh, Pennsylvania");

        ArrayList<Song> Circles = new ArrayList();
        Circles.add(new Song(MacMiller.getArtistID(), "Circles", 170));
        Circles.add(new Song(MacMiller.getArtistID(), "Comlicated", 232));
        Circles.add(new Song(MacMiller.getArtistID(), "Blue World", 209));

        MacMiller.setSongs(Circles);

        // #---------------------------------------------------------------#

        Artist Joji = new Artist();

        Joji.setFirstName("Joji");
        Joji.setLastName("Joji*");
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
        ArrayList<Artist> artistList = new ArrayList<>();

        while(!l1.isEmpty())
        {
            artistList.add(l1.firstElement());
            l1.removeElement(l1.firstElement());
        }
        artistList.add(Joji);
        artistList.add(MacMiller);
        Utils.selSortArtists(artistList);

        for(Artist artist : artistList)
        {
            l1.addElement(artist);
        }

        list.setModel(l1);
        // list.setBounds(100, 100, 75, 75);

    }

    /**
     *  @param
     *  this function takes no parameters
     *
     *  @return
     *  it returns a connection to the specified database (sqlite)
     *  the library used is maintained by Xerial
     *
     *  @see
     *  the github page of the package:
     *  <https://github.com/xerial/sqlite-jdbc>
     *
     */
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

    /**
     *  @param
     *  takes a JList of Artists
     *
     *  @return
     *  nothing
     *
     *  the function uses the function connectToDatabase to to connect to the database and read the
     *  sqlite database provided in the resources
     *
     *  2 seperate connections must be opened for each table that is to be accessed one
     *
     *  the added artists are again ordered in alphabetical order of ther first names
     *
     */
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

            while(!l1.isEmpty())
            {
                artistList.add(l1.firstElement());
                l1.removeElement(l1.firstElement());
            }
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
            Utils.selSortArtists(artistList);
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

    /**
     *  @param
     *  an arraylist of artists that is to be sorted
     *
     *  @return
     *  nothing
     *
     *  the function implements the selection sort algorithm
     *
     *  @see
     *  a description of the sorting algorithm is given below
     *  time complexity and space complexity can be read from there as well
     *  <https://en.wikipedia.org/wiki/Selection_sort>
     *
     */
    public static void selSortArtists(ArrayList<Artist> artistList)
    {
        int start = 0;
        int end = artistList.size() - 1;
        String artist1 = "";
        String artist2 = "";
        for(int i = start; i <= end; i++)
        {
            int minIdx = i;
            for(int j = i + 1; j <= end; j++)
            {
                artist1 = artistList.get(minIdx).getFirstName();
                artist2 = artistList.get(j).getFirstName();
                if(artist1.compareToIgnoreCase(artist2) > 0)
                {
                    minIdx = j;
                }
            }
            Artist buffer = artistList.get(i);
            artistList.set(i, artistList.get(minIdx));
            artistList.set(minIdx, buffer);
        }
    }
}
