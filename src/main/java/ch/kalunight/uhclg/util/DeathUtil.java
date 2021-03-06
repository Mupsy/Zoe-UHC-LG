package ch.kalunight.uhclg.util;

import java.util.List;

import ch.kalunight.uhclg.GameData;
import ch.kalunight.uhclg.model.PlayerData;
import ch.kalunight.uhclg.model.role.InfectPereDesLoups;
import ch.kalunight.uhclg.model.role.Sorciere;

public class DeathUtil {

  /*
   * Death time = 30 sec
   */
  public static final int DEATH_TIME_IN_TICKS = 600;

  private DeathUtil() {
    // hide default public constructor
  }

  public static PlayerData getAviableSavior(List<PlayerData> playerAlreadyAsked) {

    boolean containSorciere = false;

    for(PlayerData playerData : GameData.getPlayersInGame()) {
      if(!playerAlreadyAsked.contains(playerData) && playerData.getRole() instanceof Sorciere) {
        containSorciere = true;
        break;
      }
    }

    for(PlayerData playerData : GameData.getPlayersInGame()) {
      if(!playerAlreadyAsked.contains(playerData)) {
        if((containSorciere && playerData.getRole() instanceof Sorciere) || (!containSorciere && playerData.getRole() instanceof InfectPereDesLoups)) {
          return playerData;
        }
      }
    }
    return null;
  }
}
