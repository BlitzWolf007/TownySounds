package blitzwolf007.townySounds;

import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Utils {

    public static void notifyEveryone(SoundTriplet st) {
        if (st.sound() == null)
            return;
        for (Player player : Bukkit.getOnlinePlayers())
            player.playSound(player.getLocation(), st.sound(), st.volume(), st.pitch());
    }

    public static void notifyNation(Nation nation, SoundTriplet st) {
        if (st.sound() == null)
            return;
        for (Resident resident : nation.getResidents())
            if (resident.isOnline()) {
                Player player = resident.getPlayer();
                if (player != null)
                    player.playSound(player.getLocation(), st.sound(), st.volume(), st.pitch());
            }
    }

    public static void notifyTown(Town town, SoundTriplet st) {
        if (st.sound() == null)
            return;
        for (Resident resident : town.getResidents())
            if (resident.isOnline()) {
                Player player = resident.getPlayer();
                if (player != null)
                    player.playSound(player.getLocation(), st.sound(), st.volume(), st.pitch());
            }
    }

}
