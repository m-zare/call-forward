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

    /*
   * Constructor
   * open socket
   * make login
   */
    public ActionDA(ConnectionEntity connection) throws IOException, InterruptedException {
        socket = new Socket(connection.getIp(), connection.getPort());

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        out.println("action:login\nusername:" + connection.getAmiUserName() + "\nsecret:" + connection.getAmiPassword() + "\n");
        String answer = in.readLine();

        sleep(1000);
        //System.out.println("Server says: "+answer);
    }

    /*
    * ACTION: Originate
    * Channel: SIP/12345
    * Exten: 1234
    * Priority: 1
    * Timeout: 60000
    * Context: default
    */
    public String actionOriginate(ActionEntity actionEntity) throws IOException {
        out.println("Action: Originate\nChannel: SIP/" + actionEntity.getSrc() + "\nExten: " + actionEntity.getDst() +
                "\nPriority: 1\nTimeout: 60000\nContext: " + actionEntity.getContext() + "\n\n");

        return in.readLine();
    }

    /*
    * ACTION: Command
    * command: show dialplan
    */
    public String actionCommand(String command) throws IOException {
        out.println("Action: Command\ncommand: " + command + "\n\n");

        return in.readLine();
    }

    /*ACTION: Redirect
    * Channel: SIP/1234-6378
    * ExtraChannel: SIP/4321-45cf6c80
    * Exten: 680
    * Priority: 1
    * Context: default
    *
    *
    * "ExtraChannel: SIP/4321-45cf6c80\n" line has to replace sth
    */
    public String actionRedirect(ActionEntity actionEntity) throws IOException {
        out.println("ACTION: Redirect\n" +
                "Channel: SIP/" + actionEntity.getSrc() + "\n" +
                "ExtraChannel: SIP/4321-45cf6c80\n" +
                "Exten: " + actionEntity.getDst() + "\n" +
                "Priority: 1\n" +
                "Context: " + actionEntity.getContext() + "\n\n");
        return in.readLine();
    }

    /*
    * ACTION: Redirect
    * Channel: SIP/x7065558529-8f54
    * Context: default
    * Exten: 5558530
    * Priority: 1
    *
    *
    * channel should be extracted
    */
    public String actionTransfer(ActionEntity actionEntity) throws IOException {
        String channel = actionCommand("show channels");

        out.println("ACTION: Redirect\n" +
                "Channel: SIP/" + channel + "\n" +
                "Context: " + actionEntity.getContext() + "\n" +
                "Exten: " + actionEntity.getDst() + "\n" +
                "Priority: 1");
        return in.readLine();
    }

    public String actionAsyncBreak(String Channel) throws IOException {
        out.println("Action: AGI\n" +
                "Channel: " + Channel + "\n" +
                "Command: ASYNCAGI BREAK\n\n");
        return in.readLine();
    }

    public void close() throws IOException {
        socket.close();
    }
}
