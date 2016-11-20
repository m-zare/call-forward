package model.da;

import model.entity.*;
import java.util.*;

/**
 * Created by Mostafa on 11/18/2016.
 */
public interface EventHandler {
    EventEntity call(EventEntity e, ConnectionEntity connection);
    EventEntity callerID(EventEntity e, ConnectionEntity connection);
    EventEntity hangUp(EventEntity e, ConnectionEntity connection);
    void asyncAgi(EventEntity e, ConnectionEntity connection);
}
