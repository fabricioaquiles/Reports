package com.dustdev.specterreports;

import com.dustdev.specterreports.commands.registry.CommandRegistry;
import com.dustdev.specterreports.configuration.registry.ConfigurationRegistry;
import com.dustdev.specterreports.dao.ReportsDAO;
import com.dustdev.specterreports.listener.registry.ListenerRegistry;
import com.dustdev.specterreports.nms.NMSUtil;
import com.dustdev.specterreports.sql.SQLProvider;
import com.henryfabio.sqlprovider.connector.SQLConnector;
import com.henryfabio.sqlprovider.executor.SQLExecutor;
import io.netty.buffer.ByteBuf;
import me.bristermitten.pdm.PluginDependencyManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;
    public ReportsDAO reportsDAO;
    public SQLConnector sqlConnector;
    public SQLExecutor sqlExecutor;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        PluginDependencyManager.of(this).loadAllDependencies().thenRun(() -> {
            try {
                instance = this;

                ConfigurationRegistry.of(this).register();
                ListenerRegistry.of(this).register();
                CommandRegistry.of(this).register();

                sqlConnector = SQLProvider.of(instance).setup();
                sqlExecutor = new SQLExecutor(sqlConnector);

                reportsDAO = new ReportsDAO(sqlExecutor);
                reportsDAO.createTable();

                new NMSUtil(this).setupNMS();

                Bukkit.getConsoleSender().sendMessage("§b[SpecterReports]§f plugin iniciado com sucesso!");

            } catch (Throwable t) {
                t.printStackTrace();
                Bukkit.getConsoleSender().sendMessage("§b[SpecterReports]§f não foi possível iniciar o plugin!");
                Bukkit.getPluginManager().disablePlugin(this);
            }

        });

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static void sendPacket(Player player, ByteBuf buf) { NMSUtil.getNMS().sendPacket(player, buf); }
}
