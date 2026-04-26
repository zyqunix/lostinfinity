package xol.lostinfinity.block.harvest;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicBoolState;
import xol.lostinfinity.block.basic.ISpecialHarvest;
import xol.lostinfinity.init.ItemInit;
public class BlockIncandesciteOre extends BlockBasicBoolState implements ISpecialHarvest {
    public BlockIncandesciteOre(String name) {
        super(name);
        func_149675_a(true);
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void failedHarvest(World world, BlockPos pos, EntityPlayer harvester) {
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getHarvestResult(World world, BlockPos pos) {
        return ItemInit.incandescite;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getToolNeeded() {
        return ItemInit.crystalPickaxe;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void worldHarvestEffect(World world, BlockPos pos, EntityPlayer harvester) {
        world.func_175656_a(pos, func_176203_a(0));
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public boolean isHarvestable(World world, BlockPos pos, EntityPlayer harvester) {
        return func_176201_c(world.func_180495_p(pos)) == 1;
    }
    public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.field_72995_K && func_176201_c(state) == 1) {
            world.func_175656_a(pos, func_176203_a(0));
        }
    }
}
