package xol.lostinfinity.mob.entity.labyrinth;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.projectile.entity.EntityWizardBlast;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/labyrinth/EntityLabWizard.class */
public class EntityLabWizard extends EntityMultipleLives implements IMaxAttack {
    public EntityLabWizard(World worldIn) {
        super(worldIn);
        func_70105_a(1.0f, 2.0f);
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.27d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(300.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 8);
            return true;
        }
        return false;
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.LAB_CREATURE_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.LAB_CREATURE_HIT;
    }

    protected SoundEvent func_184639_G() {
        return null;
    }

    public void func_70636_d() {
        super.func_70636_d();
        EntityLivingBase target = func_70638_az();
        if (this.field_70173_aa % 20 == 0 && target != null) {
            if (!this.field_70170_p.field_72995_K) {
                EntityWizardBlast shot = new EntityWizardBlast(this.field_70170_p, this);
                double d0 = target.field_70165_t - this.field_70165_t;
                double d1 = (target.func_174813_aQ().field_72338_b + ((double) (target.field_70131_O / 5.0f))) - shot.field_70163_u;
                double d2 = target.field_70161_v - this.field_70161_v;
                double d3 = MathHelper.func_76133_a((d0 * d0) + (d2 * d2));
                shot.func_70186_c(d0, d1 + (d3 * 0.20000000298023224d), d2, 2.0f, 0.0f);
                this.field_70170_p.func_72838_d(shot);
            }
            func_184185_a(SoundEvents.field_191244_bn, 1.0f, 1.0f);
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 3;
    }

    protected ResourceLocation func_184647_J() {
        if (this.field_70146_Z.nextInt(7) == 0) {
            return LootTableRegistry.LABYRINTH_TOP;
        }
        return null;
    }
}
