package main.com.fabou.mclogger;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@Mod.EventBusSubscriber
public class PlayerDataLogger {
    private static final String DATA_FILE = "data.json";
    private static Timer timer;

    public static void startLogging() {
        timer = new Timer(true); // Démarre un timer en mode démon pour que le processus s'arrête automatiquement lorsque le serveur s'arrête
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                logPlayerData();
            }
        }, 0, 1000); // Log toutes les secondes
    }

    private static void logPlayerData() {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) return;

        int playersOnline = server.getPlayerList().getPlayerCount();

        String jsonData = String.format("{\"timestamp\": %d, \"joueurs_en_ligne\": %d}\n",
                System.currentTimeMillis(), playersOnline);

        try (FileWriter writer = new FileWriter(DATA_FILE, true)) {
            writer.write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}