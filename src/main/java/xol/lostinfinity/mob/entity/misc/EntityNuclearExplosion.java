package xol.lostinfinity.mob.entity.misc;

import java.util.Random;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.vector.Quaternion;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityNuclearExplosion.class */
public class EntityNuclearExplosion extends EntityImmaterial implements IMaxAttack {
    private static final DataParameter<Integer> ANIMATION_TICK = EntityDataManager.func_187226_a(EntityNuclearExplosion.class, DataSerializers.field_187192_b);
    private static final float PULL_STRENGTH = 0.2f;
    private UUID ownerUUID;
    private boolean hasExploded;

    @SideOnly(Side.CLIENT)
    private Quaternion[] rotations;

    public EntityNuclearExplosion(World worldIn) {
        super(worldIn);
        func_70105_a(0.001f, 0.001f);
        func_82142_c(true);
        this.field_70145_X = true;
        if (worldIn.field_72995_K) {
            this.rotations = new Quaternion[6];
            Random random = worldIn.field_73012_v;
            for (int i = 0; i < this.rotations.length; i++) {
                this.rotations[i] = LMath.fromEulerDegree(random.nextFloat() * 360.0f, random.nextFloat() * 360.0f, random.nextFloat() * 360.0f);
            }
        }
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(ANIMATION_TICK, 0);
    }

    public void addAnimationTick() {
        this.field_70180_af.func_187227_b(ANIMATION_TICK, Integer.valueOf(getAnimationTick() + 1));
        ((EntityDataManager.DataEntry) this.field_70180_af.func_187231_c().get(ANIMATION_TICK.func_187155_a())).func_187208_a(false);
    }

    public int getAnimationTick() {
        return ((Integer) this.field_70180_af.func_187225_a(ANIMATION_TICK)).intValue();
    }

    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_186854_a("OwnerUUID", this.ownerUUID);
    }

    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        this.ownerUUID = tag.func_186857_a("OwnerUUID");
    }

    public void setOwner(UUID uuid) {
        this.ownerUUID = uuid;
    }

    @SideOnly(Side.CLIENT)
    public Quaternion getRotation(int i) {
        return this.rotations[i];
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        addAnimationTick();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa > 200) {
                func_70106_y();
                return;
            } else {
                if (!this.hasExploded) {
                    if (this.field_70173_aa > 160) {
                        explosion();
                        return;
                    } else {
                        pullNearby();
                        return;
                    }
                }
                return;
            }
        }
        if (getAnimationTick() % 5 == 0) {
            if (this.rotations.length - 1 >= 0) {
                System.arraycopy(this.rotations, 1, this.rotations, 0, this.rotations.length - 1);
            }
            this.rotations[this.rotations.length - 1] = LMath.fromEulerDegree(this.field_70170_p.field_73012_v.nextFloat() * 360.0f, this.field_70170_p.field_73012_v.nextFloat() * 360.0f, this.field_70170_p.field_73012_v.nextFloat() * 360.0f);
        }
    }

    protected void func_82167_n(Entity entityIn) {
    }

    private void pullNearby() {
        EntityNuclearExplosion entityNuclearExplosion = this;
        EntityNuclearExplosion entityNuclearExplosionFunc_152378_a = this.field_70170_p.func_152378_a(this.ownerUUID);
        if (entityNuclearExplosionFunc_152378_a != null) {
            entityNuclearExplosion = entityNuclearExplosionFunc_152378_a;
        }
        Vec3d origin = func_174791_d();
        for (EntityLivingBase target : this.field_70170_p.func_175647_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(32.0d, 32.0d, 32.0d), input -> {
            return !(input instanceof EntityImmaterial);
        })) {
            if (target != entityNuclearExplosion && target != this) {
                Vec3d dir = LMath.fastNormalize(origin.func_178788_d(target.func_174791_d())).func_186678_a(0.20000000298023224d);
                target.func_70024_g(dir.field_72450_a, dir.field_72448_b, dir.field_72449_c);
                target.field_70133_I = true;
            }
        }
        if (this.field_70173_aa % 20 == 0 && this.field_70173_aa < 140) {
            this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.GENERIC_WEAPON_12, SoundCategory.MASTER, 5.0f, 0.5f);
        }
    }

    private void explosion() {
        this.hasExploded = true;
        EntityNuclearExplosion entityNuclearExplosion = this;
        EntityNuclearExplosion entityNuclearExplosionFunc_152378_a = this.field_70170_p.func_152378_a(this.ownerUUID);
        if (entityNuclearExplosionFunc_152378_a != null) {
            entityNuclearExplosion = entityNuclearExplosionFunc_152378_a;
        }
        for (EntityMultipleLives entityMultipleLives : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(60.0d, 60.0d, 60.0d))) {
            if (entityMultipleLives != entityNuclearExplosion && entityMultipleLives != this) {
                if (entityMultipleLives.func_70032_d(this) < 15.0f) {
                    if (!IMaxAttack.dealTrueDamage(entityNuclearExplosion, entityMultipleLives, entityMultipleLives.func_110138_aP() * 2.0f).wasTargetKilled() && (entityMultipleLives instanceof EntityMultipleLives)) {
                        EntityMultipleLives multicreature = entityMultipleLives;
                        multicreature.takeawayNumLives(8);
                    }
                } else {
                    IMaxAttack.dealMaxHealth((Entity) entityNuclearExplosion, (EntityLivingBase) entityMultipleLives, 1, 4.0f);
                }
            }
        }
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(70.0d, 70.0d, 70.0d))) {
            this.field_70170_p.func_184133_a((EntityPlayer) null, near_pl.func_180425_c(), SoundInit.DEEP_EXPLOSION, SoundCategory.MASTER, 1.5f, 0.75f + (this.field_70146_Z.nextFloat() * 0.5f));
        }
    }
}
