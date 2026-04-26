package xol.lostinfinity.block.misc;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicGlass;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;
public class BlockGalaxyPortal extends BlockBasicGlass {
    private int portalLocation;
    public BlockGalaxyPortal(String name, int portal) {
        super(name);
        this.portalLocation = 0;
        this.portalLocation = portal;
        func_149715_a(1.0f);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K && worldIn.field_73011_w.func_186058_p() == DimensionInit.nonexistence) {
            BlockPos teleTo = GalaxyCoordinates.getGalaxyTeleporter(this.portalLocation);
            playerIn.func_70634_a(teleTo.func_177958_n(), teleTo.func_177956_o(), teleTo.func_177952_p());
            return true;
        }
        return true;
    }
}
