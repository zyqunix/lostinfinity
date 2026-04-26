package xol.lostinfinity.block.misc;

import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.tileentity.TileEntityVoidVacuum;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.activate.ItemElasticThread;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockVoidVacuum.class */
public class BlockVoidVacuum extends BlockBasic implements ITileEntityProvider {
    public BlockVoidVacuum(String name) {
        super(name);
    }

    public TileEntity func_149915_a(World worldIn, int meta) {
        return null;
    }

    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityVoidVacuum();
    }

    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        EntityLivingBase target;
        if (!worldIn.field_72995_K) {
            ItemStack held = playerIn.func_184586_b(hand);
            if (!held.func_77942_o()) {
                held.func_77982_d(new NBTTagCompound());
            }
            if (held.func_77973_b() instanceof ItemElasticThread) {
                if (held.func_77978_p().func_186855_b("PlayerID")) {
                    UUID pl_id = held.func_77978_p().func_186857_a("PlayerID");
                    if (pl_id != null && (target = worldIn.func_73046_m().func_175576_a(pl_id)) != null) {
                        Block result_block = state.func_177230_c();
                        TileEntity tile_entity = worldIn.func_175625_s(pos);
                        if ((result_block instanceof BlockVoidVacuum) && tile_entity != null && (tile_entity instanceof TileEntityVoidVacuum)) {
                            worldIn.func_184133_a((EntityPlayer) null, target.func_180425_c(), SoundInit.GENERIC_BOING, SoundCategory.BLOCKS, 1.5f, 1.0f);
                            worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.GENERIC_BOING, SoundCategory.BLOCKS, 1.5f, 1.0f);
                            TileEntityVoidVacuum vacuum = (TileEntityVoidVacuum) tile_entity;
                            vacuum.setTarget(target);
                            vacuum.setActive(true);
                            vacuum.resetPulled();
                            playerIn.func_184611_a(hand, ItemStack.field_190927_a);
                            return true;
                        }
                        return true;
                    }
                    return true;
                }
                held.func_77978_p().func_74780_a("VacX", pos.func_177958_n());
                held.func_77978_p().func_74780_a("VacY", pos.func_177956_o());
                held.func_77978_p().func_74780_a("VacZ", pos.func_177952_p());
                playerIn.func_145747_a(new TextComponentString(TextFmt.Green + "You have stuck one end of the thread to the vacuum."));
                return true;
            }
            return true;
        }
        return true;
    }
}
