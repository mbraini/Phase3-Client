package controller.online.udp;

import java.net.DatagramSocket;
import java.net.SocketException;

public class AbilityViewReceiver extends Thread{

    private int port;
    private DatagramSocket datagramSocket;

    @Override
    public void run() {
        super.run();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUp() {
        try {
            datagramSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }


}
