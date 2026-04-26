package xol.lostinfinity.block.activator;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
public class BlockItemGame extends Block {
    public BlockItemGame(String name) {
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
        if (!worldIn.field_72995_K) {
            for (int x = -4; x <= 4; x++) {
                for (int z = -4; z <= 4; z++) {
                    if (worldIn.func_180495_p(pos.func_177982_a(x, 0, z)).func_177230_c() instanceof BlockItemChallenge) {
                        worldIn.func_175656_a(pos.func_177982_a(x, 0, z), BlockItemChallenge.randomItemBlock(worldIn.field_73012_v).func_176223_P());
                    }
                }
            }
            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187901_ga, SoundCategory.PLAYERS, 1.0f, 1.0f);
            return true;
        }
        return true;
    }
}
