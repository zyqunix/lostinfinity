package xol.lostinfinity.block.misc;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicGui;
import xol.lostinfinity.block.tileentity.TileEntityGearbox;
import xol.lostinfinity.gui.GuiHandler;
public class BlockGearbox extends BlockBasicGui implements ITileEntityProvider {
    public BlockGearbox(String name) {
        super(name, GuiHandler.RegisteredGuis.GEARBOX.getId());
    }
    public TileEntity func_149915_a(World worldIn, int meta) {
        return null;
    }
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityGearbox();
    }
    public boolean func_149716_u() {
        return true;
    }
    @Override // xol.lostinfinity.block.basic.BlockBasicGui
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity te = worldIn.func_175625_s(pos);
        if (te != null && (te instanceof TileEntityGearbox) && ((TileEntityGearbox) te).getPowered()) {
            return super.func_180639_a(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        }
        if (!worldIn.field_72995_K) {
            playerIn.func_145747_a(new TextComponentString("The gearbox must have a powered engine connected"));
            return true;
        }
        return true;
    }
}
