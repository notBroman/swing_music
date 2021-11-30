package CS2020.assignment2;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilsTest
{
    @Test
    public void isWeekend()
    {
        try{
            assertTrue(Utils.checkIfBornOnWeekend("2021-12-05"));
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @Test
    public void isWeekday()
    {
        try
        {
            assertFalse(Utils.checkIfBornOnWeekend("2021-11-30"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}