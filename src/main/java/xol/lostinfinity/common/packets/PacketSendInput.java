package xol.lostinfinity.common.packets;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import xol.lostinfinity.client.special.ClientMindControlHandler;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.special.CommonMindControlHandler;
public class PacketSendInput implements IMessage {
    private float forward;
    private float side;
    private boolean jump;
    private boolean sneak;
    public PacketSendInput() {
    }
    public PacketSendInput(float forward, float side, boolean jump, boolean sneak) {
        this.forward = forward;
        this.side = side;
        this.jump = jump;
        this.sneak = sneak;
    }
    public void fromBytes(ByteBuf b) {
        PacketBuffer buf = new PacketBuffer(b);
        this.forward = buf.readFloat();
        this.side = buf.readFloat();
        this.jump = buf.readBoolean();
        this.sneak = buf.readBoolean();
    }
    public void toBytes(ByteBuf b) {
        PacketBuffer buf = new PacketBuffer(b);
        buf.writeFloat(this.forward);
        buf.writeFloat(this.side);
        buf.writeBoolean(this.jump);
        buf.writeBoolean(this.sneak);
    }
    static  class AnonymousClass1 {
        static final  int[] $SwitchMap$net$minecraftforge$fml$relauncher$Side = new int[Side.values().length];
        static {
            try {
                $SwitchMap$net$minecraftforge$fml$relauncher$Side[Side.CLIENT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$net$minecraftforge$fml$relauncher$Side[Side.SERVER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }
    public static class SendInputPacketHandler implements IMessageHandler<PacketSendInput, IMessage> {
        public IMessage onMessage(PacketSendInput message, MessageContext ctx) {
            switch (AnonymousClass1.$SwitchMap$net$minecraftforge$fml$relauncher$Side[ctx.side.ordinal()]) {
                case 1:
                    ClientMindControlHandler.INSTANCE.lastForward = message.forward;
                    ClientMindControlHandler.INSTANCE.lastStrafe = message.side;
                    ClientMindControlHandler.INSTANCE.lastJump = message.jump;
                    ClientMindControlHandler.INSTANCE.lastSneak = message.sneak;
                    break;
                case 2:
                    EntityPlayerMP targetOfPlayer = CommonMindControlHandler.getTargetOfPlayer((EntityPlayer) ctx.getServerHandler().field_147369_b);
                    if (targetOfPlayer != null) {
                        lostinfinity.instance.packetHandler.sendToPlayer(targetOfPlayer, message);
                    }
                    break;
            }
            return null;
        }
    }
}
