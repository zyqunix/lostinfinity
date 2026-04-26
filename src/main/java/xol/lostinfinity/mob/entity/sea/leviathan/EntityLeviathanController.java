package xol.lostinfinity.mob.entity.sea.leviathan;

import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.client.fx.ClientParticleRenderer;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingBase;
import xol.lostinfinity.mob.entity.classify.IKnockbackImmunity;
import xol.lostinfinity.mob.entity.classify.ILostMultiPart;
import xol.lostinfinity.mob.entity.sea.EntityEelShark;
import xol.lostinfinity.mob.entity.sea.EntitySeaCreature;
import xol.lostinfinity.projectile.entity.EntityLeviathanBreath;
import xol.lostinfinity.projectile.entity.EntityLeviathanTeslaOrb;
import xol.lostinfinity.projectile.entity.EntityLeviathanTracer;
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/sea/leviathan/EntityLeviathanController.class */
public class EntityLeviathanController extends EntityFloatingBase implements ILostMultiPart, IKnockbackImmunity, IMaxAttack {
    private static final DataParameter<Integer> LEVIATHAN_SIZE = EntityDataManager.func_187226_a(EntityLeviathanController.class, DataSerializers.field_187192_b);
    private static final DataParameter<Integer> LEVIATHAN_PHASE = EntityDataManager.func_187226_a(EntityLeviathanController.class, DataSerializers.field_187192_b);
    private static final DataParameter<Integer> LEVIATHAN_PHASE_DATA = EntityDataManager.func_187226_a(EntityLeviathanController.class, DataSerializers.field_187192_b);
    public final EntityLeviathanSegment[] segments;
    private final LeviathanMoveHelper leviathanMoveHelper;
    protected Phase phase;
    protected int nextPhaseChange;
    protected EntityLeviathanBreath breath;
    protected int attackCooldown;
    protected int attackGracePeriod;

    public EntityLeviathanController(World worldIn) {
        super(worldIn);
        this.segments = new EntityLeviathanSegment[20];
        this.phase = Phase.CHARGE;
        this.nextPhaseChange = 400;
        this.attackCooldown = 0;
        this.attackGracePeriod = 4;
        func_70105_a(0.25f, 0.25f);
        this.field_70145_X = true;
        this.rawFlySpeed = 0.9f;
        this.leviathanMoveHelper = (LeviathanMoveHelper) this.field_70765_h;
        EntityLeviathanSegment lastSegment = new EntityLeviathanHead(this);
        lastSegment.updatePosition();
        lastSegment.setId(0);
        this.segments[0] = lastSegment;
        for (int i = 1; i < this.segments.length - 1; i++) {
            EntityLeviathanSegment segment = new EntityLeviathanSegment(this, lastSegment);
            segment.updatePosition();
            lastSegment = segment;
            segment.setId(i);
            this.segments[i] = segment;
        }
        EntityLeviathanTail tail = new EntityLeviathanTail(this, lastSegment);
        tail.updatePosition();
        tail.setId(this.segments.length - 1);
        this.segments[this.segments.length - 1] = tail;
    }

