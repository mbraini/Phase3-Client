package controller.online.udp;

import controller.online.OnlineData;
import controller.online.udp.gameInfoSender.GameInfoSender;
import model.ModelRequests;
import view.painting.ViewData;
import view.painting.ViewRequest;
import view.painting.painter.Render;

public class Game {

    private GameInfoReceiver gameInfoReceiver;
    private Render render;
    private GameInfoSender gameInfoSender;

    public Game() {
        gameInfoReceiver = new GameInfoReceiver();
        getRenderReady();
        render.start();
        OnlineData.setCurrentOnlineGame(this);
    }

    private void getRenderReady() {
        render = new Render();
        ViewRequest.resetAll();
        ViewData.resetAll();
        ViewData.initAbilities();
    }

    public GameInfoReceiver getGameInfoReceiver() {
        return gameInfoReceiver;
    }

    public void setGameInfoReceiver(GameInfoReceiver gameInfoReceiver) {
        this.gameInfoReceiver = gameInfoReceiver;
    }

    public void end() {
        gameInfoReceiver.end();
        gameInfoSender.end();
        ViewRequest.endRequest();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        render.interrupt();

    }

    public GameInfoSender getGameInfoSender() {
        return gameInfoSender;
    }

    public void setGameInfoSender(GameInfoSender gameInfoSender) {
        this.gameInfoSender = gameInfoSender;
    }
}
