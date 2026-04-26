package xol.lostinfinity.mob.entity.boss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.projectile.entity.EntityVeloMagic;
import xol.lostinfinity.stone.EntityInfinityStone;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityVelo extends EntityMultipleLives implements IMaxAttack {
    public EntityVelo(World worldIn) {
        super(worldIn);
        func_70105_a(1.7f, 3.0f);
        this.field_70178_ae = true;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(2, new EntityAIAttackMelee(this, 1.0d, true));
        this.field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsRestriction(this, 1.0d));
        this.field_70714_bg.func_75776_a(7, new EntityAIWanderAvoidWater(this, 1.0d));
        this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
        func_175456_n();
    }
    protected void func_175456_n() {
        this.field_70714_bg.func_75776_a(6, new EntityAIMoveThroughVillage(this, 1.0d, false));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityPigZombie.class}));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4000.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 6);
            return true;
        }
        return false;
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (this.field_70173_aa % 30 == 0 && func_70638_az() != null) {
            if (!this.field_70170_p.field_72995_K) {
                EntityLivingBase target = func_70638_az();
                for (int snum = -1; snum < 2; snum++) {
                    EntityVeloMagic shot = new EntityVeloMagic(this.field_70170_p, this);
                    double d0 = (target.field_70165_t - this.field_70165_t) * (1.0d + (((double) snum) * 0.75d));
                    double d1 = (target.func_174813_aQ().field_72338_b + ((double) (target.field_70131_O / (3.0f + (snum * 2))))) - shot.field_70163_u;
                    double d2 = (target.field_70161_v - this.field_70161_v) * (1.0d + (((double) snum) * 0.75d));
                    double d3 = MathHelper.func_76133_a((d0 * d0) + (d2 * d2));
                    shot.func_70186_c(d0, d1 + (d3 * 0.20000000298023224d), d2, 2.0f, 0.0f);
                    this.field_70170_p.func_72838_d(shot);
                }
            }
            func_184185_a(SoundEvents.field_193784_dd, 1.0f, 1.0f);
        }
        int roomForm = (getLivesCount() - 1) % 3;
        if (this.field_70173_aa % 80 == 0 && roomForm == 2) {
            if (!this.field_70170_p.field_72995_K) {
                for (int snum2 = 0; snum2 < 12; snum2++) {
                    EntityVeloMagic shot2 = new EntityVeloMagic(this.field_70170_p, this);
                    shot2.setGravity(Float.valueOf(0.10000001f));
                    shot2.func_70186_c((-1.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 2.0d), 0.0d, (-1.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 2.0d), 2.0f, 0.0f);
                    this.field_70170_p.func_72838_d(shot2);
                }
            }
            func_184185_a(SoundEvents.field_187631_bo, 3.0f, 1.0f);
            return;
        }
        if (this.field_70173_aa % 60 < 30 && this.field_70173_aa % 2 == 0 && roomForm == 1) {
            if (!this.field_70170_p.field_72995_K) {
                EntityVeloMagic shot3 = new EntityVeloMagic(this.field_70170_p, this);
                shot3.setGravity(Float.valueOf(0.10000001f));
                shot3.func_70186_c((-1.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 2.0d), 0.0d, (-1.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 2.0d), 2.0f, 0.0f);
                this.field_70170_p.func_72838_d(shot3);
            }
            func_184185_a(SoundEvents.field_187775_eP, 3.0f, 0.0f);
            return;
        }
        if (this.field_70173_aa % 90 <= 30 && this.field_70173_aa % 10 == 0 && roomForm == 0) {
            if (!this.field_70170_p.field_72995_K) {
                for (int snum3 = 0; snum3 < 10; snum3++) {
                    EntityVeloMagic shot4 = new EntityVeloMagic(this.field_70170_p, this);
                    shot4.setGravity(Float.valueOf(0.10000001f));
                    shot4.func_70186_c((-1.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 2.0d), 0.0d + ((double) (0.2f * Math.floorDiv(this.field_70173_aa % 90, 10))), (-1.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 2.0d), 2.0f, 0.0f);
                    this.field_70170_p.func_72838_d(shot4);
                }
            }
            func_184185_a(SoundEvents.field_187625_bm, 3.0f, 1.0f);
        }
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        if (this.field_70170_p.field_73011_w.func_186058_p() == DimensionInit.celestialVoid) {
            int stage = getLivesCount() - 1;
            if (stage == 0) {
                AxisAlignedBB aabb = new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -145.0d), new BlockPos(52.0d, 85.0d, -40.0d));
                for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, aabb)) {
                    near_pl.func_145747_a(new TextComponentString(TextFmt.Dark_Purple + "Velo: And away we go!"));
                    near_pl.func_70634_a(6.0d, 61.0d, -171.0d);
                }
                func_70634_a(12.0d, 61.0d, -171.0d);
            } else {
                int roomGo = (stage - 1) % 3;
                switch (roomGo) {
                    case 0:
                        AxisAlignedBB aabb2 = new AxisAlignedBB(new BlockPos(0.0d, 59.0d, -180.0d), new BlockPos(18.0d, 68.0d, -160.0d));
                        for (EntityPlayer near_pl2 : this.field_70170_p.func_72872_a(EntityPlayer.class, aabb2)) {
                            near_pl2.func_145747_a(new TextComponentString(TextFmt.Dark_Purple + "Velo: To Fire!"));
                            near_pl2.func_70634_a(26.0d, 62.0d, -171.0d);
                        }
                        func_70107_b(31.0d, 62.0d, -171.0d);
                        break;
                    case 1:
                        AxisAlignedBB aabb3 = new AxisAlignedBB(new BlockPos(20.0d, 59.0d, -180.0d), new BlockPos(38.0d, 68.0d, -160.0d));
                        for (EntityPlayer near_pl3 : this.field_70170_p.func_72872_a(EntityPlayer.class, aabb3)) {
                            near_pl3.func_145747_a(new TextComponentString(TextFmt.Dark_Purple + "Velo: To Ender!"));
                            near_pl3.func_70634_a(43.0d, 64.0d, -171.0d);
                        }
                        func_70107_b(48.0d, 64.0d, -171.0d);
                        break;
                    case 2:
                        AxisAlignedBB aabb4 = new AxisAlignedBB(new BlockPos(40.0d, 59.0d, -180.0d), new BlockPos(58.0d, 68.0d, -160.0d));
                        for (EntityPlayer near_pl4 : this.field_70170_p.func_72872_a(EntityPlayer.class, aabb4)) {
                            near_pl4.func_145747_a(new TextComponentString(TextFmt.Dark_Purple + "Velo: To Space!"));
                            near_pl4.func_70634_a(6.0d, 61.0d, -171.0d);
                        }
                        func_70107_b(12.0d, 61.0d, -171.0d);
                        break;
                }
            }
        }
        func_184185_a(SoundEvents.field_187534_aX, 3.0f, 1.0f);
    }
    private void clearPlayersToStart() {
        AxisAlignedBB aabb = new AxisAlignedBB(new BlockPos(0.0d, 59.0d, -180.0d), new BlockPos(18.0d, 68.0d, -160.0d));
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, aabb)) {
            near_pl.func_70634_a(25.5d, 61.2d, -73.0d);
            near_pl.func_145779_a(ItemInit.arenaCard, 1);
        }
        AxisAlignedBB aabb2 = new AxisAlignedBB(new BlockPos(20.0d, 59.0d, -180.0d), new BlockPos(38.0d, 68.0d, -160.0d));
        for (EntityPlayer near_pl2 : this.field_70170_p.func_72872_a(EntityPlayer.class, aabb2)) {
            near_pl2.func_70634_a(25.5d, 61.2d, -73.0d);
            near_pl2.func_145779_a(ItemInit.arenaCard, 1);
        }
        AxisAlignedBB aabb3 = new AxisAlignedBB(new BlockPos(40.0d, 59.0d, -180.0d), new BlockPos(58.0d, 68.0d, -160.0d));
        for (EntityPlayer near_pl3 : this.field_70170_p.func_72872_a(EntityPlayer.class, aabb3)) {
            near_pl3.func_70634_a(25.5d, 61.2d, -73.0d);
            near_pl3.func_145779_a(ItemInit.arenaCard, 1);
        }
        EntityInfinityStone stone = new EntityInfinityStone(this.field_70170_p);
        stone.setStoneNum((byte) 3);
        stone.func_70107_b(25.5d, 61.2d, -73.0d);
        this.field_70170_p.func_72838_d(stone);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 10;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
            clearPlayersToStart();
        }
    }
    protected SoundEvent func_184615_bR() {
        return null;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_193787_df;
    }
    protected SoundEvent func_184639_G() {
        return null;
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
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
}
