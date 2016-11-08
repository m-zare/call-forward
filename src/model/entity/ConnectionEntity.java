package model.entity;

import java.util.regex.Pattern;

/**
 * Created by Mostafa on 11/8/2016.
 */
public class ConnectionEntity {
    private String ip = "192.168.1.100";
    private int port = 5038;
    private String amiUserName = "mostafa";
    private String amiPassword = "m121990";

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

    public void setAmiUserName(String amiUserName) {
        this.amiUserName = amiUserName;
    }

    public String getAmiUserName() {
        return amiUserName;
    }

    public void setAmiPassword(String password) {
        this.amiPassword = amiPassword;
    }

    public String getAmiPassword() {
        return amiPassword;
    }

    private Pattern PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
}
