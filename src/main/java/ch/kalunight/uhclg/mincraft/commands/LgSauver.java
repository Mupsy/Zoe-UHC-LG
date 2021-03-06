package ch.kalunight.uhclg.mincraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import ch.kalunight.uhclg.GameData;
import ch.kalunight.uhclg.minecraft.MinecraftEventListener;
import ch.kalunight.uhclg.model.GameStatus;
import ch.kalunight.uhclg.model.PlayerData;
import ch.kalunight.uhclg.model.role.SaviorRole;
import ch.kalunight.uhclg.worker.GameWorker;
import ch.kalunight.uhclg.worker.KillerWorker;

public class LgSauver implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    
    if(!GameData.getGameStatus().equals(GameStatus.IN_GAME)) {
      sender.sendMessage("La partie n'a pas commencé !");
      return true;
    }
    
    if(GameWorker.getActualDayNumber() < 2) {
      sender.sendMessage("Vous ne connaissez pas encore votre rôle !");
      return true;
    }
    
    PlayerData playerSender = null;
    for(PlayerData player : GameData.getPlayersInGame()) {
      if(player.getAccount().getPlayer().getName().equals(sender.getName())) {
        playerSender = player;
      }
    }
    
    if(playerSender == null || !(playerSender.getRole() instanceof SaviorRole)) {
      sender.sendMessage("Vous n'êtes pas un infect père des loups ou une sorcière !");
      return true;
    }
    
    if(((SaviorRole) playerSender.getRole()).isPlayerSaved()) {
      sender.sendMessage("Vous avez déjà utilisé votre pouvoir !");
      return true;
    }
    
    if(args.length != 1) {
      sender.sendMessage("Vous devez mentionner quelqu'un pour pour pouvoir voir son rôle !");
      return false;
    }
    
    PlayerData playerToSave = null;
    for(PlayerData player : GameData.getPlayersInGame()) {
      if(player.getAccount().getPlayer().getName().equals(args[0])) {
        playerToSave = player;
      }
    }
    
    if(playerToSave == null) {
      sender.sendMessage("Le nom du joueur à sauver n'est pas valide !");
      return true;
    }
    
    KillerWorker killerWorker = null;
    for(KillerWorker killerWorkerToCheck : MinecraftEventListener.getKilledPlayersWhoCanBeSaved()) {
      if(killerWorkerToCheck.getPlayerKilled().getAccount().getPlayerUUID().equals(playerToSave.getAccount().getPlayerUUID()) && 
          killerWorkerToCheck.getPotentialSavior().getAccount().getPlayerUUID().equals(playerSender.getAccount().getPlayerUUID())) {
        killerWorker = killerWorkerToCheck;
      }
    }
    
    if(killerWorker == null) {
      playerSender.getAccount().getPlayer().sendMessage("Vous ne pouvez pas (encore ?) le sauver.");
      return true;
    }
    
    killerWorker.setHasBeenSaved(true);
    ((SaviorRole) playerSender.getRole()).setPlayerSaved(true);
    
    playerSender.getAccount().getPlayer().sendMessage("Vous avez bien sauvé le joueur " + playerToSave.getAccount().getPlayer().getName() + " !");
    
    return true;
  }

}
