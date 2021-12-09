package CS2020.assignment2;

import java.util.*;

public class Artist
{
    /**
     *  @author Roman Berger
     *
     *  the fields are:
     *  artistID as UUID
     *  firstName as String
     *  lastname as String
     *  dateOfBirth (dob) as String in format "DD MMM YYYY"
     *  placeOfBirth as String
     *  songs as an arraylist of type song objects
     *
     */
    private UUID artistID;
    private String firstName;
    private String lastName;
    private String dob;
    private String placeOfBirth;
    private ArrayList<Song> songs = new ArrayList<>();

    /**
     *  Constructor 1
     *
     *  @param
     *  none
     *
     *  @return
     *  an instance of the Artist class with a random ArtistID
     *
     */
    public Artist()
    {
        // prevent collisions when creating new UUID
        this.artistID = UUID.randomUUID();
    }

    /**
     *  Constructor 2
     *
     *  @param
     *  none
     *
     *  @return
     *  an instance of the Artist class with a specified ArtistID
     *
     */
    public Artist(UUID artistID)
    {
        this.artistID = artistID;
    }

    /**
     *  setters for all fields
     *
     *  @param
     *  take value of field as parameter
     *
     *  @return
     *  none
     *
     *  set the vaue of the field
     */
    public void setArtistID(UUID artistID)
    {
        this.artistID = artistID;
    }

    public void setFirstName(String fName)
    {
        this.firstName = fName;
    }

    public void setLastName(String lName)
    {
        this.lastName = lName;
    }

    public void setDob(String dob)
    {
        this.dob = dob;
    }

    public void setPlaceOfBirth(String placeOfBirth)
    {
        this.placeOfBirth = placeOfBirth;
    }

    public void setSongs(ArrayList<Song> songList)
    {
        this.songs = songList;
    }

    /**
     *  getters for all fields
     *
     *  @param
     *  none
     *
     *  @return
     *  the value of a field of the instance they are invoked on
     *
     */
    public UUID getArtistID()
    {
        return this.artistID;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public String getDob()
    {
        return this.dob;
    }

    public String getPlaceOfBirth()
    {
        return this.placeOfBirth;
    }

    public ArrayList<Song> getSongs()
    {
        return this.songs;
    }

    public void addSong(Song song)
    {
        /*
         * add new song to list of all songs
         * add(E e) appends to end of arrayList
         */

        this.songs.add(song);
    }

}
