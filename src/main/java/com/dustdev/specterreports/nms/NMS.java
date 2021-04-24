package com.dustdev.specterreports.nms;

import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BookMeta;

public interface NMS {

    void sendPacket(Player paramPlayer, ByteBuf paramByteBuf);

    void buildPage(Player paramPlayer, BookMeta paramBookMeta, List<String> paramList, OfflinePlayer paramName);
}
