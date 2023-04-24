package network.structure;

import java.rmi.Remote;

public interface Client extends Remote {

    public void update(View v, Object o);
}
