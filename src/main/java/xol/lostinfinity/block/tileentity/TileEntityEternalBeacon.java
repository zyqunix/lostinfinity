package xol.lostinfinity.block.tileentity;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
public class TileEntityEternalBeacon extends TileEntityGenerator implements ITickable {
    private int tickRemaining;
    private int activeTick;
    private int radius;
    public void func_73660_a() {
        this.activeTick++;
        if (this.tickRemaining > 0) {
            this.tickRemaining--;
        }
    }
    public int getTickRemaining() {
        return this.tickRemaining;
    }
    public int getActiveTick() {
        return this.activeTick;
    }
    public void setTickRemaining(int tickRemaining) {
        this.tickRemaining = tickRemaining;
    }
    public int getRadius() {
        return this.radius;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }
    @SideOnly(Side.CLIENT)
    public double func_145833_n() {
        return 65536.0d;
    }
    @Nullable
    public SPacketUpdateTileEntity func_189518_D_() {
        return new SPacketUpdateTileEntity(this.field_174879_c, 0, func_189517_E_());
    }
    public NBTTagCompound func_189517_E_() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74768_a("tick", this.tickRemaining);
        compound.func_74768_a("radius", this.radius);
        return compound;
    }
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        handleUpdateTag(pkt.func_148857_g());
    }
    public void handleUpdateTag(NBTTagCompound tag) {
        this.tickRemaining = tag.func_74762_e("tick");
        this.radius = tag.func_74762_e("radius");
    }
    public void doBlockUpdate() {
        IBlockState blockState = this.field_145850_b.func_180495_p(func_174877_v());
        this.field_145850_b.func_184138_a(func_174877_v(), blockState, blockState, 3);
    }
}
