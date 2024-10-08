package model.viewRequests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import constants.CostConstants;
import controller.configs.Configs;
import controller.configs.helper.SkillTreeJsonHelper;
import controller.enums.SkillTreeAbilityType;
import controller.gameHistory.InGameSkillTreeBuyHelper;
import controller.gameHistory.SkillTreeBuyHelper;
import controller.manager.GameState;
import controller.online.OnlineData;
import model.ModelData;
import model.skillTreeAbilities.SkillTreeAbility;
import model.skillTreeAbilities.SkillTreeAbilityHandler;
import utils.Helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class SkillTreeAbilityRequests {

    public static void abilityRequest(SkillTreeAbilityType type) {
        if (canUse(type)) {
            GameState.setXp(GameState.getXp() - SkillTreeAbilityHandler.getAbility(type).getInGameXpCost());
            ModelData.getCurrentGame().addInGameAbilityHistory(
                    new InGameSkillTreeBuyHelper(type ,SkillTreeAbilityHandler.getAbility(type).getInGameXpCost())
            );
            SkillTreeAbilityHandler.activateSkillTreeAbility(type);
        }
    }

    private static boolean canUse(SkillTreeAbilityType type) {
        SkillTreeAbility ability = SkillTreeAbilityHandler.getAbility(type);
        if (ability.isBought() && ability.CanCast() && GameState.getXp() >= ability.getInGameXpCost()){
            return true;
        }
        return false;
    }

    public static void buyRequest(SkillTreeAbilityType type) {

        if (canBuy(type) > 0) {
            buy(type ,canBuy(type));
        }

    }

    private static void buy(SkillTreeAbilityType type ,int cost) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        SkillTreeJsonHelper helper;
        StringBuilder stringBuilder = Helper.readFile("src/controller/configs/skillTree.json");
        helper = gson.fromJson(stringBuilder.toString() ,SkillTreeJsonHelper.class);
        GameState.setXp(GameState.getXp() - cost);
        addSkillTreeBuyHistory(type ,cost);
        switch (type) {
            case ares :
                helper.ares = true;
                Configs.SkillTreeConfigs.aresBought = true;
                break;
            case astrape:
                helper.astrape = true;
                Configs.SkillTreeConfigs.astrapeBought = true;
                break;
            case cerberus:
                helper.cerberus = true;
                Configs.SkillTreeConfigs.cerberusBought = true;
                break;
            case aceso:
                helper.aceso = true;
                Configs.SkillTreeConfigs.acesoBought = true;
                break;
            case melampus:
                helper.melampus = true;
                Configs.SkillTreeConfigs.melampusBought = true;
                break;
            case chiron:
                helper.chiron = true;
                Configs.SkillTreeConfigs.chironBought = true;
                break;
            case athena:
                helper.athena = true;
                Configs.SkillTreeConfigs.athenaBought = true;
                break;
            case proteus:
                helper.proteus = true;
                Configs.SkillTreeConfigs.proteusBought = true;
                break;
            case empusa:
                helper.empusa = true;
                Configs.SkillTreeConfigs.empusaBought = true;
                break;
            case dolus:
                helper.dolus = true;
                Configs.SkillTreeConfigs.dolusBought = true;
                break;
        }
        String json = gson.toJson(helper);
        Helper.writeFile("src/controller/configs/skillTree.json" ,json);
    }

    private static int canBuy(SkillTreeAbilityType type) {
        switch (type) {
            case ares :
                if (Configs.SkillTreeConfigs.aresBought)
                    return -1;
                if (GameState.getXp() >= CostConstants.ARES_UNLOCK_COST)
                    return CostConstants.ARES_UNLOCK_COST;
                break;
            case astrape:
                if (Configs.SkillTreeConfigs.astrapeBought)
                    return -1;
                if (GameState.getXp() >= CostConstants.ASTRAPE_UNLOCK_COST && Configs.SkillTreeConfigs.aresBought)
                    return CostConstants.ASTRAPE_UNLOCK_COST;
                break;
            case cerberus:
                if (Configs.SkillTreeConfigs.cerberusBought)
                    return -1;
                if (GameState.getXp() >= CostConstants.CERBERUS_UNLOCK_COST) {
                    if (Configs.SkillTreeConfigs.aresBought && Configs.SkillTreeConfigs.astrapeBought)
                        return CostConstants.CERBERUS_UNLOCK_COST;
                }
                break;
            case aceso:
                if (Configs.SkillTreeConfigs.acesoBought)
                    return -1;
                if (GameState.getXp() >= CostConstants.ACESO_UNLOCK_COST)
                    return CostConstants.ACESO_UNLOCK_COST;
                break;
            case melampus:
                if (Configs.SkillTreeConfigs.melampusBought)
                    return -1;
                if (GameState.getXp() >= CostConstants.MELAMPUS_UNLOCK_COST && Configs.SkillTreeConfigs.acesoBought)
                    return CostConstants.MELAMPUS_UNLOCK_COST;
                break;
            case chiron:
                if (Configs.SkillTreeConfigs.chironBought)
                    return -1;
                if (GameState.getXp() >= CostConstants.CHIRON_UNLOCK_COST) {
                    if (Configs.SkillTreeConfigs.acesoBought && Configs.SkillTreeConfigs.melampusBought)
                        return CostConstants.CHIRON_UNLOCK_COST;
                }
                break;
            case athena:
                if (Configs.SkillTreeConfigs.athenaBought)
                    return -1;
                if (GameState.getXp() >= CostConstants.ATHENA_UNLOCK_COST) {
                    if (Configs.SkillTreeConfigs.acesoBought && Configs.SkillTreeConfigs.melampusBought)
                        return CostConstants.ATHENA_UNLOCK_COST;
                }
                break;
            case proteus:
                if (Configs.SkillTreeConfigs.proteusBought)
                    return -1;
                if (GameState.getXp() >= CostConstants.PROTEUS_UNLOCK_COST)
                    return CostConstants.PROTEUS_UNLOCK_COST;
                break;
            case empusa:
                if (Configs.SkillTreeConfigs.empusaBought)
                    return -1;
                if (GameState.getXp() >= CostConstants.EMPUSA_UNLOCK_COST && Configs.SkillTreeConfigs.proteusBought)
                    return CostConstants.EMPUSA_UNLOCK_COST;
                break;
            case dolus:
                if (Configs.SkillTreeConfigs.dolusBought)
                    return -1;
                if (GameState.getXp() >= CostConstants.DOLUS_UNLOCK_COST) {
                    if (Configs.SkillTreeConfigs.proteusBought && Configs.SkillTreeConfigs.empusaBought)
                        return CostConstants.DOLUS_UNLOCK_COST;
                }
                break;
        }
        return -1;
    }

    public static void addSkillTreeBuyHistory(SkillTreeAbilityType skillTreeAbilityType ,int cost) {
        Gson gson = new Gson();
        StringBuilder stringBuilder = Helper.readFile("src/controller/gameHistory/skillTreeBuys.json");
        Type type = new TypeToken<ArrayList<SkillTreeBuyHelper>>(){}.getType();
        ArrayList<SkillTreeBuyHelper> skillTreeBuyHelpers = gson.fromJson(stringBuilder.toString() ,type);
        SkillTreeBuyHelper buyHelper = new SkillTreeBuyHelper(cost ,skillTreeAbilityType);
        String hash;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(gson.toJson(buyHelper).getBytes());
            hash = new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        buyHelper.setHash(hash);
        skillTreeBuyHelpers.add(buyHelper);
        Helper.writeFile("src/controller/gameHistory/skillTreeBuys.json" ,gson.toJson(skillTreeBuyHelpers));
    }

}
