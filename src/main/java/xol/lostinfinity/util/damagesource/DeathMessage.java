package xol.lostinfinity.util.damagesource;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/damagesource/DeathMessage.class */
public class DeathMessage {
    public static void broadcastDeathMessage(MinecraftServer server, String message) {
        server.func_184103_al().func_148539_a(new TextComponentString(message));
    }
}
