package view.painting.menuPanels;


import constants.SizeConstants;
import view.painting.menuPanels.onlinePanels.LogInPanel;
import view.painting.menuPanels.onlinePanels.OnlineChoicePanel;
import view.painting.menuPanels.onlinePanels.SignUpPanel;
import view.painting.menuPanels.onlinePanels.squad.*;

import javax.swing.*;

public class MainFrame extends JFrame {
    public static MainPanel mainPanel;
    public static MenuPanel menuPanel;
    public static SettingsPanel settingsPanel;
    public static SkillTreePanel skillTreePanel;
    public static Tutorial tutorial;
    public static OnlineChoicePanel onlineChoicePanel;
    public static LogInPanel logInPanel;
    public static SignUpPanel signUpPanel;
    public static HasSquadPanel hasSquadPanel;
    public static NoSquadPanel noSquadPanel;
    public static CreateNewSquadPanel createNewSquadPanel;
    public static HasBattleSquadPanel hasBattleSquadPanel;
    public static NoBattleSquadPanel noBattleSquadPanel;
    public static SquadTreasuryPanel squadTreasuryPanel;
    public static SquadTreasuryShopPanel squadTreasuryShopPanel;

    public MainFrame(){
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(SizeConstants.GAME_WIDTH + SizeConstants.barD.width , SizeConstants.GAME_HEIGHT + SizeConstants.barD.height);
        this.setLocationRelativeTo(null);
        addOthers();

        this.setVisible(true);
    }

    private void addOthers() {
        mainPanel = new MainPanel();
        this.setContentPane(mainPanel);

        menuPanel = new MenuPanel();
        settingsPanel = new SettingsPanel();
        skillTreePanel = new SkillTreePanel();
        tutorial = new Tutorial();
        onlineChoicePanel = new OnlineChoicePanel();
        signUpPanel = new SignUpPanel();
        logInPanel = new LogInPanel();
        hasSquadPanel = new HasSquadPanel();
        noSquadPanel = new NoSquadPanel();
        createNewSquadPanel = new CreateNewSquadPanel();
        hasBattleSquadPanel = new HasBattleSquadPanel();
        noBattleSquadPanel = new NoBattleSquadPanel();
        squadTreasuryPanel = new SquadTreasuryPanel();
        squadTreasuryShopPanel = new SquadTreasuryShopPanel();

        mainPanel.add(menuPanel);
        mainPanel.add(settingsPanel);
        mainPanel.add(skillTreePanel);
        mainPanel.add(tutorial);
        mainPanel.add(onlineChoicePanel);
        mainPanel.add(signUpPanel);
        mainPanel.add(logInPanel);
        mainPanel.add(hasSquadPanel);
        mainPanel.add(noSquadPanel);
        mainPanel.add(createNewSquadPanel);
        mainPanel.add(hasBattleSquadPanel);
        mainPanel.add(noBattleSquadPanel);
        mainPanel.add(squadTreasuryPanel);
        mainPanel.add(squadTreasuryShopPanel);
    }
}
