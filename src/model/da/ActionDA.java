package model.da;

import model.entity.*;

import java.io.*;
import java.net.Socket;

import static java.lang.Thread.sleep;

/**
 * Created by Mostafa on 11/8/2016.
 */
public class ActionDA {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ActionDA(ConnectionEntity connection) throws IOException, InterruptedException {
        socket = new Socket(connection.getIp(), connection.getPort());

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        out.println("action:login\nusername:" + connection.getAmiUserName() + "\nsecret:" + connection.getAmiPassword() + "\n");
        sleep(1000);
    }

    public String actionOriginate(ActionEntity actionEntity) throws IOException {
        out.println("Action: Originate\nChannel: SIP/" + actionEntity.getSrc() + "\nExten: " + actionEntity.getDst() +
                "\nPriority: 1\nTimeout: 60000\nContext: " + actionEntity.getContext() + "\n");

        return in.readLine();
    }

    public String actionCommand(String command) throws IOException {
        out.println("Action: Command\ncommand: " + command + "\n");

        return in.readLine();
    }

    public String actionRedirect(ActionEntity actionEntity) throws IOException {
        out.println("ACTION: Redirect\n" +
                "Channel: " + actionEntity.getChannel() + "\n" +
                "Exten: " + actionEntity.getDst() + "\n" +
                "Priority: 1\n" +
                "Context: " + actionEntity.getContext() + "\n");
        return in.readLine();
    }

    public String actionTransfer(ActionEntity actionEntity) throws IOException {
        String channel = actionCommand("show channels");

        out.println("ACTION: Redirect\n" +
                "Channel: SIP/" + channel + "\n" +
                "Context: " + actionEntity.getContext() + "\n" +
                "Exten: " + actionEntity.getDst() + "\n" +
                "Priority: 1\n");
        return in.readLine();
    }

    public String actionAsyncCommand(String Channel, String Command) throws IOException {
        out.println("Action: AGI\n" +
                "Channel: " + Channel + "\n" +
                "Command: " + Command + "\n");
        return in.readLine();
    }

    public void dbPut(String key, String value) throws IOException {
        out.println("Action: DBPut\n" +
                "Family: app\n" +
                "Key: " + key + "\n" +
                "Val: " + value + "\n");
    }

    public String dbGet(String key) throws IOException {
        out.println("Action: DBGet\n" +
                "Family: app\n" +
                "Key: " + key + "\n");
        String value;
        while (true) {
            String str = in.readLine();
            if (str.equals("Event: DBGetResponse")) {
                while (true) {
                    str = in.readLine();
                    String[] strs = str.split(":");
                    if (strs[0].equals("Val")) {
                        value = strs[1].trim();
                        break;
                    }
                }
                break;
            } else if (str.equals("Response: Error")) {
                value = null;
                break;
            }
        }
        return value;
    }

    public void dbDel(String key) throws IOException {
        out.println("Action: DBDel\n" +
                "Family: app\n" +
                "Key: " + key + "\n");
    }

    public void close() throws IOException {
        socket.close();
    }
}
