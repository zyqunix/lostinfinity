package xol.lostinfinity.mob.entity.sea.seaserpent;

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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingBase;
import xol.lostinfinity.mob.entity.classify.IKnockbackImmunity;
import xol.lostinfinity.mob.entity.classify.ILostMultiPart;
import xol.lostinfinity.mob.entity.sea.EntityFish;
import xol.lostinfinity.mob.entity.sea.EntitySeaCreature;
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/sea/seaserpent/EntitySeaSerpentController.class */
public class EntitySeaSerpentController extends EntityFloatingBase implements ILostMultiPart, IKnockbackImmunity, IMaxAttack {
    private static final DataParameter<Integer> SIZE = EntityDataManager.func_187226_a(EntitySeaSerpentController.class, DataSerializers.field_187192_b);
    private static final DataParameter<Integer> ANIMATION_DATA = EntityDataManager.func_187226_a(EntitySeaSerpentController.class, DataSerializers.field_187192_b);
    public final EntitySeaSerpentSegment[] segments;
    private final SeaSerpentMoveHelper serpentMoveHelper;
    protected int attackCooldown;
    protected int attackGracePeriod;

    public EntitySeaSerpentController(World worldIn) {
        super(worldIn);
        this.segments = new EntitySeaSerpentSegment[7];
        this.attackCooldown = 0;
        this.attackGracePeriod = 15;
        func_70105_a(0.0f, 0.0f);
        this.field_70145_X = true;
        this.rawFlySpeed = 0.9f;
        this.serpentMoveHelper = (SeaSerpentMoveHelper) this.field_70765_h;
        EntitySeaSerpentSegment lastSegment = new EntitySeaSerpentHead(this);
        lastSegment.updatePosition();
        lastSegment.setId(0);
        this.segments[0] = lastSegment;
        for (int i = 1; i < this.segments.length - 1; i++) {
            EntitySeaSerpentSegment segment = new EntitySeaSerpentSegment(this, lastSegment);
            segment.updatePosition();
            lastSegment = segment;
            segment.setId(i);
            this.segments[i] = segment;
        }
        EntitySeaSerpentTail tail = new EntitySeaSerpentTail(this, lastSegment);
        tail.updatePosition();
        tail.setId(this.segments.length - 1);
        this.segments[this.segments.length - 1] = tail;
    }

    @Nullable
    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        setSize(2);
        return super.func_180482_a(difficulty, livingdata);
    }

    public void func_184206_a(DataParameter<?> key) {
        if (SIZE.equals(key)) {
            int size = getSize();
            for (EntitySeaSerpentSegment segment : this.segments) {
                segment.setSize(size);
            }
        }
        super.func_184206_a(key);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(SIZE, 1);
        this.field_70180_af.func_187214_a(ANIMATION_DATA, 0);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74768_a("Size", getSize());
        tag.func_74768_a("AnimationData", getAnimation());
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setSize(tag.func_74762_e("Size"));
        setAnimation(tag.func_74762_e("AnimationData"));
    }

    public void setSize(int size) {
        this.field_70180_af.func_187227_b(SIZE, Integer.valueOf(size));
        for (EntitySeaSerpentSegment segment : this.segments) {
            segment.setSize(size);
        }
    }

    public int getSize() {
        return ((Integer) this.field_70180_af.func_187225_a(SIZE)).intValue();
    }

    public void setAnimation(int data) {
        this.field_70180_af.func_187227_b(ANIMATION_DATA, Integer.valueOf(data));
    }

    public int getAnimation() {
        return ((Integer) this.field_70180_af.func_187225_a(ANIMATION_DATA)).intValue();
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(128.0d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5000.0d);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        super.func_184651_r();
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityFish.class, false));
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        this.attackCooldown--;
        EntityLivingBase target = func_70638_az();
        if (target != null) {
            if (target.func_110143_aJ() <= 0.0f) {
                setAnimation(0);
            }
            if (this.field_70173_aa % 4 == 0) {
                int phaseData = 0;
                Vec3d selfLoc = func_174791_d();
                double distSqr = LMath.getDistanceSquaredToAABB(selfLoc, target.func_174813_aQ());
                if (distSqr <= 25.0d) {
                    this.serpentMoveHelper.setCorrectionRate(0.8d);
                    this.serpentMoveHelper.setCourseChangeChance(1);
                    if (target.func_110143_aJ() > 0.0f) {
                        phaseData = 1;
                    }
                } else {
                    this.serpentMoveHelper.setCorrectionRate(0.1d);
                    this.serpentMoveHelper.setCourseChangeChance(5);
                }
                this.serpentMoveHelper.func_75642_a(target.field_70165_t, target.field_70163_u + ((double) (target.field_70131_O / 2.0f)), target.field_70161_v, 1.0d);
                setAnimation(phaseData);
                return;
            }
            return;
        }
        setAnimation(0);
        this.serpentMoveHelper.setCorrectionRate(0.1d);
        this.serpentMoveHelper.setCourseChangeChance(5);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 20;
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_SEASERPENT;
    }

    protected void func_85033_bc() {
        if (this.attackCooldown > 0) {
            return;
        }
        this.attackCooldown = this.attackGracePeriod;
        Set<Entity> targets = new HashSet<>();
        for (Entity entity : this.segments) {
            List<Entity> segList = this.field_70170_p.func_175674_a(entity, entity.func_174813_aQ(), input -> {
                return (input == this || targets.contains(input) || !EntitySelectors.func_188442_a(entity).apply(input) || (input instanceof EntitySeaSerpentSegment) || (input instanceof EntitySeaSerpentController)) ? false : true;
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
        return new SeaSerpentMoveHelper(this);
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
        return 2.0f;
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
        IMaxAttack.dealMaxHealth((Entity) this, (EntityLivingBase) entitySeaCreature, 1, (List<String>) Collections.singletonList("Aquatic"));
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

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/sea/seaserpent/EntitySeaSerpentController$SeaSerpentMoveHelper.class */
    private static class SeaSerpentMoveHelper extends EntityMoveHelper {
        private final EntitySeaSerpentController parentEntity;
        private int courseChangeCooldown;
        private int courseChangeChance;
        private double correctionRate;

        public SeaSerpentMoveHelper(EntitySeaSerpentController leviathan) {
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
    public boolean func_70601_bi() {
        return super.func_70601_bi() && nothingInRadius(35);
    }
}
