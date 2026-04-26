package xol.lostinfinity.block.activator;

import javax.annotation.Nullable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.tileentity.TileEntityNavigationDevice;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockNavigationDevice.class */
public class BlockNavigationDevice extends BlockBasic implements ITileEntityProvider {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 12);

    public BlockNavigationDevice(String name) {
        super(name);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.func_184586_b(hand);
        TileEntity te = worldIn.func_175625_s(pos);
        if (te != null && (te instanceof TileEntityNavigationDevice)) {
            TileEntityNavigationDevice device = (TileEntityNavigationDevice) te;
            if (stack.func_77973_b() == ItemInit.basicSextant) {
                if (!worldIn.field_72995_K) {
                    playerIn.func_145747_a(new TextComponentString("From the lit starting block, light up the nodules to match the direction and colour shown in the sequence displayed."));
                }
                device.activate();
                stack.func_190918_g(1);
                return true;
            }
            device.checkCompletion(playerIn);
            return true;
        }
        return true;
    }

    public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return func_176223_P().func_177226_a(AMOUNT, 0);
    }

    public IBlockState func_176203_a(int meta) {
        return func_176223_P().func_177226_a(AMOUNT, Integer.valueOf(meta));
    }

    public int func_176201_c(IBlockState state) {
        return ((Integer) state.func_177229_b(AMOUNT)).intValue();
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer(this, new IProperty[]{AMOUNT});
    }

    @Nullable
    public TileEntity func_149915_a(World worldIn, int meta) {
        return null;
    }

    @Nullable
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityNavigationDevice();
    }

    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
}
