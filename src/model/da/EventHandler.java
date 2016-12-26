package model.da;

import model.entity.*;

/**
 * Created by Mostafa on 11/18/2016.
 */
public interface EventHandler {
    void call(EventEntity e, ConnectionEntity connection);

    void callerID(EventEntity e, ConnectionEntity connection);

    void hangUp(EventEntity e, ConnectionEntity connection);

    void asyncAgi(EventEntity e, ConnectionEntity connection);

}
