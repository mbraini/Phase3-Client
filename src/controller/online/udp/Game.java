package controller.online.udp;

import controller.online.OnlineData;
import view.painting.ViewData;
import view.painting.ViewRequest;
import view.painting.painter.Render;

public class Game {

    private GameInfoReceiver gameInfoReceiver;
    private Render render;

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
}
