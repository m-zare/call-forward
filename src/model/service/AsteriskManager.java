package model.service;

import model.da.*;
import model.entity.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mostafa on 10/28/2016.
 */
public class AsteriskManager{
    EventEntity e = new EventEntity();
    EventHandler eventHandler = new EventHandler() {
        @Override
        public EventEntity call(EventEntity e, ConnectionEntity connection) {
            /*System.out.println("***Call***");
            for (Map.Entry<String, String> entry : e.getE().entrySet()) {
                System.out.println(entry.getKey() + entry.getValue());
            }*/
            return e;
        }

        @Override
        public EventEntity callerID(EventEntity e, ConnectionEntity connection) {
            System.out.println("***callerID***");
            for (Map.Entry<String, String> entry : e.getE().entrySet()) {
                System.out.println(entry.getKey() + entry.getValue());
            }
            breakAsync(e);
            return e;
        }

        @Override
        public EventEntity hangUp(EventEntity e, ConnectionEntity connection) {
            /*System.out.println("***hangUp***");
            for (Map.Entry<String, String> entry : e.getE().entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }*/
            return e;
        }

        @Override
        public void asyncAgi(EventEntity e, ConnectionEntity connection) {
            breakAsync(e);
        }
    };

    private void breakAsync(EventEntity e){
        if ("callerid is in db" == "") {

        } else {
            try {
                ActionDA actionDA = new ActionDA(new ConnectionEntity());
                actionDA.actionAsyncBreak(e.getE().get("Channel"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private HashMap callDate = new HashMap();

    public HashMap getCallDate() {
        return callDate;
    }

    public void setCallDate(HashMap callDate) {
        this.callDate = callDate;
    }

    public void test1() {
        /*try {
            ConnectionEntity connection = new ConnectionEntity();
            HashMap callDate = new HashMap();

            EventListener eventListener = new EventListener(connection);
            eventListener.start();


            *//*ActionDA actionDA=new ActionDA(connection);
            ActionEntity actionEntity = new ActionEntity("100","101");
            actionDA.actionOriginate(actionEntity);

            actionDA.close();*//*
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        // while (true){}
    }

    public void test() {

        try {
            ConnectionEntity connection = new ConnectionEntity();
            HashMap callDate = new HashMap();

            EventListener eventListener = new EventListener(new ConnectionEntity(), eventHandler);
            eventListener.start();


            /*ActionDA actionDA=new ActionDA(connection);
            ActionEntity actionEntity = new ActionEntity("100","101");
            actionDA.actionOriginate(actionEntity);

            actionDA.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /*
    * open connection
    * listen for calls
    *   if callerid is in list
    *       send it to desired extention
    * close connection
    */
}
