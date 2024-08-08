package controller;

import view.painting.gamePanels.Shop;
import view.painting.gamePanels.ShopFrame;
import view.painting.menuPanels.MainFrame;

public class PauseController {

    public static ShopFrame shopFrame;

    public static Shop shop;

    public static void createPausePanel() {
        shopFrame = new ShopFrame();
        shop = new Shop(shopFrame ,true);
        shop.grabFocus();
        shop.revalidate();
        shop.repaint();
    }

    public static void endPausePanel() {
        if (shop != null)
            shop.end();
    }

}
