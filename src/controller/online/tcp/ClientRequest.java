package controller.online.tcp;

public abstract class ClientRequest {

    protected ClientRequestType type;

    public abstract void sendRequest();


}
