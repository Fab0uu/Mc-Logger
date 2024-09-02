package main.com.fabou.mclogger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("modname")
public class MainMod {
    public MainMod() {
        // Enregistrer les événements
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Démarre le logger de données de joueurs
        PlayerDataLogger.startLogging();
    }
}