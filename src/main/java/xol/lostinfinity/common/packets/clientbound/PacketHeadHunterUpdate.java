package xol.lostinfinity.common.packets.clientbound;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xol.lostinfinity.client.screen.HeadHunterVision;
public class PacketHeadHunterUpdate implements IMessage {
    private UUID id;
    private float x;
    private float y;
    private float z;
    public PacketHeadHunterUpdate() {
    }
    public PacketHeadHunterUpdate(EntityPlayer target) {
        this.id = target.func_110124_au();
        this.x = (float) target.field_70165_t;
        this.y = (float) target.field_70163_u;
        this.z = (float) target.field_70161_v;
    }
    public void fromBytes(ByteBuf b) {
        PacketBuffer buf = new PacketBuffer(b);
        this.id = buf.func_179253_g();
        this.x = buf.readFloat();
        this.y = buf.readFloat();
        this.z = buf.readFloat();
    }
    public void toBytes(ByteBuf b) {
        PacketBuffer buf = new PacketBuffer(b);
        buf.func_179252_a(this.id);
        buf.writeFloat(this.x);
        buf.writeFloat(this.y);
        buf.writeFloat(this.z);
    }
    public static class HeadHunterUpdatePacketHandler implements IMessageHandler<PacketHeadHunterUpdate, IMessage> {
        public IMessage onMessage(PacketHeadHunterUpdate message, MessageContext ctx) {
            HeadHunterVision.oorPlayer.put(message.id, new Vec3d(message.x, message.y, message.z));
            return null;
        }
    }
}
