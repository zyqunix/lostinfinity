package xol.lostinfinity.mob.entity.boss;

import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
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

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/boss/EntityDarrio.class */
public class EntityDarrio extends EntityMultipleLives implements IMaxAttack {
    private static final DataParameter<Boolean> SPINNING = EntityDataManager.func_187226_a(EntityDarrio.class, DataSerializers.field_187198_h);

    public EntityDarrio(World worldIn) {
        super(worldIn);
        func_70105_a(3.5f, 5.5f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(SPINNING, false);
    }

    public boolean isSpinning() {
        return ((Boolean) this.field_70180_af.func_187225_a(SPINNING)).booleanValue();
    }

    public void setSpinning(boolean f) {
        this.field_70180_af.func_187227_b(SPINNING, Boolean.valueOf(f));
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74757_a("SpinningAttack", isSpinning());
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setSpinning(tag.func_74767_n("SpinningAttack"));
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5000.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 3);
            return true;
        }
        return false;
    }

    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -145.0d), new BlockPos(52.0d, 85.0d, -40.0d));
    }

    private void spawnCrystals(int count) {
        for (int crystal = 0; crystal < count; crystal++) {
            EntitySentryCrystal crystal_spawn = new EntitySentryCrystal(this.field_70170_p);
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
            this.field_70170_p.func_72838_d(crystal_spawn);
        }
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            near_pl.func_145747_a(new TextComponentString(TextFmt.Red + "Darrio: Behold my sentry crystals!"));
        }
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        func_70024_g(0.0d, this.field_70173_aa % 60 < 20 ? 0.10000000149011612d : 0.0d, 0.0d);
        if (!this.field_70170_p.field_72995_K) {
            if (func_70638_az() == null) {
                boolean found_target = false;
                AxisAlignedBB aabb = getArenaAABB();
                Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, aabb).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    EntityPlayer near_pl = (EntityPlayer) it.next();
                    if (!near_pl.func_184812_l_()) {
                        if (this.field_70159_w > -0.699999988079071d && this.field_70159_w < 0.699999988079071d && this.field_70179_y > -0.699999988079071d && this.field_70179_y < 0.699999988079071d) {
                            func_70024_g((near_pl.field_70165_t - this.field_70165_t) * 0.03d, (near_pl.field_70163_u - this.field_70163_u) * 0.02d, (near_pl.field_70161_v - this.field_70161_v) * 0.03d);
                            this.field_70133_I = true;
                        }
                        found_target = true;
                        setSpinning(true);
                    }
                }
                if (!found_target) {
                    setSpinning(false);
                }
            } else {
                setSpinning(true);
            }
            if ((this.field_70173_aa + 1) % 120 == 0) {
                int crystals = this.field_70146_Z.nextInt(2) + 1;
                if (getLivesCount() > 3) {
                    crystals += 2;
                    if (getLivesCount() > 6) {
                        crystals += 3;
                    }
                }
                spawnCrystals(crystals);
            }
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            near_pl.func_145747_a(new TextComponentString(TextFmt.Gold + "Darrio: I cannot be killed so easily mortal."));
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
        return 10;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
            EntityInfinityStone stone = new EntityInfinityStone(this.field_70170_p);
            stone.setStoneNum((byte) 7);
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
