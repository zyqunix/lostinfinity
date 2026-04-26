package xol.lostinfinity.block.misc;

import java.util.Random;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockSynchroniteSignal.class */
public class BlockSynchroniteSignal extends BlockBasic {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 3);

    public BlockSynchroniteSignal(String name) {
        super(name);
        func_149675_a(true);
    }

    public int func_149738_a(World worldIn) {
        return 3;
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            int meta = func_176201_c(state);
            boolean run = true;
            int newMeta = meta;
            while (run) {
                newMeta = worldIn.field_73012_v.nextInt(4);
                if (newMeta != meta) {
                    run = false;
                }
            }
            worldIn.func_184133_a((EntityPlayer) null, pos.func_177977_b(), SoundInit.SYNCHRONITE_SWITCH, SoundCategory.BLOCKS, 1.5f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
            worldIn.func_175656_a(pos, func_176203_a(newMeta));
            return true;
        }
        return true;
    }

    public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.field_72995_K) {
            int meta = func_176201_c(state);
            boolean run = true;
            int newMeta = meta;
            while (run) {
                newMeta = rand.nextInt(4);
                if (newMeta != meta) {
                    run = false;
                }
            }
            world.func_184133_a((EntityPlayer) null, pos.func_177977_b(), SoundInit.SYNCHRONITE_SWITCH, SoundCategory.BLOCKS, 1.5f, 0.8f + (world.field_73012_v.nextFloat() * 0.4f));
            world.func_175656_a(pos, func_176203_a(newMeta));
        }
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

    public IBlockState getStateWithAmount(int amount) {
        return func_176223_P().func_177226_a(AMOUNT, Integer.valueOf(amount));
    }
}
