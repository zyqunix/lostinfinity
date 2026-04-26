package xol.lostinfinity.block.harvest;
import java.util.Random;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.basic.ISpecialHarvest;
import xol.lostinfinity.block.misc.BlockSynchroniteSignal;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class BlockSynchroniteOre extends BlockBasic implements ISpecialHarvest {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 4);
    public BlockSynchroniteOre(String name) {
        super(name);
        func_149675_a(true);
        func_149711_c(2.0f);
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
    public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.field_72995_K) {
            Iterable<BlockPos> nearblocks = BlockPos.func_177980_a(pos.func_177982_a(-3, -3, -3), pos.func_177982_a(3, 3, 3));
            for (BlockPos nearpos : nearblocks) {
                if (world.func_180495_p(nearpos).func_177230_c() instanceof BlockSynchroniteOre) {
                    int newMeta = 1 + rand.nextInt(4);
                    world.func_175656_a(nearpos, func_176203_a(newMeta));
                }
            }
        }
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getHarvestResult(World world, BlockPos pos) {
        switch (func_176201_c(world.func_180495_p(pos))) {
            case 1:
                return ItemInit.synchroniteTypeA;
            case 2:
                return ItemInit.synchroniteTypeB;
            case 3:
                return ItemInit.synchroniteTypeC;
            case TileEntityFusionTable.BOARD_ROWS :
                return ItemInit.synchroniteTypeD;
            default:
                return ItemInit.synchroniteTypeA;
        }
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getToolNeeded() {
        return ItemInit.crystalPickaxe;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void worldHarvestEffect(World world, BlockPos pos, EntityPlayer harvester) {
        if (!world.field_72995_K) {
            world.func_175656_a(pos, func_176203_a(0));
        }
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public boolean isHarvestable(World world, BlockPos pos, EntityPlayer harvester) {
        int meta = func_176201_c(world.func_180495_p(pos));
        IBlockState state = world.func_180495_p(GalaxyCoordinates.SynchroniteSignal());
        BlockSynchroniteSignal signal = (BlockSynchroniteSignal) state.func_177230_c();
        int requiredMeta = 1 + signal.func_176201_c(state);
        return meta != 0 && meta == requiredMeta;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void failedHarvest(World world, BlockPos pos, EntityPlayer harvester) {
        if (!world.field_72995_K) {
            IBlockState state = world.func_180495_p(pos);
            if (func_176201_c(state) != 0) {
                world.func_72876_a((Entity) null, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 3.0f, false);
                for (EntityPlayer target : world.func_72872_a(EntityPlayer.class, new AxisAlignedBB(pos).func_186662_g(5.0d))) {
                    harvester.func_70606_j(harvester.func_110143_aJ() - (harvester.func_110138_aP() * 2.0f));
                    target.func_145747_a(new TextComponentString(TextFmt.Red + "A massive reaction occured from mining an asynchronous ore!"));
                }
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.ION_BLAST).setSpread(6.0d, 4.0d, 6.0d).setCount(5).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(world, config1, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
                CustomParticleConfig config2 = new CustomParticleConfig();
                config2.createInstance().setParticle(ParticleInit.NUCLEAR_BLAST).setSpread(1.0d, 1.0d, 1.0d).setCount(5).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(world, config2, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            }
        }
    }
}
