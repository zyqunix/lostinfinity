package xol.lostinfinity.block.activator;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.mob.entity.starforge.EntityEssenceIdol;
public class BlockEssenceIdol extends Block {
    public BlockEssenceIdol(String name) {
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
        if (!playerIn.func_70093_af() && playerIn.func_184586_b(hand).func_77973_b().equals(ItemInit.illuminationStone)) {
            if (!worldIn.field_72995_K) {
                BlockPos reference = pos.func_177982_a(0, 2, 0);
                AxisAlignedBB aabb = new AxisAlignedBB(reference.func_177982_a(-32, -5, -32), reference.func_177982_a(32, 15, 32));
                for (EntityEssenceIdol existing_idols : worldIn.func_72872_a(EntityEssenceIdol.class, aabb)) {
                    existing_idols.func_70106_y();
                }
                EntityEssenceIdol idol = new EntityEssenceIdol(worldIn);
                idol.func_70107_b(pos.func_177958_n(), pos.func_177956_o() + 1, pos.func_177952_p());
                idol.setGameRef(reference, 0);
                idol.darkenAll();
                worldIn.func_72838_d(idol);
            }
            playerIn.func_184586_b(hand).func_190918_g(1);
            worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187620_cL, SoundCategory.MASTER, 1.0f, 1.0f);
            return true;
        }
        return true;
    }
}
