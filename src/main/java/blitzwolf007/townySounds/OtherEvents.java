package blitzwolf007.townySounds;

import com.palmergames.bukkit.towny.event.NewDayEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OtherEvents implements Listener {

    @EventHandler
    public void onNewday(NewDayEvent event) {
        Utils.notifyEveryone(Config.getSoundTriplet(this.getClass(), event.getClass()));
    }

}
