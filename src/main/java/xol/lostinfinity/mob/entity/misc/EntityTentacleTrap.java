package xol.lostinfinity.mob.entity.misc;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityTentacleTrap extends EntityLiving implements IMaxAttack {
    private static final DataParameter<Float> TARGET_HEIGHT = EntityDataManager.func_187226_a(EntityTentacleTrap.class, DataSerializers.field_187193_c);
    private EntityPlayer owner;
    private EntityLivingBase target;
    private float tentacleAngle;
    public EntityTentacleTrap(World worldIn) {
        super(worldIn);
        this.owner = null;
        this.target = null;
        this.tentacleAngle = 3.0f;
        func_70105_a(1.0f, 1.0f);
        func_184224_h(true);
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(TARGET_HEIGHT, Float.valueOf(1.0f));
    }
    public float getTargetHeight() {
        return ((Float) this.field_70180_af.func_187225_a(TARGET_HEIGHT)).floatValue();
    }
    public void setTargetHeight(float i) {
        this.field_70180_af.func_187227_b(TARGET_HEIGHT, Float.valueOf(i));
    }
    public boolean func_70067_L() {
        return false;
    }
    public void setOwner(EntityPlayer play) {
        this.owner = play;
    }
    public void setTarget(EntityLivingBase t) {
        this.target = t;
    }
    public EntityLivingBase getTarget() {
        return this.target;
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5000.0d);
    }
    public float getTentacleAngle() {
        return this.tentacleAngle;
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (this.field_70170_p.field_72995_K) {
            if (this.tentacleAngle > 1.5707963267948966d) {
                this.tentacleAngle -= 0.05f;
            }
        } else {
            if (this.target == null || this.target.field_70128_L || this.field_70173_aa > 300) {
                func_70106_y();
                return;
            }
            setTargetHeight(this.target.field_70131_O);
            this.target.func_70634_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.target.field_70159_w = 0.0d;
            this.target.field_70181_x = 0.0d;
            this.target.field_70179_y = 0.0d;
            this.target.field_70133_I = true;
            if (this.field_70173_aa % 20 == 0) {
                IMaxAttack.dealMaxHealth(this, this.target, 5);
            }
        }
    }
}
