package de.philipp.spec;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_EndSpec implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Dazu musst du ein Spieler sein");
            return false;
        }
        Player player = (Player) sender;
        if(!player.hasPermission("spec.use")) {
            player.sendMessage("§cDazu hast du keine Rechte!");
            return false;
        }

        if(!Spec.getInstance().getSpectating().containsKey(player)) {
            player.sendMessage("§cDu beobachtest niemanden!");
            return false;
        }

        player.teleport(Spec.getInstance().getOldLocation().get(player));
        Player target = Bukkit.getPlayer(Spec.getInstance().getSpectating().get(player));
        Spec.getInstance().getGettingSpeced().remove(Spec.getInstance().getSpectating().get(player));

        if(target != null) {
            target.showPlayer(player);
        }

        player.setGameMode(GameMode.SURVIVAL);
        Spec.getInstance().getSpectating().remove(player);
        Spec.getInstance().getOldLocation().remove(player);
        player.sendMessage("§aDu hast das beobachten beendet");
        return false;
    }
}
