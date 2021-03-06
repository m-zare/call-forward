package model.entity;

/**
 * Created by Mostafa on 10/28/2016.
 */
public class ActionEntity {
    private String src = "100";
    private String dst = "101";
    private String context = "default";
    private String channel;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public ActionEntity() {
    }

    public ActionEntity(String src, String dst) {
        this.src = src;
        this.dst = dst;
    }

    public ActionEntity(String src, String dst, String context) {
        this.src = src;
        this.dst = dst;
        this.context = context;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getDst() {
        return dst;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getContext() {
        return context;
    }
}
