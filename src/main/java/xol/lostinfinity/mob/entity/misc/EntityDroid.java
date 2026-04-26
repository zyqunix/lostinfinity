package xol.lostinfinity.mob.entity.misc;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.projectile.entity.EntityDroidLaser;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityDroid extends EntityTameable implements IMaxAttack {
    private static final DataParameter<Integer> GRADE = EntityDataManager.func_187226_a(EntityDroid.class, DataSerializers.field_187192_b);
    private static final DataParameter<Boolean> AGGRESSIVE = EntityDataManager.func_187226_a(EntityDroid.class, DataSerializers.field_187198_h);
    private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.func_187226_a(EntityDroid.class, DataSerializers.field_187192_b);
    private static final DataParameter<Integer> TARGET_LEVEL = EntityDataManager.func_187226_a(EntityDroid.class, DataSerializers.field_187192_b);
    private int killcount;
    public EntityDroid(World worldIn) {
        super(worldIn);
        this.killcount = 0;
        func_70105_a(1.2f, 2.2f);
    }
    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(3, new EntityAILeapAtTarget(this, 0.4f));
        this.field_70714_bg.func_75776_a(4, new EntityAIAttackMelee(this, 1.0d, true));
        this.field_70714_bg.func_75776_a(7, new EntityAIWanderAvoidWater(this, 1.0d));
        this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(9, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIOwnerHurtByTarget(this));
        this.field_70715_bh.func_75776_a(2, new EntityAIOwnerHurtTarget(this));
        this.field_70715_bh.func_75776_a(3, new EntityAIHurtByTarget(this, true, new Class[0]));
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1250.0d);
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(GRADE, 0);
        this.field_70180_af.func_187214_a(AGGRESSIVE, true);
        this.field_70180_af.func_187214_a(ATTACK_TIME, 0);
        this.field_70180_af.func_187214_a(TARGET_LEVEL, 0);
    }
    public int getGrade() {
        return ((Integer) this.field_70180_af.func_187225_a(GRADE)).intValue();
    }
    public void setGrade(int grade) {
        this.field_70180_af.func_187227_b(GRADE, Integer.valueOf(grade));
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35d + (0.1d * ((double) grade)));
    }
    public boolean isAggressive() {
        return ((Boolean) this.field_70180_af.func_187225_a(AGGRESSIVE)).booleanValue();
    }
    public void setAggressive(boolean aggro) {
        this.field_70180_af.func_187227_b(AGGRESSIVE, Boolean.valueOf(aggro));
    }
    public int getAttackTime() {
        return ((Integer) this.field_70180_af.func_187225_a(ATTACK_TIME)).intValue();
    }
    public void setAttackTime(int f) {
        this.field_70180_af.func_187227_b(ATTACK_TIME, Integer.valueOf(f));
    }
    public int getTargetLevel() {
        return ((Integer) this.field_70180_af.func_187225_a(TARGET_LEVEL)).intValue();
    }
    public void setTargetLevel(int f) {
        this.field_70180_af.func_187227_b(TARGET_LEVEL, Integer.valueOf(f));
    }
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74768_a("KillCount", this.killcount);
        tag.func_74757_a("AttackStyle", isAggressive());
        tag.func_74768_a("DroidGrade", getGrade());
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        this.killcount = tag.func_74762_e("KillCount");
        setAggressive(tag.func_74767_n("AttackStyle"));
        setGrade(tag.func_74762_e("DroidGrade"));
    }
    public boolean func_70652_k(Entity entity) {
        setAttackTime(20);
        super.func_70652_k(entity);
        if (!this.field_70170_p.field_72995_K && func_70638_az() != null) {
            boolean attackingDeviant = func_70638_az() instanceof EntityDeviantMob;
            boolean wasKilled = IMaxAttack.dealMaxHealth((Entity) this, func_70638_az(), 10, 2 + (2 * getGrade())).wasTargetKilled();
            this.field_70170_p.func_175739_a(EnumParticleTypes.SWEEP_ATTACK, this.field_70165_t, this.field_70163_u + 1.0d, this.field_70161_v, 2, (-0.5d) + this.field_70146_Z.nextDouble(), 0.3d * ((-0.5d) + this.field_70146_Z.nextDouble()), (-0.5d) + this.field_70146_Z.nextDouble(), 0.15000000596046448d, new int[0]);
            if (!wasKilled && (func_70638_az() instanceof EntityPlayer)) {
                if (getGrade() == 2 && this.field_70146_Z.nextInt(10) == 1) {
                    EntityPlayer player = func_70638_az();
                    player.field_71071_by.func_70436_m();
                    return true;
                }
                return true;
            }
            if (wasKilled && attackingDeviant) {
                if (this.killcount == 5) {
                    func_145779_a(ItemInit.droidLocationDataD, 1);
                    this.killcount = 0;
                    return true;
                }
                this.killcount++;
                return true;
            }
            return true;
        }
        return false;
    }
    public void func_70645_a(DamageSource cause) {
        super.func_70645_a(cause);
        if ((cause.func_76346_g() instanceof EntityPlayer) && cause.func_76346_g() == func_70902_q() && !this.field_70170_p.field_72995_K) {
            if (this.field_70170_p.field_73011_w.func_186058_p() == DimensionType.OVERWORLD) {
                func_145779_a(ItemInit.droidLocationDataO, 1);
            } else if (this.field_70170_p.field_73011_w.func_186058_p() == DimensionType.THE_END) {
                func_145779_a(ItemInit.droidLocationDataE, 1);
            } else if (this.field_70170_p.field_73011_w.func_186058_p() == DimensionType.NETHER) {
                func_145779_a(ItemInit.droidLocationDataN, 1);
            }
        }
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.field_70170_p.func_175659_aa() == EnumDifficulty.PEACEFUL) {
            func_70106_y();
        }
        if (getAttackTime() > 0) {
            setAttackTime(getAttackTime() - 1);
        }
        this.field_70143_R = -1.0f;
        if (isAggressive() && this.field_70173_aa % 30 == 0) {
            float lowest = 100000.0f;
            for (EntityLivingBase li : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(12.0d, 12.0d, 12.0d))) {
                if (!li.func_110124_au().equals(func_184753_b()) && !(li instanceof EntityDroid) && !li.field_70128_L) {
                    float newdist = func_70032_d(li);
                    if (newdist < lowest) {
                        func_70624_b(li);
                        lowest = newdist;
                    }
                }
            }
        }
        int grade = getGrade();
        if (grade > 0 && !this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % 10 == 0) {
                func_70691_i(150.0f);
            }
            float distance_to_target = 0.0f;
            if (func_70638_az() != null) {
                distance_to_target = func_70032_d(func_70638_az());
            }
            if (this.field_70173_aa % 20 == 0) {
                if (func_70638_az() != null) {
                    setTargetLevel(1 + (distance_to_target < 10.0f ? 0 : 1));
                } else {
                    setTargetLevel(0);
                }
            }
            if (func_70638_az() != null) {
                if (func_70638_az().field_70128_L) {
                    func_70624_b(null);
                    return;
                }
                int ticksRemainder = this.field_70173_aa % 200;
                if (ticksRemainder == 0) {
                    if (grade == 1 && func_70032_d(func_70638_az()) < 30.0f) {
                        func_70024_g((func_70638_az().field_70165_t - this.field_70165_t) * 0.35d, (func_70638_az().field_70163_u - this.field_70163_u) * 0.3d, (func_70638_az().field_70161_v - this.field_70161_v) * 0.35d);
                    } else if (grade == 2) {
                        this.field_70170_p.func_175739_a(EnumParticleTypes.LAVA, this.field_70165_t, this.field_70163_u + 1.0d, this.field_70161_v, 5, (-0.5d) + this.field_70146_Z.nextDouble(), 0.3d * ((-0.5d) + this.field_70146_Z.nextDouble()), (-0.5d) + this.field_70146_Z.nextDouble(), 0.15000000596046448d, new int[0]);
                        func_70634_a(func_70638_az().field_70165_t, func_70638_az().field_70163_u, func_70638_az().field_70161_v);
                        func_184185_a(SoundEvents.field_187534_aX, 1.0f, 1.0f);
                    }
                }
                if (grade == 2) {
                    if (ticksRemainder % 3 == 0 && distance_to_target >= 10.0f) {
                        BlockPos startPos = func_180425_c().func_177984_a();
                        BlockPos endPos = func_70638_az().func_180425_c().func_177984_a();
                        double xdiff = startPos.func_177958_n() - endPos.func_177958_n();
                        double ydiff = startPos.func_177956_o() - endPos.func_177956_o();
                        double zdiff = startPos.func_177952_p() - endPos.func_177952_p();
                        for (int part = 0; part < 8; part++) {
                            BlockPos curPos = new BlockPos(((double) startPos.func_177958_n()) + (((-xdiff) / ((double) 8)) * ((double) part)), ((double) startPos.func_177956_o()) + (((-ydiff) / ((double) 8)) * ((double) part)), ((double) startPos.func_177952_p()) + (((-zdiff) / ((double) 8)) * ((double) part)));
                            this.field_70170_p.func_175739_a(EnumParticleTypes.PORTAL, curPos.func_177958_n(), curPos.func_177956_o(), curPos.func_177952_p(), 2, (-0.5d) + this.field_70146_Z.nextDouble(), 0.3d * ((-0.5d) + this.field_70146_Z.nextDouble()), (-0.5d) + this.field_70146_Z.nextDouble(), 0.15000000596046448d, new int[0]);
                        }
                        func_70638_az().func_70024_g(Math.signum(this.field_70165_t - func_70638_az().field_70165_t) * 0.8d, Math.signum(this.field_70163_u - func_70638_az().field_70163_u) * 0.7d, Math.signum(this.field_70161_v - func_70638_az().field_70161_v) * 0.8d);
                        func_70638_az().field_70133_I = true;
                    }
                    if (ticksRemainder % 20 == 0) {
                        EntityLivingBase target = func_70638_az();
                        EntityDroidLaser shot = new EntityDroidLaser(this.field_70170_p, this);
                        double d0 = target.field_70165_t - this.field_70165_t;
                        double d1 = (target.func_174813_aQ().field_72338_b + ((double) (target.field_70131_O / 6.0f))) - shot.field_70163_u;
                        double d2 = target.field_70161_v - this.field_70161_v;
                        double d3 = MathHelper.func_76133_a((d0 * d0) + (d2 * d2));
                        shot.func_70186_c(d0, d1 + (d3 * 0.20000000298023224d), d2, 2.0f, 0.0f);
                        shot.setOwner(func_70902_q());
                        this.field_70170_p.func_72838_d(shot);
                        func_184185_a(SoundInit.LASER_WEAPON_1, 1.0f, 0.75f + (this.field_70146_Z.nextFloat() * 0.5f));
                    }
                }
            }
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187599_cE;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187602_cF;
    }
    protected SoundEvent func_184639_G() {
        return null;
    }
    protected boolean func_70692_ba() {
        return func_70902_q() == null;
    }
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
    public EntityAgeable func_90011_a(EntityAgeable ageable) {
        return null;
    }
}
