package xol.lostinfinity.mob.entity.cthulhu;

import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.vector.Quaternion;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/cthulhu/EntityCthulhuBlackHole.class */
public class EntityCthulhuBlackHole extends Entity implements ICthulhuMinion {
    private static final DataParameter<Integer> ANIMATION_TICK = EntityDataManager.func_187226_a(EntityCthulhuBlackHole.class, DataSerializers.field_187192_b);
    private static final double RANGE = 6.0d;
    private float lastGrowth;
    private float growth;
    private float lastRotation;
    private float rotation;
    private float angle;
    protected EntityCthulhu owner;

    @SideOnly(Side.CLIENT)
    private Quaternion[] rotations;
    private static final int LIFESPAN_TICKS = 200;

    public EntityCthulhuBlackHole(World worldIn) {
        super(worldIn);
        this.lastGrowth = 0.0f;
        this.growth = 0.0f;
        this.lastRotation = 0.0f;
        this.rotation = 0.0f;
        this.angle = 0.0f;
        func_70105_a(0.5f, 0.5f);
        if (worldIn.field_72995_K) {
            this.rotations = new Quaternion[6];
            Random random = worldIn.field_73012_v;
            for (int i = 0; i < this.rotations.length; i++) {
                this.rotations[i] = LMath.fromEulerDegree(random.nextFloat() * 360.0f, random.nextFloat() * 360.0f, random.nextFloat() * 360.0f);
            }
        }
    }

    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(ANIMATION_TICK, 0);
    }

    public void addAnimationTick() {
        this.field_70180_af.func_187227_b(ANIMATION_TICK, Integer.valueOf(getAnimationTick() + 1));
        ((EntityDataManager.DataEntry) this.field_70180_af.func_187231_c().get(ANIMATION_TICK.func_187155_a())).func_187208_a(false);
    }

    public int getAnimationTick() {
        return ((Integer) this.field_70180_af.func_187225_a(ANIMATION_TICK)).intValue();
    }

    @Override // xol.lostinfinity.mob.entity.cthulhu.ICthulhuMinion
    public void setOwner(EntityCthulhu owner) {
        this.owner = owner;
    }

    @Override // xol.lostinfinity.mob.entity.cthulhu.ICthulhuMinion
    public EntityCthulhu getOwner() {
        return this.owner;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        addAnimationTick();
        this.lastGrowth = this.growth;
        this.lastRotation = this.rotation;
        if (this.field_70173_aa < 100) {
            this.growth += 0.1f;
        } else {
            this.growth -= 0.075f;
        }
        this.rotation += 0.2f;
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % 5 == 0) {
                this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.GHOSTLY_CLOUDS, SoundCategory.PLAYERS, 1.5f, 0.5f + (this.field_70146_Z.nextFloat() * 0.6f));
            }
            if (this.field_70173_aa >= LIFESPAN_TICKS) {
                func_70106_y();
                return;
            }
            List<EntityLivingBase> nearEntities = this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(RANGE));
            for (EntityLivingBase entity : nearEntities) {
                if (!entity.equals(this.owner) && !(entity instanceof ICthulhuMinion) && !(entity instanceof EntityCthulhu)) {
                    entity.func_70024_g((this.field_70165_t - entity.field_70165_t) / 30.0d, (this.field_70163_u - entity.field_70163_u) / 20.0d, (this.field_70161_v - entity.field_70161_v) / 30.0d);
                    entity.field_70133_I = true;
                    if (entity.func_174813_aQ().func_72326_a(func_174813_aQ().func_186662_g(this.growth)) && this.field_70173_aa % 10 == 0) {
                        IMaxAttack.dealTrueDamage(this.owner, entity, entity.func_110138_aP() * 0.15f);
                    }
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                float growthDist = this.growth * 1.06f;
                this.angle += 0.15f;
                double velocity_x = ((double) growthDist) * Math.cos(this.angle);
                double velocity_z = ((double) growthDist) * Math.sin(this.angle);
                this.field_70170_p.func_175688_a(ParticleInit.GENERIC_DOT_PINK, this.field_70165_t - (velocity_x / 2.0d), this.field_70163_u - 0.25d, this.field_70161_v - (velocity_z / 2.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                this.field_70170_p.func_175688_a(ParticleInit.GENERIC_DOT_ACID, this.field_70165_t + (velocity_x / 2.0d), this.field_70163_u - 0.25d, this.field_70161_v + (velocity_z / 2.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                this.field_70170_p.func_175688_a(this.field_70146_Z.nextBoolean() ? ParticleInit.GENERIC_DOT_BLACK : ParticleInit.GENERIC_DOT_WHITE, this.field_70165_t + (velocity_x / 2.0d), this.field_70163_u, this.field_70161_v + (velocity_z / 2.0d), 0.0d, 0.0d, 0.0d, new int[0]);
            }
        }
        if (this.field_70170_p.field_72995_K && getAnimationTick() % 5 == 0) {
            if (this.rotations.length - 1 >= 0) {
                System.arraycopy(this.rotations, 1, this.rotations, 0, this.rotations.length - 1);
            }
            this.rotations[this.rotations.length - 1] = LMath.fromEulerDegree(this.field_70170_p.field_73012_v.nextFloat() * 360.0f, this.field_70170_p.field_73012_v.nextFloat() * 360.0f, this.field_70170_p.field_73012_v.nextFloat() * 360.0f);
        }
    }

    @SideOnly(Side.CLIENT)
    public Quaternion getRotation(int i) {
        return this.rotations[i];
    }

    public void func_70037_a(NBTTagCompound compound) {
        this.growth = compound.func_74760_g("growth");
        this.rotation = compound.func_74760_g("rotation");
        this.angle = compound.func_74760_g("angle");
        read(compound, this);
    }

    public void func_70014_b(NBTTagCompound compound) {
        compound.func_74776_a("growth", this.growth);
        compound.func_74776_a("rotation", this.rotation);
        compound.func_74776_a("angle", this.angle);
        write(compound);
    }

    public float getGrowth() {
        return this.growth;
    }

    public float getLastGrowth() {
        return this.lastGrowth;
    }

    public float getRotation() {
        return this.rotation;
    }

    public float getLastRotation() {
        return this.lastRotation;
    }

    public void func_70106_y() {
        this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 5.0f, false);
        List<EntityLivingBase> nearbyEntities = this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(5.0d));
        for (EntityLivingBase entityLivingBase : nearbyEntities) {
            if (!(entityLivingBase instanceof ICthulhuMinion) && entityLivingBase != this.owner) {
                entityLivingBase.func_70690_d(new PotionEffect(PotionInit.PLAGUE, LIFESPAN_TICKS, 3, false, false));
                entityLivingBase.func_70690_d(new PotionEffect(MobEffects.field_76436_u, LIFESPAN_TICKS, 3, false, false));
                entityLivingBase.func_70690_d(new PotionEffect(MobEffects.field_76421_d, LIFESPAN_TICKS, 3, false, false));
                IMaxAttack.dealTrueDamage(this, entityLivingBase, entityLivingBase.func_110138_aP());
            }
        }
        super.func_70106_y();
    }
}
