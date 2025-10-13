package blitzwolf007.townySounds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("townysounds.reload")) {
            sender.sendMessage("§cYou don't have permission to run this command!");
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            Config.reload();
            sender.sendMessage("Reloaded configuration.");
        } else {
            sender.sendMessage("Invalid usage. Try: /townysounds reload");
        }

        return true;
    }
}
