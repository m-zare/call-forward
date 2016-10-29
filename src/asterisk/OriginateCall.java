package asterisk;

/**
 * Created by Mostafa on 10/28/2016.
 */

public class OriginateCall {
    private String src="100";
    private String dst="101";
    private String context="default";

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

    public String originate(){
        return "Action: Originate\nChannel: sip/"+getSrc()+"\nExten:"+getDst()+"\nContext:"+getContext()+"\nPriority: 1\n\n";
    }
}
