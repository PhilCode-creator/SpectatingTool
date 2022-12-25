package de.philipp.spec;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Spec implements CommandExecutor {
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

        if(args.length == 1) {
            if(player.getName().equalsIgnoreCase(args[0])) {
                player.sendMessage("§cDu kannst dich nicht selbst spectaten");
                return false;
            }

            if(Spec.getInstance().getSpectating().containsKey(player)) {
                player.sendMessage("§cDu Beobachtest bereits einen Spieler!");
                player.sendMessage("§cBenutze /endSpec");
                return false;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null) {
                player.sendMessage("§cDieser Spieler ist nicht Online!");
                return false;
            }
            target.hidePlayer(player);
            Spec.getInstance().getOldLocation().put(player, player.getLocation());
            Spec.getInstance().getSpectating().put(player, target.getName());
            Spec.getInstance().getGettingSpeced().put(target.getName(), player);
            player.sendMessage("§aDu beobachstest nun §e" + target.getName());
            player.teleport(target);
            player.setGameMode(GameMode.SPECTATOR);
        } else {
            player.sendMessage("§cBenutze: /spec <Spieler>");
        }

        return false;
    }
}
