package xol.lostinfinity.block.activator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.data.LightTileMap;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
public class BlockLightTileGame extends BlockBasic {
    public BlockLightTileGame(String name) {
        super(name);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        boolean isCrafter = false;
        if (worldIn.func_180495_p(pos).func_177230_c() == BlockInit.highPoweredCharger) {
            isCrafter = true;
        }
        if (!playerIn.func_70093_af()) {
            int checkoffset = isCrafter ? 2 : 1;
            boolean downX = true;
            boolean downZ = true;
            int i = 0;
            while (true) {
                if (i >= 4) {
                    break;
                }
                int checkX = (-checkoffset) + ((i % 2) * checkoffset * 2);
                int checkZ = (-checkoffset) + (MathHelper.func_76141_d(i / 2) * checkoffset * 2);
                BlockPos checkPos = new BlockPos(pos.func_177982_a(checkX, 0, checkZ));
                if (!(worldIn.func_180495_p(checkPos).func_177230_c() instanceof BlockLightTile)) {
                    i++;
                } else {
                    if (checkX > 0) {
                        downX = false;
                    }
                    if (checkZ > 0) {
                        downZ = false;
                    }
                }
            }
            BlockPos gridStart = pos.func_177982_a(downX ? (-8) - checkoffset : checkoffset, 0, downZ ? (-8) - checkoffset : checkoffset);
            if (!isCrafter) {
                if (!worldIn.field_72995_K) {
                    LightTileMap lightTileMap = new LightTileMap(9, 9, 10);
                    for (int c = 0; c < 9; c++) {
                        for (int r = 0; r < 9; r++) {
                            if (lightTileMap.getNodeAtLocation(c, r).isLit()) {
                                worldIn.func_175656_a(gridStart.func_177982_a(c, 0, r), BlockInit.lightTileLit.func_176223_P());
                            } else {
                                worldIn.func_175656_a(gridStart.func_177982_a(c, 0, r), BlockInit.lightTileDark.func_176223_P());
                            }
                        }
                    }
                    return true;
                }
                return true;
            }
            if (playerIn.func_184586_b(hand).func_77973_b().equals(ItemInit.superchargedCell)) {
                boolean allLit = true;
                int c2 = 0;
                loop3: while (true) {
                    if (c2 >= 9) {
                        break;
                    }
                    for (int r2 = 0; r2 < 9; r2++) {
                        if (worldIn.func_180495_p(gridStart.func_177982_a(c2, 0, r2)).func_177230_c() == BlockInit.lightTileDark) {
                            allLit = false;
                            break loop3;
                        }
                    }
                    c2++;
                }
                if (allLit) {
                    if (!worldIn.field_72995_K) {
                        EntityItem cellItem = new EntityItem(worldIn, pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p(), new ItemStack(ItemInit.overchargedCell));
                        cellItem.field_70159_w = 0.0d;
                        cellItem.field_70181_x = 0.0d;
                        cellItem.field_70179_y = 0.0d;
                        worldIn.func_72838_d(cellItem);
                        worldIn.func_184133_a((EntityPlayer) null, pos.func_177984_a(), SoundInit.SPECIAL_CRAFT, SoundCategory.BLOCKS, 2.0f, 1.0f);
                        for (int c3 = 0; c3 < 9; c3++) {
                            for (int r3 = 0; r3 < 9; r3++) {
                                worldIn.func_175656_a(gridStart.func_177982_a(c3, 0, r3), BlockInit.lightTileDark.func_176223_P());
                            }
                        }
                    }
                    playerIn.func_184586_b(hand).func_190918_g(1);
                    return true;
                }
                if (!worldIn.field_72995_K) {
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "Light tiles not all lit or missing."));
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }
}
