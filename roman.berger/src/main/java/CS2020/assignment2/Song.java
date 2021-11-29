package CS2020.assignment2;

import java.util.*;

public class Song
{
    private UUID songID;
    private UUID artistID;
    private String title;
    private int duration;

    public Song()
    {
        //nohting to do yet
    }

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
