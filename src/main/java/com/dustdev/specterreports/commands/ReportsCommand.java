package com.dustdev.specterreports.commands;

import com.dustdev.specterreports.configuration.values.MensagensValue;
import com.dustdev.specterreports.inventory.ReportInventory;
import com.dustdev.specterreports.inventory.ReportsInventory;
import com.dustdev.specterreports.model.ReportsModel;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import com.dustdev.specterreports.Main;

import java.text.SimpleDateFormat;

public class ReportsCommand {

    @Command(
            name = "reportar",
            target = CommandTarget.PLAYER
    ) public void reportar(Context<Player> sender, String target, @Optional String motivo) {
        Player p = sender.getSender();
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(target);

        if(motivo == null) {
            new ReportInventory().open(p, offlinePlayer);
            return;
        }

        ReportsModel reportsModel = ReportsModel.builder()
                .player(offlinePlayer.getName())
                .autor(p.getName())
                .data(System.currentTimeMillis())
                .motivo(motivo)
                .build();

        Main.instance.reportsDAO.insertOne(reportsModel);
        p.sendMessage(MensagensValue.get(MensagensValue::reportou).replace("{player}", offlinePlayer.getName()));
        Bukkit.getOnlinePlayers().stream()
                .filter(filter -> filter.hasPermission("reports.usar"))
                .forEach(all -> {
                    MensagensValue.get(MensagensValue::reportado).forEach(msg -> {
                        all.sendMessage(msg.replace("{autor}", reportsModel.getAutor()).replace("{player}", reportsModel.getPlayer()).replace("{motivo}", reportsModel.getMotivo().replace("_", " ")).replace("{data}",  new SimpleDateFormat("dd-MM-yyyy HH:mm").format(reportsModel.getData())));
                    });
                });
    }

    @Command(
            name = "reports",
            permission = "reports.usar",
            target = CommandTarget.PLAYER
    ) public void reports(Context<Player> sender) {
        new ReportsInventory().open(sender.getSender());
    }
}
