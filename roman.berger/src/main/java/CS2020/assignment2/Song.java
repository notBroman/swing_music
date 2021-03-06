package CS2020.assignment2;

import java.util.*;

public class Song
{
    /**
     *  the fields are:
     *  UUID of the song
     *  UUID of the artist
     *  title of the song as String
     *  duration of the song in seconds
     *
     */
    private UUID songID;
    private UUID artistID;
    private String title;
    private int duration;

    /**
     *  Constructor 1
     *
     *  @param
     *  none
     *
     *  @return
     *  an instance of the Song class with none of its fields initalized
     *
     */
    public Song()
    {
        // do nothing, just create the object
    }

    /**
     *  Constructor2
     *
     *  @param
     *  artistID as UUID
     *  title as String
     *  duration as int in seconds
     *  the SongID is generated by the function
     *
     *  @return
     *  an instance of the Song class with all its fields initalized
     *
     */
    public Song(UUID artistID, String title, int duration)
    {
        this.setSongID(UUID.randomUUID());
        this.setArtistID(artistID);
        this.setTitle(title);
        this.setDuration(duration);
    }

    /**
     *  setters for all the fields
     *
     *  @param
     *  takes the field as parameter
     *
     *  @return
     *  nothing
     *
     *  changes the value of the corresponding field
     *
     */
    public void setSongID(UUID songID)
    {
        this.songID = songID;
    }

    public void setArtistID(UUID artistID)
    {
        this.artistID = artistID;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    /**
     *  getters of all the classes fields
     *
     *  @param
     *  no parameters, but needs to be invoked on an instance of the class
     *
     *  @return
     *  returns the value of the corresponding field
     */
    public UUID getSongID()
    {
        return this.songID;
    }

    public UUID getArtistID()
    {
        return this.artistID;
    }

    public String getTitle()
    {
        return this.title;
    }

    public int getDuration()
    {
        return this.duration;
    }

}
