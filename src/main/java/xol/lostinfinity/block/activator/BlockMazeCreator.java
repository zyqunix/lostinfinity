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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.misc.BlockTrackTrigger;
import xol.lostinfinity.dimension.data.MazeMap;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.coordinates.CelestialCoordinates;
public class BlockMazeCreator extends Block {
    public BlockMazeCreator(String name) {
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
        ItemStack held = playerIn.func_184586_b(hand);
        if (held.func_77973_b() == ItemInit.mazeToken) {
            if (!worldIn.field_72995_K) {
                BlockPos mazeStartPos = CelestialCoordinates.mazeReferencePos();
                MazeMap maze = new MazeMap(29, 29);
                for (int c = 0; c < 29; c++) {
                    for (int r = 0; r < 29; r++) {
                        BlockPos mazeReferencePos = mazeStartPos.func_177982_a(c, 0, r);
                        worldIn.func_175656_a(mazeReferencePos, BlockInit.blockTrack.func_176223_P());
                        worldIn.func_175698_g(mazeReferencePos.func_177984_a());
                    }
                }
                for (int c2 = 0; c2 < 29; c2++) {
                    for (int r2 = 0; r2 < 29; r2++) {
                        BlockPos mazeReferencePos2 = mazeStartPos.func_177982_a(c2, 0, r2);
                        if (c2 != 0 || r2 != 0) {
                            if (c2 == 29 - 1 && r2 == 29 - 1) {
                                worldIn.func_175656_a(mazeReferencePos2, BlockInit.blockTrackWin.func_176223_P());
                            } else if (maze.getNodeAtLocation(c2, r2).getType().equals("path") && maze.getNodeAtLocation(c2, r2).isVisited()) {
                                worldIn.func_175656_a(mazeReferencePos2, BlockInit.blockTrack.func_176223_P());
                            } else if (maze.getNodeAtLocation(c2, r2).getType().equals("trigger") && maze.getNodeAtLocation(c2, r2).isVisited()) {
                                worldIn.func_175656_a(mazeReferencePos2, BlockTrackTrigger.randomTriggerBlock(worldIn.field_73012_v).func_176223_P());
                            } else {
                                worldIn.func_175656_a(mazeReferencePos2.func_177984_a(), BlockInit.trackWall.func_176223_P());
                            }
                        } else {
                            worldIn.func_175656_a(mazeReferencePos2.func_177984_a(), BlockInit.guideBlock.func_176223_P());
                        }
                    }
                }
                worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.MAZE_BUILD, SoundCategory.MASTER, 1.0f, 1.0f);
            }
            held.func_190918_g(1);
            return true;
        }
        return true;
    }
}
