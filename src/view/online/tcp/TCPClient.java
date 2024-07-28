package view.online.tcp;

import utils.TCPMessager;

import java.io.IOException;
import java.net.Socket;

public class TCPClient {

    private final TCPMessager tcpMessager;

    public TCPClient(String serverIP ,int serverPort) throws IOException {
        Socket socket = new Socket(serverIP ,serverPort);
        tcpMessager = new TCPMessager(socket);
    }


    public TCPMessager getTcpMessager() {
        return tcpMessager;
    }
}
