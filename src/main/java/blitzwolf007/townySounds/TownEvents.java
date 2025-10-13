package blitzwolf007.townySounds;

import com.palmergames.bukkit.towny.event.nation.NationKingChangeEvent;
import com.palmergames.bukkit.towny.event.town.TownMayorChangedEvent;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.palmergames.bukkit.towny.event.*;

public class TownEvents implements Listener {

    private void notifyTown(Town town, Sound sound) {
        if (sound == null)
            return;
        for (Resident resident : town.getResidents())
            if (resident.isOnline()) {
                Player player = resident.getPlayer();
                if (player != null)
                    player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
            }
    }

    // New, delete & rename

    @EventHandler
    public void onNew(NewTownEvent event) {
        Sound sound = Config.getSound(this.getClass(), event.getClass());
        if (sound == null)
            return;
        for (Player player : Bukkit.getOnlinePlayers())
            player.playSound(player.getLocation(), sound, 1f, 1f);
    }

    @EventHandler
    public void onPreDelete(PreDeleteTownEvent event) {
        Sound sound = Config.getSound(this.getClass(), event.getClass());
        if (sound == null)
            return;
        for (Player player : Bukkit.getOnlinePlayers())
            player.playSound(player.getLocation(), sound, 1f, 1f);
    }

    // Leader

    @EventHandler
    public void onMayorChanged(TownMayorChangedEvent event) {
        notifyTown(event.getTown(), Config.getSound(this.getClass(), event.getClass()));
    }

    // Resident related

    @EventHandler
    public void onInvitePlayer(TownInvitePlayerEvent event) {
        notifyTown(event.getInvite().getSender(), Config.getSound(this.getClass(), event.getClass()));
    }

    @EventHandler
    public void onTownAddResident(TownAddResidentEvent event) {
        notifyTown(event.getTown(), Config.getSound(this.getClass(), event.getClass()));
    }

    @EventHandler
    public void onTownRemoveResidentEvent(TownRemoveResidentEvent event) {
        notifyTown(event.getTown(), Config.getSound(this.getClass(), event.getClass()));
    }

}
