package cz.uhk.fim.ase.communication.impl;

import Ice.Communicator;
import Ice.InitializationData;
import Ice.ObjectAdapter;
import Ice.Util;
import cz.uhk.fim.ase.common.LoggedObject;
import cz.uhk.fim.ase.communication.Listener;
import cz.uhk.fim.ase.communication.MessagesQueue;
import cz.uhk.fim.ase.container.Container;

import java.net.URL;

/**
 * @author Tomáš Kolinger <tomas@kolinger.name>
 */
public class IceListener extends LoggedObject implements Listener {

    private String address;
    private MessagesQueue queue;
    private Communicator communicator;

    public IceListener(Container container) {
        this.address = container.getAddress();
        this.queue = container.getQueue();
    }

    public Communicator getCommunicator() {
        return communicator;
    }

    public void listen() {
        communicator = createCommunicator();

        String[] parts = address.split(":");
        String endpoint = "tcp -h " + parts[0] + " -p " + parts[1];
        ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("Handler", endpoint);
        adapter.add(new MessageTransporter(queue), communicator.stringToIdentity("handler"));
        adapter.activate();

        communicator.waitForShutdown();
        communicator.destroy();
    }

    private Communicator createCommunicator() {
        InitializationData initializationData = new InitializationData();
        initializationData.properties = Ice.Util.createProperties();
        URL file = this.getClass().getClassLoader().getResource("/config.server");
        if (file != null) {
            initializationData.properties.load(file.getFile());
        }
        return Util.initialize(initializationData);
    }
}