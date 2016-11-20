package model.da;

import model.entity.*;

/**
 * Created by Mostafa on 11/18/2016.
 */
public interface EventHandler {
    public void call(EventEntity e, ConnectionEntity connection);

    public void callerID(EventEntity e, ConnectionEntity connection);

    public void hangUp(EventEntity e, ConnectionEntity connection);

    public void asyncAgi(EventEntity e, ConnectionEntity connection);
}
