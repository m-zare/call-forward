package model.da;

import model.entity.ConnectionEntity;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by Mostafa on 11/18/2016.
 */
public class EventListener extends Thread {
    private ConnectionEntity connection;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private HashMap<String,String> callData = new HashMap();

    public HashMap<String, String> getCallData() {
        return callData;
    }

    public void setCallData(HashMap<String, String> callData) {
        this.callData = callData;
    }

    public EventListener(ConnectionEntity connection){
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            EventHandler eventHandler = new EventHandler();

            connection = new ConnectionEntity();
            socket = new Socket(connection.getIp(), connection.getPort());

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("action:login\nusername:" + connection.getAmiUserName() + "\nsecret:" + connection.getAmiPassword() + "\n");
            sleep(1000);

            while (true)
            {
                String str = in.readLine();
                if(str.length()==0) {
                    if (!callData.isEmpty() && callData.get("Event")!= null) {
                        if (callData.get("Event").trim().equals("NewCallerid")) {
                            eventHandler.callerID(callData,connection);
                        }
                        if (callData.get("Event").trim().equals("Dial")) {
                            eventHandler.call(callData,connection);
                        }
                        if (callData.get("Event").trim().equals("Hangup")) {
                            eventHandler.hangUp(callData,connection);
                        }
                        callData.clear();
                    }
                }
                else if (str.contains(":")) {
                    if (str.lastIndexOf(":")+1 == str.length())
                    {
                        str+=" ";
                    }
                    String[] strs = str.split(":");
                    callData.put(strs[0], strs[1]);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
