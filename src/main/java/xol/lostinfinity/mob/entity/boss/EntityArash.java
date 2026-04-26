package xol.lostinfinity.mob.entity.boss;

import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
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
import xol.lostinfinity.stone.EntityInfinityStone;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/boss/EntityArash.class */
public class EntityArash extends EntityMultipleLives implements IMaxAttack {
    public EntityArash(World worldIn) {
        super(worldIn);
        func_70105_a(2.0f, 3.2f);
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

    private void spawnCrystals(int count) {
        for (int crystal = 0; crystal < count; crystal++) {
            EntityDeviantCrystal crystal_spawn = new EntityDeviantCrystal(this.field_70170_p);
            boolean inAir = false;
            int x_pos = 0;
            int z_pos = 0;
            while (!inAir) {
                x_pos = this.field_70146_Z.nextInt(30);
                z_pos = this.field_70146_Z.nextInt(80);
                if (this.field_70170_p.func_175623_d(new BlockPos(0 + x_pos, 63, (-140) + z_pos))) {
                    inAir = true;
                }
            }
            crystal_spawn.func_70107_b(0 + x_pos, 63.0d, (-140) + z_pos);
            if (getLivesCount() > 5) {
                crystal_spawn.setSuperMutated(true);
            }
            this.field_70170_p.func_72838_d(crystal_spawn);
        }
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            if (getLivesCount() > 5) {
                near_pl.func_145747_a(new TextComponentString(TextFmt.Green + "Arash: Super mutants, rise!"));
            } else {
                near_pl.func_145747_a(new TextComponentString(TextFmt.Green + "Arash: Let's add some deviants to the mix."));
            }
        }
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (func_70638_az() == null && this.field_70173_aa % 120 == 0) {
                Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB()).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    EntityPlayer near_pl = (EntityPlayer) it.next();
                    if (!near_pl.func_184812_l_()) {
                        func_70624_b(near_pl);
                        func_70634_a(near_pl.field_70165_t, near_pl.field_70163_u, near_pl.field_70161_v);
                        IMaxAttack.dealMaxHealth(this, near_pl, 4);
                        near_pl.func_145747_a(new TextComponentString(TextFmt.Light_Purple + "Arash: Surprise attack!"));
                        this.field_70170_p.func_175739_a(EnumParticleTypes.SWEEP_ATTACK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 5, this.field_70146_Z.nextDouble() * 3.0d, 0.3d, this.field_70146_Z.nextDouble() * 3.0d, 0.15000000596046448d, new int[0]);
                        break;
                    }
                }
            }
            if ((this.field_70173_aa + 80) % 100 == 0) {
                int crystal_count = 0;
                for (EntityDeviantCrystal crys : this.field_70170_p.func_72872_a(EntityDeviantCrystal.class, getArenaAABB())) {
                    crystal_count++;
                    if (getLivesCount() > 5) {
                        crys.setSuperMutated(true);
                    }
                }
                if (crystal_count < 5) {
                    int crystals = this.field_70146_Z.nextInt(1) + 1;
                    if (getLivesCount() > 3) {
                        crystals++;
                        if (getLivesCount() > 6) {
                            crystals++;
                        }
                    }
                    spawnCrystals(crystals);
                }
            }
        }
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.UROGO_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.UROGO_HIT;
    }

    protected SoundEvent func_184639_G() {
        return null;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 15;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            near_pl.func_145747_a(new TextComponentString(TextFmt.Dark_Gray + "Arash: Die already!"));
        }
        for (EntityPlayer e : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(4.0d, 4.0d, 4.0d))) {
            e.func_70024_g(Math.signum(this.field_70165_t - e.field_70165_t) * (-0.59d), 0.35d, Math.signum(this.field_70161_v - e.field_70161_v) * (-0.59d));
            e.field_70133_I = true;
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
            EntityInfinityStone stone = new EntityInfinityStone(this.field_70170_p);
            stone.setStoneNum((byte) 6);
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
