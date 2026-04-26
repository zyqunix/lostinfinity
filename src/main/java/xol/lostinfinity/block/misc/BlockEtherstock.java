package xol.lostinfinity.block.misc;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockCappedPlant;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
public class BlockEtherstock extends BlockCappedPlant {
    public BlockEtherstock(String name) {
        super(name);
        func_149675_a(true);
    }
    @Override // xol.lostinfinity.block.basic.BlockCappedPlant
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.func_184586_b(hand);
        if (stack.func_77973_b() == ItemInit.magicBiopowder) {
            grow(worldIn, pos);
            if (!worldIn.field_72995_K) {
                worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.MAGIC_POWDER, SoundCategory.MASTER, 1.0f, 1.0f);
            }
            stack.func_190918_g(1);
            return true;
        }
        if (!worldIn.field_72995_K && func_176201_c(worldIn.func_180495_p(pos)) == 1) {
            BlockPos blockPosFunc_177977_b = pos;
            while (true) {
                BlockPos down = blockPosFunc_177977_b;
                if (worldIn.func_180495_p(down).func_177230_c() instanceof BlockEtherstock) {
                    worldIn.func_175698_g(down);
                    blockPosFunc_177977_b = down.func_177977_b();
                } else {
                    EntityItem ether = new EntityItem(worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), new ItemStack(ItemInit.etherflower, 1));
                    ether.field_70159_w = 0.0d;
                    ether.field_70181_x = 0.0d;
                    ether.field_70179_y = 0.0d;
                    worldIn.func_72838_d(ether);
                    return true;
                }
            }
        } else {
            return true;
        }
    }
    private void grow(World worldIn, BlockPos pos) {
        if (!worldIn.field_72995_K && func_176201_c(worldIn.func_180495_p(pos)) != 1 && worldIn.func_175623_d(pos.func_177984_a())) {
            boolean tallest = true;
            Iterator it = BlockPos.func_177980_a(pos.func_177982_a(-48, 0, -48), pos.func_177982_a(48, 0, 48)).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BlockPos checkPos = (BlockPos) it.next();
                if (!checkPos.equals(pos) && !worldIn.func_175623_d(checkPos)) {
                    tallest = false;
                    break;
                }
            }
            if (tallest) {
                worldIn.func_175656_a(pos, func_176203_a(1));
            } else {
                worldIn.func_175656_a(pos.func_177984_a(), func_176203_a(0));
            }
        }
    }
    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        grow(worldIn, pos);
    }
}
