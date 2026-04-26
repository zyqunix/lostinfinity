package xol.lostinfinity.mob.entity.starforge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.projectile.entity.EntityStunAttack;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityPhaser extends EntityMob implements IMaxAttack, IBasicAI {
    public EntityPhaser(World worldIn) {
        super(worldIn);
        func_70105_a(1.5f, 1.5f);
    }
    protected void func_184651_r() {
        initBasicTasks(this);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 5);
            return true;
        }
        return false;
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(900.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (Math.sin(((double) (this.field_70173_aa * 0.05f)) + 1.5707963267948966d) > 0.0d) {
            EntityLivingBase target = func_70638_az();
            if (this.field_70173_aa % 20 == 0 && target != null) {
                if (!this.field_70170_p.field_72995_K) {
                    EntityStunAttack shot = new EntityStunAttack(this.field_70170_p, this);
                    double d0 = target.field_70165_t - this.field_70165_t;
                    double d1 = (target.func_174813_aQ().field_72338_b + ((double) (target.field_70131_O / 6.0f))) - shot.field_70163_u;
                    double d2 = target.field_70161_v - this.field_70161_v;
                    double d3 = MathHelper.func_76133_a((d0 * d0) + (d2 * d2));
                    shot.func_70186_c(d0, d1 + (d3 * 0.20000000298023224d), d2, 2.0f, 0.0f);
                    this.field_70170_p.func_72838_d(shot);
                }
                func_184185_a(SoundEvents.field_193784_dd, 1.0f, 1.0f);
                return;
            }
            return;
        }
        EntityLivingBase target2 = func_70638_az();
        if (this.field_70173_aa % 7 == 0 && target2 != null) {
            if (!this.field_70170_p.field_72995_K) {
                EntityStunAttack shot2 = new EntityStunAttack(this.field_70170_p, this);
                double d02 = target2.field_70165_t - this.field_70165_t;
                double d12 = (target2.func_174813_aQ().field_72338_b + ((double) (target2.field_70131_O / 6.0f))) - shot2.field_70163_u;
                double d22 = target2.field_70161_v - this.field_70161_v;
                double d32 = MathHelper.func_76133_a((d02 * d02) + (d22 * d22));
                shot2.func_70186_c(d02, d12 + (d32 * 0.20000000298023224d), d22, 2.0f, 0.0f);
                shot2.setForm((byte) 1);
                this.field_70170_p.func_72838_d(shot2);
            }
            func_184185_a(SoundEvents.field_191265_hd, 1.0f, 1.0f);
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.STARFORGE_PHASER_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.STARFORGE_PHASER_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.STARFORGE_PHASER_AMBIENT;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_STARFORGE_PHASER;
    }
    protected boolean func_70692_ba() {
        return false;
    }
    public boolean func_70814_o() {
        return true;
    }
    public int func_70641_bl() {
        return 1;
    }
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
}
