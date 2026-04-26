package xol.lostinfinity.block.tileentity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.BlockInit;
public class TileEntityPortalNode extends TileEntity implements ITickable {
    private boolean active = false;
    private BlockPos nexusPos = null;
    public void func_73660_a() {
        if (!this.field_145850_b.field_72995_K) {
            if (this.field_145850_b.func_180495_p(func_174877_v()).equals(BlockInit.portalNode.func_176203_a(1))) {
                this.active = true;
            } else {
                this.active = false;
            }
        }
    }
    public boolean isActive() {
        return this.active;
    }
    public boolean shouldRenderInPass(int pass) {
        return true;
    }
    public AxisAlignedBB getRenderBoundingBox() {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        return bb;
    }
    @SideOnly(Side.CLIENT)
    public double func_145833_n() {
        return 65536.0d;
    }
    public BlockPos getNexusPos() {
        return this.nexusPos;
    }
    public void setNexusPos(BlockPos nexusPos) {
        this.nexusPos = nexusPos;
    }
}
