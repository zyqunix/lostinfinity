package xol.lostinfinity.mob.entity.boss;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityWitherSkullling extends EntityMultipleLives implements IMaxAttack {
    public EntityWitherSkullling(World worldIn) {
        super(worldIn);
        func_70105_a(1.3f, 3.25f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        initBasicTasks(this);
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1500.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 4);
            this.field_70170_p.func_175739_a(EnumParticleTypes.SMOKE_LARGE, entity.field_70165_t, entity.field_70163_u + 1.0d, entity.field_70161_v, 2, (-0.5d) + this.field_70146_Z.nextDouble(), 0.3d * ((-0.5d) + this.field_70146_Z.nextDouble()), (-0.5d) + this.field_70146_Z.nextDouble(), 0.15000000596046448d, new int[0]);
            return true;
        }
        return false;
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (this.field_70173_aa % 200 > 120) {
            func_189654_d(true);
            if (this.field_70181_x < 0.5d) {
                func_70024_g(0.0d, 0.009999999776482582d, 0.0d);
            }
            this.field_70133_I = true;
        } else {
            func_189654_d(false);
        }
        if (!this.field_70170_p.field_72995_K && func_70638_az() == null) {
            Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB()).iterator();
            if (it.hasNext()) {
                EntityPlayer near_pl = (EntityPlayer) it.next();
                func_70624_b(near_pl);
            }
        }
    }
    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(958.0d, 60.0d, 877.0d), new BlockPos(1012.0d, 82.0d, 924.0d));
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_190037_hb;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_190038_hc;
    }
    protected SoundEvent func_184639_G() {
        return SoundEvents.field_190036_ha;
    }
}
