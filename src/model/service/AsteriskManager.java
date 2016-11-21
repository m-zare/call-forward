package model.service;

import model.da.*;
import model.entity.*;

/**
 * Created by Mostafa on 10/28/2016.
 */
public class AsteriskManager {
    private EventEntity e = new EventEntity();
    public EventHandler eventHandler = new EventHandler() {
        @Override
        public void call(EventEntity e, ConnectionEntity connection) {
            //System.out.println("***Call***");
            /*for (Map.Entry<String, String> entry : e.getE().entrySet()) {
                System.out.println(entry.getKey() + entry.getValue());
            }*/
        }

        @Override
        public void callerID(EventEntity e, ConnectionEntity connection) {
            String CID = e.getE().get("CallerIDNum").trim();
            System.out.println("CID: " + CID);
            String Exten = "";
            ActionDA actionDA = null;
            boolean specifiedCall=false;
            try {
                actionDA = new ActionDA(new ConnectionEntity());
                Exten = actionDA.dbGet(CID);
                if (Exten!=null){
                    specifiedCall = true;
                }
                else
                {
                    specifiedCall = false;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (specifiedCall) {
                try {
                    System.out.println(CID + " routed to " + Exten);
                    ActionEntity actionEntity = new ActionEntity();
                    actionEntity.setChannel(e.getE().get("Channel").trim());
                    actionEntity.setDst(Exten);

                    actionDA.actionAsyncCommand(e.getE().get("Channel").trim(), "Answer");
                    actionDA.actionRedirect(actionEntity);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                try {
                    System.out.println(CID + " will be routed to the Dialed Extention.");
                    actionDA = new ActionDA(new ConnectionEntity());
                    actionDA.actionAsyncCommand(e.getE().get("Channel").trim(), "ASYNCAGI BREAK");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        @Override
        public void hangUp(EventEntity e, ConnectionEntity connection) {
            /*System.out.println("***hangUp***");
            for (Map.Entry<String, String> entry : e.getE().entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }*/
        }

        @Override
        public void asyncAgi(EventEntity e, ConnectionEntity connection) {
        }
    };

    public void test() {
        try {
            EventListener eventListener = new EventListener(new ConnectionEntity(),eventHandler);
            eventListener.start();

            //ActionDA actionDA = new ActionDA(new ConnectionEntity());
            //actionDA.dbDel("0912");
            //actionDA.dbPut("101","100");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
