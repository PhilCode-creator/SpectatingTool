package de.philipp.spec;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Event_Spec implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(Spec.getInstance().getGettingSpeced().containsKey(event.getPlayer().getName()) &&
                Spec.getInstance().getGettingSpeced().get(event.getPlayer().getName()) != null) {
            event.getPlayer().hidePlayer(Spec.getInstance().getGettingSpeced().get(event.getPlayer().getName()));
        }
    }

}
