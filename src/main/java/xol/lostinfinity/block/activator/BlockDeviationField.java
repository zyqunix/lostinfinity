package xol.lostinfinity.block.activator;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockDeviationField.class */
public class BlockDeviationField extends BlockBasic {
    public BlockDeviationField(String name) {
        super(name, Material.field_151576_e);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            ItemStack heldstack = playerIn.func_184586_b(hand);
            if (heldstack.func_77973_b() == Items.field_151156_bN) {
                if (!worldIn.field_72995_K) {
                    for (EntityLiving mob_die : worldIn.func_72872_a(EntityLiving.class, getArenaAABB())) {
                        mob_die.func_70106_y();
                    }
                    EntityWither wither = new EntityWither(worldIn);
                    wither.func_70107_b(985.5d, 63.0d, 890.0d);
                    worldIn.func_72838_d(wither);
                    playerIn.func_70634_a(985.5d, 62.0d, 906.0d);
                    playerIn.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Aqua) + "Looming Voice: A wither has been released. Deviate it, and show me what you are made of " + playerIn.func_70005_c_()));
                }
                heldstack.func_190918_g(1);
                return true;
            }
            if (heldstack.func_77973_b().equals(ItemInit.arenaCard)) {
                playerIn.func_184185_a(SoundInit.ARENA_TELEPORT, 3.0f, 1.0f);
                heldstack.func_190918_g(1);
                if (!worldIn.field_72995_K) {
                    playerIn.func_70634_a(985.5d, 62.0d, 927.0d);
                    return true;
                }
                return true;
            }
            if (heldstack.func_77973_b().equals(Items.field_190929_cY)) {
                if (!worldIn.field_72995_K) {
                    for (EntityLiving mob_die2 : worldIn.func_72872_a(EntityLiving.class, getArenaAABB())) {
                        mob_die2.func_70106_y();
                    }
                    EntityEvoker evoker = new EntityEvoker(worldIn);
                    evoker.func_70107_b(985.5d, 63.0d, 890.0d);
                    worldIn.func_72838_d(evoker);
                    playerIn.func_70634_a(985.5d, 62.0d, 906.0d);
                    playerIn.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Aqua) + "Looming Voice: An evoker has been released. Deviate it, and show me what you are made of " + playerIn.func_70005_c_()));
                }
                heldstack.func_190918_g(1);
                return true;
            }
            return true;
        }
        return true;
    }

    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(958.0d, 60.0d, 877.0d), new BlockPos(1012.0d, 82.0d, 924.0d));
    }
}
