package com.dustdev.specterreports.listener;

import com.dustdev.specterreports.configuration.values.MensagensValue;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import com.dustdev.specterreports.SpecterReports;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void click(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getName().startsWith("Report : ")) {
            e.setCancelled(true);
            if(e.getRawSlot() == 12) {
                String jogador = e.getInventory().getName().split(":")[1].replaceAll(" ", "");
                Player target = Bukkit.getPlayerExact(jogador);
                if(target == null) {
                    p.sendMessage("§cNão foi possivel se teleportar ao jogador, pois ele se encontra offline.");
                    return;
                }
                p.teleport(target.getLocation());
            }
            if(e.getRawSlot() == 14) {
                String jogador = e.getInventory().getName().split(":")[1].replaceAll(" ", "");
                SpecterReports.instance.reportsDAO.deleteOne(jogador);
                p.sendMessage(MensagensValue.get(MensagensValue::excluiu).replace("{player}", jogador));
                p.closeInventory();
            }
        }
    }
}
