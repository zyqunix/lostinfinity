package xol.lostinfinity.block.tileentity;
import java.util.ArrayList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.BlockInit;
public class TileEntityPortalNexus extends TileEntity implements ITickable {
    private static final double maxGrowth = 20.0d;
    private static final double growthSpeed = 0.1d;
    private static final double rotationSpeed = 0.20000000298023224d;
    private boolean active = false;
    private ArrayList<BlockPos> nodePositions = null;
    private double growth = 0.05d;
    private float rotation = 0.0f;
    public void func_73660_a() {
        if (this.field_145850_b.func_180495_p(func_174877_v()).equals(BlockInit.portalNexus.func_176203_a(1))) {
            this.active = true;
        } else {
            this.active = false;
            this.growth = 0.05d;
            this.rotation = 0.0f;
        }
        this.rotation = (float) (((double) this.rotation) + rotationSpeed);
        if (this.growth <= maxGrowth) {
            this.growth += growthSpeed;
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
    public void addNodePos(BlockPos pos) {
        if (this.nodePositions == null) {
            this.nodePositions = new ArrayList<>();
        }
        if (!this.nodePositions.contains(pos)) {
            this.nodePositions.add(pos);
        }
        if (this.nodePositions.size() >= 4) {
            this.field_145850_b.func_175656_a(func_174877_v(), BlockInit.portalNexus.func_176203_a(1));
        }
    }
    public double getGrowth() {
        return this.growth;
    }
    public float getRotation() {
        return this.rotation;
    }
}
