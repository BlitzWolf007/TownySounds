package blitzwolf007.townySounds;

import com.palmergames.bukkit.towny.event.NewTownEvent;
import com.palmergames.bukkit.towny.event.PreDeleteTownEvent;
import com.palmergames.bukkit.towny.event.TownAddResidentEvent;
import com.palmergames.bukkit.towny.event.TownInvitePlayerEvent;
import com.palmergames.bukkit.towny.event.TownRemoveResidentEvent;
import com.palmergames.bukkit.towny.event.town.TownMayorChangedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TownEvents implements Listener {

    // New, delete

    @EventHandler
    public void onNew(NewTownEvent event) {
        Utils.notifyEveryone(Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

    @EventHandler
    public void onPreDelete(PreDeleteTownEvent event) {
        Utils.notifyEveryone(Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

    // Leader

    @EventHandler
    public void onMayorChanged(TownMayorChangedEvent event) {
        Utils.notifyTown(event.getTown(), Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

    // Resident related

    @EventHandler
    public void onInvitePlayer(TownInvitePlayerEvent event) {
        Utils.notifyTown(event.getInvite().getSender(), Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

    @EventHandler
    public void onTownAddResident(TownAddResidentEvent event) {
        Utils.notifyTown(event.getTown(), Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

    @EventHandler
    public void onTownRemoveResidentEvent(TownRemoveResidentEvent event) {
        Utils.notifyTown(event.getTown(), Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

}
