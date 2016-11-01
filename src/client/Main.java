package client;

import java.io.IOException;

/**
 * Created by Mostafa on 10/28/2016.
 */
public class Main {
   public static void main(String[] args) {
       Asterisk asterisk = new Asterisk();
       asterisk.openConnection();

       try {
           System.out.println(asterisk.actionOriginate());
       } catch (IOException e) {
           e.printStackTrace();
       }

   }
}
