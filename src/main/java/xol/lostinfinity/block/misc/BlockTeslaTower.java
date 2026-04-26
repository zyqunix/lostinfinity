package xol.lostinfinity.block.misc;
import javax.annotation.Nullable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicNoDrop;
import xol.lostinfinity.block.tileentity.TileEntityTeslaTower;
public class BlockTeslaTower extends BlockBasicNoDrop implements ITileEntityProvider {
    public BlockTeslaTower(String name) {
        super(name);
    }
    @Nullable
    public TileEntity func_149915_a(World worldIn, int meta) {
        return new TileEntityTeslaTower();
    }
}
