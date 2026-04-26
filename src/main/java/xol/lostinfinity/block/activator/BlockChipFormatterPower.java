package xol.lostinfinity.block.activator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.dimension.data.SlideMap;
import xol.lostinfinity.dimension.data.SlideNode;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockChipFormatterPower.class */
public class BlockChipFormatterPower extends BlockBasic {
    public BlockChipFormatterPower(String name) {
        super(name);
        func_149715_a(1.0f);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            BlockPos tablePos = GalaxyCoordinates.ChipTable();
            SlideMap puzzle = new SlideMap(5, 5, 40, 17);
            for (int z = 0; z < 5; z++) {
                for (int x = 0; x < 5; x++) {
                    BlockPos addPos = tablePos.func_177982_a(x, 0, z);
                    SlideNode node = puzzle.getNodeAtLocation(z, x);
                    if (node.isEmpty()) {
                        worldIn.func_175656_a(addPos, BlockAlienSlide.getSlideByNum(17).func_176223_P());
                    } else {
                        worldIn.func_175656_a(addPos, BlockAlienSlide.getSlideByNum(node.getTileNum()).func_176223_P());
                    }
                }
            }
            worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187556_aj, SoundCategory.MASTER, 1.0f, 1.0f);
            return true;
        }
        return true;
    }
}
