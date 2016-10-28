package asterisk;

import java.util.regex.Pattern;

/**
 * Created by Mostafa on 10/28/2016.
 */
public class Socket {
    protected String ip;
    protected int port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        if (PATTERN.matcher(ip).matches()){
            this.ip = ip;
        }
        else {
            System.out.println("IP is not valid.");
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        if (port > 0 && port <= 65536){
            this.port = port;
        }
        else {
            System.out.println("Port is not valid.");
        }

    }

    private Pattern PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
}
