package com.dustdev.specterreports.nms;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BookMeta;


public class NMS_V1_8_R3 implements NMS {

    @Override
    public void sendPacket(Player player, ByteBuf buf) {
        PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("MC|BOpen", new PacketDataSerializer(buf));


        CraftPlayer craftPlayer = (CraftPlayer)player;
        (craftPlayer.getHandle()).playerConnection.sendPacket(packet);
    }


    @Override
    public void buildPage(Player player, BookMeta meta, List<String> configPages, Player name) {
        List<IChatBaseComponent> pages = new ArrayList<IChatBaseComponent>();
        List<IChatBaseComponent> bookPages = null;
        try {
            bookPages = (List)org.bukkit.craftbukkit.v1_8_R3.inventory.CraftMetaBook.class.getDeclaredField("pages").get(meta);
        } catch (IllegalArgumentException|IllegalAccessException|NoSuchFieldException|SecurityException e) {

            e.printStackTrace();
        }
        for (String page : configPages) {
            TextComponent tc = NMSUtil.sendMotivos(player, page, name);
            pages.add(IChatBaseComponent.ChatSerializer.a(ComponentSerializer.toString(tc)));
        }
        bookPages.addAll(pages);
    }
}
