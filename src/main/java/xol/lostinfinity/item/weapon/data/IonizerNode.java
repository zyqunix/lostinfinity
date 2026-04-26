package xol.lostinfinity.item.weapon.data;
import java.util.ArrayList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
public class IonizerNode {
    private EntityLivingBase origin;
    private Vec3d originPos;
    private double originHeight;
    private ArrayList<IonizerNode> targets = null;
    private int timer = 0;
    private boolean active = false;
    public IonizerNode(EntityLivingBase origin) {
        this.origin = null;
        this.originPos = null;
        this.originHeight = 0.0d;
        this.origin = origin;
        this.originPos = origin.func_174791_d();
        this.originHeight = origin.field_70131_O;
    }
    public void updatePos() {
        if (this.origin != null && !this.origin.field_70128_L) {
            this.originPos = this.origin.func_174791_d();
        }
    }
    public double getHeight() {
        return this.originHeight;
    }
    public boolean isActive() {
        return this.active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public ArrayList<IonizerNode> getTargets() {
        return this.targets;
    }
    public void setTimer(int timer) {
        this.timer = timer;
    }
    public void setTargets(ArrayList<IonizerNode> targets) {
        this.targets = targets;
    }
    public EntityLivingBase getOrigin() {
        return this.origin;
    }
    public Vec3d getOriginPos() {
        return this.originPos;
    }
    public int getTimer() {
        return this.timer;
    }
}
