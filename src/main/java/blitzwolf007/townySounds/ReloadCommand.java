package blitzwolf007.townySounds;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;

@CommandAlias("townysounds|tws")
public class ReloadCommand extends BaseCommand {
    @Default
    @Description("Lists the version of the plugin")
    public void onTownySounds(CommandSender player) {
        player.sendMessage(TownySounds.getInstance().toString());
    }

    @Subcommand("reload")
    @Description("Reloads the config")
    @CommandPermission("townysounds.reload")
    public void onReload(CommandSender sender) {
        TownySounds.getInstance().reloadConfig();
        TownySounds.getInstance().loadConfig();
        sender.sendMessage(Component.text("Config reloaded!", NamedTextColor.GREEN));
    }
}
