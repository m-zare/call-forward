package model.da;

import model.entity.ConnectionEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mostafa on 11/18/2016.
 */
public class EventListener extends Thread {
    private ConnectionEntity connection;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    @Override
    public void run() {
        try {
            connection = new ConnectionEntity();
            EventHandler eventHandler = new EventHandler();

            socket = new Socket(connection.getIp(), connection.getPort());

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("action:login\nusername:" + connection.getAmiUserName() + "\nsecret:" + connection.getAmiPassword() + "\n");
            //out.println("action:events\neventmask:user\n");
            sleep(1000);

            HashMap<String,String> callData = new HashMap();
            while (true)
            {
                String str = in.readLine();
                if(str.length()==0) {
                    if (!callData.isEmpty() && callData.get("Event")!= null) {
                        //System.out.println(callData.get("Event").trim());
                        if (callData.get("Event").trim().equals("NewCallerid")) {
                            eventHandler.callerID(callData);
                        }
                        if (callData.get("Event").trim().equals("Dial")) {
                            eventHandler.call(callData);
                        }
                        if (callData.get("Event").trim().equals("Hangup")) {
                            eventHandler.hangUp(callData);
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
