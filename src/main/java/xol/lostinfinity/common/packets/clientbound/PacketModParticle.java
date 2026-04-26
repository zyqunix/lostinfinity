package xol.lostinfinity.common.packets.clientbound;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xol.lostinfinity.client.fx.ClientParticleRenderer;
import xol.lostinfinity.util.data.CustomParticleConfig;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/clientbound/PacketModParticle.class */
public class PacketModParticle implements IMessage {
    private boolean isSimple;
    private int id;
    private int extra;
    private float x;
    private float y;
    private float z;
    private CustomParticleConfig config;

    public PacketModParticle() {
    }

    public PacketModParticle(int id, int extra, double x, double y, double z) {
        this.isSimple = true;
        this.id = id;
        this.extra = extra;
        this.x = (float) x;
        this.y = (float) y;
        this.z = (float) z;
    }

    public PacketModParticle(CustomParticleConfig config) {
        this.isSimple = false;
        this.config = config;
    }

    public void fromBytes(ByteBuf b) {
        PacketBuffer buf = new PacketBuffer(b);
        this.isSimple = buf.readBoolean();
        if (this.isSimple) {
            this.id = buf.func_150792_a();
            this.extra = buf.func_150792_a();
            this.x = buf.readFloat();
            this.y = buf.readFloat();
            this.z = buf.readFloat();
            return;
        }
        this.config = CustomParticleConfig.read(buf);
    }

    public void toBytes(ByteBuf b) {
        PacketBuffer buf = new PacketBuffer(b);
        buf.writeBoolean(this.isSimple);
        if (this.isSimple) {
            buf.func_150787_b(this.id);
            buf.func_150787_b(this.extra);
            buf.writeFloat(this.x);
            buf.writeFloat(this.y);
            buf.writeFloat(this.z);
            return;
        }
        CustomParticleConfig.write(this.config, buf);
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/clientbound/PacketModParticle$ModParticlePacketHandler.class */
    public static class ModParticlePacketHandler implements IMessageHandler<PacketModParticle, IMessage> {
        public IMessage onMessage(PacketModParticle message, MessageContext ctx) {
            if (message.isSimple) {
                ClientParticleRenderer.renderSimple(message.id, message.extra, message.x, message.y, message.z);
                return null;
            }
            ClientParticleRenderer.renderComplex(message.config);
            return null;
        }
    }
}
