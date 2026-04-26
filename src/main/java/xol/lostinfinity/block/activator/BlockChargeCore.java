package xol.lostinfinity.block.activator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.mob.entity.misc.EntityMemPuzzleMerchant;
public class BlockChargeCore extends Block {
    private boolean hard_mode;
    public BlockChargeCore(String name, boolean hard) {
        super(Material.field_151573_f);
        this.hard_mode = false;
        func_149663_c(name);
        setRegistryName(name);
        func_149711_c(3.0f);
        func_149647_a(TabsInit.TAB_BLOCKS);
        func_149672_a(SoundType.field_185851_d);
        func_149715_a(1.0f);
        this.hard_mode = hard;
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }
    private boolean validInput(Item item) {
        if (this.hard_mode) {
            return item.equals(ItemInit.unpoweredEmberstar);
        }
        return item.equals(ItemInit.unpoweredStarcrystal);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af() && !worldIn.field_72995_K && validInput(playerIn.func_184586_b(hand).func_77973_b())) {
            Random rand = worldIn.field_73012_v;
            AxisAlignedBB checkBox = new AxisAlignedBB(pos.func_177982_a(-10, -10, -10), pos.func_177982_a(10, 10, 10));
            for (EntityMemPuzzleMerchant merch : worldIn.func_72872_a(EntityMemPuzzleMerchant.class, checkBox)) {
                ((WorldServer) worldIn).func_175739_a(EnumParticleTypes.PORTAL, merch.field_70165_t, merch.field_70163_u, merch.field_70161_v, 5, ((-0.5d) + rand.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + rand.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
                merch.func_70106_y();
            }
            worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187620_cL, SoundCategory.MASTER, 1.0f, 1.0f);
            EntityMemPuzzleMerchant newMerchant = new EntityMemPuzzleMerchant(worldIn);
            newMerchant.func_70107_b(pos.func_177958_n(), ((double) pos.func_177956_o()) + 1.5d, pos.func_177952_p());
            ((WorldServer) worldIn).func_175739_a(EnumParticleTypes.PORTAL, newMerchant.field_70165_t, newMerchant.field_70163_u, newMerchant.field_70161_v, 8, ((-0.5d) + rand.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + rand.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
            newMerchant.setLightPositions(pos, 6 + (this.hard_mode ? 3 : 0), this.hard_mode ? 1 : 0);
            newMerchant.setHardMode(this.hard_mode);
            worldIn.func_72838_d(newMerchant);
            playerIn.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Aqua) + "Merchant: Give me an unpowered " + (this.hard_mode ? "emberstar" : "starcrystal") + " to begin the challenge that can charge it."));
            return true;
        }
        return true;
    }
}
