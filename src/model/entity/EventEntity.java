package model.entity;

import java.util.HashMap;

/**
 * Created by Mostafa on 11/20/2016.
 */
public class EventEntity {
    private HashMap<String,String> e = new HashMap();

    public HashMap<String, String> getE() {
        return e;
    }

    public void setE(HashMap<String, String> e) {
        this.e = e;
    }

    public void clear(){
        e.clear();
    }
}
