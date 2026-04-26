package xol.lostinfinity.block.activator;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.item.weapon.ItemHeadCollector;
public class BlockAlienSlide extends BlockBasic {
    private int slideNum;
    public BlockAlienSlide(String name) {
        super(name);
        this.slideNum = 0;
        this.slideNum = Integer.parseInt(name.replace("slide_puzzle_alien_", ""));
        func_149715_a(1.0f);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            if (this.slideNum != 17) {
                BlockPos[] adjBlocks = {pos.func_177978_c(), pos.func_177974_f(), pos.func_177968_d(), pos.func_177976_e()};
                int length = adjBlocks.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    BlockPos side = adjBlocks[i];
                    if (worldIn.func_180495_p(side).func_177230_c() instanceof BlockAlienSlide) {
                        BlockAlienSlide nextBlock = (BlockAlienSlide) worldIn.func_180495_p(side).func_177230_c();
                        if (nextBlock.getBlockNum() == 17) {
                            worldIn.func_175656_a(side, func_176223_P());
                            worldIn.func_175656_a(pos, BlockInit.alienSlide17.func_176223_P());
                            break;
                        }
                    }
                    i++;
                }
            }
            worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187750_dc, SoundCategory.MASTER, 1.0f, 1.0f);
            return true;
        }
        return true;
    }
    public int getBlockNum() {
        return this.slideNum;
    }
    public static Block getSlideByNum(int num) {
        switch (num) {
            case 0:
                return BlockInit.alienSlide0;
            case 1:
                return BlockInit.alienSlide1;
            case 2:
                return BlockInit.alienSlide2;
            case 3:
                return BlockInit.alienSlide3;
            case TileEntityFusionTable.BOARD_ROWS :
                return BlockInit.alienSlide4;
            case 5:
                return BlockInit.alienSlide5;
            case TileEntityFusionTable.BOARD_COLUMNS :
                return BlockInit.alienSlide6;
            case 7:
                return BlockInit.alienSlide7;
            case 8:
                return BlockInit.alienSlide8;
            case 9:
                return BlockInit.alienSlide9;
            case ItemHeadCollector.CHARGE_LIMIT :
                return BlockInit.alienSlide10;
            case 11:
                return BlockInit.alienSlide11;
            case 12:
                return BlockInit.alienSlide12;
            case 13:
                return BlockInit.alienSlide13;
            case 14:
                return BlockInit.alienSlide14;
            case 15:
                return BlockInit.alienSlide15;
            case 16:
                return BlockInit.alienSlide16;
            case 17:
                return BlockInit.alienSlide17;
            case 18:
                return BlockInit.alienSlide18;
            case 19:
                return BlockInit.alienSlide19;
            case 20:
                return BlockInit.alienSlide20;
            case 21:
                return BlockInit.alienSlide21;
            case 22:
                return BlockInit.alienSlide22;
            case 23:
                return BlockInit.alienSlide23;
            case TileEntityFusionTable.BOARD_SIZE :
                return BlockInit.alienSlide24;
            default:
                return BlockInit.alienSlide17;
        }
    }
}
