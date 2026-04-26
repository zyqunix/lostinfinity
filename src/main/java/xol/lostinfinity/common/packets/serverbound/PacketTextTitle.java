package xol.lostinfinity.common.packets.serverbound;

import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketTextTitle.class */
public class PacketTextTitle implements IMessage {
    private boolean title;
    private boolean subtitle;
    private int fadeIn;
    private int fadeOut;
    private int timeUntilFade;
    private String message;

    public PacketTextTitle() {
    }

    public PacketTextTitle(boolean title, boolean subtitle, int timeUntilFade, int fadeIn, int fadeOut, String message) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.fadeOut = fadeOut;
        this.timeUntilFade = timeUntilFade;
        this.message = message;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.title);
        buf.writeBoolean(this.subtitle);
        buf.writeInt(this.fadeIn);
        buf.writeInt(this.fadeOut);
        buf.writeInt(this.timeUntilFade);
        buf.writeInt(this.message.length());
        buf.writeCharSequence(this.message, Charset.defaultCharset());
    }

    public void fromBytes(ByteBuf buf) {
        this.title = buf.readBoolean();
        this.subtitle = buf.readBoolean();
        this.fadeIn = buf.readInt();
        this.fadeOut = buf.readInt();
        this.timeUntilFade = buf.readInt();
        int msgLength = buf.readInt();
        this.message = String.valueOf(buf.readCharSequence(msgLength, Charset.defaultCharset()));
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketTextTitle$TitlePacketHandler.class */
    public static class TitlePacketHandler implements IMessageHandler<PacketTextTitle, IMessage> {
        public IMessage onMessage(PacketTextTitle message, MessageContext ctx) {
            SPacketTitle.Type type;
            if (message.title) {
                type = SPacketTitle.Type.TITLE;
            } else {
                type = message.subtitle ? SPacketTitle.Type.SUBTITLE : SPacketTitle.Type.ACTIONBAR;
            }
            SPacketTitle.Type titleType = type;
            ctx.getServerHandler().field_147369_b.field_71135_a.func_147359_a(new SPacketTitle(titleType, new TextComponentString(message.message), message.fadeIn, message.timeUntilFade, message.fadeOut));
            return null;
        }
    }
}
