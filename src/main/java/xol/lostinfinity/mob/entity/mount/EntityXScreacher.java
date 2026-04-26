package xol.lostinfinity.mob.entity.mount;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLivesMount;
import xol.lostinfinity.mob.entity.base.IConditionalDamage;
import xol.lostinfinity.projectile.entity.EntityXSonicAttack;
public class EntityXScreacher extends EntityMultipleLivesMount implements IConditionalDamage {
    private static final Vec3d GROUND_OFFSET = new Vec3d(0.0d, 1.3d, 1.0d);
    private static final Vec3d AIR_OFFSET = new Vec3d(0.0d, 0.765d, 1.0d);
    private long nextFireAttack;
    public EntityXScreacher(World worldIn) {
        super(worldIn);
        func_70105_a(2.0f, 1.53f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 25;
    }
    public float func_70047_e() {
        return this.field_70131_O;
    }
    public void func_184232_k(Entity passenger) {
        if (func_184196_w(passenger)) {
            Vec3d forward = new Vec3d(0.0d, 0.0d, 1.0d).func_178785_b((-this.field_70177_z) * 0.017453292f);
            passenger.func_70107_b(this.field_70165_t - (1.3d * forward.field_72450_a), this.field_70163_u + func_70042_X() + passenger.func_70033_W(), this.field_70161_v - (1.3d * forward.field_72449_c));
        }
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLivesMount
    public double func_70042_X() {
        return super.func_70042_X() + 1.5d;
    }
    public Vec3d getHeadOffset() {
        return func_174791_d().func_178787_e((this.isActuallyOnGround ? GROUND_OFFSET : AIR_OFFSET).func_178785_b((-this.field_70177_z) * 0.017453292f));
    }
    public void fireAttack(EntityPlayer driver) {
        if (System.currentTimeMillis() < this.nextFireAttack) {
            return;
        }
        Vec3d driverVec = driver.func_70040_Z();
        Vec3d location = getHeadOffset();
        EntityXSonicAttack shot = new EntityXSonicAttack(this.field_70170_p, location.field_72450_a, location.field_72448_b, location.field_72449_c);
        shot.setThrower(this);
        shot.setSecondaryThrower(driver);
        Vec3d driverVec2 = driverVec.func_186678_a(3.0d);
        shot.func_70024_g(driverVec2.field_72450_a, driverVec2.field_72448_b, driverVec2.field_72449_c);
        this.field_70170_p.func_72838_d(shot);
        func_184185_a(SoundInit.STARFORGE_SCREACHER_ATTACK, 2.0f, 0.5f + this.field_70146_Z.nextFloat());
        this.nextFireAttack = System.currentTimeMillis() + 500;
    }
    @Override // xol.lostinfinity.mob.entity.base.IConditionalDamage
    public boolean canBeDamaged(Entity attacker) {
        return attacker != this.owner;
    }
    public void func_70636_d() {
        EntityPlayer entityPlayerFunc_184179_bs;
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && (entityPlayerFunc_184179_bs = func_184179_bs()) != null && (entityPlayerFunc_184179_bs instanceof EntityPlayer)) {
            EntityPlayer player = entityPlayerFunc_184179_bs;
            player.func_70691_i(player.func_110138_aP() / 20.0f);
            func_70691_i(func_110138_aP() / 10.0f);
        }
    }
}
