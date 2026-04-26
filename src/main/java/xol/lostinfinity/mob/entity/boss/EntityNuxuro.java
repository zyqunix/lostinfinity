package xol.lostinfinity.mob.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.projectile.entity.EntityVeloMagic;
import xol.lostinfinity.stone.EntityInfinityStone;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/boss/EntityNuxuro.class */
public class EntityNuxuro extends EntityMultipleLives implements IMaxAttack {
    public EntityNuxuro(World worldIn) {
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
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 2);
            return true;
        }
        return false;
    }

    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -145.0d), new BlockPos(52.0d, 85.0d, -40.0d));
    }

    private void playSoundToPlayers(SoundEvent sound) {
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            this.field_70170_p.func_184133_a((EntityPlayer) null, near_pl.func_180425_c(), sound, SoundCategory.MASTER, 0.35f + (this.field_70146_Z.nextFloat() * 0.2f), 0.5f + this.field_70146_Z.nextFloat());
        }
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(3.0d, 3.0d, 3.0d))) {
            near_pl.func_70024_g(Math.signum(this.field_70165_t - near_pl.field_70165_t) * (-1.1d), 0.5d, Math.signum(this.field_70161_v - near_pl.field_70161_v) * (-1.1d));
            near_pl.field_70133_I = true;
        }
        if (!this.field_70170_p.field_72995_K && (this.field_70173_aa % 30 == 0 || (this.field_70173_aa % 120 < 20 && this.field_70173_aa % 7 == 0))) {
            boolean didSound = false;
            for (EntityPlayer near_pl2 : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
                if (func_70638_az() == null) {
                    func_70624_b(near_pl2);
                    func_70024_g((near_pl2.field_70165_t - this.field_70165_t) * 0.2d, (near_pl2.field_70163_u - this.field_70163_u) * 0.1d, (near_pl2.field_70161_v - this.field_70161_v) * 0.2d);
                    this.field_70133_I = true;
                }
                double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
                double makeY = this.field_70163_u + ((double) (this.field_70131_O / 2.0f)) + 0.5d;
                double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
                double d2 = near_pl2.field_70165_t - makeX;
                double d3 = (near_pl2.func_174813_aQ().field_72338_b + ((double) (near_pl2.field_70131_O / 4.0f))) - makeY;
                double d4 = near_pl2.field_70161_v - makeZ;
                EntityVeloMagic shot = new EntityVeloMagic(this.field_70170_p, this);
                shot.func_70186_c(d2, d3, d4, 2.0f, 0.0f);
                shot.setDenom(5);
                shot.setGravity(Float.valueOf(0.0f));
                this.field_70170_p.func_72838_d(shot);
                didSound = true;
            }
            if (didSound) {
                playSoundToPlayers(SoundInit.MAGIC_WEAPON_4);
            }
        }
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 70 == 50) {
            double x0 = this.field_70165_t;
            double y0 = this.field_70163_u + 2.5d;
            double z0 = this.field_70161_v;
            playSoundToPlayers(SoundInit.MAGIC_WEAPON_3);
            float f = 0.0f;
            while (true) {
                float angle = f;
                if (angle <= 6.283185307179586d) {
                    EntityVeloMagic shot2 = new EntityVeloMagic(this.field_70170_p, this);
                    shot2.func_70107_b(x0, y0, z0);
                    double velocity_x = 5.0d * Math.cos(angle);
                    double velocity_z = 5.0d * Math.sin(angle);
                    shot2.setThrower(this);
                    shot2.calculateVelocity(velocity_x, -1.5d, velocity_z);
                    shot2.func_184538_a(this, shot2.field_70125_A, shot2.field_70177_z, 0.0f, 1.5f, 0.0f);
                    shot2.setGravity(Float.valueOf(0.05f));
                    this.field_70170_p.func_72838_d(shot2);
                    f = (float) (((double) angle) + 0.3141592653589793d);
                } else {
                    return;
                }
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

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 6;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
            EntityInfinityStone stone = new EntityInfinityStone(this.field_70170_p);
            stone.setStoneNum((byte) 5);
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
