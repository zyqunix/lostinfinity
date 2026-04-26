package xol.lostinfinity.block.activator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockChipFormatter.class */
public class BlockChipFormatter extends BlockBasic {
    public BlockChipFormatter(String name) {
        super(name);
        func_149715_a(1.0f);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack held = playerIn.func_184586_b(hand);
        if (held.func_77973_b() == ItemInit.freshDataChip) {
            BlockPos tablePos = GalaxyCoordinates.ChipTable();
            int pieceId = 0;
            boolean allCorrect = true;
            for (int z = 0; z < 5; z++) {
                for (int x = 0; x < 5; x++) {
                    BlockPos addPos = tablePos.func_177982_a(x, 0, z);
                    if (worldIn.func_180495_p(addPos).func_177230_c() instanceof BlockAlienSlide) {
                        BlockAlienSlide slide = (BlockAlienSlide) worldIn.func_180495_p(addPos).func_177230_c();
                        if (slide.getBlockNum() != pieceId) {
                            allCorrect = false;
                        }
                    } else {
                        allCorrect = false;
                    }
                    pieceId++;
                }
            }
            if (allCorrect) {
                held.func_190918_g(1);
                if (!worldIn.field_72995_K) {
                    EntityItem cellItem = new EntityItem(worldIn, pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p(), new ItemStack(ItemInit.basicStorageChip));
                    cellItem.field_70159_w = 0.0d;
                    cellItem.field_70181_x = 0.0d;
                    cellItem.field_70179_y = 0.0d;
                    worldIn.func_72838_d(cellItem);
                    worldIn.func_184133_a((EntityPlayer) null, pos.func_177984_a(), SoundInit.CHIP_FORMAT, SoundCategory.BLOCKS, 2.0f, 1.0f);
                    for (int z2 = 0; z2 < 5; z2++) {
                        for (int x2 = 0; x2 < 5; x2++) {
                            worldIn.func_175656_a(tablePos.func_177982_a(x2, 0, z2), BlockAlienSlide.getSlideByNum(17).func_176223_P());
                        }
                    }
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }
}
