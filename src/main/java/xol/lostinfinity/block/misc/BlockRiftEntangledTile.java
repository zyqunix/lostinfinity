package xol.lostinfinity.block.misc;
import java.util.ArrayList;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.basic.ISpecialMurkMeta;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.weapon.ItemHeadCollector;
public class BlockRiftEntangledTile extends BlockBasic implements ISpecialMurkMeta {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 15);
    public BlockRiftEntangledTile(String name) {
        super(name);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        int meta;
        if (!worldIn.field_72995_K && playerIn.func_184586_b(hand).func_77973_b().equals(ItemInit.powerAnalyzer)) {
            if (worldIn.field_73011_w.func_186058_p() != DimensionInit.infiniteMurk) {
                if (worldIn.field_73011_w.func_186058_p() == DimensionType.OVERWORLD) {
                    int meta2 = func_176201_c(state);
                    if (meta2 < 15) {
                        meta = meta2 + 1;
                    } else {
                        meta = 0;
                    }
                    playerIn.func_145747_a(new TextComponentString(String.format("This block is showing: Tile %d", Integer.valueOf(meta))));
                    worldIn.func_175656_a(pos, func_176203_a(meta));
                    worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187750_dc, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    return true;
                }
                return true;
            }
            BlockPos zeroPos = null;
            int i = -5;
            loop0: while (true) {
                if (i > 5) {
                    break;
                }
                for (int j = -5; j <= 5; j++) {
                    BlockPos check = pos.func_177982_a(i, 0, j);
                    IBlockState checkState = worldIn.func_180495_p(check);
                    if ((checkState.func_177230_c() instanceof BlockRiftEntangledTile) && func_176201_c(checkState) == 0) {
                        zeroPos = check;
                        break loop0;
                    }
                }
                i++;
            }
            if (zeroPos != null) {
                ArrayList<Vec3i> positions = new ArrayList<>();
                positions.add(new Vec3i(0, 0, 1));
                positions.add(new Vec3i(0, 0, -1));
                positions.add(new Vec3i(1, 0, 0));
                positions.add(new Vec3i(-1, 0, 0));
                Vec3i dirRight = null;
                Vec3i dirDown = null;
                for (Vec3i position : positions) {
                    IBlockState checkState2 = worldIn.func_180495_p(zeroPos.func_177971_a(position));
                    if (checkState2.func_177230_c() instanceof BlockRiftEntangledTile) {
                        if (func_176201_c(checkState2) == 1 && dirRight == null) {
                            dirRight = position;
                        }
                        if (func_176201_c(checkState2) == 4 && dirDown == null) {
                            dirDown = position;
                        }
                    }
                }
                boolean complete = true;
                int meta3 = 0;
                ArrayList<BlockPos> tiles = new ArrayList<>();
                int i2 = 0;
                loop3: while (true) {
                    if (i2 >= 4) {
                        break;
                    }
                    for (int j2 = 0; j2 < 4; j2++) {
                        BlockPos check2 = zeroPos.func_177982_a((dirRight.func_177958_n() * j2) + (dirDown.func_177958_n() * i2), 0, (dirRight.func_177952_p() * j2) + (dirDown.func_177952_p() * i2));
                        IBlockState checkState3 = worldIn.func_180495_p(check2);
                        if (checkState3.func_177230_c() instanceof BlockRiftEntangledTile) {
                            if (func_176201_c(checkState3) != meta3) {
                                complete = false;
                                break loop3;
                            }
                            tiles.add(check2);
                            meta3++;
                        } else {
                            complete = false;
                            break loop3;
                        }
                    }
                    i2++;
                }
                if (complete) {
                    worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.MACHINE_CRAFT, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    EntityItem item = new EntityItem(worldIn, pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p(), new ItemStack(ItemInit.alignedDisc));
                    item.field_70159_w = 0.0d;
                    item.field_70181_x = 0.0d;
                    item.field_70179_y = 0.0d;
                    worldIn.func_72838_d(item);
                    for (BlockPos tilePos : tiles) {
                        worldIn.func_175698_g(tilePos);
                        worldIn.func_73046_m().func_71218_a(0).func_175698_g(tilePos);
                    }
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }
    public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return func_176223_P().func_177226_a(AMOUNT, 0);
    }
    public IBlockState func_176203_a(int meta) {
        return func_176223_P().func_177226_a(AMOUNT, Integer.valueOf(meta));
    }
    public int func_176201_c(IBlockState state) {
        return ((Integer) state.func_177229_b(AMOUNT)).intValue();
    }
    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer(this, new IProperty[]{AMOUNT});
    }
    public IBlockState getStateWithAmount(int amount) {
        return func_176223_P().func_177226_a(AMOUNT, Integer.valueOf(amount));
    }
    @Override // xol.lostinfinity.block.basic.ISpecialMurkMeta
    public int getMurkMeta(IBlockState state) {
        int meta = func_176201_c(state);
        switch (meta) {
            case 0:
                return 13;
            case 1:
                return 6;
            case 2:
                return 11;
            case 3:
                return 15;
            case TileEntityFusionTable.BOARD_ROWS :
                return 5;
            case 5:
                return 0;
            case TileEntityFusionTable.BOARD_COLUMNS :
                return 3;
            case 7:
                return 12;
            case 8:
                return 1;
            case 9:
                return 7;
            case ItemHeadCollector.CHARGE_LIMIT :
                return 14;
            case 11:
                return 2;
            case 12:
                return 8;
            case 13:
                return 4;
            case 14:
                return 9;
            case 15:
                return 10;
            default:
                return 0;
        }
    }
}
