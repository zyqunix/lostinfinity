package xol.lostinfinity.mob.entity.contest.misc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.mob.entity.fungal.EntityFungfly;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class EntityTreadmillObstacle extends EntityImmaterial {
    private static final DataParameter<Integer> VISUAL_STYLE = EntityDataManager.func_187226_a(EntityFungfly.class, DataSerializers.field_187192_b);
    private float speed;
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(VISUAL_STYLE, -1);
    }
    public int getVisual() {
        return ((Integer) this.field_70180_af.func_187225_a(VISUAL_STYLE)).intValue();
    }
    public void setVisual(int v) {
        this.field_70180_af.func_187227_b(VISUAL_STYLE, Integer.valueOf(v));
    }
    public EntityTreadmillObstacle(World worldIn) {
        super(worldIn);
        this.speed = 0.2f;
        func_70105_a(1.0f, 1.0f);
        func_189654_d(true);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            this.field_70181_x = 0.0d;
            this.field_70179_y = 0.0d;
            this.field_70159_w = -this.speed;
            this.field_70133_I = true;
            AxisAlignedBB grid = ContestCoordinates.treadmillGridAABB();
            if (this.field_70165_t < grid.field_72340_a) {
                func_70106_y();
            }
        }
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public boolean func_70067_L() {
        return true;
    }
    public void func_70100_b_(EntityPlayer entityIn) {
        if (!this.field_70170_p.field_72995_K && this.field_70165_t > entityIn.field_70165_t && Math.abs(this.field_70161_v - entityIn.field_70161_v) < 0.3d) {
            entityIn.field_70159_w += this.field_70159_w;
            entityIn.field_70133_I = true;
        }
    }
}
