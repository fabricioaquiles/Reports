package com.dustdev.specterreports.commands.registry;

import com.dustdev.specterreports.Main;
import com.dustdev.specterreports.commands.ReportsCommand;
import lombok.Data;
import lombok.Getter;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import org.bukkit.Bukkit;

@Getter
@Data(staticConstructor = "of")
public class CommandRegistry {

    private final Main plugin;

    public void register() {
        try {
            BukkitFrame bukkitFrame = new BukkitFrame(plugin);
            bukkitFrame.registerCommands(new ReportsCommand());
            Bukkit.getConsoleSender().sendMessage("§b[SpecterReports] [Comandos] §fcomandos carregados com sucesso.");
        }catch (Throwable t) {
            t.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§b[SpecterReports] [Comandos] §fnão foi possível fazer o carregamento dos comandos.");
        }
    }
}
