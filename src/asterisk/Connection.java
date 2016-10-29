package asterisk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.Thread.sleep;

/**
 * Created by Mostafa on 10/28/2016.
 */
public class Connection {
    private String userName = "mostafa";
    private String password = "m121990";

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void openConnection() throws Exception{
        System.out.println("Client started");

        ServerInfo serverInfo = new ServerInfo();
        Socket socket = new Socket(serverInfo.getIp(),serverInfo.getPort());

        //get Commands to send to sever
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String answer = in.readLine();

        out.println("action:login\nusername:"+getUserName()+"\nsecret:"+getPassword()+"\n");

        sleep(1000);

        OriginateCall originateCall = new OriginateCall();

        out.println(originateCall.originate());

        System.out.println("Server says: "+answer);

        System.out.println("Client terminated");
        /*int option = 0;
        int num1 = 0;
        int num2 = 0;
        while (true)
        {
            System.out.println("Choose an operation:");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exit");
            System.out.println("Enter an option:");
            option = Integer.parseInt(userInput.readLine());
            if(option != 5)
            {
                System.out.println("Enter first number");
                num1 = Integer.parseInt(userInput.readLine());
                System.out.println("Enter second number");
                num2 = Integer.parseInt(userInput.readLine());

                //send to server
                out.println(option+":"+num1+":"+num2);
            }
            else
            {
                out.println(option+":0:0");
                break;
            }

            //server answer
            String answer = in.readLine();
            System.out.println("Server says: "+answer);
            System.out.println("");
        }
*/

    }
}