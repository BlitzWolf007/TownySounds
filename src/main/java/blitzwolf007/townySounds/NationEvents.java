package blitzwolf007.townySounds;

import com.palmergames.bukkit.towny.event.*;
import com.palmergames.bukkit.towny.event.nation.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NationEvents implements Listener {

    // New & delete

    @EventHandler
    public void onNew(NewNationEvent event) {
        Utils.notifyEveryone(Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

    @EventHandler
    public void onPreDelete(PreDeleteNationEvent event) {
        Utils.notifyEveryone(Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

    // Leader

    @EventHandler
    public void onKingChange(NationKingChangeEvent event) {
        Utils.notifyNation(event.getNation(), Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

    // Town related

    @EventHandler
    public void onInviteTown(NationInviteTownEvent event) {
        Utils.notifyNation(event.getInvite().getSender(), Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

    @EventHandler
    public void onAddTown(NationAddTownEvent event) {
        Utils.notifyNation(event.getNation(), Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

    @EventHandler
    public void onRemoveTown(NationRemoveTownEvent event) {
        Utils.notifyNation(event.getNation(), Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

    // Ally related

    @EventHandler
    public void onRequestAllyNation(NationRequestAllyNationEvent event) {
        Utils.notifyNation(event.getInvite().getSender(), Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

    @EventHandler
    public void onPreAddAlly(NationPreAddAllyEvent event) {
        Utils.notifyNation(event.getNation(), Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

    @EventHandler
    public void onRemoveAlly(NationRemoveAllyEvent event) {
        Utils.notifyNation(event.getNation(), Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

    // Enemy related

    @EventHandler
    public void onAddEnemy(NationAddEnemyEvent event) {
        Utils.notifyNation(event.getNation(), Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

    @EventHandler
    public void onRemoveEnemy(NationRemoveEnemyEvent event) {
        Utils.notifyNation(event.getNation(), Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

}
