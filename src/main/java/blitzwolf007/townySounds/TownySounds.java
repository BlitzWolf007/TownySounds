package blitzwolf007.townySounds;

import co.aikar.commands.PaperCommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TownySounds extends JavaPlugin {

    @Override
    public void onEnable() {
        new PaperCommandManager(this).registerCommand(new ReloadCommand());

        getServer().getPluginManager().registerEvents(new NationEvents(), this);
        getServer().getPluginManager().registerEvents(new OtherEvents(), this);
        getServer().getPluginManager().registerEvents(new TownEvents(), this);

        Config.init(this);
    }

}
