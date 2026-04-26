package xol.lostinfinity.mob.entity.misc;

import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityBaseCannon.class */
public class EntityBaseCannon extends EntityImmaterial {
    private static final DataParameter<Integer> ROTATION = EntityDataManager.func_187226_a(EntityBaseCannon.class, DataSerializers.field_187192_b);
    private UUID ownerID;
    private EntityPlayer owner;
    private Vec3d homePos;

    public EntityBaseCannon(World worldIn) {
        super(worldIn);
        this.ownerID = null;
        this.owner = null;
        this.homePos = null;
        func_70105_a(1.0f, 1.75f);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        func_184224_h(true);
        this.field_70765_h = null;
        this.field_70767_i = null;
        this.field_70180_af.func_187214_a(ROTATION, -1);
    }

    public void setHomePos(Vec3d homePos) {
        this.homePos = homePos;
    }

    protected void func_184651_r() {
    }

    public int getRotationMulti() {
        return ((Integer) this.field_70180_af.func_187225_a(ROTATION)).intValue();
    }

    public float getRotation() {
        return (float) ((((double) ((Integer) this.field_70180_af.func_187225_a(ROTATION)).intValue()) * 3.141592653589793d) / 8.0d);
    }

    public void setRotation(int i) {
        this.field_70180_af.func_187227_b(ROTATION, Integer.valueOf(i));
    }

    public void setOwner(EntityPlayer play) {
        this.owner = play;
    }

    public EntityPlayer getOwner() {
        return this.owner;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        EntityPlayer player;
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        this.field_70177_z = 0.0f;
        if (!this.field_70170_p.field_72995_K) {
            if (this.homePos != null && !func_174791_d().equals(this.homePos)) {
                func_70634_a(this.homePos.field_72450_a, this.homePos.field_72448_b, this.homePos.field_72449_c);
            }
            if (this.owner == null && this.ownerID != null && (player = this.field_70170_p.func_152378_a(this.ownerID)) != null) {
                this.owner = player;
            }
            if (this.field_70173_aa >= 900) {
                func_70106_y();
            }
        }
    }

    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        if (this.owner != null) {
            compound.func_186854_a("ownerID", this.owner.func_110124_au());
        }
        if (this.homePos != null) {
            compound.func_74780_a("homeX", this.homePos.field_72450_a);
            compound.func_74780_a("homeY", this.homePos.field_72448_b);
            compound.func_74780_a("homeZ", this.homePos.field_72449_c);
        }
    }

    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_186855_b("ownerID")) {
            this.ownerID = compound.func_186857_a("ownerID");
        }
        if (compound.func_74764_b("homeX")) {
            this.homePos = new Vec3d(compound.func_74769_h("homeX"), compound.func_74769_h("homeY"), compound.func_74769_h("homeZ"));
        } else {
            this.homePos = func_174791_d();
        }
    }

    protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (!this.field_70170_p.field_72995_K && this.owner != null && player.func_110124_au().equals(this.owner.func_110124_au())) {
            if (player.func_70093_af()) {
                func_70106_y();
                return true;
            }
            int rotation = getRotationMulti() + 1;
            if (rotation == 16) {
                rotation = 0;
            }
            setRotation(rotation);
            this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.GEAR_MACHINE, SoundCategory.PLAYERS, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
            return true;
        }
        return true;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public boolean func_70067_L() {
        return true;
    }
}
