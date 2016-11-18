package model.da;

import java.util.*;

/**
 * Created by Mostafa on 11/18/2016.
 */
public class EventHandler {

    public void call(HashMap<String,String> callDate){
        System.out.println("***Call***");
        for (Map.Entry<String, String> entry : callDate.entrySet())
        {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }
    public void callerID(HashMap<String,String> callDate){
        System.out.println("***callerID***");
        for (Map.Entry<String, String> entry : callDate.entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
    public void hangUp(HashMap<String,String> callDate){
        System.out.println("***hangUp***");
        for (Map.Entry<String, String> entry : callDate.entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
