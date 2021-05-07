package com.dustdev.specterreports.inventory;

import com.dustdev.specterreports.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class DetalhesInventory {

    public void open(Player p, String reportado) {
        Inventory inv = Bukkit.createInventory(null, 3*9, "Report : "+reportado);

        inv.setItem(12, new ItemBuilder(Material.ENDER_PEARL)
                .setName("§aTeleportar")
                .setLore(
                        "§7Clique para teleportar",
                        "§7ao jogador "+reportado
                ).toItemStack());

        inv.setItem(14, new ItemBuilder(Material.BARRIER)
                .setName("§cExcluir reporte")
                .setLore(
                        "§7Clique retirar o reporte",
                        "§7do jogador "+reportado
                ).toItemStack());

        p.openInventory(inv);
    }
}
