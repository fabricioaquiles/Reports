package com.dustdev.specterreports.configuration.registry;

import com.dustdev.specterreports.SpecterReports;
import com.dustdev.specterreports.configuration.values.GeneralValue;
import com.dustdev.specterreports.configuration.values.MensagensValue;
import com.henryfabio.minecraft.configinjector.bukkit.injector.BukkitConfigurationInjector;
import lombok.Data;

@Data(staticConstructor = "of")
public class ConfigurationRegistry {

    private final SpecterReports plugin;

    public void register() {
        BukkitConfigurationInjector configurationInjector = new BukkitConfigurationInjector(plugin);

        configurationInjector.saveDefaultConfiguration(plugin,
                "mensagens.yml"
        );

        configurationInjector.injectConfiguration(
                GeneralValue.instance(),
                MensagensValue.instance()
        );

    }

}
