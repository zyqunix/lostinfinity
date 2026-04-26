package xol.lostinfinity.block.activator;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
public class BlockStarforgeInput extends BlockBasic {
    public BlockStarforgeInput(String name) {
        super(name, Material.field_151576_e);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            int freeX = 0;
            boolean freespot = false;
            for (int xpos = 13; xpos <= 15; xpos++) {
                BlockPos checkPos = new BlockPos(pos.func_177982_a(xpos, 9, -5));
                IBlockState checkState = worldIn.func_180495_p(checkPos);
                if (checkState.func_177230_c().equals(Blocks.field_150356_k) || checkState.func_177230_c().equals(Blocks.field_150353_l)) {
                    freespot = true;
                    freeX = xpos;
                }
            }
            if (freespot) {
                Item resultFromHeld = returnItem(playerIn.func_184586_b(hand).func_77973_b());
                if (resultFromHeld != null) {
                    worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187652_bv, SoundCategory.MASTER, 2.0f, worldIn.field_73012_v.nextFloat() + 0.5f);
                    if (!worldIn.field_72995_K) {
                        BlockPos dropPos = new BlockPos(pos.func_177963_a(6.5d, 4.0d, -6.0d));
                        EntityItem dropIngot = new EntityItem(worldIn, dropPos.func_177958_n(), dropPos.func_177956_o(), dropPos.func_177952_p(), new ItemStack(resultFromHeld));
                        dropIngot.field_70159_w = 0.0d;
                        dropIngot.field_70181_x = 0.0d;
                        dropIngot.field_70179_y = 0.0d;
                        worldIn.func_72838_d(dropIngot);
                        if (worldIn.field_73012_v.nextInt(8) == 0) {
                            worldIn.func_175656_a(pos.func_177982_a(freeX, 9, -5), BlockInit.slagDeposit.func_176223_P());
                        }
                    }
                    playerIn.func_184586_b(hand).func_190918_g(1);
                    return true;
                }
                return true;
            }
            if (!worldIn.field_72995_K) {
                playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "The forge is clogged. Clear it out with carbonic acid."));
                return true;
            }
            return true;
        }
        return true;
    }
    private Item returnItem(Item held) {
        if (held.equals(ItemInit.emberium)) {
            return ItemInit.emberiumIngot;
        }
        if (held.equals(ItemInit.noxerium)) {
            return ItemInit.noxeriumIngot;
        }
        if (held.equals(ItemInit.incadium)) {
            return ItemInit.incadiumIngot;
        }
        if (held.equals(ItemInit.hextorium)) {
            return ItemInit.hextoriumIngot;
        }
        if (held.equals(ItemInit.kylaxium)) {
            return ItemInit.kylaxiumIngot;
        }
        if (held.equals(ItemInit.vellorium)) {
            return ItemInit.velloriumIngot;
        }
        if (held.equals(ItemInit.xerovium)) {
            return ItemInit.xeroviumIngot;
        }
        if (held.equals(ItemInit.phytrosium)) {
            return ItemInit.phytrosiumIngot;
        }
        if (held.equals(ItemInit.detherium)) {
            return ItemInit.detheriumIngot;
        }
        if (held.equals(ItemInit.olysium)) {
            return ItemInit.olysiumIngot;
        }
        if (held.equals(ItemInit.astrallium)) {
            return ItemInit.astralliumIngot;
        }
        if (held.equals(ItemInit.crystonium)) {
            return ItemInit.crystoniumIngot;
        }
        return null;
    }
}
