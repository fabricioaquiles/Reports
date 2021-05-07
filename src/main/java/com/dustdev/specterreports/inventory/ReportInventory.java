package com.dustdev.specterreports.inventory;

import com.dustdev.specterreports.SpecterReports;
import com.dustdev.specterreports.configuration.values.GeneralValue;
import com.dustdev.specterreports.nms.NMSUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class ReportInventory {

    public void open(Player p, Player name) {
        int slot = p.getInventory().getHeldItemSlot();
        ItemStack oldItem = p.getItemInHand();

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta)book.getItemMeta();
        ConfigurationSection cs = GeneralValue.get(GeneralValue::book);
        List<String> pagesList = new ArrayList<String>();

        for (String key : cs.getKeys(false)) {
            pagesList.add(key);
        }

        meta.setAuthor("Server");
        meta.setTitle("Report");

        NMSUtil.getNMS().buildPage(p, meta, pagesList, name);

        book.setItemMeta(meta);

        p.getInventory().setItem(slot, book);

        ByteBuf buf = Unpooled.buffer(256);
        buf.setByte(0, 0);
        buf.writerIndex(1);
        SpecterReports.sendPacket(p, buf);
        p.getInventory().setItem(slot, oldItem);
    }
}
