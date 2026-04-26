package xol.lostinfinity.client.audio;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class MovingSoundHandler {
    public static final MovingSoundHandler instance = new MovingSoundHandler();
    private final Map<Integer, UniversalMovingSound> activeSounds = Maps.newConcurrentMap();
    public void trackSound(int sourceId, UniversalMovingSound sound) {
        if (this.activeSounds.containsKey(Integer.valueOf(sourceId))) {
            return;
        }
        Minecraft.func_71410_x().func_147118_V().func_147682_a(sound);
        this.activeSounds.put(Integer.valueOf(sourceId), sound);
    }
    public void stopSound(int sourceId) {
        UniversalMovingSound sound = this.activeSounds.remove(Integer.valueOf(sourceId));
        if (sound != null) {
            sound.setDonePlaying(true);
        }
    }
}
