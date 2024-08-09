package model.viewRequests;

import controller.enums.InGameAbilityType;
import controller.gameHistory.InGameAbilityBuyHelper;
import controller.manager.GameState;
import model.ModelData;
import model.inGameAbilities.InGameAbility;
import model.inGameAbilities.InGameAbilityHandler;

public class InGameAbilityRequests {


    public static void abilityRequest(InGameAbilityType type){

        if (canUse(type)) {
            ModelData.getCurrentGame().addInGameAbilityHistory(new InGameAbilityBuyHelper(
                    type ,InGameAbilityHandler.getInGameAbility(type).getXpCost()
            ));
            InGameAbilityHandler.activateInGameAbility(type);
            GameState.setXp(GameState.getXp() - InGameAbilityHandler.getInGameAbility(type).getXpCost());
        }

    }

    private static boolean canUse(InGameAbilityType type) {
        InGameAbility inGameAbility = InGameAbilityHandler.getInGameAbility(type);
        if (GameState.getXp() >= inGameAbility.getXpCost()  && inGameAbility.isAvailable() && !inGameAbility.isActive())
            return true;
        return false;
    }



}
