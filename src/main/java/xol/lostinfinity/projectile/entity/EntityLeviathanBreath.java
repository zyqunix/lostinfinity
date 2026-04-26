package xol.lostinfinity.projectile.entity;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.client.fx.ClientParticleRenderer;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.sea.leviathan.EntityLeviathanController;
import xol.lostinfinity.mob.entity.sea.leviathan.EntityLeviathanSegment;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.data.RayTraceBuilder;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityLeviathanBreath.class */
public class EntityLeviathanBreath extends Entity implements IMaxAttack {
    private static final List<String> DAMAGE_TYPE = Collections.singletonList("Aquatic");
    private static final DataParameter<Integer> OWNER = EntityDataManager.func_187226_a(EntityLeviathanBreath.class, DataSerializers.field_187192_b);
    private static final double BREATH_RANGE = 192.0d;
    private EntityLeviathanController owner;
    private Vec3d direction;
    private Vec3d prevDirection;
    private float size;

    public EntityLeviathanBreath(World worldIn) {
        super(worldIn);
        func_70105_a(0.25f, 0.25f);
        func_189654_d(true);
        this.field_70158_ak = true;
    }

    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(OWNER, 0);
    }

    public void setOwner(EntityLeviathanController owner) {
        this.owner = owner;
        this.field_70180_af.func_187227_b(OWNER, Integer.valueOf(owner.func_145782_y()));
    }

    public EntityLeviathanController getOwner() {
        return this.owner;
    }

    public void func_184206_a(DataParameter<?> key) {
        if (OWNER.equals(key)) {
            EntityLeviathanController entityLeviathanControllerFunc_73045_a = this.field_70170_p.func_73045_a(((Integer) this.field_70180_af.func_187225_a(OWNER)).intValue());
            if (entityLeviathanControllerFunc_73045_a instanceof EntityLeviathanController) {
                this.owner = entityLeviathanControllerFunc_73045_a;
                this.size = this.owner.getLeviathanSize();
            }
        }
    }

    public void onAddedToWorld() {
        super.onAddedToWorld();
        if (!this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.GENERIC_WEAPON_14, SoundCategory.HOSTILE, 16.0f, 1.0f);
        }
    }

    public void func_70030_z() {
        CustomRayTraceResult result;
        if (this.field_70173_aa > 200 || this.owner == null || this.owner.func_110143_aJ() <= 0.0f) {
            func_70106_y();
            return;
        }
        super.func_70030_z();
        Entity entity = this.owner.segments[0];
        func_70634_a(((EntityLeviathanSegment) entity).field_70165_t, ((EntityLeviathanSegment) entity).field_70163_u + ((double) (((EntityLeviathanSegment) entity).field_70131_O / 2.0f)), ((EntityLeviathanSegment) entity).field_70161_v);
        if (this.field_70170_p.field_72995_K) {
            this.prevDirection = this.direction;
            Vec3d lookVec = entity.func_70040_Z();
            this.direction = lookVec.func_186678_a(BREATH_RANGE);
            CustomParticleConfig config = new CustomParticleConfig();
            config.createInstance().setWeight(50).setParticle(ParticleInit.LARGE_BUBBLE).setSpread(this.size, this.size, this.size).setIgnoreRange(true);
            config.createInstance().setWeight(50).setParticle(ParticleInit.LARGE_BUBBLE_PURPLE).setSpread(this.size, this.size, this.size).setIgnoreRange(true);
            config.createInstance().setParticle(ParticleInit.TESLA_RING_YELLOW).setSpread(0.0d, 0.0d, 0.0d).setIgnoreRange(true);
            float f = 0.0f;
            while (true) {
                float i = f;
                if (i < BREATH_RANGE) {
                    config.setOrigin(((EntityLeviathanSegment) entity).field_70165_t + (lookVec.field_72450_a * ((double) i)), ((EntityLeviathanSegment) entity).field_70163_u + (lookVec.field_72448_b * ((double) i)), ((EntityLeviathanSegment) entity).field_70161_v + (lookVec.field_72449_c * ((double) i)));
                    ClientParticleRenderer.renderComplex(config);
                    f = i + this.size;
                } else {
                    return;
                }
            }
        } else {
            if (this.field_70173_aa % 5 != 0 || (result = RayTraceBuilder.entity(EntityLivingBase.class, 100).maxEntity(0).entityFilter(input -> {
                return (!RayTraceBuilder.checkEntityImmaterial(input) || (input instanceof EntityLeviathanSegment) || (input instanceof EntityLeviathanController)) ? false : true;
            }).force(true).raySize(this.size / 2.0f).trace(entity, true)) == null) {
                return;
            }
            Iterator<Entity> it = result.getResultEntities().iterator();
            while (it.hasNext()) {
                EntityLivingBase entityLivingBase = (Entity) it.next();
                IMaxAttack.dealTrueDamage(this, entityLivingBase, entityLivingBase.func_110138_aP() * 0.2f, DAMAGE_TYPE);
                this.field_70170_p.func_175688_a(EnumParticleTypes.EXPLOSION_HUGE, ((Entity) entityLivingBase).field_70165_t, ((Entity) entityLivingBase).field_70163_u, ((Entity) entityLivingBase).field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
            }
            this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.ELECTRIC_WOOSH, SoundCategory.HOSTILE, 16.0f, 1.0f);
        }
    }

    public Vec3d getDirection(float partialTick) {
        if (this.owner == null) {
            return null;
        }
        if (this.direction == null) {
            this.direction = this.owner.segments[0].func_70040_Z().func_186678_a(BREATH_RANGE);
        }
        return this.prevDirection != null ? LMath.lerp(this.prevDirection, this.direction, partialTick) : this.direction;
    }

    public float getOwnerSize() {
        return this.size;
    }

    protected void func_70037_a(NBTTagCompound compound) {
    }

    protected void func_70014_b(NBTTagCompound compound) {
    }

    public int func_70070_b() {
        return 15728880;
    }

    public float func_70013_c() {
        return 1.0f;
    }
}
