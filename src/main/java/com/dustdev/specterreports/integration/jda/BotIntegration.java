package com.dustdev.specterreports.integration.jda;

import com.dustdev.specterreports.SpecterReports;
import com.dustdev.specterreports.model.ReportsModel;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import javax.security.auth.login.LoginException;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class BotIntegration {

    public static JDA jda;

    public void start() {
        if(SpecterReports.instance.getConfig().getBoolean("Config.bot.use")) {
            try {
                jda = JDABuilder.createDefault(SpecterReports.instance.getConfig().getString("Config.bot.token")).build();
            } catch (LoginException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(ReportsModel reportsModel) {
        if(SpecterReports.instance.getConfig().getBoolean("Config.bot.use")) {
            MessageEmbed messageEmbed = new EmbedBuilder()
                    .setTitle(":no_entry: Jogador reportado.")
                    .addField("Jogador", reportsModel.getPlayer(), false)
                    .addField("Autor", reportsModel.getAutor(), false)
                    .addField("Motivo", reportsModel.getMotivo().replace("_", " "), false)
                    .addField("Data", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(reportsModel.getData()), false)
                    .setColor(Color.red)
                    .setTimestamp(new Date(System.currentTimeMillis()).toInstant())
                    .build();

            Objects.requireNonNull(jda.getTextChannelById(SpecterReports.instance.getConfig().getString("Config.bot.channel-id"))).sendMessage(messageEmbed).queue();
        }
    }

}
