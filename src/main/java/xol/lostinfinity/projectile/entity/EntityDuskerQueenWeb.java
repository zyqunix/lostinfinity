package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.boss.EntityDuskerQueen;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityDuskerQueenWeb.class */
public class EntityDuskerQueenWeb extends Entity implements IMaxAttack {
    private static final DataParameter<Float> PLAYER_X = EntityDataManager.func_187226_a(EntityDuskerQueenWeb.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> PLAYER_Y = EntityDataManager.func_187226_a(EntityDuskerQueenWeb.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> PLAYER_Z = EntityDataManager.func_187226_a(EntityDuskerQueenWeb.class, DataSerializers.field_187193_c);
    private EntityPlayer target;
    private EntityDuskerQueen owner;
    private Vec3d stopPos;
    private float growth;

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

    public EntityPlayer getTarget() {
        return this.target;
    }

    public EntityDuskerQueenWeb(World worldIn) {
        super(worldIn);
        this.target = null;
        this.owner = null;
        this.stopPos = null;
        this.growth = 0.0f;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.growth < 1.0f) {
            this.growth += 0.05f;
        }
        Vec3d playerPos = getPlayerPos();
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
            if (this.target == null || this.owner == null || this.owner.field_70128_L || this.target.field_70128_L) {
                func_70106_y();
            } else {
                func_70634_a(this.owner.field_70165_t, this.owner.field_70163_u + ((double) (this.owner.field_70131_O / 2.0f)), this.owner.field_70161_v);
                setPlayerXYZ(new Vec3d(this.target.field_70165_t, this.target.field_70163_u, this.target.field_70161_v));
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
        this.field_70180_af.func_187214_a(PLAYER_X, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(PLAYER_Y, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(PLAYER_Z, Float.valueOf(0.0f));
    }

    public void setOwner(EntityDuskerQueen entityDuskerQueen) {
        this.owner = entityDuskerQueen;
    }

    public void setTarget(EntityPlayer target) {
        this.target = target;
    }

    public float getGrowth() {
        return this.growth;
    }
}
