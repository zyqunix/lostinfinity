package xol.lostinfinity.common.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.common.ClientProxy;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.serverbound.PacketDismount;
import xol.lostinfinity.common.packets.serverbound.PacketItemMode;
import xol.lostinfinity.common.packets.serverbound.PacketSetBonus;
import xol.lostinfinity.mob.entity.base.EntityMultipleLivesMount;
import xol.lostinfinity.util.player.PlayerManager;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/events/EventsPlayerInput.class */
public class EventsPlayerInput {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (ClientProxy.armorSetBonus.func_151468_f() && PlayerManager.isWearingAnySet(Minecraft.func_71410_x().field_71439_g)) {
            lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketSetBonus());
        }
        if (ClientProxy.itemMode.func_151468_f()) {
            lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketItemMode());
        }
        if (ClientProxy.dismount.func_151468_f() && (Minecraft.func_71410_x().field_71439_g.func_184208_bv() instanceof EntityMultipleLivesMount)) {
            lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketDismount());
        }
    }
}
