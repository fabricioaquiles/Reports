package com.dustdev.specterreports.nms;

import com.dustdev.specterreports.SpecterReports;
import com.dustdev.specterreports.configuration.values.GeneralValue;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class NMSUtil
{
    private static NMS nms;
    protected final SpecterReports plugin;

    public NMSUtil(SpecterReports plugin) { this.plugin = plugin; }

    public void setupNMS() {
        String version = null;
        try {
            version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        } catch (ArrayIndexOutOfBoundsException e) {
            Bukkit.getPluginManager().disablePlugin(this.plugin);
            return;
        }
        switch (version) {
            case "v1_8_R3":
                try {
                    nms = (NMS)Class.forName("com.dustdev.specterreports.nms.NMS_" + version.toUpperCase()).newInstance();
                } catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return;
        }
        Bukkit.getConsoleSender().sendMessage("§b[SpecterReports] §fa versão do seu servidor, não é compatível com o plugin.");

        Bukkit.getPluginManager().disablePlugin(this.plugin);
    }

    public static NMS getNMS() { return nms; }

    public static TextComponent sendMotivos(Player player, String page, Player name) {
        TextComponent textComponent = new TextComponent("Reportar "+name.getName()+":\n\n");
        for(String key :GeneralValue.get(GeneralValue::book).getStringList(page+".motivos")) {

            TextComponent textComponent1 = new TextComponent(" §l❂ §0"+key.replace("_", " ")+"\n");
            textComponent1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§8Clique para reportar o jogador por "+key.replace("_", " ")).create()));
            textComponent1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reportar "+name.getName()+" "+key));
            textComponent.addExtra(textComponent1);

        }

        return textComponent;
    }

}
