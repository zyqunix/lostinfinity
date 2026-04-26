package xol.lostinfinity.block.activator;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.mob.entity.starforge.EntityGalacticTerror;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;
public class BlockGalaxyChallenge extends Block {
    public BlockGalaxyChallenge(String name) {
        super(Material.field_151573_f);
        func_149663_c(name);
        setRegistryName(name);
        func_149711_c(3.0f);
        func_149647_a(TabsInit.TAB_BLOCKS);
        func_149672_a(SoundType.field_185851_d);
        func_149715_a(1.0f);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            ItemStack stack = playerIn.func_184586_b(hand);
            if (stack.func_77973_b() == ItemInit.galacticChallengeEmblem) {
                if (!worldIn.field_72995_K) {
                    AxisAlignedBB teleport = GalaxyCoordinates.getShockArenaAABB();
                    playerIn.func_70634_a(teleport.field_72340_a + 6.0d, teleport.field_72338_b + 2.0d, teleport.field_72339_c + 6.0d);
                    EntityGalacticTerror terror = new EntityGalacticTerror(worldIn);
                    terror.func_70107_b(teleport.field_72336_d - 6.0d, teleport.field_72338_b + 3.0d, teleport.field_72334_f - 6.0d);
                    worldIn.func_72838_d(terror);
                }
                stack.func_190918_g(1);
                return true;
            }
            return true;
        }
        return true;
    }
}
