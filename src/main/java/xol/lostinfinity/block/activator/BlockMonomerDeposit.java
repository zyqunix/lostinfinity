package xol.lostinfinity.block.activator;

import java.util.Random;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockMonomerDeposit.class */
public class BlockMonomerDeposit extends BlockBasic {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 5);

    public BlockMonomerDeposit(String name) {
        super(name);
        func_149675_a(true);
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
        ItemStack held = playerIn.func_184586_b(hand);
        if (playerIn.func_70093_af() && !worldIn.field_72995_K) {
            int meta = func_176201_c(state);
            if (meta < 5) {
                worldIn.func_175656_a(pos, func_176203_a(meta + 1));
            } else {
                worldIn.func_175656_a(pos, func_176203_a(0));
            }
        }
        if (held.func_77973_b().equals(ItemInit.monomerCollector) && held.func_77942_o() && !worldIn.field_72995_K) {
            long endTime = held.func_77978_p().func_74763_f("EndTime");
            if (endTime > System.currentTimeMillis()) {
                int amount = worldIn.field_73012_v.nextInt(4) + 2;
                int meta2 = func_176201_c(state);
                if (meta2 != 0) {
                    switch (meta2) {
                        case 1:
                            playerIn.func_191521_c(new ItemStack(ItemInit.purpleMonomerSample, amount));
                            break;
                        case 2:
                            playerIn.func_191521_c(new ItemStack(ItemInit.blueMonomerSample, amount));
                            break;
                        case 3:
                            playerIn.func_191521_c(new ItemStack(ItemInit.redMonomerSample, amount));
                            break;
                        case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                            playerIn.func_191521_c(new ItemStack(ItemInit.yellowMonomerSample, amount));
                            break;
                        case 5:
                            playerIn.func_191521_c(new ItemStack(ItemInit.greenMonomerSample, amount));
                            break;
                    }
                    worldIn.func_175656_a(pos, func_176203_a(0));
                    return true;
                }
                return true;
            }
            playerIn.func_145747_a(new TextComponentString("You have taken too long to collect the monomers, the collector has been diminished"));
            return true;
        }
        return true;
    }

    public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.field_72995_K) {
            int meta = func_176201_c(state);
            if (meta == 0) {
                int randMeta = world.field_73012_v.nextInt(5) + 1;
                world.func_175656_a(pos, func_176203_a(randMeta));
            }
        }
    }
}
