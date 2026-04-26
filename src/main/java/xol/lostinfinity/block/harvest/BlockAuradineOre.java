package xol.lostinfinity.block.harvest;
import java.util.Random;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.basic.ISpecialHarvest;
import xol.lostinfinity.init.ItemInit;
public class BlockAuradineOre extends BlockBasic implements ISpecialHarvest {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 4);
    public BlockAuradineOre(String name) {
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
        if (held.func_77973_b() == ItemInit.chlorodivergentSolution) {
            int oreValue = func_176201_c(state);
            if (oreValue < 4 && !worldIn.field_72995_K) {
                Iterable<BlockPos> nearblocks = BlockPos.func_177980_a(pos.func_177982_a(-7, -7, -7), pos.func_177982_a(7, 7, 7));
                BlockPos nearestPos = null;
                for (BlockPos nearpos : nearblocks) {
                    IBlockState nearState = worldIn.func_180495_p(nearpos);
                    if (nearState.func_177230_c() instanceof BlockAuradineOre) {
                        int nearValue = func_176201_c(nearState);
                        if (!nearpos.equals(pos) && nearValue == oreValue && (nearestPos == null || pos.func_185332_f(nearpos.func_177958_n(), nearpos.func_177956_o(), nearpos.func_177952_p()) < pos.func_185332_f(nearestPos.func_177958_n(), nearestPos.func_177956_o(), nearestPos.func_177952_p()))) {
                            nearestPos = nearpos;
                        }
                    }
                }
                if (nearestPos != null) {
                    worldIn.func_175656_a(nearestPos, func_176203_a(oreValue + 1));
                    worldIn.func_175656_a(pos, func_176203_a(0));
                }
                worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187884_fr, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
            held.func_190918_g(1);
            return true;
        }
        return true;
    }
    public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
        int oreValue;
        if (!world.field_72995_K && (oreValue = func_176201_c(state)) > 0 && world.field_73012_v.nextInt(8) == 0) {
            world.func_175656_a(pos, func_176203_a(oreValue - 1));
        }
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getHarvestResult(World world, BlockPos pos) {
        return ItemInit.auradine;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getToolNeeded() {
        return ItemInit.crystalPickaxe;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void worldHarvestEffect(World world, BlockPos pos, EntityPlayer harvester) {
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public boolean isHarvestable(World world, BlockPos pos, EntityPlayer harvester) {
        return func_176201_c(world.func_180495_p(pos)) == 4;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void failedHarvest(World world, BlockPos pos, EntityPlayer harvester) {
    }
}
