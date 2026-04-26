package xol.lostinfinity.block.harvest;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicLight;
import xol.lostinfinity.block.basic.ISpecialHarvest;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.util.data.IMaxAttack;
public class BlockMyriteOre extends BlockBasicLight implements ISpecialHarvest, IMaxAttack {
    public BlockMyriteOre(String name) {
        super(name);
        func_149711_c(2.0f);
        func_149675_a(true);
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void failedHarvest(World world, BlockPos pos, EntityPlayer harvester) {
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getHarvestResult(World world, BlockPos pos) {
        return ItemInit.myrite;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getToolNeeded() {
        return ItemInit.crystalPickaxe;
    }
    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.field_72995_K) {
            worldIn.func_175656_a(pos, BlockInit.myriteOreDark.func_176223_P());
        }
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void worldHarvestEffect(World world, BlockPos pos, EntityPlayer harvester) {
        if (!world.field_72995_K) {
            world.func_175656_a(pos, BlockInit.myriteOreDark.func_176223_P());
        }
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public boolean isHarvestable(World world, BlockPos pos, EntityPlayer harvester) {
        return true;
    }
}
