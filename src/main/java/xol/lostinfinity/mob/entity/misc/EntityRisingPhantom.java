package xol.lostinfinity.mob.entity.misc;

import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityRisingPhantom.class */
public class EntityRisingPhantom extends EntityImmaterial {
    private List<EntityLivingBase> hitTargets;
    private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.func_187226_a(EntityRisingPhantom.class, DataSerializers.field_187203_m);
    private static final DataParameter<Float> VELOCITY = EntityDataManager.func_187226_a(EntityRisingPhantom.class, DataSerializers.field_187193_c);
    private int livesTaken;
    private EntityPlayer owner;
    private float faceRotation;

    public EntityRisingPhantom(World world) {
        this(world, null);
    }

    public EntityRisingPhantom(World worldIn, EntityPlayer owner) {
        super(worldIn);
        this.hitTargets = new ArrayList();
        this.livesTaken = 0;
        this.faceRotation = 0.0f;
        func_70105_a(2.75f, 1.75f);
        func_184224_h(true);
        this.owner = owner;
        setOwner(owner == null ? null : owner.func_110124_au());
    }

    public float getFaceRotation() {
        return this.faceRotation;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(OWNER, Optional.absent());
        this.field_70180_af.func_187214_a(VELOCITY, Float.valueOf(0.0f));
    }

    public void setLivesTaken(int toTake) {
        this.livesTaken = toTake;
    }

    public void func_70071_h_() {
        this.field_70145_X = true;
        super.func_70071_h_();
        this.field_70145_X = false;
        func_189654_d(true);
    }

    public boolean func_70075_an() {
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        this.field_70759_as = this.field_70177_z;
        this.field_70761_aq = this.field_70177_z;
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K) {
            if (this.faceRotation < 1.5707963267948966d) {
                this.faceRotation += 0.02f;
            }
        } else if (this.field_70173_aa > 80) {
            func_70106_y();
        } else {
            this.field_70181_x = getVelocity();
            this.field_70133_I = true;
        }
    }

    protected void func_82167_n(Entity entityIn) {
        if (!this.field_70170_p.field_72995_K && (entityIn instanceof EntityLivingBase) && !(entityIn instanceof EntityRisingPhantom) && !entityIn.equals(this.owner)) {
            EntityMultipleLives entityMultipleLives = (EntityLivingBase) entityIn;
            if (!this.hitTargets.contains(entityMultipleLives)) {
                this.hitTargets.add(entityMultipleLives);
                if (!IMaxAttack.dealTrueDamage(this.owner, entityMultipleLives, entityMultipleLives.func_110138_aP() * 1.5f, Arrays.asList("Darkborn")).wasTargetKilled() && (entityMultipleLives instanceof EntityMultipleLives)) {
                    EntityMultipleLives multiLifer = entityMultipleLives;
                    multiLifer.takeawayNumLives(this.livesTaken);
                }
            }
        }
    }

    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_186854_a("phantom_owner", getOwner());
        tag.func_74776_a("init_velocity", getVelocity());
    }

    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setOwner(tag.func_186857_a("phantom_owner"));
        setVelocity(tag.func_74760_g("init_velocity"));
        this.owner = getOwnerEntity();
    }

    public UUID getOwner() {
        return (UUID) ((Optional) this.field_70180_af.func_187225_a(OWNER)).orNull();
    }

    public EntityPlayer getOwnerEntity() {
        UUID uuid = getOwner();
        if (uuid == null) {
            return null;
        }
        return this.field_70170_p.func_152378_a(uuid);
    }

    public void setOwner(UUID uuid) {
        this.field_70180_af.func_187227_b(OWNER, Optional.fromNullable(uuid));
    }

    public float getVelocity() {
        return ((Float) this.field_70180_af.func_187225_a(VELOCITY)).floatValue();
    }

    public void setVelocity(float vel) {
        this.field_70180_af.func_187227_b(VELOCITY, Float.valueOf(vel));
    }
}
