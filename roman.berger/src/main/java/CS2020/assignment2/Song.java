package CS2020.assignment2;

public class Song
{
    private UUID songID;
    private UUID artistID;
    private String title;
    private int duration;

    public static Song()
    {
        //nohting to do yet
    }

    public static void setSongID(UUID songID)
    {
        this.songID = songID;
    }

    public static void setArtistID(UUID artistID)
    {
        this.artistID = artistID;
    }

    public static void setTitle(String title)
    {
        this.title = title;
    }

    public static void setDuration(int duration)
    {
        this.duration = duration;
    }

    public static UUID getSongID()
    {
        return this.songID;
    }

    public static UUID getArtistID()
    {
        return this.artistID;
    }

    public static String getTitle()
    {
        return this.title;
    }

    public static int getDuration()
    {
        return this.duration;
    }

}
