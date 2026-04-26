package xol.lostinfinity.mob.entity.contest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerHunters;
import xol.lostinfinity.projectile.entity.EntityBloodhunterBlast;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class EntityBloodhunter extends EntityMob implements IBasicAI {
    private boolean ranged;
    public EntityBloodhunter(World worldIn) {
        super(worldIn);
        this.ranged = false;
        func_70105_a(1.5f, 2.2f);
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.5d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10000.0d);
    }
    public void setRanged() {
        this.ranged = true;
    }
    protected void func_184651_r() {
        initBasicTasks(this);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null && !this.field_70170_p.field_72995_K) {
            if (this.field_70170_p.field_73011_w.func_186058_p() == DimensionInit.grandmasterOutpost && (func_70638_az() instanceof EntityPlayer)) {
                EntityPlayer player = func_70638_az();
                for (EntityControllerHunters search_cont : this.field_70170_p.func_72872_a(EntityControllerHunters.class, getArenaAABB())) {
                    search_cont.removePlayer(player);
                }
                return true;
            }
            return true;
        }
        return false;
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % 10 == 0 && func_70638_az() == null) {
                float closest = 99999.0f;
                EntityPlayer close_pl = null;
                for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(12.0d, 12.0d, 12.0d))) {
                    if (!near_pl.func_70093_af() && (near_pl.field_70159_w != 0.0d || near_pl.field_70179_y == 0.0d)) {
                        float test_dist = func_70032_d(near_pl);
                        if (close_pl == null || test_dist < closest) {
                            close_pl = near_pl;
                            closest = test_dist;
                        }
                    }
                }
                if (close_pl != null) {
                    func_70624_b(close_pl);
                }
            }
            if (this.ranged && this.field_70173_aa % 20 == 0 && func_70638_az() != null) {
                func_184185_a(SoundEvents.field_191255_dF, 1.0f, 0.5f + this.field_70146_Z.nextFloat());
                EntityLivingBase target = func_70638_az();
                EntityBloodhunterBlast shot = new EntityBloodhunterBlast(this.field_70170_p, this);
                double d0 = target.field_70165_t - this.field_70165_t;
                double d1 = (target.func_174813_aQ().field_72338_b + ((double) (target.field_70131_O / 6.0f))) - shot.field_70163_u;
                double d2 = target.field_70161_v - this.field_70161_v;
                double d3 = MathHelper.func_76133_a((d0 * d0) + (d2 * d2));
                shot.func_70186_c(d0, d1 + (d3 * 0.20000000298023224d), d2, 2.0f, 0.0f);
                this.field_70170_p.func_72838_d(shot);
            }
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.STARFORGE_CRAWKER_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.STARFORGE_CRAWKER_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.STARFORGE_CRAWKER_AMBIENT;
    }
    protected boolean func_70692_ba() {
        return false;
    }
    public boolean func_70814_o() {
        return true;
    }
    protected AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.huntersArenaAABB();
    }
}
