package xol.lostinfinity.block.basic;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/basic/BlockStarforgeOre.class */
public class BlockStarforgeOre extends BlockBasic {
    public final boolean isDepletedType;
    private Item correspondingDrop;

    public BlockStarforgeOre(String name, boolean depleted) {
        super(name);
        this.isDepletedType = depleted;
        func_149675_a(depleted);
    }

    public BlockStarforgeOre(String name) {
        this(name, false);
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        worldIn.func_175656_a(pos, BlockInit.getRandomOre(worldIn).func_176223_P());
    }

    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.clear();
        drops.add(new ItemStack(this.correspondingDrop));
    }

    public BlockStarforgeOre setCorrespondingItem(Item item) {
        this.correspondingDrop = item;
        return this;
    }
}
