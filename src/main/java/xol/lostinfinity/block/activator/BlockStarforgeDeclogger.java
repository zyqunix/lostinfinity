package xol.lostinfinity.block.activator;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
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
public class BlockStarforgeDeclogger extends BlockBasic {
    public BlockStarforgeDeclogger(String name) {
        super(name, Material.field_151576_e);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            List<BlockPos> blocks = new ArrayList<>();
            for (int xpos = -6; xpos >= -9; xpos--) {
                BlockPos checkPos = new BlockPos(pos.func_177982_a(xpos, 9, -1));
                IBlockState checkState = worldIn.func_180495_p(checkPos);
                if (checkState.func_177230_c().equals(BlockInit.slagDeposit)) {
                    blocks.add(checkPos);
                }
            }
            if (!blocks.isEmpty()) {
                ItemStack resultFromHeld = playerIn.func_184586_b(hand);
                if (!resultFromHeld.func_190926_b() && resultFromHeld.func_77973_b().equals(ItemInit.carbonicAcid)) {
                    worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187633_N, SoundCategory.MASTER, 2.0f, worldIn.field_73012_v.nextFloat() + 0.5f);
                    if (!worldIn.field_72995_K) {
                        for (BlockPos b : blocks) {
                            worldIn.func_175656_a(b, Blocks.field_150356_k.func_176223_P());
                        }
                    }
                    playerIn.func_184586_b(hand).func_190918_g(1);
                    return true;
                }
                return true;
            }
            if (!worldIn.field_72995_K) {
                playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "The forge is not clogged. There is no need to unclog it."));
                return true;
            }
            return true;
        }
        return true;
    }
}
