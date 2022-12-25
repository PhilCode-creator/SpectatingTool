package de.philipp.spec;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Spec extends JavaPlugin {

    private static Spec instance;

    public static Spec getInstance() {
        return instance;
    }

    private HashMap<Player, String> spectating;
    private HashMap<String, Player> gettingSpeced;



    private HashMap<Player, Location> oldLocation;
    public HashMap<Player, String> getSpectating() {
        return spectating;
    }

    public HashMap<Player, Location> getOldLocation() {
        return oldLocation;
    }

    public HashMap<String, Player> getGettingSpeced() {
        return gettingSpeced;
    }

    @Override
    public void onEnable() {
        oldLocation = new HashMap<>();
        spectating = new HashMap<>();
        gettingSpeced = new HashMap<>();
        getCommand("spec").setExecutor(new Command_Spec());
        getCommand("endSpec").setExecutor(new Command_EndSpec());
        Bukkit.getPluginManager().registerEvents(new Event_Spec(), this);
        instance = this;
        System.out.println("------------------");
        System.out.println("Spec von CuzImPhilipp");
        System.out.println("Aktiviert");
        System.out.println("------------------");
    }

    @Override
    public void onDisable() {
        System.out.println("------------------");
        System.out.println("Spec von CuzImPhilipp");
        System.out.println("Deaktiviert");
        System.out.println("------------------");
    }
}
