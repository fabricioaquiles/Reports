package com.dustdev.specterreports.listener;

import com.dustdev.specterreports.configuration.values.MensagensValue;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import com.dustdev.specterreports.Main;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void click(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getName().startsWith("Report : ")) {
            e.setCancelled(true);
            if(e.getRawSlot() == 13) {
                String jogador = e.getInventory().getName().split(":")[1].replaceAll(" ", "");
                Main.instance.reportsDAO.deleteOne(jogador);
                p.sendMessage(MensagensValue.get(MensagensValue::excluiu).replace("{player}", jogador));
                p.closeInventory();
            }
        }
    }
}
