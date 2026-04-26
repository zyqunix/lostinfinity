package xol.lostinfinity.block.harvest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicLight;
import xol.lostinfinity.block.basic.ISpecialHarvest;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.starforge.EntityGlomite;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class BlockPrismosisOre extends BlockBasicLight implements ISpecialHarvest, IMaxAttack {
    public BlockPrismosisOre(String name) {
        super(name);
        func_149711_c(2.0f);
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void failedHarvest(World world, BlockPos pos, EntityPlayer harvester) {
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getHarvestResult(World world, BlockPos pos) {
        return ItemInit.prismosisShards;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getToolNeeded() {
        return ItemInit.crystalPickaxe;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void worldHarvestEffect(World world, BlockPos pos, EntityPlayer harvester) {
        if (!world.field_72995_K) {
            boolean teleported = false;
            for (EntityGlomite glomite : world.func_72872_a(EntityGlomite.class, new AxisAlignedBB(pos.func_177982_a(-7, -7, -7), pos.func_177982_a(7, 7, 7)))) {
                glomite.func_70634_a(harvester.field_70165_t, harvester.field_70163_u, harvester.field_70161_v);
                glomite.func_70624_b(harvester);
                teleported = true;
            }
            if (teleported) {
                world.func_184133_a((EntityPlayer) null, pos, SoundInit.GLOMITE_TELEPORT, SoundCategory.HOSTILE, 2.0f, 1.0f);
                CustomParticleConfig config = new CustomParticleConfig();
                config.createInstance().setParticle(ParticleInit.GLOMITE_WARP).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(world, config, harvester.field_70165_t, harvester.field_70163_u + 0.5d, harvester.field_70161_v);
            }
        }
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public boolean isHarvestable(World world, BlockPos pos, EntityPlayer harvester) {
        return true;
    }
}
