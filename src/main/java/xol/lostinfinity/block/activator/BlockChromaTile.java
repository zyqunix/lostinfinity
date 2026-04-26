package xol.lostinfinity.block.activator;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.tileentity.TileEntityChromaGame;
import xol.lostinfinity.init.BlockInit;
public class BlockChromaTile extends BlockBasic {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 3);
    public BlockChromaTile(String name) {
        super(name);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            worldIn.func_175656_a(pos, getNewState(worldIn, pos, state, playerIn));
            ArrayList<Vec3i> neighbours = new ArrayList<>();
            neighbours.add(new Vec3i(1, 0, 0));
            neighbours.add(new Vec3i(-1, 0, 0));
            neighbours.add(new Vec3i(0, 0, 1));
            neighbours.add(new Vec3i(0, 0, -1));
            for (Vec3i neighbour : neighbours) {
                IBlockState neighbourState = worldIn.func_180495_p(pos.func_177971_a(neighbour));
                Block block = neighbourState.func_177230_c();
                if (block instanceof BlockChromaTile) {
                    worldIn.func_175656_a(pos.func_177971_a(neighbour), getNewState(worldIn, pos, neighbourState, playerIn));
                }
            }
            return true;
        }
        return true;
    }
    private IBlockState getNewState(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
        int meta = func_176201_c(state);
        BlockPos ref = pos.func_177982_a(0, 0, 0);
        for (int i = -20; i <= 20; i++) {
            for (int j = -20; j <= 20; j++) {
                BlockPos check = ref.func_177982_a(i, 0, j);
                if (worldIn.func_180495_p(check).func_177230_c().equals(BlockInit.chromaGame) && worldIn.func_175625_s(check) != null && (worldIn.func_175625_s(check) instanceof TileEntityChromaGame)) {
                    TileEntityChromaGame tileentity = (TileEntityChromaGame) worldIn.func_175625_s(check);
                    if (tileentity.isFirst(playerIn)) {
                        switch (meta) {
                            case 0:
                                return func_176203_a(1);
                            case 1:
                                return func_176203_a(0);
                            case 2:
                                return func_176203_a(3);
                            case 3:
                                return func_176203_a(2);
                        }
                    }
                    if (tileentity.isSecond(playerIn)) {
                        switch (meta) {
                            case 0:
                                return func_176203_a(2);
                            case 1:
                                return func_176203_a(3);
                            case 2:
                                return func_176203_a(0);
                            case 3:
                                return func_176203_a(1);
                        }
                    }
                    return state;
                }
            }
        }
        return state;
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
}
