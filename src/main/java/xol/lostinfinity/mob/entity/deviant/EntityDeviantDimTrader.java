package xol.lostinfinity.mob.entity.deviant;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.projectile.entity.EntityVoidLaser;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/deviant/EntityDeviantDimTrader.class */
public class EntityDeviantDimTrader extends EntityDeviantMob {
    private float scale;

    public EntityDeviantDimTrader(World worldIn) {
        super(worldIn);
        this.scale = 1.0f;
        func_70105_a(3.0f, 7.5f);
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5000.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 4);
            return true;
        }
        return false;
    }

    public float getMyScale() {
        return this.scale;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item playerInput() {
        return ItemInit.reinforcedBlade;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected Item mutantOutput() {
        return ItemInit.laserBlade;
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187911_gk;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187912_gl;
    }

    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187910_gj;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.scale < 3.7f) {
            this.scale += 0.1f;
        }
        EntityLivingBase target = func_70638_az();
        for (Entity proj : this.field_70170_p.func_72872_a(Entity.class, func_174813_aQ().func_72314_b(10.0d, 10.0d, 10.0d))) {
            if ((proj instanceof EntityThrowable) || (proj instanceof EntityArrow) || (proj instanceof EntityFireball)) {
                if (!(proj instanceof EntityVoidLaser) && !this.field_70170_p.field_72995_K) {
                    proj.func_70106_y();
                    proj.func_184185_a(SoundInit.ITEM_AXIOMAVORUM, 1.0f, 0.5f + this.field_70146_Z.nextFloat());
                    this.field_70170_p.func_175739_a(EnumParticleTypes.SWEEP_ATTACK, proj.field_70165_t, proj.field_70163_u, proj.field_70161_v, 2, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
                }
            }
        }
        int ability = this.field_70173_aa % 100;
        if (ability < 60 + (10 * getMutation()) && this.field_70173_aa % 3 == 0 && target != null) {
            if (!this.field_70170_p.field_72995_K) {
                func_70676_i(1.0f);
                double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
                double makeY = this.field_70163_u + ((double) (this.field_70131_O / 2.0f));
                double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
                double d2 = target.field_70165_t - makeX;
                double d3 = ((target.func_174813_aQ().field_72338_b + target.func_174813_aQ().field_72337_e) / 2.0d) - makeY;
                double d4 = target.field_70161_v - makeZ;
                EntityVoidLaser shot = new EntityVoidLaser(this.field_70170_p, makeX, makeY, makeZ);
                shot.setThrower(this);
                shot.func_70186_c(d2, d3, d4, 2.5f, 0.0f);
                this.field_70170_p.func_72838_d(shot);
            }
            func_184185_a(SoundInit.LASER_WEAPON_1, 1.5f + (0.3f * getMutation()), 0.7f + (this.field_70146_Z.nextFloat() * 0.5f));
        }
        if (ability == 95) {
            if (!this.field_70170_p.field_72995_K) {
                teleportRandomly();
            }
        } else if (ability == 50 && !this.field_70170_p.field_72995_K) {
            AxisAlignedBB check = func_174813_aQ().func_72314_b(8.0d, 8.0d, 8.0d);
            for (EntityEnderman ender : this.field_70170_p.func_72872_a(EntityEnderman.class, check)) {
                EntityDeviantEnderman newEntity = new EntityDeviantEnderman(this.field_70170_p);
                newEntity.func_70107_b(ender.field_70165_t, ender.field_70163_u, ender.field_70161_v);
                this.field_70170_p.func_72838_d(newEntity);
                this.field_70170_p.func_175739_a(EnumParticleTypes.LAVA, ender.field_70165_t, ender.field_70163_u + 2.0d + this.field_70146_Z.nextDouble(), ender.field_70161_v, 2, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
                ender.func_70106_y();
            }
        }
    }

    private boolean teleportRandomly() {
        double d0 = this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * 32.0d);
        double d1 = this.field_70163_u + ((double) (this.field_70146_Z.nextInt(64) - 32));
        double d2 = this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * 32.0d);
        return teleportTo(d0, d1, d2);
    }

    private boolean teleportTo(double x, double y, double z) {
        boolean flag = func_184595_k(x, y, z);
        if (flag) {
            this.field_70170_p.func_184148_a((EntityPlayer) null, this.field_70169_q, this.field_70167_r, this.field_70166_s, SoundEvents.field_187534_aX, func_184176_by(), 1.0f, 1.0f);
            func_184185_a(SoundEvents.field_187534_aX, 1.5f, 1.0f);
        }
        return flag;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return getMutation() == 0 ? 15 : 10;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation deviantDrop() {
        return LootTableRegistry.ENTITIES_DEVIANTDIMTRADER;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation superMutatedDrop() {
        return LootTableRegistry.ENTITIES_SUPERMUTANT_DIMTRADER;
    }
}
