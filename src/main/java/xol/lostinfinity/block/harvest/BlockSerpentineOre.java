package xol.lostinfinity.block.harvest;
import java.util.ArrayList;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicLight;
import xol.lostinfinity.block.basic.ISpecialHarvest;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
public class BlockSerpentineOre extends BlockBasicLight implements ISpecialHarvest {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 2);
    public BlockSerpentineOre(String name) {
        super(name);
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
    public IBlockState getStateWithAmount(int amount) {
        return func_176223_P().func_177226_a(AMOUNT, Integer.valueOf(amount));
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public boolean isHarvestable(World world, BlockPos pos, EntityPlayer harvester) {
        return func_176201_c(world.func_180495_p(pos)) == 2;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void failedHarvest(World world, BlockPos pos, EntityPlayer harvester) {
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getHarvestResult(World world, BlockPos pos) {
        return ItemInit.serpentineCrystal;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getToolNeeded() {
        return ItemInit.crystalPickaxe;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void worldHarvestEffect(World world, BlockPos pos, EntityPlayer harvester) {
        if (!world.field_72995_K) {
            reset(world, pos);
        }
    }
    private boolean validInput(Item item) {
        return item.equals(ItemInit.ultravioletSolution);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af() && validInput(playerIn.func_184586_b(hand).func_77973_b()) && !worldIn.field_72995_K) {
            if (state.equals(getStateWithAmount(1))) {
                worldIn.func_175656_a(pos, getStateWithAmount(2));
                playerIn.func_184586_b(hand).func_190918_g(1);
                worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.GLOW_BOMB, SoundCategory.BLOCKS, 1.0f, 1.0f);
                return true;
            }
            if (state.equals(getStateWithAmount(2))) {
                return true;
            }
            playerIn.func_184586_b(hand).func_190918_g(1);
            worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187620_cL, SoundCategory.BLOCKS, 1.0f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
            boolean found = findOreRadius(worldIn, pos, 20);
            if (!found) {
                reset(worldIn, pos);
                findOreRadius(worldIn, pos, 20);
                return true;
            }
            return true;
        }
        return true;
    }
    private boolean findOreRadius(World worldIn, BlockPos pos, int radius) {
        boolean found = false;
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                BlockPos checkPos = new BlockPos(pos.func_177958_n() + i, pos.func_177956_o(), pos.func_177952_p() + j);
                IBlockState checkState = worldIn.func_180495_p(checkPos);
                if (checkState.equals(getStateWithAmount(1))) {
                    int dist = ((int) pos.func_185332_f(checkPos.func_177958_n(), checkPos.func_177956_o(), checkPos.func_177952_p())) + worldIn.field_73012_v.nextInt(3);
                    for (EntityPlayer player : worldIn.func_72872_a(EntityPlayer.class, new AxisAlignedBB(pos.func_177982_a(-15, -15, -15), pos.func_177982_a(15, 15, 15)))) {
                        String message = String.format("The level of shine indicates the ore is within a radius of %d", Integer.valueOf(dist));
                        player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + message));
                        found = true;
                    }
                }
            }
        }
        return found;
    }
    private ArrayList<BlockPos> setBlockNeighbours(World world, BlockPos pos, ArrayList<BlockPos> visited) {
        ArrayList<BlockPos> moreNeighbours;
        ArrayList<BlockPos> reset = new ArrayList<>();
        if (world.func_180495_p(pos).equals(getStateWithAmount(2))) {
            world.func_175656_a(pos, getStateWithAmount(0));
            reset.add(pos);
        } else if (world.func_180495_p(pos).equals(getStateWithAmount(0))) {
            reset.add(pos);
        } else {
            return null;
        }
        ArrayList<BlockPos> neighbours = new ArrayList<>();
        visited.add(pos);
        neighbours.add(pos.func_177982_a(0, 0, 1));
        neighbours.add(pos.func_177982_a(0, 0, -1));
        neighbours.add(pos.func_177982_a(1, 0, 0));
        neighbours.add(pos.func_177982_a(-1, 0, 0));
        neighbours.add(pos.func_177982_a(-1, 0, 1));
        neighbours.add(pos.func_177982_a(-1, 0, -1));
        neighbours.add(pos.func_177982_a(1, 0, 1));
        neighbours.add(pos.func_177982_a(1, 0, -1));
        for (BlockPos neighbour : neighbours) {
            if (!visited.contains(neighbour) && (moreNeighbours = setBlockNeighbours(world, neighbour, visited)) != null) {
                for (BlockPos n : moreNeighbours) {
                    reset.add(n);
                }
            }
        }
        return reset;
    }
    private void reset(World world, BlockPos pos) {
        new ArrayList();
        ArrayList<BlockPos> visited = new ArrayList<>();
        ArrayList<BlockPos> blocks = setBlockNeighbours(world, pos, visited);
        int randDecoy = world.field_73012_v.nextInt(blocks.size());
        world.func_175656_a(blocks.get(randDecoy), getStateWithAmount(1));
    }
}
