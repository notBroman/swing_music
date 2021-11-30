package CS2020.assignment2;

import java.util.*;

public class Utils
{
    public static HashMap<Song> returnSongDurationAndTitleformatted(ArrayList<Song> songList)
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
            String min_sec = "" + songIter.getDuration()/60 + ":" + songIter.getDuration()%60;
            String fText = "" + SongIter.getTitle() +"(" + min_sec+ ")";
            // add entry to HashMap
            formattedSongs.put(songIter.getSongID, fText);
        }

        return formattedSongs;
    }
}
