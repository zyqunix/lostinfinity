package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.mob.entity.boss.EntityCryonus;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityCryoBeamEffect extends Entity implements IMaxAttack {
    private static final DataParameter<Float> PLAYER_X = EntityDataManager.func_187226_a(EntityCryoBeamEffect.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> PLAYER_Y = EntityDataManager.func_187226_a(EntityCryoBeamEffect.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> PLAYER_Z = EntityDataManager.func_187226_a(EntityCryoBeamEffect.class, DataSerializers.field_187193_c);
    private static final DataParameter<Boolean> ICE_BLAST = EntityDataManager.func_187226_a(EntityCryoBeamEffect.class, DataSerializers.field_187198_h);
    private EntityPlayer target;
    private EntityCryonus owner;
    private Vec3d stopPos;
    private float growth;
    private float alpha;
    private boolean hasDamaged;
    public void setIceBlast(boolean mode) {
        this.field_70180_af.func_187227_b(ICE_BLAST, Boolean.valueOf(mode));
    }
    public boolean isIceBlast() {
        return ((Boolean) this.field_70180_af.func_187225_a(ICE_BLAST)).booleanValue();
    }
    public Vec3d getPlayerPos() {
        double x = ((Float) this.field_70180_af.func_187225_a(PLAYER_X)).floatValue();
        double y = ((Float) this.field_70180_af.func_187225_a(PLAYER_Y)).floatValue();
        double z = ((Float) this.field_70180_af.func_187225_a(PLAYER_Z)).floatValue();
        return new Vec3d(x, y, z);
    }
    public void setPlayerXYZ(Vec3d pos) {
        float xpos = (float) pos.field_72450_a;
        float ypos = (float) pos.field_72448_b;
        float zpos = (float) pos.field_72449_c;
        this.field_70180_af.func_187227_b(PLAYER_X, Float.valueOf(xpos));
        this.field_70180_af.func_187227_b(PLAYER_Y, Float.valueOf(ypos));
        this.field_70180_af.func_187227_b(PLAYER_Z, Float.valueOf(zpos));
    }
    public EntityCryoBeamEffect(World worldIn) {
        super(worldIn);
        this.target = null;
        this.owner = null;
        this.stopPos = null;
        this.growth = 1.0f;
        this.alpha = 1.0f;
        this.hasDamaged = false;
    }
    public void func_70071_h_() {
        super.func_70071_h_();
        boolean isIceBlast = isIceBlast();
        if (isIceBlast) {
            if (this.growth < 40.0f) {
                this.growth += 0.9f;
            } else {
                this.growth += 0.05f;
                if (this.alpha > 0.04f) {
                    this.alpha -= 0.1f;
                } else {
                    func_70106_y();
                }
            }
            if (!this.field_70170_p.field_72995_K) {
                if (this.target == null || this.owner == null || this.owner.field_70128_L || this.target.field_70128_L) {
                    func_70106_y();
                } else {
                    func_70634_a(this.owner.field_70165_t, this.owner.field_70163_u, this.owner.field_70161_v);
                    setPlayerXYZ(new Vec3d(this.target.field_70165_t, this.target.field_70163_u, this.target.field_70161_v));
                }
                Vec3d playerPos = getPlayerPos();
                double dist = playerPos.func_72438_d(func_174791_d());
                double yDiff = Math.abs(playerPos.field_72448_b - this.field_70163_u);
                if (Math.abs(dist - (((double) this.growth) / 2.0d)) < 0.20000000298023224d && yDiff < 10.0d && !this.hasDamaged) {
                    this.hasDamaged = true;
                    if (IMaxAttack.dealMaxHealth(this.owner, this.target, 2).didSuccessfulHit()) {
                        this.target.func_70690_d(new PotionEffect(PotionInit.VULNERABILITY, 200));
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        Vec3d playerPos2 = getPlayerPos();
        Vec3d dir = playerPos2.func_178788_d(func_174791_d()).func_72432_b();
        int dist2 = (int) playerPos2.func_72438_d(func_174791_d());
        Vec3d nextPos = func_174791_d().func_178787_e(dir);
        int steps = 1;
        boolean foundBlock = false;
        while (!foundBlock && steps < dist2) {
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
            if ((this.field_70173_aa > 4 && this.target == null) || this.owner == null) {
                func_70106_y();
            } else {
                func_70634_a(this.owner.field_70165_t, this.owner.field_70163_u, this.owner.field_70161_v);
                setPlayerXYZ(new Vec3d(this.target.field_70165_t, this.target.field_70163_u, this.target.field_70161_v));
            }
            if (this.stopPos == null && this.field_70173_aa % 10 == 0) {
                IMaxAttack.dealMaxHealth(this.owner, this.target, 3);
            }
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
        this.field_70180_af.func_187214_a(ICE_BLAST, false);
        this.field_70180_af.func_187214_a(PLAYER_X, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(PLAYER_Y, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(PLAYER_Z, Float.valueOf(0.0f));
    }
    public void setOwner(EntityCryonus owner) {
        this.owner = owner;
    }
    public void setTarget(EntityPlayer target) {
        this.target = target;
    }
    public float getGrowth() {
        return this.growth;
    }
    public float getAlpha() {
        return this.alpha;
    }
}
