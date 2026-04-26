package xol.lostinfinity.block.activator;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockRotatableTileGame.class */
public class BlockRotatableTileGame extends BlockBasic {
    public BlockRotatableTileGame(String name, boolean hard) {
        super(name);
    }

    private boolean validInput(ItemStack stack) {
        return stack.func_77973_b().equals(ItemInit.geocoordinatedOrb);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            if (validInput(playerIn.func_184586_b(hand))) {
                if (!worldIn.field_72995_K) {
                    reset(pos, worldIn, playerIn, hand, facing, hitX, hitY, hitZ);
                }
                playerIn.func_184586_b(hand).func_190918_g(1);
                return true;
            }
            if (!worldIn.field_72995_K) {
                BlockPos ref = pos.func_177982_a(0, 0, 0);
                Vec3i dir = findTileDir(worldIn, ref);
                int xDir = dir.func_177958_n();
                int zDir = dir.func_177952_p();
                BlockPos ref2 = ref.func_177971_a(dir);
                boolean flag = true;
                for (int i = 0; i < 6; i++) {
                    int j = 0;
                    while (true) {
                        if (j < 6) {
                            BlockPos tile = ref2.func_177982_a(xDir * i, 0, zDir * j);
                            if (worldIn.func_180495_p(tile).equals(BlockInit.rotatableTile.getStateWithFacing(EnumFacing.NORTH))) {
                                j++;
                            } else {
                                flag = false;
                                break;
                            }
                        }
                    }
                }
                if (flag) {
                    EntityItem item = new EntityItem(worldIn, pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p(), new ItemStack(ItemInit.georedirectionOrb));
                    item.field_70159_w = 0.0d;
                    item.field_70181_x = 0.0d;
                    item.field_70179_y = 0.0d;
                    worldIn.func_72838_d(item);
                    reset(pos, worldIn, playerIn, hand, facing, hitX, hitY, hitZ);
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }

    private void reset(BlockPos pos, World worldIn, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        BlockPos ref = pos.func_177982_a(0, 0, 0);
        Vec3i dir = findTileDir(worldIn, ref);
        int xDir = dir.func_177958_n();
        int zDir = dir.func_177952_p();
        BlockPos ref2 = ref.func_177971_a(dir);
        ArrayList<BlockPos> tiles = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                BlockPos tile = ref2.func_177982_a(xDir * i, 0, zDir * j);
                if (worldIn.func_180495_p(tile).func_177230_c().equals(BlockInit.rotatableTile)) {
                    tiles.add(tile);
                    worldIn.func_175656_a(tile, BlockInit.rotatableTile.getStateWithFacing(EnumFacing.NORTH));
                }
            }
        }
        Random rand = new Random();
        for (int k = 0; k < 10; k++) {
            int index = rand.nextInt(tiles.size());
            IBlockState tileState = worldIn.func_180495_p(tiles.get(index));
            BlockRotatableTile blockRotatableTileFunc_177230_c = tileState.func_177230_c();
            if (blockRotatableTileFunc_177230_c instanceof BlockRotatableTile) {
                blockRotatableTileFunc_177230_c.func_180639_a(worldIn, tiles.get(index), tileState, playerIn, hand, facing, hitX, hitY, hitZ);
            }
        }
    }

    private static Vec3i findTileDir(World worldIn, BlockPos pos) {
        ArrayList<Vec3i> dirs = new ArrayList<>();
        dirs.add(new Vec3i(1, 0, 1));
        dirs.add(new Vec3i(-1, 0, 1));
        dirs.add(new Vec3i(-1, 0, -1));
        dirs.add(new Vec3i(1, 0, -1));
        for (Vec3i dir : dirs) {
            if (worldIn.func_180495_p(pos.func_177971_a(dir)).func_177230_c().equals(BlockInit.rotatableTile)) {
                return dir;
            }
        }
        return null;
    }
}
