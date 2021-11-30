package CS2020.assignment2;

import java.util.*;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.LocalDate;
import java.time.format.*;

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
            String min_sec = "" + songIter.next().getDuration()/60 + ":" + songIter.next().getDuration()%60;
            String fText = "" + songIter.next().getTitle() +"(" + min_sec+ ")";
            // add entry to HashMap
            formattedSongs.put(songIter.next().getSongID(), fText);
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
}
