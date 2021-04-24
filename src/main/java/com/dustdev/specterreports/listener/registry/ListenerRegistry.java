package com.dustdev.specterreports.listener.registry;

import com.dustdev.specterreports.Main;
import com.dustdev.specterreports.listener.*;
import lombok.Data;
import org.bukkit.Bukkit;

@Data(staticConstructor = "of")
public class ListenerRegistry {

    private final Main instance;

    public void register() {
        try {

            Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), instance);

            Bukkit.getConsoleSender().sendMessage("§b[SpecterReports] [Eventos]§f eventos carregados com sucesso.");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§b[SpecterReports] [Eventos]§f não foi possível fazer o carregamento dos eventos.");
        }
    }

}
