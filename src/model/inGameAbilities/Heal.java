package model.inGameAbilities;

import constants.CostConstants;
import controller.enums.InGameAbilityType;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;

public class Heal extends InGameAbility{

    private EpsilonModel epsilon;

    public Heal(EpsilonModel epsilon){
        type = InGameAbilityType.heal;
        xpCost = CostConstants.HEAL_COST;
        this.epsilon = epsilon;
    }

    @Override
    public void performAbility() {
        epsilon.setHP(epsilon.getHP() + 10);
        if (epsilon.getHP() > 100)
            epsilon.setHP(100);
        isActive = false;
    }

    @Override
    public void setUp() {
        epsilon = ModelData.getEpsilon();
    }


}
