package xol.lostinfinity.common.packets.clientbound;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xol.lostinfinity.client.audio.MovingSoundHandler;
import xol.lostinfinity.client.audio.UniversalMovingSound;
import xol.lostinfinity.item.classify.IMovingSoundSource;
public class PacketUniversalMovingSound implements IMessage {
    private boolean isPlay;
    private int soundId;
    private SoundEvent sound;
    private SoundCategory category;
    private int sourceId;
    private float soundVolume;
    private float soundPitch;
    private boolean repeat;
    private int repeatDelay;
    public PacketUniversalMovingSound() {
    }
    public PacketUniversalMovingSound(int soundId) {
        this.soundId = soundId;
        this.isPlay = false;
    }
    public PacketUniversalMovingSound(int soundId, SoundEvent sound, SoundCategory category, int sourceId, float soundVolume, float soundPitch, boolean repeat, int repeatDelay) {
        this.soundId = soundId;
        this.isPlay = true;
        this.sound = sound;
        this.category = category;
        this.sourceId = sourceId;
        this.soundVolume = soundVolume;
        this.soundPitch = soundPitch;
        this.repeat = repeat;
        this.repeatDelay = repeatDelay;
    }
    public void fromBytes(ByteBuf b) {
        PacketBuffer buf = new PacketBuffer(b);
        this.soundId = buf.func_150792_a();
        this.isPlay = buf.readBoolean();
        if (this.isPlay) {
            this.sound = (SoundEvent) SoundEvent.field_187505_a.func_148754_a(buf.func_150792_a());
            this.category = buf.func_179257_a(SoundCategory.class);
            this.sourceId = buf.func_150792_a();
            this.soundVolume = buf.readFloat();
            this.soundPitch = buf.readFloat();
            this.repeat = buf.readBoolean();
            this.repeatDelay = buf.readInt();
        }
    }
    public void toBytes(ByteBuf b) {
        PacketBuffer buf = new PacketBuffer(b);
        buf.func_150787_b(this.soundId);
        buf.writeBoolean(this.isPlay);
        if (this.isPlay) {
            buf.func_150787_b(SoundEvent.field_187505_a.func_148757_b(this.sound));
            buf.func_179249_a(this.category);
            buf.func_150787_b(this.sourceId);
            buf.writeFloat(this.soundVolume);
            buf.writeFloat(this.soundPitch);
            buf.writeBoolean(this.repeat);
            buf.writeInt(this.repeatDelay);
        }
    }
    public static class UniversalMovingSoundPacketHandler implements IMessageHandler<PacketUniversalMovingSound, IMessage> {
        public IMessage onMessage(PacketUniversalMovingSound message, MessageContext ctx) {
            if (!message.isPlay) {
                MovingSoundHandler.instance.stopSound(message.soundId);
                return null;
            }
            Entity source = Minecraft.func_71410_x().field_71441_e.func_73045_a(message.sourceId);
            if (source == null) {
                return null;
            }
            UniversalMovingSound sound = new UniversalMovingSound(message.sound, message.category, new IMovingSoundSource.Follower(message.soundId, source, message.repeat, message.repeatDelay), message.soundVolume, message.soundPitch);
            MovingSoundHandler.instance.trackSound(message.soundId, sound);
            return null;
        }
    }
}
