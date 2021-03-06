package ch.kalunight.uhclg.model;

import org.bukkit.scoreboard.Scoreboard;
import ch.kalunight.uhclg.model.role.RoleImpl;

public class PlayerData {
  
  private LinkedDiscordAccount account;
  
  private RoleImpl role;
  
  private boolean infected;
  
  private Scoreboard scoreboard;
  
  private boolean alive;
  
  private boolean minecraftConnected;
  
  private boolean discordVoiceConnected;
  
  private boolean inLove;
  
  public PlayerData(LinkedDiscordAccount account) {
    this.account = account;
    this.alive = true;
    this.minecraftConnected = true;
    this.discordVoiceConnected = true;
  }

  public LinkedDiscordAccount getAccount() {
    return account;
  }

  public void setAccount(LinkedDiscordAccount account) {
    this.account = account;
  }

  public RoleImpl getRole() {
    return role;
  }

  public void setRole(RoleImpl role) {
    this.role = role;
  }

  public Scoreboard getScoreboard() {
    return scoreboard;
  }

  public void setScoreboard(Scoreboard scoreboard) {
    this.scoreboard = scoreboard;
  }

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }
  
  public boolean isConnected() {
    return minecraftConnected && discordVoiceConnected;
  }

  public boolean isMinecraftConnected() {
    return minecraftConnected;
  }

  public void setMinecraftConnected(boolean connected) {
    this.minecraftConnected = connected;
  }

  public boolean isDiscordVoiceConnected() {
    return discordVoiceConnected;
  }

  public void setDiscordVoiceConnected(boolean discordVoiceConnected) {
    this.discordVoiceConnected = discordVoiceConnected;
  }

  public boolean isInLove() {
    return inLove;
  }

  public void setInLove(boolean inLove) {
    this.inLove = inLove;
  }

  public boolean isInfected() {
    return infected;
  }

  public void setInfected(boolean infected) {
    this.infected = infected;
  }
  
}
