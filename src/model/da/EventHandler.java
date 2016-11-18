package model.da;

import model.entity.ConnectionEntity;

import java.io.IOException;
import java.util.*;

/**
 * Created by Mostafa on 11/18/2016.
 */
public class EventHandler {

    public void call(HashMap<String,String> callData, ConnectionEntity connection){
        System.out.println("***Call***");
        for (Map.Entry<String, String> entry : callData.entrySet())
        {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }

    public void callerID(HashMap<String,String> callData, ConnectionEntity connection){
        System.out.println("***callerID***");
        if ("callerid is in db"=="")
        {

        }
        else
        {
            try {
                ActionDA actionDA = new ActionDA(connection);
                actionDA.actionAsyncBreak(callData.get("Channel"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void hangUp(HashMap<String,String> callData, ConnectionEntity connection){
        System.out.println("***hangUp***");
        for (Map.Entry<String, String> entry : callData.entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
