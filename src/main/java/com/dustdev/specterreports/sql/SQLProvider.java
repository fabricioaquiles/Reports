package com.dustdev.specterreports.sql;

import com.henryfabio.sqlprovider.connector.SQLConnector;
import com.henryfabio.sqlprovider.connector.type.SQLDatabaseType;
import com.henryfabio.sqlprovider.connector.type.impl.MySQLDatabaseType;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Data(staticConstructor = "of")
public class SQLProvider {

    private final JavaPlugin plugin;

    public SQLConnector setup() {
        FileConfiguration config = plugin.getConfig();

        SQLConnector sqlConnector;

        ConfigurationSection mysql = config.getConfigurationSection("MySQL");
        sqlConnector = mysqlDatabaseType(mysql).connect();
        Bukkit.getConsoleSender().sendMessage("§b[SpecterReports] [MySQL]§f conexão com o MySQL efetuada com sucesso, criando tabelas...");

        return sqlConnector;
    }

    private SQLDatabaseType mysqlDatabaseType(ConfigurationSection section) {
        return MySQLDatabaseType.builder()
                .address(section.getString("host"))
                .username(section.getString("user"))
                .password(section.getString("password"))
                .database(section.getString("database"))
                .build();
    }
}
