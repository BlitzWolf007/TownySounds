package blitzwolf007.townySounds;

import com.palmergames.bukkit.towny.event.*;
import com.palmergames.bukkit.towny.event.nation.*;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NationEvents implements Listener {

    private void notifyNation(Nation nation, Sound sound) {
        if (sound == null)
            return;
        for (Resident resident : nation.getResidents())
            if (resident.isOnline()) {
                Player player = resident.getPlayer();
                if (player != null)
                    player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
            }
    }

    // New & delete

    @EventHandler
    public void onNew(NewNationEvent event) {
        Sound sound = Config.getSound(this.getClass(), event.getClass());
        if (sound == null)
            return;
        for (Player player : Bukkit.getOnlinePlayers())
            player.playSound(player.getLocation(), sound, 1f, 1f);
    }

    @EventHandler
    public void onPreDelete(PreDeleteNationEvent event) {
        Sound sound = Config.getSound(this.getClass(), event.getClass());
        if (sound == null)
            return;
        for (Player player : Bukkit.getOnlinePlayers())
            player.playSound(player.getLocation(), sound, 1f, 1f);
    }

    // Leader

    @EventHandler
    public void onKingChange(NationKingChangeEvent event) {
        for (Player player : Bukkit.getOnlinePlayers())
            notifyNation(event.getNation(), Config.getSound(this.getClass(), event.getClass()));
    }

    // Town related

    @EventHandler
    public void onInviteTown(NationInviteTownEvent event) {
        notifyNation(event.getInvite().getSender(), Config.getSound(this.getClass(), event.getClass()));
    }

    @EventHandler
    public void onAddTown(NationAddTownEvent event) {
        notifyNation(event.getNation(), Config.getSound(this.getClass(), event.getClass()));
    }

    @EventHandler
    public void onRemoveTown(NationRemoveTownEvent event) {
        notifyNation(event.getNation(), Config.getSound(this.getClass(), event.getClass()));
    }

    // Ally related

    @EventHandler
    public void onRequestAllyNation(NationRequestAllyNationEvent event) {
        notifyNation(event.getInvite().getSender(), Config.getSound(this.getClass(), event.getClass()));
    }

    @EventHandler
    public void onPreAddAlly(NationPreAddAllyEvent event) {
        notifyNation(event.getNation(), Config.getSound(this.getClass(), event.getClass()));
    }

    @EventHandler
    public void onRemoveAlly(NationRemoveAllyEvent event) {
        notifyNation(event.getNation(), Config.getSound(this.getClass(), event.getClass()));
    }

    // Enemy related

    @EventHandler
    public void onAddEnemy(NationAddEnemyEvent event) {
        notifyNation(event.getNation(), Config.getSound(this.getClass(), event.getClass()));
    }

    @EventHandler
    public void onRemoveEnemy(NationRemoveEnemyEvent event) {
        notifyNation(event.getNation(), Config.getSound(this.getClass(), event.getClass()));
    }

}
