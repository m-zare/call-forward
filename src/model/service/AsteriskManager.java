package model.service;

import model.da.*;
import model.entity.*;

/**
 * Created by Mostafa on 10/28/2016.
 */
public class AsteriskManager {
    private String CID;
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
            CID = e.getE().get("CallerIDNum").trim();
            System.out.println("CID: " + CID);
            String Exten = "101";

            if (CID.equals("0912")) {
                try {
                    System.out.println(CID + " routed to "+ Exten);
                    ActionDA actionDA = new ActionDA(new ConnectionEntity());
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
                    ActionDA actionDA = new ActionDA(new ConnectionEntity());
                    actionDA.actionAsyncCommand(e.getE().get("Channel"), "ASYNCAGI BREAK");
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
            EventListener eventListener = new EventListener(new ConnectionEntity(), eventHandler);
            eventListener.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
