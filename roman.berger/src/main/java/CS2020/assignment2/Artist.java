package CS2020.assignment2;

import java.util.*;

public class Artist
{
    private UUID artistID;
    private String firstName;
    private String lastName;
    private String dob;
    private String placeOfBirth;
    private ArrayList<Song> songs = new ArrayList<>();

    public static Artist()
    {
        // prevent collisions when creating new UUID
        this.UUID = UUID.randomUUID();
    }

    public static void setArtistID(UUID artistID)
    {
        this.artistID = artistID;
    }

    public static void setFirstName(String fName)
    {
        this.firstName = fName;
    }

    public static void setLastName(String lName)
    {
        this.lastName = lName;
    }

    public static void setDob(String dob)
    {
        this.dob = dob;
    }

    public static void setPlaceOfBirth(String placeOfBirth)
    {
        this.placeOfBirth = placeOfBirth;
    }

    public static void setSongs(ArrayList<Song> songList)
    {
        this.songs = songList;
    }

    public static UUID getArtistID()
    {
        return this.artistID;
    }

    public static String getFirstName()
    {
        return this.firstName;
    }

    public static String getLastName()
    {
        return this.lastName;
    }

    public static String getDob()
    {
        return this.dob;
    }

    public static String getPlaceOfBirth()
    {
        return this.placeOfBirth;
    }

    public static ArrayList<Song> getSongs()
    {
        return this.songs;
    }

}
