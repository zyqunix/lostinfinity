package xol.lostinfinity.block.activator;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.labyrinth.EntityAspect;
public class BlockLabGameType1 extends BlockBasic {
    public BlockLabGameType1(String name) {
        super(name, Material.field_151576_e);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Item mapItem;
        if (!playerIn.func_70093_af() && !playerIn.func_184586_b(hand).func_190926_b() && (mapItem = canStartGame(playerIn.func_184586_b(hand).func_77973_b())) != null) {
            if (!worldIn.field_72995_K) {
                EntityAspect aspect = new EntityAspect(worldIn);
                aspect.func_70107_b(pos.func_177958_n(), ((double) pos.func_177956_o()) + 9.0d, pos.func_177952_p());
                aspect.setMapDrop(mapItem);
                worldIn.func_72838_d(aspect);
            } else {
                playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Aqua + "The Echo: So you wish to play a game " + playerIn.func_70005_c_() + "?"));
                playerIn.func_145747_a(new TextComponentString(TextFmt.Gray + "You have 2 minutes to kill the aspect above you."));
            }
            worldIn.func_184134_a(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v, SoundInit.LAB_GAME_BEGIN, SoundCategory.MASTER, 2.0f, 1.0f, false);
            playerIn.func_184586_b(hand).func_190918_g(1);
            return true;
        }
        return true;
    }
    private Item canStartGame(Item held) {
        if (held.equals(ItemInit.deviantString)) {
            return ItemInit.ingenuityMap;
        }
        if (held.equals(ItemInit.perfectPearl)) {
            return ItemInit.dualityMap;
        }
        if (held.equals(ItemInit.deviantGunpowder)) {
            return ItemInit.corruptionMap;
        }
        if (held.equals(ItemInit.deviantBearHide)) {
            return ItemInit.aspirationMap;
        }
        if (held.equals(ItemInit.reflectiveShard)) {
            return ItemInit.misdirectionMap;
        }
        if (held.equals(ItemInit.deviantGhastTear)) {
            return ItemInit.vengeanceMap;
        }
        return null;
    }
}
