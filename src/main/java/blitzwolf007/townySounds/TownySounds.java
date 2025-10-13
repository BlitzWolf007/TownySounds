package blitzwolf007.townySounds;

import org.bukkit.plugin.java.JavaPlugin;

public final class TownySounds extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("townysounds").setExecutor(new Commands());

        getServer().getPluginManager().registerEvents(new NationEvents(), this);
        getServer().getPluginManager().registerEvents(new TownEvents(), this);

        Config.init(this);
    }
}
