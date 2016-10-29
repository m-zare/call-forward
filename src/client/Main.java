package client;

import asterisk.Connection;

/**
 * Created by Mostafa on 10/28/2016.
 */
public class Main {
   /* public static void main(String[] args) {
        Asterisk asterisk = new Asterisk();
        asterisk.a();
    }*/
   public static void main(String[] args) {
       Connection connection = new Connection();
       try {
           connection.openConnection();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}
