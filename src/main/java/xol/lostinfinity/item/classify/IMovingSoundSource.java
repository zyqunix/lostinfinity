package xol.lostinfinity.item.classify;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.audio.ISound;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.audio.MovingSoundHandler;
import xol.lostinfinity.client.audio.UniversalMovingSound;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.clientbound.PacketUniversalMovingSound;
public interface IMovingSoundSource {
    public static final AtomicInteger idCounter = new AtomicInteger();
    public static final Special NORMAL = new Special() { // from class: xol.lostinfinity.item.classify.IMovingSoundSource.1
    };
    public static final Special MOVING = new Special() { // from class: xol.lostinfinity.item.classify.IMovingSoundSource.2
        @Override // xol.lostinfinity.item.classify.IMovingSoundSource.Special
        public void onInit(UniversalMovingSound sound) {
            sound.setAttenuationType(ISound.AttenuationType.NONE);
        }
    };
    default void playSound(SoundEvent soundIn, SoundCategory categoryIn, float volume, float pitch) {
        playSound(soundIn, categoryIn, NORMAL, volume, pitch);
    }
    default void playSound(SoundEvent soundIn, SoundCategory categoryIn, Special special, float volume, float pitch) {
        lostinfinity.proxy.playMovingSound(soundIn, categoryIn, special, volume, pitch);
    }
    default int playSoundAround(SoundEvent soundIn, SoundCategory categoryIn, Entity source, float volume, float pitch, boolean repeat, int repeatDelay) {
        int soundId = idCounter.getAndIncrement();
        lostinfinity.instance.packetHandler.sendToPlayerExcept(source, new PacketUniversalMovingSound(soundId, soundIn, categoryIn, source.func_145782_y(), volume, pitch, repeat, repeatDelay));
        return soundId;
    }
    default void stopSoundAround(int soundId, Entity source) {
        lostinfinity.instance.packetHandler.sendToPlayerExcept(source, new PacketUniversalMovingSound(soundId));
    }
    public interface Special {
        default void onInit(UniversalMovingSound sound) {
        }
        default void onUpdate(UniversalMovingSound sound) {
        }
        default void onDone(UniversalMovingSound sound) {
        }
    }
    public static class Repeating implements Special {
        private final int repeatDelay;
        public Repeating(int repeatDelay) {
            this.repeatDelay = repeatDelay;
        }
        @Override // xol.lostinfinity.item.classify.IMovingSoundSource.Special
        public void onInit(UniversalMovingSound sound) {
            sound.setRepeat(true);
            sound.setRepeatDelay(this.repeatDelay);
            sound.setAttenuationType(ISound.AttenuationType.NONE);
        }
    }
    @SideOnly(Side.CLIENT)
    public static class Follower implements Special {
        private final int soundId;
        private final Entity source;
        private final boolean repeat;
        private final int repeatDelay;
        public Follower(int soundId, Entity source) {
            this(soundId, source, false, 0);
        }
        public Follower(int soundId, Entity source, boolean repeat, int repeatDelay) {
            this.soundId = soundId;
            this.source = source;
            this.repeat = repeat;
            this.repeatDelay = repeatDelay;
        }
        @Override // xol.lostinfinity.item.classify.IMovingSoundSource.Special
        public void onInit(UniversalMovingSound sound) {
            sound.setRepeat(this.repeat);
            sound.setRepeatDelay(this.repeatDelay);
        }
        @Override // xol.lostinfinity.item.classify.IMovingSoundSource.Special
        public void onUpdate(UniversalMovingSound sound) {
            sound.setXPosF((float) this.source.field_70165_t);
            sound.setYPosF((float) this.source.field_70163_u);
            sound.setZPosF((float) this.source.field_70161_v);
        }
        @Override // xol.lostinfinity.item.classify.IMovingSoundSource.Special
        public void onDone(UniversalMovingSound sound) {
            MovingSoundHandler.instance.stopSound(this.soundId);
        }
    }
}
