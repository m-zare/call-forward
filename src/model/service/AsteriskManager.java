package model.service;

import model.da.*;
import model.entity.*;

import java.io.*;

/**
 * Created by Mostafa on 10/28/2016.
 */
public class AsteriskManager{

    public void test() {
        try {
            ConnectionEntity connection = new ConnectionEntity();
            ActionDA actionDA=new ActionDA(connection);

            ActionEntity actionEntity = new ActionEntity("100","101");
            actionDA.actionOriginate(actionEntity);

            actionDA.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){}
    }
    /*
    * open connection
    * listen for calls
    *   if callerid is in list
    *       send it to desired extention
    * close connection
    */
}
