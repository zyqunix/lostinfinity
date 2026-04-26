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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockGuideBlock.class */
public class BlockGuideBlock extends Block {
    public BlockGuideBlock(String name) {
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
        if (!worldIn.field_72995_K && worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() == BlockInit.blockTrackWin) {
            worldIn.func_175698_g(pos);
            ItemStack stack = playerIn.func_184586_b(hand);
            if (stack.func_77973_b() == ItemInit.webbedIdol) {
                playerIn.func_184611_a(hand, new ItemStack(ItemInit.tokenVycellia));
                playerIn.func_70634_a(25.5d, 31.5d, 31.5d);
                return true;
            }
            if (stack.func_77973_b() == ItemInit.pendantOfTheStorm) {
                playerIn.func_184611_a(hand, new ItemStack(ItemInit.tokenOzor));
                playerIn.func_70634_a(25.5d, 31.5d, 31.5d);
                return true;
            }
            if (stack.func_77973_b() == ItemInit.conductiveBar) {
                playerIn.func_184611_a(hand, new ItemStack(ItemInit.tokenThundyron));
                playerIn.func_70634_a(25.5d, 31.5d, 31.5d);
                return true;
            }
            if (stack.func_77973_b() == ItemInit.jarOfBlood) {
                playerIn.func_184611_a(hand, new ItemStack(ItemInit.tokenBarul));
                playerIn.func_70634_a(25.5d, 31.5d, 31.5d);
                return true;
            }
            if (stack.func_77973_b() == ItemInit.frostedLog) {
                playerIn.func_184611_a(hand, new ItemStack(ItemInit.tokenCryonus));
                playerIn.func_70634_a(25.5d, 31.5d, 31.5d);
                return true;
            }
            return true;
        }
        return true;
    }
}
