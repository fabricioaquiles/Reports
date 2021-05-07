package com.dustdev.specterreports.inventory;

import com.dustdev.specterreports.utils.Scroller;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import com.dustdev.specterreports.SpecterReports;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportsInventory {

    public void open(Player p) {
        List<ItemStack> itens = new ArrayList<>();
        SpecterReports.instance.reportsDAO.selectAll().forEach(key -> {

            ItemStack item = new ItemStack(Material.SKULL_ITEM);
            item.setDurability((short) 3);
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setOwner(key.getPlayer());
            meta.setDisplayName("§a"+key.getPlayer());
            meta.setLore(Arrays.asList(
                    "",
                    "§7ID: §f#"+key.getId(),
                    "§7Player: §f"+key.getPlayer(),
                    "§7Autor: §f"+key.getAutor(),
                    "§7Data: §f"+new SimpleDateFormat("dd-MM-yyyy HH:mm").format(key.getData()),
                    "§7Motivo §f"+key.getMotivo().replace("_", " "),
                    "",
                    "§aClique para mais detalhes."
            ));
            item.setItemMeta(meta);

            itens.add(item);
        });

        if(itens.size() == 0 ) {
            p.sendMessage("§cNo momento não há nenhum report.");
            return;
        }

        Scroller scroller = new Scroller.ScrollerBuilder()
                .withSize(54)
                .withName("Reports")
                .withItems(itens)
                .withItemsSlots(10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,37,38,39,40,41,42,43)
                .withArrowsSlots(45, 53)
                .withOnClick(new Scroller.ChooseItemRunnable() {
                    @Override
                    public void run(Player player, ItemStack item) {
                        SpecterReports.instance.reportsDAO.selectAll().forEach(key -> {
                            if (item.getItemMeta().getDisplayName().replace("§a", "").equalsIgnoreCase(key.getPlayer())) {
                                new DetalhesInventory().open(player, key.getPlayer());
                            }
                        });
                    }
                })
                .build();

        scroller.open(p);
    }
}
