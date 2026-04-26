package xol.lostinfinity.block.activator;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.mob.entity.galaxy.EntityGalaxySpire;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;
public class BlockGalaxyRewardSelector extends Block {
    public BlockGalaxyRewardSelector(String name, float hardness) {
        this(name, hardness, 1.0f);
    }
    public BlockGalaxyRewardSelector(String name, float hardness, float lilev) {
        super(Material.field_151576_e);
        func_149663_c(name);
        setRegistryName(name);
        func_149711_c(hardness);
        func_149647_a(TabsInit.TAB_BLOCKS);
        func_149672_a(SoundType.field_185851_d);
        func_149715_a(lilev);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            Block below = worldIn.func_180495_p(pos.func_177977_b()).func_177230_c();
            int game_style = -1;
            AxisAlignedBB arena_aabb = null;
            double spireHeight = 8.0d;
            if (below.equals(BlockInit.astroRockBlue)) {
                game_style = 1;
                arena_aabb = GalaxyCoordinates.getBlueAABB();
            } else if (below.equals(BlockInit.astroRockGreen)) {
                game_style = 2;
                arena_aabb = GalaxyCoordinates.getGreenAABB();
            } else if (below.equals(BlockInit.astroRockPurple)) {
                game_style = 3;
                arena_aabb = GalaxyCoordinates.getPinkAABB();
            } else if (below.equals(BlockInit.astroRockYellow)) {
                game_style = 4;
                arena_aabb = GalaxyCoordinates.getYellowAABB();
            } else if (below.equals(BlockInit.astroRockLampBlue)) {
                game_style = 5;
                arena_aabb = GalaxyCoordinates.getSwordAABB();
                spireHeight = 15.0d;
            } else if (below.equals(BlockInit.astroRockLampGreen)) {
                game_style = 6;
                arena_aabb = GalaxyCoordinates.getBombAABB();
                spireHeight = 15.0d;
            } else if (below.equals(BlockInit.astroRockLampPurple)) {
                game_style = 7;
                arena_aabb = GalaxyCoordinates.getKnifeAABB();
                spireHeight = 15.0d;
            }
            if (game_style > 0) {
                BlockPos spirePos = new BlockPos((arena_aabb.field_72336_d + arena_aabb.field_72340_a) / 2.0d, arena_aabb.field_72338_b + spireHeight, (arena_aabb.field_72334_f + arena_aabb.field_72339_c) / 2.0d);
                BlockPos gameStart = new BlockPos(arena_aabb.field_72340_a + 2.0d, arena_aabb.field_72338_b + 6.0d, arena_aabb.field_72339_c + 15.0d);
                int playCount = 0;
                for (EntityPlayer entityPlayer : worldIn.func_72872_a(EntityPlayer.class, arena_aabb)) {
                    playCount++;
                }
                if (playCount == 0) {
                    for (EntityMob leftover : worldIn.func_72872_a(EntityMob.class, arena_aabb)) {
                        leftover.func_70106_y();
                    }
                    replaceFloor(worldIn, arena_aabb, Blocks.field_150350_a, getMyBarrier(game_style), game_style >= 5 ? 64 : 32);
                    playerIn.func_70634_a(gameStart.func_177958_n(), gameStart.func_177956_o(), gameStart.func_177952_p());
                    EntityGalaxySpire spire = new EntityGalaxySpire(worldIn);
                    spire.func_70107_b(spirePos.func_177958_n(), spirePos.func_177956_o(), spirePos.func_177952_p());
                    spire.setItemDrop(lightToItem());
                    spire.setGameStyle(game_style);
                    if (game_style >= 5) {
                        spire.setElite();
                    }
                    worldIn.func_72838_d(spire);
                    return true;
                }
                playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Red + "The arena is currently in use."));
                return true;
            }
            return true;
        }
        return true;
    }
    private void replaceFloor(World world, AxisAlignedBB box, Block repblock, Block newblock, int size) {
        for (int blx = 0; blx < size; blx++) {
            for (int blz = 0; blz < size; blz++) {
                BlockPos pos = new BlockPos(box.field_72340_a + ((double) blx), box.field_72338_b, box.field_72339_c + ((double) blz));
                if (world.func_180495_p(pos).func_177230_c().equals(repblock)) {
                    world.func_175656_a(pos, newblock.func_176223_P());
                }
            }
        }
    }
    private int lightToItem() {
        switch (this.field_149784_t) {
            case TileEntityFusionTable.BOARD_COLUMNS :
                return 3;
            case 9:
                return 2;
            case 12:
                return 1;
            default:
                return 0;
        }
    }
    private Block getMyBarrier(int style) {
        Block bl = BlockInit.astroBarrierBlue;
        switch (style) {
            case 2:
                bl = BlockInit.astroBarrierGreen;
                break;
            case 3:
                bl = BlockInit.astroBarrierPurple;
                break;
            case TileEntityFusionTable.BOARD_ROWS :
                bl = BlockInit.astroBarrierYellow;
                break;
            case TileEntityFusionTable.BOARD_COLUMNS :
                bl = BlockInit.astroBarrierGreen;
                break;
            case 7:
                bl = BlockInit.astroBarrierPurple;
                break;
        }
        return bl;
    }
}
