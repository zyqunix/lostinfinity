package xol.lostinfinity.block.activator;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.misc.BlockArchlumioGate;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
public class BlockContainmentGate extends BlockBasic {
    public BlockContainmentGate(String name) {
        super(name);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            Item heldItem = playerIn.func_184614_ca().func_77973_b();
            if (heldItem.equals(ItemInit.powerOverrideDevice)) {
                worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.CONTAINMENT_PASS, SoundCategory.MASTER, 1.0f, 1.0f);
                playerIn.func_145747_a(new TextComponentString(TextFmt.Light_Purple + "It has been a long time since someone has entered these parts where I'm held. A very, very long time..."));
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        for (int k = -1; k <= 1; k++) {
                            if (i != 0 || j != 0 || k != 0) {
                                BlockPos check = pos.func_177982_a(i, j, k);
                                Block block = worldIn.func_180495_p(check).func_177230_c();
                                if (block.equals(BlockInit.archlumioGate)) {
                                    ((BlockArchlumioGate) block).propogatePass(worldIn, check, null);
                                }
                            }
                        }
                    }
                }
                return true;
            }
            return true;
        }
        return true;
    }
}
