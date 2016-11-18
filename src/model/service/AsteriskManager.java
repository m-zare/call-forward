package model.service;

import model.da.*;
import model.entity.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mostafa on 10/28/2016.
 */
public class AsteriskManager{
    private HashMap callDate = new HashMap();

    public HashMap getCallDate() {
        return callDate;
    }

    public void setCallDate(HashMap callDate) {
        this.callDate = callDate;
    }

    public void test() {
        try {
            ConnectionEntity connection = new ConnectionEntity();
            HashMap callDate = new HashMap();

            EventListener eventListener = new EventListener(connection);
            eventListener.start();


            /*ActionDA actionDA=new ActionDA(connection);
            ActionEntity actionEntity = new ActionEntity("100","101");
            actionDA.actionOriginate(actionEntity);

            actionDA.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
       // while (true){}
    }


    /*
    * open connection
    * listen for calls
    *   if callerid is in list
    *       send it to desired extention
    * close connection
    */
}
