package xol.lostinfinity.block.activator;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;
public class BlockCellCharger extends BlockBasic {
    public BlockCellCharger(String name) {
        super(name);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.func_184586_b(hand);
        if (stack.func_77973_b() == ItemInit.unpoweredCell) {
            ArrayList<BlockPos> corePositions = GalaxyCoordinates.getCorePositions();
            boolean foundAll = true;
            Iterator<BlockPos> it = corePositions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BlockPos corePos = it.next();
                if (worldIn.func_180495_p(corePos) != BlockInit.blightedCore.func_176203_a(1)) {
                    foundAll = false;
                    break;
                }
            }
            if (foundAll) {
                if (!worldIn.field_72995_K) {
                    for (BlockPos corePos2 : corePositions) {
                        worldIn.func_175656_a(corePos2, BlockInit.blightedCore.func_176203_a(0));
                    }
                    playerIn.func_191521_c(new ItemStack(ItemInit.eternoCell));
                    worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.ELECTRIC_WOOSH, SoundCategory.MASTER, 1.0f, 1.0f);
                }
                stack.func_190918_g(1);
                return true;
            }
            if (!worldIn.field_72995_K) {
                playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "The power cores are not all powered."));
                return true;
            }
            return true;
        }
        return true;
    }
}
