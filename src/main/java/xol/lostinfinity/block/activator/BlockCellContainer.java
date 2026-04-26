package xol.lostinfinity.block.activator;

import java.util.Iterator;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.misc.EntityCellGameMerchant;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockCellContainer.class */
public class BlockCellContainer extends BlockBasic {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 9);

    public BlockCellContainer(String name, int i) {
        super(name);
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

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            AxisAlignedBB checkBox = new AxisAlignedBB(pos.func_177982_a(-9, -9, -9), pos.func_177982_a(9, 9, 9));
            Iterator it = worldIn.func_72872_a(EntityCellGameMerchant.class, checkBox).iterator();
            if (it.hasNext()) {
                EntityCellGameMerchant merch = (EntityCellGameMerchant) it.next();
                if (playerIn.func_184586_b(hand).func_77973_b().equals(ItemInit.powerAnalyzer)) {
                    merch.readPowerLevel(pos, worldIn, playerIn);
                    merch.selectCell1(pos, worldIn, playerIn);
                    return true;
                }
                merch.selectCell2(pos, worldIn, playerIn);
                return true;
            }
            return true;
        }
        return true;
    }

    public IBlockState getStateWithAmount(int amount) {
        return func_176223_P().func_177226_a(AMOUNT, Integer.valueOf(amount));
    }
}
