package xol.lostinfinity.block.harvest;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.basic.ISpecialHarvest;
import xol.lostinfinity.init.ItemInit;
public class BlockCelestialLogsRed extends BlockBasic implements ISpecialHarvest {
    public BlockCelestialLogsRed(String name) {
        super(name, Material.field_151575_d);
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void failedHarvest(World world, BlockPos pos, EntityPlayer harvester) {
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getHarvestResult(World world, BlockPos pos) {
        return ItemInit.negativeMagnecronite;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getToolNeeded() {
        return ItemInit.forgefireAxe;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void worldHarvestEffect(World world, BlockPos pos, EntityPlayer harvester) {
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public boolean isHarvestable(World world, BlockPos pos, EntityPlayer harvester) {
        return true;
    }
}
