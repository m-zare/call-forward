package client;

import asterisk.*;
import java.io.*;
import java.net.Socket;
import static java.lang.Thread.sleep;

/**
 * Created by Mostafa on 10/28/2016.
 */
public class Asterisk extends MakeAction {
    private Connection connection = new Connection();
    private PrintWriter out;
    private BufferedReader in;

    /*
    * open socket
    * make login
    */
    public void openConnection() {
        //Connection connection = new Connection();
        try {
            Socket socket = connection.openConnection();

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String answer = in.readLine();

            out.println("action:login\nusername:"+connection.getAmiUserName()+"\nsecret:"+connection.getAmiPassword()+"\n");

            sleep(1000);

            System.out.println("Server says: "+answer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * ACTION: Originate
    * Channel: SIP/12345
    * Exten: 1234
    * Priority: 1
    * Timeout: 60000
    * Context: default
    */
    @Override
    public String actionOriginate() throws IOException{
        out.println("Action: Originate\nChannel: SIP/" + getSrc() + "\nExten: " + getDst() +
                "\nPriority: 1\nTimeout: 60000\nContext: " + getContext() + "\n\n");

        return in.readLine();
    }

    /*
    * ACTION: Command
    * command: show dialplan
    */
    @Override
    public String actionCommand(String command) throws IOException{
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
    @Override
    public String actionRedirect() throws IOException{
        out.println( "ACTION: Redirect\n" +
                "Channel: SIP/" + getSrc() + "\n" +
                "ExtraChannel: SIP/4321-45cf6c80\n" +
                "Exten: " + getDst() + "\n" +
                "Priority: 1\n" +
                "Context: " + getContext() + "\n\n");
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
    @Override
    public String actionTransfer() throws IOException {
        String channel=actionCommand("show channels");

        out.println("ACTION: Redirect\n" +
                "Channel: SIP/" + channel + "\n" +
                "Context: " + getContext() + "\n" +
                "Exten: " + getDst() + "\n" +
                "Priority: 1");
        return in.readLine();
    }

    /*
    * open connection
    * listen for calls
    *   if callerid is in list
    *       send it to desired extention
    * close connection
    */
}
