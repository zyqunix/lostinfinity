package xol.lostinfinity.block.activator;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.tileentity.TileEntityPowerCollider;
import xol.lostinfinity.init.ItemInit;
public class BlockPowerCollider extends BlockBasic implements ITileEntityProvider {
    public BlockPowerCollider(String name) {
        super(name);
    }
    private boolean validInput(ItemStack stack) {
        return stack.func_77973_b().equals(ItemInit.chargeCell);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af() && validInput(playerIn.func_184586_b(hand))) {
            if (!worldIn.field_72995_K) {
                reset(worldIn, pos, state);
            }
            playerIn.func_184586_b(hand).func_190918_g(1);
            return true;
        }
        return true;
    }
    private void reset(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileEntity;
        if ((worldIn.func_180495_p(pos).func_177230_c() instanceof BlockPowerCollider) && (tileEntity = worldIn.func_175625_s(pos)) != null && (tileEntity instanceof TileEntityPowerCollider)) {
            TileEntityPowerCollider TE = (TileEntityPowerCollider) tileEntity;
            TE.reset();
        }
    }
    public TileEntity func_149915_a(World worldIn, int meta) {
        return null;
    }
    public boolean func_149716_u() {
        return true;
    }
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityPowerCollider();
    }
}
