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
    public static HashMap<UUID, String> returnSongDurationAndTitleformatted(ArrayList<Song> songList)
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
            formattedSongs.put(songIter.next().getSongID(), formatted.apply(songIter.next().getTitle(), songIter.next().getDuration()));
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
        MacMiller.setLastName("McCormick");
        MacMiller.setDob("1992 Jan 19");
        MacMiller.setPlaceOfBirth("Pittsburgh, Pennsylvania");

        ArrayList<Song> Circles = new ArrayList();
        Circles.add(new Song(MacMiller.getArtistID(), "Circles", 170));
        Circles.add(new Song(MacMiller.getArtistID(), "Comlicated", 232));
        Circles.add(new Song(MacMiller.getArtistID(), "Blue World", 209));

        MacMiller.setSongs(Circles);

        // #---------------------------------------------------------------#

        Artist Joji = new Artist();

        Joji.setFirstName("George Kusunoki");
        Joji.setLastName("Miller");
        Joji.setDob("1992 Sep 18");
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
        list.setBounds(100, 100, 75, 75);

    }

    public void connectToDatabase()
    {
        /*
         *  a function that connects to the database in the resources folder
         *  sqlite-jdbc maintained by xerial
         */
        Connection dbConnect = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:/CS2020-assignment2.db");
        }
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
        }
    }
}
