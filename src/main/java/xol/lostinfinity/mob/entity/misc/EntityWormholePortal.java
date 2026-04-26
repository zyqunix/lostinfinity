package xol.lostinfinity.mob.entity.misc;
import com.google.common.base.Optional;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.basics.ItemChanneling;
import xol.lostinfinity.projectile.entity.EntityWormholeShot;
public class EntityWormholePortal extends Entity {
    protected static final DataParameter<Optional<UUID>> CASTER_ID = EntityDataManager.func_187226_a(EntityWormholePortal.class, DataSerializers.field_187203_m);
    protected static final DataParameter<Float> LOOK_X = EntityDataManager.func_187226_a(EntityWormholePortal.class, DataSerializers.field_187193_c);
    protected static final DataParameter<Float> LOOK_Y = EntityDataManager.func_187226_a(EntityWormholePortal.class, DataSerializers.field_187193_c);
    protected static final DataParameter<Float> LOOK_Z = EntityDataManager.func_187226_a(EntityWormholePortal.class, DataSerializers.field_187193_c);
    protected static final DataParameter<Boolean> SHOOT = EntityDataManager.func_187226_a(EntityWormholePortal.class, DataSerializers.field_187198_h);
    public EntityWormholePortal(World worldIn) {
        super(worldIn);
        func_184224_h(true);
    }
    public void func_70071_h_() {
        super.func_70071_h_();
        if (getCaster() == null) {
            func_70106_y();
            return;
        }
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % 5 == 0) {
                this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.GENERIC_WEAPON_23, SoundCategory.PLAYERS, 0.5f, 0.6f + (this.field_70146_Z.nextFloat() * 0.8f));
            }
            Entity caster = getCaster();
            ItemStack stack = caster.func_184614_ca();
            if (stack.func_77973_b() == ItemInit.wormholeRifle && stack.func_77942_o()) {
                int range = (stack.func_77978_p().func_74762_e("usetype_data") + 1) * 10;
                Vec3d look = caster.func_70040_Z().func_72432_b();
                Vec3d sideVec = caster.func_70040_Z().func_178785_b(1.5707964f);
                Vec3d firstPortalPos = caster.func_174791_d().func_72441_c((-sideVec.field_72450_a) / 4.0d, ((double) ((EntityPlayer) caster).field_70131_O) / 1.1d, (-sideVec.field_72449_c) / 4.0d).func_72441_c(look.field_72450_a, 0.0d, look.field_72449_c);
                Vec3d secondPortalPos = firstPortalPos.func_178787_e(look.func_186678_a(range));
                if (ItemChanneling.isChanneling(caster, stack)) {
                    if (getShoot()) {
                        func_70634_a(secondPortalPos.field_72450_a, secondPortalPos.field_72448_b, secondPortalPos.field_72449_c);
                        if (this.field_70173_aa % 5 == 0) {
                            Vec3d normLook = caster.func_70040_Z().func_72432_b().func_186678_a(1.0d);
                            EntityWormholeShot shot = new EntityWormholeShot(this.field_70170_p, caster);
                            shot.func_70634_a(this.field_70165_t + normLook.field_72450_a, this.field_70163_u + normLook.field_72448_b, this.field_70161_v + normLook.field_72449_c);
                            shot.func_184538_a(caster, ((EntityPlayer) caster).field_70125_A, ((EntityPlayer) caster).field_70177_z, 0.0f, 1.5f, 0.5f);
                            this.field_70170_p.func_72838_d(shot);
                            return;
                        }
                        return;
                    }
                    func_70634_a(firstPortalPos.field_72450_a, firstPortalPos.field_72448_b, firstPortalPos.field_72449_c);
                    return;
                }
                func_70106_y();
                return;
            }
            func_70106_y();
        }
    }
    public EntityPlayer getCaster() {
        if (((Optional) this.field_70180_af.func_187225_a(CASTER_ID)).orNull() != null) {
            return this.field_70170_p.func_152378_a((UUID) ((Optional) this.field_70180_af.func_187225_a(CASTER_ID)).get());
        }
        return null;
    }
    public void setCaster(EntityPlayer player) {
        this.field_70180_af.func_187227_b(CASTER_ID, Optional.fromNullable(player.func_110124_au()));
    }
    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(CASTER_ID, Optional.absent());
        this.field_70180_af.func_187214_a(LOOK_X, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(LOOK_Y, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(LOOK_Z, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(SHOOT, false);
    }
    public void setPlayerLook(Vec3d lookVec) {
        this.field_70180_af.func_187227_b(LOOK_X, Float.valueOf((float) lookVec.field_72450_a));
        this.field_70180_af.func_187227_b(LOOK_Y, Float.valueOf((float) lookVec.field_72448_b));
        this.field_70180_af.func_187227_b(LOOK_Z, Float.valueOf((float) lookVec.field_72449_c));
    }
    protected void func_70037_a(NBTTagCompound compound) {
    }
    protected void func_70014_b(NBTTagCompound compound) {
    }
    public Vec3d getPlayerLook() {
        return new Vec3d(((Float) this.field_70180_af.func_187225_a(LOOK_X)).floatValue(), ((Float) this.field_70180_af.func_187225_a(LOOK_Y)).floatValue(), ((Float) this.field_70180_af.func_187225_a(LOOK_Z)).floatValue());
    }
    public void setShoot() {
        this.field_70180_af.func_187227_b(SHOOT, true);
    }
    private boolean getShoot() {
        return ((Boolean) this.field_70180_af.func_187225_a(SHOOT)).booleanValue();
    }
}
