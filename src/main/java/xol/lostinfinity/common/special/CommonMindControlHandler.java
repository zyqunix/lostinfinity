package xol.lostinfinity.common.special;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumActionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.clientbound.PacketSetControlled;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/special/CommonMindControlHandler.class */
public class CommonMindControlHandler {
    public static CommonMindControlHandler INSTANCE;
    private final Map<UUID, EntityPlayer> puppeteers = new ConcurrentHashMap();
    private final Map<UUID, EntityPlayer> controlled = new ConcurrentHashMap();

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/special/CommonMindControlHandler$State.class */
    public enum State {
        NONE,
        CONTROLLED,
        CONTROLLING
    }

    public CommonMindControlHandler() {
        INSTANCE = this;
    }

    @SubscribeEvent
    public void onClientDisconnect(PlayerEvent.PlayerLoggedOutEvent event) {
        onDisconnect(event.player);
    }

    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent event) {
        if (this.controlled.containsKey(event.getEntityPlayer().func_110124_au())) {
            event.setCancellationResult(EnumActionResult.PASS);
            if (event.isCancelable()) {
                event.setCanceled(true);
            }
        }
    }

    public static void registerPair(EntityPlayer puppeteer, EntityPlayer target) {
        INSTANCE.puppeteers.put(puppeteer.func_110124_au(), target);
        INSTANCE.controlled.put(target.func_110124_au(), puppeteer);
    }

    public static void unregisterPair(EntityPlayer puppeteer, EntityPlayer target) {
        INSTANCE.puppeteers.remove(puppeteer.func_110124_au());
        INSTANCE.controlled.remove(target.func_110124_au());
    }

    public static void onDisconnect(EntityPlayer disconnected) {
        EntityPlayerMP entityPlayerMPRemoveControllerOfPlayer = removeControllerOfPlayer(disconnected.func_110124_au());
        if (entityPlayerMPRemoveControllerOfPlayer != null) {
            lostinfinity.instance.packetHandler.sendToPlayer(entityPlayerMPRemoveControllerOfPlayer, new PacketSetControlled());
        }
        EntityPlayerMP entityPlayerMPRemoveTargetOfPlayer = removeTargetOfPlayer(disconnected.func_110124_au());
        if (entityPlayerMPRemoveTargetOfPlayer != null) {
            lostinfinity.instance.packetHandler.sendToPlayer(entityPlayerMPRemoveTargetOfPlayer, new PacketSetControlled());
        }
    }

    public static EntityPlayer getTargetOfPlayer(EntityPlayer player) {
        return getTargetOfPlayer(player.func_110124_au());
    }

    public static EntityPlayer getTargetOfPlayer(UUID uuid) {
        return INSTANCE.puppeteers.get(uuid);
    }

    public static EntityPlayer removeTargetOfPlayer(EntityPlayer player) {
        return removeTargetOfPlayer(player.func_110124_au());
    }

    public static EntityPlayer removeTargetOfPlayer(UUID uuid) {
        return INSTANCE.puppeteers.remove(uuid);
    }

    public static EntityPlayer getControllerOfPlayer(EntityPlayer player) {
        return getControllerOfPlayer(player.func_110124_au());
    }

    public static EntityPlayer getControllerOfPlayer(UUID uuid) {
        return INSTANCE.controlled.get(uuid);
    }

    public static EntityPlayer removeControllerOfPlayer(EntityPlayer player) {
        return removeControllerOfPlayer(player.func_110124_au());
    }

    public static EntityPlayer removeControllerOfPlayer(UUID uuid) {
        return INSTANCE.controlled.remove(uuid);
    }
}
