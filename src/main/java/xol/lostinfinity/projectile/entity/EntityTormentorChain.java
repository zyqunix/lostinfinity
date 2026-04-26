package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityTormentorChain extends Entity implements IMaxAttack {
    private static final DataParameter<Float> TARGET_X = EntityDataManager.func_187226_a(EntityTormentorChain.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> TARGET_Y = EntityDataManager.func_187226_a(EntityTormentorChain.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> TARGET_Z = EntityDataManager.func_187226_a(EntityTormentorChain.class, DataSerializers.field_187193_c);
    private EntityLivingBase target;
    private EntityPlayer owner;
    private Vec3d stopPos;
    private BlockPos spawnLocation;
    private float growth;
    private boolean playedPull;
    public Vec3d getTargetPos() {
        double x = ((Float) this.field_70180_af.func_187225_a(TARGET_X)).floatValue();
        double y = ((Float) this.field_70180_af.func_187225_a(TARGET_Y)).floatValue();
        double z = ((Float) this.field_70180_af.func_187225_a(TARGET_Z)).floatValue();
        return new Vec3d(x, y, z);
    }
    public void setTargetXYZ(Vec3d pos) {
        float xpos = (float) pos.field_72450_a;
        float ypos = (float) pos.field_72448_b;
        float zpos = (float) pos.field_72449_c;
        this.field_70180_af.func_187227_b(TARGET_X, Float.valueOf(xpos));
        this.field_70180_af.func_187227_b(TARGET_Y, Float.valueOf(ypos));
        this.field_70180_af.func_187227_b(TARGET_Z, Float.valueOf(zpos));
    }
    public EntityLivingBase getTarget() {
        return this.target;
    }
    public EntityTormentorChain(World worldIn) {
        super(worldIn);
        this.target = null;
        this.owner = null;
        this.stopPos = null;
        this.spawnLocation = null;
        this.growth = 0.0f;
        this.playedPull = false;
    }
    public void setRespawnPosition(BlockPos newSpawn) {
        this.spawnLocation = newSpawn;
    }
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.growth < 1.0f) {
            this.growth += 0.04f;
        } else if (!this.playedPull && this.owner != null) {
            this.field_70170_p.func_184133_a((EntityPlayer) null, this.owner.func_180425_c(), SoundInit.MAGIC_WEAPON_16, SoundCategory.PLAYERS, 1.0f, 0.9f + (this.field_70170_p.field_73012_v.nextFloat() * 0.2f));
            this.playedPull = true;
        }
        Vec3d playerPos = getTargetPos();
        Vec3d dir = playerPos.func_178788_d(func_174791_d()).func_72432_b();
        int dist = (int) playerPos.func_72438_d(func_174791_d());
        Vec3d nextPos = func_174791_d().func_178787_e(dir);
        int steps = 1;
        boolean foundBlock = false;
        while (!foundBlock && steps < dist) {
            if (this.field_70170_p.func_175623_d(new BlockPos(nextPos.field_72450_a, nextPos.field_72448_b, nextPos.field_72449_c))) {
                steps++;
                nextPos = nextPos.func_178787_e(dir);
            } else {
                foundBlock = true;
            }
        }
        if (foundBlock) {
            this.stopPos = nextPos;
        } else {
            this.stopPos = null;
        }
        if (!this.field_70170_p.field_72995_K) {
            if (this.target == null || this.owner == null || this.owner.field_70128_L || this.target.field_70128_L || this.target.func_110143_aJ() <= 0.0f) {
                func_70106_y();
                return;
            }
            Vec3d lookVec = this.owner.func_70040_Z().func_178785_b(1.5707964f).func_72432_b();
            func_70634_a(this.owner.field_70165_t - (lookVec.field_72450_a / 2.0d), this.owner.field_70163_u + (((double) this.owner.field_70131_O) / 2.2d), this.owner.field_70161_v - (lookVec.field_72449_c / 2.0d));
            setTargetXYZ(new Vec3d(this.target.field_70165_t, (this.target.field_70163_u + (((double) this.target.field_70131_O) / 2.0d)) - 0.3d, this.target.field_70161_v));
            if (getStopPos() == null && this.growth >= 1.0f) {
                double length = this.target.func_174791_d().func_72438_d(func_174791_d());
                if (length < 2.0d) {
                    if (this.spawnLocation != null && (this.target instanceof EntityPlayer)) {
                        this.target.setSpawnDimension(0);
                        this.target.func_180473_a(this.spawnLocation, true);
                    }
                    AxisAlignedBB checkBox = new AxisAlignedBB(func_180425_c()).func_186662_g(10.0d);
                    for (EntityLivingBase entity : this.field_70170_p.func_72872_a(EntityLivingBase.class, checkBox)) {
                        if (!entity.equals(this.owner)) {
                            CustomParticleConfig config1 = new CustomParticleConfig();
                            config1.createInstance().setParticle(ParticleInit.BLUE_SKULL).setSpread(1.0d, 1.0d, 1.0d).setIgnoreRange(true);
                            IParticleSpawner.spawnParticle(this.field_70170_p, config1, entity.field_70165_t, entity.field_70163_u + ((double) (entity.field_70131_O / 2.0f)), entity.field_70161_v);
                            IMaxAttack.dealTrueDamage(this.owner, entity, this.target.func_110138_aP());
                        }
                    }
                    this.target.field_70159_w = 0.0d;
                    this.target.field_70181_x = 0.0d;
                    this.target.field_70179_y = 0.0d;
                    this.target.field_70133_I = true;
                    this.field_70170_p.func_184133_a((EntityPlayer) null, this.owner.func_180425_c(), SoundInit.MAGIC_WEAPON_17, SoundCategory.PLAYERS, 1.5f, 0.9f + (this.field_70170_p.field_73012_v.nextFloat() * 0.2f));
                    func_70106_y();
                    return;
                }
                Vec3d pullDir = func_174791_d().func_178788_d(this.target.func_174791_d()).func_72432_b();
                this.target.field_70159_w = pullDir.field_72450_a * 1.5d;
                this.target.field_70181_x = pullDir.field_72448_b * 1.5d;
                this.target.field_70179_y = pullDir.field_72449_c * 1.5d;
                this.target.field_70133_I = true;
                return;
            }
            return;
        }
        for (int i = 0; i < 3; i++) {
            double randX = (this.field_70165_t - 10.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 20.0d);
            double randY = (this.field_70163_u - 2.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 4.0d);
            double randZ = (this.field_70161_v - 10.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 20.0d);
            this.field_70170_p.func_175688_a(ParticleInit.SNOW_BUBBLE, randX, randY, randZ, 0.0d, 0.0d, 0.0d, new int[0]);
        }
    }
    public Vec3d getStopPos() {
        return this.stopPos;
    }
    protected void func_70037_a(NBTTagCompound compound) {
    }
    protected void func_70014_b(NBTTagCompound compound) {
    }
    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(TARGET_X, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(TARGET_Y, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(TARGET_Z, Float.valueOf(0.0f));
    }
    public void setOwner(EntityPlayer owner) {
        this.owner = owner;
    }
    public void setTarget(EntityLivingBase hit_entity) {
        this.target = hit_entity;
    }
    public float getGrowth() {
        return this.growth;
    }
}
