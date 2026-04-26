package xol.lostinfinity.mob.entity.deviant;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/deviant/EntityDeviantMagmacube.class */
public class EntityDeviantMagmacube extends EntityDeviantMob implements IMaxAttack {
    public EntityDeviantMagmacube(World worldIn) {
        super(worldIn);
        func_70105_a(4.0f, 4.0f);
        func_189654_d(true);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.5d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1200.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 4 - getMutation());
            return true;
        }
        return false;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70173_aa % (40 - (8 * getMutation())) == 0) {
            func_70024_g(0.0d, 1.0d + (0.2d * ((double) getMutation())), 0.0d);
            func_184185_a(SoundEvents.field_187762_di, 1.0f, 0.5f + this.field_70146_Z.nextFloat());
        }
        this.field_70181_x -= 0.08d + (0.03d * ((double) getMutation()));
        float scl = 2.5f + MathHelper.func_76126_a(this.field_70173_aa * (0.1f + (0.1f * getMutation())));
        func_70105_a(scl, scl);
    }

    public void func_180430_e(float distance, float damageMultiplier) {
        if (!this.field_70170_p.field_72995_K) {
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(10.0d, 10.0d, 10.0d))) {
                if (!near_pl.func_184812_l_()) {
                    near_pl.func_70024_g(Math.signum(this.field_70165_t - near_pl.field_70165_t) * 0.5d, Math.signum(this.field_70163_u - near_pl.field_70163_u) * 0.5d, Math.signum(this.field_70161_v - near_pl.field_70161_v) * 0.5d);
                    near_pl.field_70133_I = true;
                }
            }
            this.field_70170_p.func_175739_a(EnumParticleTypes.SWEEP_ATTACK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 5, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
        }
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187758_dg;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187760_dh;
    }

    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187764_dj;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    public void updateSupermutationAI() {
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35d + (0.07d * ((double) getMutation())));
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item playerInput() {
        return ItemInit.slipperyIngot;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item mutantOutput() {
        return ItemInit.unstableIngot;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation deviantDrop() {
        return LootTableRegistry.ENTITIES_DEVIANTMAGMACUBE;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation superMutatedDrop() {
        return LootTableRegistry.ENTITIES_SUPERMUTANT_MAGMACUBE;
    }
}
