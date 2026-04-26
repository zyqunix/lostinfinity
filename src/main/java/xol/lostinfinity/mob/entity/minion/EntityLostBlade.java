package xol.lostinfinity.mob.entity.minion;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.weapon.ItemLostBladesOfInfinity;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.math.LMath;
public class EntityLostBlade extends EntityMinion {
    private static final DataParameter<Integer> POSE = EntityDataManager.func_187226_a(EntityLostBlade.class, DataSerializers.field_187192_b);
    private static final double VELOCITY = 2.0d;
    private static final double ATTACK_COOLDOWN = 400.0d;
    private final Map<Entity, Long> attackCooldown;
    private EntityLivingBase target;
    public EntityLostBlade(World worldIn) {
        super(worldIn);
        this.attackCooldown = new ConcurrentHashMap();
        func_70105_a(0.5f, 0.5f);
    }
    @Override // xol.lostinfinity.mob.entity.minion.EntityMinion
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(POSE, -1);
    }
    public void setTarget(EntityLivingBase livingBase) {
        this.target = livingBase;
    }
    public void setPose(int pose) {
        this.field_70180_af.func_187227_b(POSE, Integer.valueOf(pose));
        setLocation();
    }
    public int getPose() {
        return ((Integer) this.field_70180_af.func_187225_a(POSE)).intValue();
    }
    @Override // xol.lostinfinity.mob.entity.minion.EntityMinion
    public void livingUpdate() {
        EntityPlayer owner = func_70902_q();
        if (owner == null || owner.field_70128_L) {
            func_70106_y();
            return;
        }
        if (!this.field_70170_p.field_72995_K) {
            ItemLostBladesOfInfinity.BladeMode bladeMode = getMode(this.trackedItemStack);
            if (bladeMode == ItemLostBladesOfInfinity.BladeMode.STANDBY) {
                setActive(false);
                this.target = null;
                setLocation();
                return;
            }
            if (this.target != null) {
                if (this.target.field_70128_L || this.target.func_110143_aJ() <= 0.0f) {
                    this.target = null;
                } else {
                    double dist = this.target.func_174791_d().func_178788_d(owner.func_174791_d()).func_189985_c();
                    if (dist > 1000.0d) {
                        this.target = null;
                    }
                }
            }
            int randomTick = this.field_70173_aa + getPose();
            if (randomTick % 20 == 0) {
                switch (bladeMode) {
                    case GENOCIDE:
                        findClosestTarget();
                        break;
                    case TARGET:
                        this.target = owner.func_110144_aD();
                        break;
                }
            }
            if (randomTick % 6 == 0) {
                if (this.target != null) {
                    Vec3d targetVec = new Vec3d(this.target.field_70165_t, this.target.field_70163_u + ((double) (this.target.field_70131_O * this.field_70146_Z.nextFloat())), this.target.field_70161_v);
                    Vec3d motion = LMath.fastNormalize(targetVec.func_178788_d(func_174791_d())).func_186678_a(VELOCITY);
                    this.field_70159_w = motion.field_72450_a;
                    this.field_70181_x = motion.field_72448_b;
                    this.field_70179_y = motion.field_72449_c;
                    this.field_70133_I = true;
                    setActive(true);
                } else {
                    setActive(false);
                }
            } else if (randomTick % 6 == 3 && this.target != null) {
                Vec3d motion2 = LMath.fastNormalize(new Vec3d(this.field_70159_w + getRandomDouble(3.0d), this.field_70181_x, this.field_70179_y + getRandomDouble(3.0d))).func_186678_a(VELOCITY);
                this.field_70159_w = motion2.field_72450_a;
                this.field_70181_x = motion2.field_72448_b;
                this.field_70179_y = motion2.field_72449_c;
                this.field_70133_I = true;
            }
        } else if (isActive()) {
            for (int i = 0; i < 4; i++) {
                this.field_70170_p.func_175682_a(this.field_70146_Z.nextBoolean() ? ParticleInit.GENERIC_DOT_ORANGE : ParticleInit.GENERIC_DOT_WHITE, true, MathHelper.func_151238_b(this.field_70169_q, this.field_70165_t, i / 4.0f), MathHelper.func_151238_b(this.field_70167_r, this.field_70163_u, i / 4.0f), MathHelper.func_151238_b(this.field_70166_s, this.field_70161_v, i / 4.0f), 0.0d, 0.0d, 0.0d, new int[0]);
            }
        }
        if (!isActive()) {
            setLocation();
            return;
        }
        this.field_70126_B = 0.0f;
        this.field_70177_z = 0.0f;
        this.field_70759_as = 0.0f;
        this.field_70761_aq = 0.0f;
    }
    protected void func_82167_n(Entity entityIn) {
        if (!this.field_70170_p.field_72995_K && isActive() && (entityIn instanceof EntityLivingBase) && !(entityIn instanceof EntityImmaterial) && entityIn != func_70902_q()) {
            Long time = this.attackCooldown.get(entityIn);
            if (time == null || System.currentTimeMillis() - time.longValue() >= ATTACK_COOLDOWN) {
                double dX = entityIn.field_70159_w;
                double dY = entityIn.field_70181_x;
                double dZ = entityIn.field_70179_y;
                CustomDamageResult damageResult = IMaxAttack.dealMaxHealth((Entity) func_70902_q(), (EntityLivingBase) entityIn, 5, (List<String>) Arrays.asList("Aquatic"));
                if (damageResult.didSuccessfulHit() && damageResult.targetHealthChanged()) {
                    entityIn.field_70159_w = dX;
                    entityIn.field_70181_x = dY;
                    entityIn.field_70179_y = dZ;
                }
                this.attackCooldown.put(entityIn, Long.valueOf(System.currentTimeMillis()));
                CustomParticleConfig config = new CustomParticleConfig();
                config.createInstance().setParticle(EnumParticleTypes.SWEEP_ATTACK).setCount(2).setSpread(entityIn.field_70130_N / 2.0f, entityIn.field_70131_O / 2.0f, entityIn.field_70130_N / 2.0f).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(this.field_70170_p, config, entityIn.field_70165_t, entityIn.field_70163_u + ((double) (entityIn.field_70131_O / 2.0f)), entityIn.field_70161_v);
                if (this.field_70146_Z.nextInt(6) == 0) {
                    this.field_70170_p.func_184148_a((EntityPlayer) null, this.field_70165_t, this.field_70163_u, this.field_70161_v, SoundInit.GENERIC_SLICE, SoundCategory.NEUTRAL, 1.0f, 1.5f + this.field_70146_Z.nextFloat());
                }
            }
        }
    }
    protected void func_70665_d(DamageSource damageSrc, float damageAmount) {
        super.func_70665_d(damageSrc, damageAmount);
    }
    protected void setLocation() {
        Vec3d pos;
        float yaw;
        switch (getPose()) {
            case 0:
                pos = new Vec3d(0.25d, 1.0d, -0.5d);
                yaw = this.owner.field_70761_aq + 10.0f;
                break;
            case 1:
                pos = new Vec3d(0.5d, 1.1d, -0.55d);
                yaw = this.owner.field_70761_aq + 20.0f;
                break;
            case 2:
                pos = new Vec3d(0.75d, 1.2d, -0.6d);
                yaw = this.owner.field_70761_aq + 30.0f;
                break;
            case 3:
                pos = new Vec3d(-0.25d, 1.0d, -0.5d);
                yaw = this.owner.field_70761_aq - 10.0f;
                break;
            case TileEntityFusionTable.BOARD_ROWS :
                pos = new Vec3d(-0.5d, 1.1d, -0.55d);
                yaw = this.owner.field_70761_aq - 20.0f;
                break;
            case 5:
                pos = new Vec3d(-0.75d, 1.2d, -0.6d);
                yaw = this.owner.field_70761_aq - 30.0f;
                break;
            default:
                return;
        }
        Vec3d pos2 = pos.func_178785_b((-yaw) * 0.017453292f);
        func_70080_a(this.owner.field_70165_t + pos2.field_72450_a, this.owner.field_70163_u + pos2.field_72448_b, this.owner.field_70161_v + pos2.field_72449_c, yaw, 0.0f);
        this.field_70759_as = yaw;
        this.field_70761_aq = yaw;
    }
    private ItemLostBladesOfInfinity.BladeMode getMode(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            return ItemLostBladesOfInfinity.BladeMode.STANDBY;
        }
        return ItemLostBladesOfInfinity.BladeMode.values()[stack.func_77978_p().func_74762_e("blade_mode")];
    }
    private void findClosestTarget() {
        List<EntityLivingBase> targets = this.field_70170_p.func_175647_a(EntityLivingBase.class, this.owner.func_174813_aQ().func_186662_g(24.0d), (v1) -> {
            return validateTarget(v1);
        });
        Vec3d pos = this.owner.func_174791_d();
        double targetDist = Double.MAX_VALUE;
        for (EntityLivingBase entity : targets) {
            double dist = LMath.getDistanceSquaredToAABB(pos, entity.func_174813_aQ());
            if (dist < targetDist) {
                targetDist = dist;
                this.target = entity;
            }
        }
    }
}
