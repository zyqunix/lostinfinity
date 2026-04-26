package xol.lostinfinity.mob.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.projectile.entity.EntityMobComet;
import xol.lostinfinity.stone.EntityInfinityStone;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/boss/EntityAlestria.class */
public class EntityAlestria extends EntityMultipleLives implements IMaxAttack {
    public EntityAlestria(World worldIn) {
        super(worldIn);
        func_70105_a(3.5f, 5.5f);
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
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

    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -145.0d), new BlockPos(52.0d, 85.0d, -40.0d));
    }

    private void fireComet(float speed) {
        if (!this.field_70170_p.field_72995_K) {
            boolean fired = false;
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
                if (!near_pl.func_184812_l_()) {
                    fired = true;
                    double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
                    double makeY = this.field_70163_u + ((double) (this.field_70131_O / 2.0f)) + 0.5d;
                    double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
                    double d2 = near_pl.field_70165_t - makeX;
                    double d3 = (near_pl.func_174813_aQ().field_72338_b + ((double) (near_pl.field_70131_O / 2.0f))) - makeY;
                    double d4 = near_pl.field_70161_v - makeZ;
                    EntityMobComet shot = new EntityMobComet(this.field_70170_p, this);
                    shot.func_70186_c(d2, d3, d4, speed, 0.0f);
                    this.field_70170_p.func_72838_d(shot);
                    this.field_70170_p.func_175739_a(EnumParticleTypes.LAVA, shot.field_70165_t, shot.field_70163_u, shot.field_70161_v, 2, this.field_70146_Z.nextDouble() * 3.0d, 0.3d, this.field_70146_Z.nextDouble() * 3.0d, 0.15000000596046448d, new int[0]);
                }
            }
            if (fired) {
                func_184185_a(SoundInit.MAGIC_WEAPON_4, 2.0f, 1.0f);
            }
        }
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            for (Entity proj : this.field_70170_p.func_72872_a(Entity.class, func_174813_aQ().func_72314_b(10.0d, 10.0d, 10.0d))) {
                if ((proj instanceof EntityThrowable) || (proj instanceof EntityArrow) || (proj instanceof EntityFireball)) {
                    if (!(proj instanceof EntityMobComet)) {
                        proj.func_70106_y();
                        proj.func_184185_a(SoundInit.ITEM_AXIOMAVORUM, 1.0f, 0.5f + this.field_70146_Z.nextFloat());
                        this.field_70170_p.func_175739_a(EnumParticleTypes.SWEEP_ATTACK, proj.field_70165_t, proj.field_70163_u, proj.field_70161_v, 2, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
                    }
                }
            }
        }
        if (!this.field_70170_p.field_72995_K) {
            int modCheck = 40;
            if (getLivesCount() > 5) {
                modCheck = 20;
            }
            float spd = 1.25f;
            if (getLivesCount() > 7) {
                spd = 2.5f;
            }
            if (this.field_70173_aa % 300 < 200) {
                if (this.field_70173_aa % modCheck == 0) {
                    fireComet(spd);
                }
            } else if (this.field_70173_aa % (modCheck / 2) == 0) {
                fireComet(spd);
            }
        }
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.GENERIC_STYLE1_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.GENERIC_STYLE1_HURT;
    }

    protected SoundEvent func_184639_G() {
        return null;
    }

    public boolean func_180427_aV() {
        return true;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 15;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            near_pl.func_145747_a(new TextComponentString(TextFmt.Gold + "Alestria is losing life."));
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
            EntityInfinityStone stone = new EntityInfinityStone(this.field_70170_p);
            stone.setStoneNum((byte) 8);
            stone.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.field_70170_p.func_72838_d(stone);
            func_145779_a(ItemInit.arenaCard, 1);
        }
    }

    protected boolean func_70692_ba() {
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70814_o() {
        return true;
    }

    public int func_70641_bl() {
        return 1;
    }
}
