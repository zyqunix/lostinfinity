package xol.lostinfinity.projectile.entity;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.world.World;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityWandAttack.class */
public class EntityWandAttack extends Entity {
    private UUID caster;
    private static final DataParameter<Float> AIM_X = EntityDataManager.func_187226_a(EntityWandAttack.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> AIM_Y = EntityDataManager.func_187226_a(EntityWandAttack.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> AIM_Z = EntityDataManager.func_187226_a(EntityWandAttack.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> PLAYER_X = EntityDataManager.func_187226_a(EntityWandAttack.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> PLAYER_Y = EntityDataManager.func_187226_a(EntityWandAttack.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> PLAYER_Z = EntityDataManager.func_187226_a(EntityWandAttack.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> YAW = EntityDataManager.func_187226_a(EntityWandAttack.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> PITCH = EntityDataManager.func_187226_a(EntityWandAttack.class, DataSerializers.field_187193_c);

    public EntityWandAttack(World worldIn) {
        super(worldIn);
    }

    public void setCaster(EntityPlayer playerIn) {
        this.caster = playerIn.func_110124_au();
    }

    public void setAimBlock(BlockPos pos) {
        float xpos = pos.func_177958_n();
        float ypos = pos.func_177956_o();
        float zpos = pos.func_177952_p();
        this.field_70180_af.func_187227_b(AIM_X, Float.valueOf(xpos));
        this.field_70180_af.func_187227_b(AIM_Y, Float.valueOf(ypos));
        this.field_70180_af.func_187227_b(AIM_Z, Float.valueOf(zpos));
    }

    public BlockPos getAimBlock() {
        double xpos = ((Float) this.field_70180_af.func_187225_a(AIM_X)).floatValue();
        double ypos = ((Float) this.field_70180_af.func_187225_a(AIM_Y)).floatValue();
        double zpos = ((Float) this.field_70180_af.func_187225_a(AIM_Z)).floatValue();
        return new BlockPos(xpos, ypos, zpos);
    }

    public void setPlayerXYZ(BlockPos pos) {
        float xpos = pos.func_177958_n();
        float ypos = pos.func_177956_o();
        float zpos = pos.func_177952_p();
        this.field_70180_af.func_187227_b(PLAYER_X, Float.valueOf(xpos));
        this.field_70180_af.func_187227_b(PLAYER_Y, Float.valueOf(ypos));
        this.field_70180_af.func_187227_b(PLAYER_Z, Float.valueOf(zpos));
    }

    public BlockPos getPlayerPos() {
        double xpos = ((Float) this.field_70180_af.func_187225_a(PLAYER_X)).floatValue();
        double ypos = ((Float) this.field_70180_af.func_187225_a(PLAYER_Y)).floatValue();
        double zpos = ((Float) this.field_70180_af.func_187225_a(PLAYER_Z)).floatValue();
        return new BlockPos(xpos, ypos, zpos);
    }

    public Vec2f func_189653_aC() {
        float playerYaw = ((Float) this.field_70180_af.func_187225_a(YAW)).floatValue();
        float playerPitch = ((Float) this.field_70180_af.func_187225_a(PITCH)).floatValue();
        Vec2f pitchYaw = new Vec2f(playerYaw, playerPitch);
        return pitchYaw;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa == 30) {
                func_70106_y();
            }
            EntityPlayer play = this.field_70170_p.func_152378_a(this.caster);
            if (play != null) {
                setPlayerXYZ(play.func_180425_c().func_177982_a(0, 1, 0));
                setPlayerYawPitch(play.func_189653_aC());
                return;
            } else {
                func_70106_y();
                return;
            }
        }
        BlockPos playerPos = getPlayerPos();
        for (int i = 0; i < 5; i++) {
            this.field_70170_p.func_175688_a(EnumParticleTypes.FIREWORKS_SPARK, playerPos.func_177958_n(), playerPos.func_177956_o(), playerPos.func_177952_p(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
        }
    }

    private void setPlayerYawPitch(Vec2f pitchYaw) {
        float playerYaw = pitchYaw.field_189982_i;
        float playerPitch = pitchYaw.field_189983_j;
        this.field_70180_af.func_187227_b(YAW, Float.valueOf(playerYaw));
        this.field_70180_af.func_187227_b(PITCH, Float.valueOf(playerPitch));
    }

    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(AIM_X, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(AIM_Y, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(AIM_Z, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(PLAYER_X, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(PLAYER_Y, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(PLAYER_Z, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(YAW, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(PITCH, Float.valueOf(0.0f));
    }

    protected void func_70037_a(NBTTagCompound compound) {
    }

    protected void func_70014_b(NBTTagCompound compound) {
    }
}
