package blitzwolf007.townySounds;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends BaseCommand  {
    @Default
    @Description("Reloads the config")
    @CommandPermission("townysounds.reload")
    public void onReload (CommandSender sender) {
        Config.reload();
        sender.sendMessage(Component.text("Config reloaded!", NamedTextColor.GREEN));
    }
}
