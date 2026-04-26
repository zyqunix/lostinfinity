package xol.lostinfinity.projectile.entity;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityChainOfVenomsAttack.class */
public class EntityChainOfVenomsAttack extends Entity {
    private static final DataParameter<Float> PLAYER_X = EntityDataManager.func_187226_a(EntityChainOfVenomsAttack.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> PLAYER_Y = EntityDataManager.func_187226_a(EntityChainOfVenomsAttack.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> PLAYER_Z = EntityDataManager.func_187226_a(EntityChainOfVenomsAttack.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> ORIGIN_X = EntityDataManager.func_187226_a(EntityChainOfVenomsAttack.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> ORIGIN_Y = EntityDataManager.func_187226_a(EntityChainOfVenomsAttack.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> ORIGIN_Z = EntityDataManager.func_187226_a(EntityChainOfVenomsAttack.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> TARGET_X = EntityDataManager.func_187226_a(EntityChainOfVenomsAttack.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> TARGET_Y = EntityDataManager.func_187226_a(EntityChainOfVenomsAttack.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> TARGET_Z = EntityDataManager.func_187226_a(EntityChainOfVenomsAttack.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> ORIGIN_HEIGHT = EntityDataManager.func_187226_a(EntityChainOfVenomsAttack.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> TARGET_HEIGHT = EntityDataManager.func_187226_a(EntityChainOfVenomsAttack.class, DataSerializers.field_187193_c);
    private UUID caster;
    private int origin;
    private int target;

    public double getOriginHeight() {
        return ((Float) this.field_70180_af.func_187225_a(ORIGIN_HEIGHT)).floatValue();
    }

    public double getTargetHeight() {
        return ((Float) this.field_70180_af.func_187225_a(TARGET_HEIGHT)).floatValue();
    }

    public void setOriginHeight(double height) {
        this.field_70180_af.func_187227_b(ORIGIN_HEIGHT, Float.valueOf((float) height));
    }

    public void setTargetHeight(double height) {
        this.field_70180_af.func_187227_b(TARGET_HEIGHT, Float.valueOf((float) height));
    }

    public Vec3d getOriginPos() {
        double x = ((Float) this.field_70180_af.func_187225_a(ORIGIN_X)).floatValue();
        double y = ((Float) this.field_70180_af.func_187225_a(ORIGIN_Y)).floatValue();
        double z = ((Float) this.field_70180_af.func_187225_a(ORIGIN_Z)).floatValue();
        return new Vec3d(x, y, z);
    }

    public void setOriginPos(Vec3d pos) {
        float xpos = (float) pos.field_72450_a;
        float ypos = (float) pos.field_72448_b;
        float zpos = (float) pos.field_72449_c;
        this.field_70180_af.func_187227_b(ORIGIN_X, Float.valueOf(xpos));
        this.field_70180_af.func_187227_b(ORIGIN_Y, Float.valueOf(ypos));
        this.field_70180_af.func_187227_b(ORIGIN_Z, Float.valueOf(zpos));
    }

    public Vec3d getTargetPos() {
        double x = ((Float) this.field_70180_af.func_187225_a(TARGET_X)).floatValue();
        double y = ((Float) this.field_70180_af.func_187225_a(TARGET_Y)).floatValue();
        double z = ((Float) this.field_70180_af.func_187225_a(TARGET_Z)).floatValue();
        return new Vec3d(x, y, z);
    }

    public void setTargetPos(Vec3d pos) {
        float xpos = (float) pos.field_72450_a;
        float ypos = (float) pos.field_72448_b;
        float zpos = (float) pos.field_72449_c;
        this.field_70180_af.func_187227_b(TARGET_X, Float.valueOf(xpos));
        this.field_70180_af.func_187227_b(TARGET_Y, Float.valueOf(ypos));
        this.field_70180_af.func_187227_b(TARGET_Z, Float.valueOf(zpos));
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

    public void setCaster(EntityPlayer playerIn) {
        this.caster = playerIn.func_110124_au();
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public Entity getOrigin() {
        return this.field_70170_p.func_73045_a(this.origin);
    }

    public Entity getTarget() {
        return this.field_70170_p.func_73045_a(this.target);
    }

    public EntityChainOfVenomsAttack(World worldIn) {
        super(worldIn);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            if (this.caster == null) {
                func_70106_y();
                return;
            }
            EntityPlayer player = this.field_70170_p.func_152378_a(this.caster);
            EntityLivingBase originEntity = this.field_70170_p.func_73045_a(this.origin);
            EntityLivingBase targetEntity = this.field_70170_p.func_73045_a(this.target);
            if (player != null && originEntity != null && targetEntity != null) {
                setPlayerXYZ(new Vec3d(player.field_70165_t, player.field_70163_u, player.field_70161_v));
                setOriginPos(new Vec3d(originEntity.field_70165_t, originEntity.field_70163_u, originEntity.field_70161_v));
                setTargetPos(new Vec3d(targetEntity.field_70165_t, targetEntity.field_70163_u, targetEntity.field_70161_v));
                setOriginHeight(originEntity.field_70131_O);
                setTargetHeight(targetEntity.field_70131_O);
            }
            if (this.field_70173_aa == 20) {
                func_70106_y();
            }
        }
    }

    protected void func_70037_a(NBTTagCompound compound) {
    }

    protected void func_70014_b(NBTTagCompound compound) {
    }

    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(ORIGIN_HEIGHT, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(TARGET_HEIGHT, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(PLAYER_X, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(PLAYER_Y, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(PLAYER_Z, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(ORIGIN_X, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(ORIGIN_Y, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(ORIGIN_Z, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(TARGET_X, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(TARGET_Y, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(TARGET_Z, Float.valueOf(0.0f));
    }

    public EntityPlayer getCaster() {
        return this.field_70170_p.func_152378_a(this.caster);
    }
}
