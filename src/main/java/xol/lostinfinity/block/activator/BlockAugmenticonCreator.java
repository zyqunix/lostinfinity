package xol.lostinfinity.block.activator;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.misc.ItemAugmentSlide;
import xol.lostinfinity.item.misc.ItemAugmenticonBox;
import xol.lostinfinity.mob.entity.starforge.EntityAugmenticon;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockAugmenticonCreator.class */
public class BlockAugmenticonCreator extends Block {
    public BlockAugmenticonCreator(String name) {
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
        if (!playerIn.func_70093_af()) {
            ItemStack stack = playerIn.func_184586_b(hand);
            if (stack.func_190926_b()) {
                if (!worldIn.field_72995_K) {
                    playerIn.func_70634_a(1553.0d, 11.0d, -584.0d);
                    worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.ARENA_TELEPORT, SoundCategory.MASTER, 1.0f, 1.0f);
                    return true;
                }
                return true;
            }
            if (stack.func_77973_b() instanceof ItemAugmenticonBox) {
                if (!ItemAugmenticonBox.getAugmentList(stack).func_82582_d()) {
                    if (!worldIn.field_72995_K) {
                        NBTTagList<NBTTagInt> list = ItemAugmenticonBox.getAugmentList(stack);
                        playerIn.func_70634_a(1554.0d, 12.0d, -556.0d);
                        EntityAugmenticon augmenticon = new EntityAugmenticon(worldIn);
                        augmenticon.func_70107_b(1576.0d, 13.0d, -551.0d);
                        for (NBTTagInt strTag : list) {
                            augmenticon.addAbility(ItemAugmentSlide.SlideType.values()[strTag.func_150287_d()].registryName);
                        }
                        worldIn.func_72838_d(augmenticon);
                    }
                    playerIn.func_184611_a(hand, ItemStack.field_190927_a);
                    worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187620_cL, SoundCategory.MASTER, 1.0f, 1.0f);
                    return true;
                }
                if (!worldIn.field_72995_K) {
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "The augmenticon must have at least one programmed ability."));
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }
}