    @Nullable
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        setLeviathanSize(5);
        setLeviathanPhase(Phase.CHARGE);
        return super.func_180482_a(difficulty, livingdata);
    }

    public void func_184206_a(DataParameter<?> key) {
        if (LEVIATHAN_SIZE.equals(key)) {
            int size = getLeviathanSize();
            for (EntityLeviathanSegment segment : this.segments) {
                segment.setSize(size);
            }
        }
        super.func_184206_a(key);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(LEVIATHAN_SIZE, 1);
        this.field_70180_af.func_187214_a(LEVIATHAN_PHASE, 0);
        this.field_70180_af.func_187214_a(LEVIATHAN_PHASE_DATA, 0);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74768_a("LeviathanSize", getLeviathanSize());
        tag.func_74768_a("LeviathanPhase", getLeviathanPhase().ordinal());
        tag.func_74768_a("LeviathanPhaseData", getLeviathanPhaseData());
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setLeviathanSize(tag.func_74762_e("LeviathanSize"));
        setLeviathanPhase(Phase.values()[tag.func_74762_e("LeviathanPhase")]);
        setLeviathanPhaseData(tag.func_74762_e("LeviathanPhaseData"));
    }

    public void setLeviathanSize(int size) {
        this.field_70180_af.func_187227_b(LEVIATHAN_SIZE, Integer.valueOf(size));
        for (EntityLeviathanSegment segment : this.segments) {
            segment.setSize(size);
        }
    }

    public int getLeviathanSize() {
        return ((Integer) this.field_70180_af.func_187225_a(LEVIATHAN_SIZE)).intValue();
    }

    public void setLeviathanPhase(Phase phase) {
        this.phase = phase;
        this.breath = null;
        switch (AnonymousClass1.$SwitchMap$xol$lostinfinity$mob$entity$sea$leviathan$EntityLeviathanController$Phase[phase.ordinal()]) {
            case 1:
                this.nextPhaseChange = this.field_70173_aa + 400;
                break;
            case 2:
                this.nextPhaseChange = this.field_70173_aa + 200;
                break;
            case 3:
                this.nextPhaseChange = this.field_70173_aa + 100;
                break;
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                this.nextPhaseChange = this.field_70173_aa + 600;
                break;
        }
        this.field_70180_af.func_187227_b(LEVIATHAN_PHASE, Integer.valueOf(phase.ordinal()));
    }

    /* JADX INFO: renamed from: xol.lostinfinity.mob.entity.sea.leviathan.EntityLeviathanController$1, reason: invalid class name */
    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/sea/leviathan/EntityLeviathanController$1.class */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$xol$lostinfinity$mob$entity$sea$leviathan$EntityLeviathanController$Phase = new int[Phase.values().length];

        static {
            try {
                $SwitchMap$xol$lostinfinity$mob$entity$sea$leviathan$EntityLeviathanController$Phase[Phase.CHARGE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$xol$lostinfinity$mob$entity$sea$leviathan$EntityLeviathanController$Phase[Phase.VOLLEY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$xol$lostinfinity$mob$entity$sea$leviathan$EntityLeviathanController$Phase[Phase.TESLA.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$xol$lostinfinity$mob$entity$sea$leviathan$EntityLeviathanController$Phase[Phase.BEAM.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public Phase getLeviathanPhase() {
        return Phase.values()[((Integer) this.field_70180_af.func_187225_a(LEVIATHAN_PHASE)).intValue()];
    }

    public void setLeviathanPhaseData(int data) {
        this.field_70180_af.func_187227_b(LEVIATHAN_PHASE_DATA, Integer.valueOf(data));
    }

    public int getLeviathanPhaseData() {
        return ((Integer) this.field_70180_af.func_187225_a(LEVIATHAN_PHASE_DATA)).intValue();
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(256.0d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5000.0d);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        super.func_184651_r();
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityEelShark.class, 5, false, false, (Predicate) null));
        this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntitySeaCreature.class, 5, false, false, (Predicate) null));
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        CustomParticleConfig customParticleConfigCreateYellowShock;
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K) {
            boolean isTeslaPhase = getLeviathanPhase() == Phase.TESLA;
            int id = isTeslaPhase ? this.field_70173_aa % this.segments.length : (this.field_70173_aa / 3) % (this.segments.length * 5);
            if (id < this.segments.length) {
                Vec3d loc = LMath.getEntityMiddle(this.segments[id]);
                if (isTeslaPhase) {
                    customParticleConfigCreateYellowShock = createBlueShock(((EntityLeviathanSegment) r0).field_70130_N, ((EntityLeviathanSegment) r0).field_70131_O, ((EntityLeviathanSegment) r0).field_70130_N);
                } else {
                    customParticleConfigCreateYellowShock = createYellowShock(((EntityLeviathanSegment) r0).field_70130_N / 2.0f, ((EntityLeviathanSegment) r0).field_70131_O / 2.0f, ((EntityLeviathanSegment) r0).field_70130_N / 2.0f);
                }
                CustomParticleConfig config = customParticleConfigCreateYellowShock;
                config.setCount(4);
                config.setOrigin(loc);
                ClientParticleRenderer.renderComplex(config);
                return;
            }
            return;
        }
        this.attackCooldown--;
        EntityLivingBase target = func_70638_az();
        if (target != null) {
            if (this.field_70173_aa > this.nextPhaseChange) {
                setLeviathanPhase(Phase.randomPhase(this.field_70170_p, this.phase));
            }
            boolean shouldLookAtTarget = false;
            switch (AnonymousClass1.$SwitchMap$xol$lostinfinity$mob$entity$sea$leviathan$EntityLeviathanController$Phase[this.phase.ordinal()]) {
                case 1:
                    if (target.func_110143_aJ() <= 0.0f) {
                        setLeviathanPhaseData(0);
                    }
                    if (this.field_70173_aa % 4 == 0) {
                        int phaseData = 0;
                        Vec3d selfLoc = func_174791_d();
                        double distSqr = LMath.getDistanceSquaredToAABB(selfLoc, target.func_174813_aQ());
                        if (distSqr <= 100.0d && distSqr > 1.0d) {
                            this.leviathanMoveHelper.setCorrectionRate(0.8d);
                            this.leviathanMoveHelper.setCourseChangeChance(1);
                            if (target.func_110143_aJ() > 0.0f) {
                                phaseData = 1;
                            }
                        } else {
                            this.leviathanMoveHelper.setCorrectionRate(0.1d);
                            this.leviathanMoveHelper.setCourseChangeChance(5);
                        }
                        this.leviathanMoveHelper.func_75642_a(target.field_70165_t, target.field_70163_u + ((double) (target.field_70131_O / 2.0f)), target.field_70161_v, 1.0d);
                        setLeviathanPhaseData(phaseData);
                    }
                    this.rawFlySpeed = 1.0f;
                    break;
                case 2:
                    if (this.field_70173_aa % 4 == 0) {
                        EntityLeviathanSegment segment = this.segments[this.field_70170_p.field_73012_v.nextInt(this.segments.length - 2) + 1];
                        EntityLeviathanTracer tracer = new EntityLeviathanTracer(this.field_70170_p, segment.field_70165_t, segment.field_70163_u + ((double) (segment.field_70131_O / 2.0f)), segment.field_70161_v, target);
                        tracer.setLeviathanThrower(this);
                        Vec3d motion = LMath.fastNormalize(new Vec3d(this.field_70170_p.field_73012_v.nextDouble() - 0.5d, this.field_70170_p.field_73012_v.nextDouble() - 0.5d, this.field_70170_p.field_73012_v.nextDouble() - 0.5d));
                        tracer.field_70159_w = motion.field_72450_a;
                        tracer.field_70181_x = motion.field_72448_b;
                        tracer.field_70179_y = motion.field_72449_c;
                        this.field_70170_p.func_72838_d(tracer);
                        this.field_70170_p.func_184133_a((EntityPlayer) null, tracer.func_180425_c(), SoundInit.ELECTRIC_WOOSH, SoundCategory.HOSTILE, 8.0f, 0.5f + (this.field_70170_p.field_73012_v.nextFloat() * 0.25f));
                        IParticleSpawner.spawnParticle(this.field_70170_p, createBlueShock(0.0d, 0.0d, 0.0d), tracer.field_70165_t + (motion.field_72450_a * 3.0d), tracer.field_70163_u + (motion.field_72448_b * 3.0d), tracer.field_70161_v + (motion.field_72449_c * 3.0d));
                    }
                    this.rawFlySpeed = 0.5f;
                    shouldLookAtTarget = true;
                    break;
                case 3:
                    if (this.field_70173_aa % 20 == 0) {
                        EntityLeviathanTeslaOrb teslaOrb = new EntityLeviathanTeslaOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u + ((double) (this.field_70131_O / 2.0f)), this.field_70161_v);
                        teslaOrb.setLeviathanThrower(this);
                        Vec3d motion2 = this.segments[0].func_70040_Z().func_186678_a(0.5d);
                        teslaOrb.field_70159_w = motion2.field_72450_a;
                        teslaOrb.field_70181_x = motion2.field_72448_b;
                        teslaOrb.field_70179_y = motion2.field_72449_c;
                        this.field_70170_p.func_72838_d(teslaOrb);
                    }
                    this.rawFlySpeed = 0.5f;
                    shouldLookAtTarget = true;
                    break;
                case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                    int tick = 600 - (this.nextPhaseChange - this.field_70173_aa);
                    if (tick >= 0 && tick < 200) {
                        if (tick % 2 == 0) {
                            double ratio = MathHelper.func_76131_a(tick / 180.0f, 0.0f, 1.0f);
                            int range = (int) (((double) (this.segments.length - 2)) * (1.0d - ratio));
                            int size = getLeviathanSize();
                            CustomParticleConfig config2 = createYellowShock(size, size, size);
                            config2.setCount(2);
                            for (int i = this.segments.length - 2; i > range; i--) {
                                Vec3d loc2 = LMath.getEntityMiddle(this.segments[i]);
                                IParticleSpawner.spawnParticle(this.field_70170_p, config2, loc2);
                            }
                        }
                        if (tick % 20 == 0) {
                            this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.GENERIC_WEAPON_12, SoundCategory.HOSTILE, func_70599_aP(), 1.0f);
                        }
                        this.rawFlySpeed = 0.5f;
                        shouldLookAtTarget = true;
                    } else {
                        if (this.breath == null) {
                            this.breath = new EntityLeviathanBreath(this.field_70170_p);
                            this.breath.setOwner(this);
                            EntityLeviathanSegment seg = this.segments[0];
                            this.breath.func_70107_b(seg.field_70165_t, seg.field_70163_u + ((double) (seg.field_70131_O / 2.0f)), seg.field_70161_v);
                            this.field_70170_p.func_72838_d(this.breath);
                        }
                        if (!this.breath.field_70128_L) {
                            this.rawFlySpeed = 0.5f;
                            shouldLookAtTarget = true;
                            setLeviathanPhaseData(1);
                            if (tick % 2 == 0) {
                                int size2 = getLeviathanSize();
                                CustomParticleConfig config3 = createYellowShock(size2, size2, size2);
                                config3.setCount(2);
                                for (int i2 = 1; i2 < this.segments.length - 1; i2++) {
                                    Vec3d loc3 = LMath.getEntityMiddle(this.segments[i2]);
                                    IParticleSpawner.spawnParticle(this.field_70170_p, config3, loc3);
                                }
                            }
                        } else {
                            this.rawFlySpeed = 0.9f;
                            setLeviathanPhaseData(0);
                        }
                    }
                    break;
            }
            if (shouldLookAtTarget) {
                EntityLeviathanSegment segment2 = this.segments[0];
                Vec3d dir = LMath.fastNormalize(target.func_174791_d().func_178788_d(func_174791_d()));
                double hMagnitude = LMath.fastSqrt((dir.field_72450_a * dir.field_72450_a) + (dir.field_72449_c * dir.field_72449_c));
                float pitch = ((float) MathHelper.func_181159_b(-dir.field_72448_b, hMagnitude)) * 57.29578f;
                float yaw = ((float) MathHelper.func_181159_b(-dir.field_72450_a, dir.field_72449_c)) * 57.29578f;
                if (Math.abs(pitch - segment2.field_70125_A) > 5.0f || Math.abs(yaw - segment2.field_70177_z) > 5.0f) {
                    this.leviathanMoveHelper.field_188491_h = EntityMoveHelper.Action.WAIT;
                    this.field_70159_w = dir.field_72450_a * 0.2d;
                    this.field_70181_x = dir.field_72448_b * 0.2d;
                    this.field_70179_y = dir.field_72449_c * 0.2d;
                    this.field_70133_I = true;
                    return;
                }
                return;
            }
            return;
        }
        if (this.phase != Phase.BEAM || this.breath == null || this.breath.field_70128_L) {
            setLeviathanPhaseData(0);
        }
        this.leviathanMoveHelper.setCorrectionRate(0.1d);
        this.leviathanMoveHelper.setCourseChangeChance(5);
        this.rawFlySpeed = 0.9f;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 200;
    }

    protected void func_85033_bc() {
        if (this.attackCooldown > 0) {
            return;
        }
        this.attackCooldown = this.attackGracePeriod;
        Set<Entity> targets = new HashSet<>();
        for (Entity entity : this.segments) {
            List<Entity> segList = this.field_70170_p.func_175674_a(entity, entity.func_174813_aQ(), input -> {
                return (input == this || targets.contains(input) || !EntitySelectors.func_188442_a(entity).apply(input) || (input instanceof EntityLeviathanSegment) || (input instanceof EntityLeviathanController)) ? false : true;
            });
            targets.addAll(segList);
        }
        if (targets.isEmpty()) {
            return;
        }
        for (Entity entity2 : targets) {
            func_82167_n(entity2);
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    @Nullable
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    protected void func_82167_n(Entity entityIn) {
        func_70652_k(entityIn);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    protected EntityMoveHelper createMoveHelper() {
        return new LeviathanMoveHelper(this);
    }

    public void func_70106_y() {
        super.func_70106_y();
        for (Entity entity : this.segments) {
            this.field_70170_p.func_72973_f(entity);
        }
    }

    @Nullable
    public Entity[] func_70021_al() {
        return this.segments;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        for (Entity entity : this.segments) {
            this.field_70170_p.func_72866_a(entity, true);
            entity.updatePosition();
        }
    }

    public boolean func_70072_I() {
        return false;
    }

    public boolean func_180799_ab() {
        return false;
    }

    @Nullable
    protected SoundEvent func_184639_G() {
        return SoundInit.LEVIATHAN_AMBIENT;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.LEVIATHAN_HURT;
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.LEVIATHAN_DEATH;
    }

    protected float func_70599_aP() {
        return 8.0f;
    }

    public boolean func_70652_k(Entity entityIn) {
        super.func_70652_k(entityIn);
        if (!(entityIn instanceof EntityLivingBase)) {
            return false;
        }
        if ((entityIn instanceof EntityPlayer) && ((EntityPlayer) entityIn).func_184812_l_()) {
            return false;
        }
        EntitySeaCreature entitySeaCreature = (EntityLivingBase) entityIn;
        if (entitySeaCreature instanceof EntitySeaCreature) {
            EntitySeaCreature seaCreature = entitySeaCreature;
            seaCreature.takeawayNumLives(seaCreature.remainingLives() + 1);
            return true;
        }
        IMaxAttack.dealTrueDamage(this, entitySeaCreature, entitySeaCreature.func_110138_aP() * 0.5f, Collections.singletonList("Aquatic"));
        return true;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public World func_82194_d() {
        return this.field_70170_p;
    }

    @Override // xol.lostinfinity.mob.entity.classify.ILostMultiPart
    public boolean attackEntityFromPart(EntityLivingBase part, DamageSource source, float damage) {
        return func_70097_a(source, damage);
    }

    @Override // xol.lostinfinity.mob.entity.classify.IKnockbackImmunity
    public float getKnockbackResistance(CustomDamageResult damageResult) {
        return 1.0f;
    }

    private static CustomParticleConfig createYellowShock(double spreadX, double spreadY, double spreadZ) {
        CustomParticleConfig config = new CustomParticleConfig();
        config.createInstance().setParticle(ParticleInit.TESLA_RING_YELLOW).setSpread(spreadX, spreadY, spreadZ).setIgnoreRange(true);
        return config;
    }

    private static CustomParticleConfig createBlueShock(double spreadX, double spreadY, double spreadZ) {
        CustomParticleConfig config = new CustomParticleConfig();
        config.createInstance().setParticle(ParticleInit.TESLA_RING_BLUE).setSpread(spreadX, spreadY, spreadZ).setIgnoreRange(true);
        return config;
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/sea/leviathan/EntityLeviathanController$Phase.class */
    public enum Phase {
        CHARGE,
        VOLLEY,
        TESLA,
        BEAM;

        public static Phase randomPhase(World world, Phase exclude) {
            int nextPhase = world.field_73012_v.nextInt(3);
            if (nextPhase == exclude.ordinal()) {
                nextPhase++;
            }
            return values()[nextPhase];
        }
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/sea/leviathan/EntityLeviathanController$LeviathanMoveHelper.class */
    private static class LeviathanMoveHelper extends EntityMoveHelper {
        private final EntityLeviathanController parentEntity;
        private int courseChangeCooldown;
        private int courseChangeChance;
        private double correctionRate;

        public LeviathanMoveHelper(EntityLeviathanController leviathan) {
            super(leviathan);
            this.courseChangeChance = 5;
            this.correctionRate = 0.1d;
            this.parentEntity = leviathan;
        }

        public void func_75642_a(double x, double y, double z, double speedIn) {
            super.func_75642_a(x, this.parentEntity.func_70638_az() == null ? MathHelper.func_151237_a(y, 30.0d, 220.0d) : y, z, speedIn);
        }

        public void func_75641_c() {
            if (this.field_188491_h == EntityMoveHelper.Action.MOVE_TO) {
                double dX = this.field_75646_b - this.parentEntity.field_70165_t;
                double dY = this.field_75647_c - this.parentEntity.field_70163_u;
                double dZ = this.field_75644_d - this.parentEntity.field_70161_v;
                double invMag = MathHelper.func_181161_i((dX * dX) + (dY * dY) + (dZ * dZ));
                int i = this.courseChangeCooldown;
                this.courseChangeCooldown = i - 1;
                if (i <= 0) {
                    this.courseChangeCooldown += this.parentEntity.func_70681_au().nextInt(this.courseChangeChance) + 2;
                    this.parentEntity.field_70159_w *= 1.0d - this.correctionRate;
                    this.parentEntity.field_70181_x *= 1.0d - this.correctionRate;
                    this.parentEntity.field_70179_y *= 1.0d - this.correctionRate;
                    this.parentEntity.field_70159_w += dX * invMag * this.correctionRate;
                    this.parentEntity.field_70181_x += dY * invMag * this.correctionRate;
                    this.parentEntity.field_70179_y += dZ * invMag * this.correctionRate;
                }
            }
        }

        public void setCourseChangeChance(int chance) {
            this.courseChangeChance = chance;
        }

        public void setCorrectionRate(double rate) {
            this.correctionRate = rate;
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
            func_145779_a(ItemInit.giantHeart, 1);
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        int lifePercent = Math.round((100 * (numberOfLives() - getLivesCount())) / numberOfLives());
        for (EntityPlayerMP playerMP : this.field_70170_p.func_73046_m().func_184103_al().func_181057_v()) {
            if (func_70032_d(playerMP) < 150.0f) {
                playerMP.func_145747_a(new TextComponentString(TextFmt.Gold + "The Leviathan is at " + lifePercent + "% health."));
            }
        }
    }
}
