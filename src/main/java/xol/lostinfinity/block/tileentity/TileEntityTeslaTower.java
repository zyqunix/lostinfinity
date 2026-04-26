package xol.lostinfinity.block.tileentity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.data.RayTraceBuilder;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityTeslaTower.class */
public class TileEntityTeslaTower extends TileEntity implements ITickable {
    private static final double RANGE = 40.0d;
    private final Set<BlockPos> others = new HashSet();
    private final Set<BlockPos> connected = new HashSet();
    private int towerId;
    private UUID owner;
    private boolean active;
    private int tickExisted;
    private boolean pendingUpdate;
    private int random1;
    private int random2;
    private float random3;

    public void func_73660_a() {
        if (this.tickExisted == 1200) {
            this.field_145850_b.func_175698_g(func_174877_v());
            return;
        }
        if (shouldRefresh()) {
            this.pendingUpdate = false;
            updateConnected();
        }
        if (!this.field_145850_b.field_72995_K && this.tickExisted % 5 == 0 && this.active) {
            zapEntities();
        }
        this.tickExisted++;
        this.random1 = this.field_145850_b.field_73012_v.nextInt(19);
        this.random2 = this.field_145850_b.field_73012_v.nextInt(7);
        this.random3 = this.field_145850_b.field_73012_v.nextFloat();
    }

    public void setTowerId(int towerId) {
        this.towerId = towerId;
    }

    public int getTowerId() {
        return this.towerId;
    }

    public int getRandom1() {
        return this.random1;
    }

    public int getRandom2() {
        return this.random2;
    }

    public float getRandom3() {
        return this.random3;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public void setOthers(Set<BlockPos> set) {
        this.others.clear();
        this.others.addAll(set);
        this.others.remove(func_174877_v());
        updateConnected();
    }

    public void setActive(boolean state) {
        this.active = state;
        if (this.active) {
            this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundInit.ELECTRIC_SHOCK, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }
    }

    public boolean isActive() {
        return this.active;
    }

    public void doBlockUpdate() {
        IBlockState blockState = this.field_145850_b.func_180495_p(func_174877_v());
        this.field_145850_b.func_184138_a(func_174877_v(), blockState, blockState, 3);
    }

    public Set<BlockPos> getConnected() {
        return this.connected;
    }

    public int getTickExisted() {
        return this.tickExisted;
    }

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.towerId = compound.func_74762_e("tower_id");
        this.tickExisted = compound.func_74762_e("age");
        this.owner = compound.func_186857_a("owner");
        this.active = compound.func_74767_n("active");
        readOthers(compound);
    }

    public NBTTagCompound func_189515_b(NBTTagCompound compound) {
        super.func_189515_b(compound);
        compound.func_74768_a("tower_id", this.towerId);
        compound.func_74768_a("age", this.tickExisted);
        if (this.owner != null) {
            compound.func_186854_a("owner", this.owner);
        }
        compound.func_74757_a("active", this.active);
        writeOthers(compound);
        return compound;
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    private boolean shouldRefresh() {
        for (BlockPos pos : this.connected) {
            if (this.field_145850_b.func_180495_p(pos) != BlockInit.teslaTower.func_176223_P()) {
                return true;
            }
        }
        return this.pendingUpdate || this.tickExisted % 100 == 0;
    }

    private void updateConnected() {
        this.connected.clear();
        for (BlockPos pos : this.others) {
            if (pos.func_177951_i(func_174877_v()) <= 1600.0d && (this.field_145850_b.func_175625_s(pos) instanceof TileEntityTeslaTower)) {
                this.connected.add(pos);
            }
        }
    }

    private void zapEntities() {
        EntityPlayer player = null;
        if (this.owner != null) {
            player = this.field_145850_b.func_152378_a(this.owner);
        }
        for (BlockPos other : this.connected) {
            Vec3d loc = new Vec3d(((double) this.field_174879_c.func_177958_n()) + 0.5d, ((double) this.field_174879_c.func_177956_o()) + 0.5d, ((double) this.field_174879_c.func_177952_p()) + 0.5d);
            Vec3d dir = LMath.fastNormalize(new Vec3d(other.func_177958_n() - this.field_174879_c.func_177958_n(), other.func_177956_o() - this.field_174879_c.func_177956_o(), other.func_177952_p() - this.field_174879_c.func_177952_p()));
            CustomRayTraceResult result = RayTraceBuilder.entity(EntityLivingBase.class, 40).maxEntity(0).trace(this.field_145850_b, null, loc.func_178787_e(dir), dir);
            if (result != null) {
                Iterator<Entity> it = result.getResultEntities().iterator();
                while (it.hasNext()) {
                    EntityPlayer entityPlayer = (Entity) it.next();
                    if ((entityPlayer instanceof EntityLivingBase) && entityPlayer != player) {
                        IMaxAttack.dealTrueDamage(player, (EntityLivingBase) entityPlayer, ((EntityLivingBase) entityPlayer).func_110138_aP() * 0.05f);
                    }
                }
            }
        }
    }

    private void readOthers(NBTTagCompound compound) {
        this.others.clear();
        for (int id = 0; compound.func_74764_b("pos_x_" + id); id++) {
            this.others.add(new BlockPos(compound.func_74762_e("pos_x_" + id), compound.func_74762_e("pos_y_" + id), compound.func_74762_e("pos_z_" + id)));
        }
    }

    private void writeOthers(NBTTagCompound compound) {
        int i = 0;
        for (BlockPos pos : this.others) {
            compound.func_74768_a("pos_x_" + i, pos.func_177958_n());
            compound.func_74768_a("pos_y_" + i, pos.func_177956_o());
            compound.func_74768_a("pos_z_" + i, pos.func_177952_p());
            i++;
        }
    }

    public NBTTagCompound func_189517_E_() {
        return func_189515_b(new NBTTagCompound());
    }

    @Nullable
    public SPacketUpdateTileEntity func_189518_D_() {
        return new SPacketUpdateTileEntity(this.field_174879_c, 0, func_189517_E_());
    }

    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        func_145839_a(pkt.func_148857_g());
        updateConnected();
    }
}
