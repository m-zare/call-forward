package asterisk;

import java.io.IOException;

/**
 * Created by Mostafa on 10/28/2016.
 */

public abstract class MakeAction {
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

    public abstract String actionOriginate() throws IOException;
    public abstract String actionRedirect() throws IOException;
    public abstract String actionTransfer() throws IOException;
    public abstract String actionCommand(String command) throws IOException;
}
