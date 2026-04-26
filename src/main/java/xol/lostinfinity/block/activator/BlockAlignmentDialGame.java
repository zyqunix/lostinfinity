package xol.lostinfinity.block.activator;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.tileentity.TileEntityAlignmentDialGame;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.util.DimensionActivator;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.item.activate.ItemComplexLostMap;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockAlignmentDialGame.class */
public class BlockAlignmentDialGame extends BlockBasic implements ITileEntityProvider {
    public BlockAlignmentDialGame(String name) {
        super(name);
    }

    private boolean validMap(ItemStack stack) {
        if ((stack.func_77973_b() instanceof ItemComplexLostMap) && stack.func_77942_o() && stack.func_77978_p().func_74762_e("MapProgress") == 2) {
            return true;
        }
        return false;
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack held = playerIn.func_184586_b(hand);
        if (validMap(held)) {
            if (!worldIn.field_72995_K && checkWin(pos, worldIn, playerIn)) {
                playerIn.func_184586_b(hand).func_190918_g(1);
                playerIn.func_184611_a(hand, new ItemStack(ItemInit.mazeToken, 1));
                DimensionActivator.transferEntityWithCoords(playerIn, DimensionInit.celestialVoid, 1465.0d, 65.0d, 431.0d);
                if (worldIn.func_175625_s(pos) != null) {
                    TileEntityAlignmentDialGame tileentity = (TileEntityAlignmentDialGame) worldIn.func_175625_s(pos);
                    tileentity.reset();
                    return true;
                }
                return true;
            }
            return true;
        }
        if (!worldIn.field_72995_K && worldIn.func_175625_s(pos) != null) {
            TileEntityAlignmentDialGame tileEntity = (TileEntityAlignmentDialGame) worldIn.func_175625_s(pos);
            tileEntity.reset();
            return true;
        }
        return true;
    }

    private boolean checkWin(BlockPos pos, World worldIn, EntityPlayer playerIn) {
        Vec3i dir;
        int count;
        if (worldIn.func_175625_s(pos) != null) {
            TileEntityAlignmentDialGame tileEntity = (TileEntityAlignmentDialGame) worldIn.func_175625_s(pos);
            dir = null;
            switch (tileEntity.getDir()) {
                case "North":
                    dir = new Vec3i(0, 0, -1);
                    break;
                case "East":
                    dir = new Vec3i(1, 0, 0);
                    break;
                case "South":
                    dir = new Vec3i(0, 0, 1);
                    break;
                case "West":
                    dir = new Vec3i(-1, 0, 0);
                    break;
            }
            if (dir != null && (count = tileEntity.getRingCount()) > 0) {
                for (int i = 1; i <= count; i++) {
                    BlockPos check = pos.func_177982_a(dir.func_177958_n() * count, 0, dir.func_177952_p() * count);
                    if (BlockInit.alignmentTile.func_176201_c(worldIn.func_180495_p(check)) != 1) {
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Gold + String.format("The dials must point %s", tileEntity.getDir())));
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public TileEntity func_149915_a(World worldIn, int meta) {
        return null;
    }

    public boolean func_149716_u() {
        return true;
    }

    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityAlignmentDialGame();
    }
}
