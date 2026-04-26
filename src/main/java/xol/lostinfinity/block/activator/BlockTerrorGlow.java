package xol.lostinfinity.block.activator;
import java.util.ArrayList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicBoolState;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.boss.EntityDuskerQueen;
public class BlockTerrorGlow extends BlockBasicBoolState {
    public BlockTerrorGlow(String name) {
        super(name);
    }
    private static boolean validInput(ItemStack stack) {
        return stack.func_77973_b().equals(ItemInit.quickflameSolution);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            if (validInput(playerIn.func_184586_b(hand)) && !worldIn.field_72995_K && state == BlockInit.terrorGlow.func_176203_a(0)) {
                worldIn.func_175656_a(pos, BlockInit.terrorGlow.func_176203_a(1));
                ArrayList<BlockPos> lit = new ArrayList<>();
                lit.add(pos);
                int count = 0;
                for (int i = -20; i <= 20; i++) {
                    for (int j = -2; j <= 2; j++) {
                        for (int k = -20; k <= 20; k++) {
                            BlockPos check = pos.func_177982_a(i, j, k);
                            if (worldIn.func_180495_p(check) == BlockInit.terrorGlow.func_176203_a(1)) {
                                count++;
                                lit.add(check);
                            }
                        }
                    }
                }
                if (count >= 8) {
                    for (BlockPos litPos : lit) {
                        worldIn.func_175656_a(litPos, BlockInit.terrorGlow.func_176203_a(0));
                    }
                    EntityDuskerQueen queen = new EntityDuskerQueen(worldIn);
                    queen.func_70107_b(173.0d, 35.0d, -86.0d);
                    worldIn.func_72838_d(queen);
                }
            }
            playerIn.func_184586_b(hand).func_190918_g(1);
            return true;
        }
        return true;
    }
}
