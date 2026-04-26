package xol.lostinfinity.dimension.data;
import net.minecraft.util.math.BlockPos;
public class TargetNode {
    private BlockPos pos;
    private int timing;
    private int duration;
    private int endTime = 0;
    public TargetNode(BlockPos pos, int timing, int duration) {
        this.pos = null;
        this.timing = 0;
        this.duration = 0;
        this.pos = new BlockPos(pos);
        this.timing = timing;
        this.duration = duration;
        updateEndTime();
    }
    private void updateEndTime() {
        this.endTime = this.timing - this.duration;
    }
    public BlockPos getPos() {
        return this.pos;
    }
    public void setPos(BlockPos pos) {
        this.pos = new BlockPos(pos);
    }
    public int getTiming() {
        return this.timing;
    }
    public void setTiming(int timing) {
        this.timing = timing;
    }
    public int getDuration() {
        return this.duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public int getEndTime() {
        return this.endTime;
    }
}
