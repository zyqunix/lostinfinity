package xol.lostinfinity.block.harvest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicLight;
import xol.lostinfinity.block.basic.ISpecialHarvest;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.starforge.EntityFlutterbee;
public class BlockHiveniteOre extends BlockBasicLight implements ISpecialHarvest {
    public BlockHiveniteOre(String name) {
        super(name);
        func_149711_c(2.0f);
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void failedHarvest(World world, BlockPos pos, EntityPlayer harvester) {
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getHarvestResult(World world, BlockPos pos) {
        return ItemInit.hivenite;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getToolNeeded() {
        return ItemInit.crystalPickaxe;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void worldHarvestEffect(World world, BlockPos pos, EntityPlayer harvester) {
        if (!world.field_72995_K) {
            world.func_175656_a(pos, BlockInit.hiveniteEmpty.func_176223_P());
            for (EntityFlutterbee bee : world.func_72872_a(EntityFlutterbee.class, new AxisAlignedBB(pos.func_177982_a(-15, -15, -15), pos.func_177982_a(15, 15, 15)))) {
                bee.shootMiner(harvester.func_180425_c().func_177984_a());
            }
        }
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public boolean isHarvestable(World world, BlockPos pos, EntityPlayer harvester) {
        return true;
    }
}
