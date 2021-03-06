package model.da;

import model.entity.*;

import java.io.*;
import java.net.Socket;

/**
 * Created by Mostafa on 11/18/2016.
 */
public class EventListener extends Thread {
    private ConnectionEntity connection;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private EventHandler eventHandler;

    public ConnectionEntity getConnection() {
        return connection;
    }

    public void setConnection(ConnectionEntity connection) {
        this.connection = connection;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public EventListener(ConnectionEntity connection, EventHandler eventHandler) {
        setEventHandler(eventHandler);
        this.connection = connection;
    }

    @Override
    public void run()  {
        EventEntity callData = new EventEntity();
        try {
            connection = new ConnectionEntity();
            socket = new Socket(connection.getIp(), connection.getPort());

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("action:login\nusername:" + connection.getAmiUserName() + "\nsecret:" + connection.getAmiPassword() + "\n");
            //sleep(1000);

            while (true) {
                String str = in.readLine();
                //Detect blank line after each action
                //Blank line determine end of an event
                if (str.length() == 0) {
                    //Throws Event
                    if (!callData.getE().isEmpty() && callData.getE().get("Event") != null) {
                        if (callData.getE().get("Event").toString().trim().equals("NewCallerid")) {
                            eventHandler.callerID(callData, getConnection());

                        } else if (callData.getE().get("Event").toString().trim().equals("Dial")) {
                            eventHandler.call(callData, getConnection());

                        } else if (callData.getE().get("Event").toString().trim().equals("Hangup")) {
                            eventHandler.hangUp(callData, getConnection());

                        } else if (callData.getE().get("Event").toString().trim().equals("AsyncAGI")) {
                            eventHandler.asyncAgi(callData, getConnection());
                        }
                        callData.clear();
                    }
                //Road line belongs an action. Add it to Event Hashmap
                } else if (str.contains(":")) {
                    //If line does not contain ":", do not consider it as a Variable.
                    if (str.lastIndexOf(":") + 1 == str.length()) {
                        str += " ";
                    }
                    String[] strs = str.split(":");
                    callData.getE().put(strs[0], strs[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
