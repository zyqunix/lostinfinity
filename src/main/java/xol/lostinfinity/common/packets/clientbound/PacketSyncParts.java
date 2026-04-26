package xol.lostinfinity.common.packets.clientbound;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xol.lostinfinity.mob.entity.classify.ILostMultiPart;
import xol.lostinfinity.mob.entity.classify.IRelay;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/clientbound/PacketSyncParts.class */
public class PacketSyncParts implements IMessage {
    private int parentId;
    private int partId;
    private double posX;
    private double posY;
    private double posZ;
    private float yaw;
    private float pitch;

    public PacketSyncParts() {
    }

    public PacketSyncParts(IRelay<?> part) {
        this.parentId = part.mo307getRelay().func_145782_y();
        this.partId = part.getId();
        this.posX = part.getX();
        this.posY = part.getY();
        this.posZ = part.getZ();
        this.yaw = part.getYaw();
        this.pitch = part.getPitch();
    }

    public void fromBytes(ByteBuf b) {
        PacketBuffer buf = new PacketBuffer(b);
        this.parentId = buf.func_150792_a();
        this.partId = buf.func_150792_a();
        this.posX = buf.readDouble();
        this.posY = buf.readDouble();
        this.posZ = buf.readDouble();
        this.yaw = buf.readFloat();
        this.pitch = buf.readFloat();
    }

    public void toBytes(ByteBuf b) {
        PacketBuffer buf = new PacketBuffer(b);
        buf.func_150787_b(this.parentId);
        buf.func_150787_b(this.partId);
        buf.writeDouble(this.posX);
        buf.writeDouble(this.posY);
        buf.writeDouble(this.posZ);
        buf.writeFloat(this.yaw);
        buf.writeFloat(this.pitch);
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/clientbound/PacketSyncParts$SyncPartsPacketHandler.class */
    public static class SyncPartsPacketHandler implements IMessageHandler<PacketSyncParts, IMessage> {
        public IMessage onMessage(PacketSyncParts message, MessageContext ctx) {
            IRelay[] iRelayArrFunc_70021_al;
            Entity main = Minecraft.func_71410_x().field_71441_e.func_73045_a(message.parentId);
            if ((main instanceof ILostMultiPart) && (iRelayArrFunc_70021_al = main.func_70021_al()) != null && message.partId < iRelayArrFunc_70021_al.length) {
                IRelay iRelay = iRelayArrFunc_70021_al[message.partId];
                if (iRelay instanceof IRelay) {
                    iRelay.setPos(message.posX, message.posY, message.posZ, message.yaw, message.pitch);
                    return null;
                }
                return null;
            }
            return null;
        }
    }
}
