package xol.lostinfinity.block.activator;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.misc.EntityCellGameMerchant;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockCellGame.class */
public class BlockCellGame extends BlockBasic {
    public BlockCellGame(String name) {
        super(name);
        func_149715_a(1.0f);
    }

    private boolean validInput1(ItemStack stack) {
        return stack.func_77973_b().equals(ItemInit.clovinitePowerBank) && stack.func_190916_E() >= 4;
    }

    private boolean validInput2(ItemStack stack) {
        return stack.func_77973_b().equals(ItemInit.reinforcedBlade);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            if (validInput1(playerIn.func_184586_b(hand))) {
                if (!worldIn.field_72995_K) {
                    Random rand = worldIn.field_73012_v;
                    AxisAlignedBB checkBox = new AxisAlignedBB(pos.func_177982_a(-10, -10, -10), pos.func_177982_a(10, 10, 10));
                    for (EntityCellGameMerchant merch : worldIn.func_72872_a(EntityCellGameMerchant.class, checkBox)) {
                        ((WorldServer) worldIn).func_175739_a(EnumParticleTypes.PORTAL, merch.field_70165_t, merch.field_70163_u, merch.field_70161_v, 5, ((-0.5d) + rand.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + rand.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
                        merch.func_70106_y();
                    }
                    worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187620_cL, SoundCategory.MASTER, 1.0f, 1.0f);
                    EntityCellGameMerchant newMerchant = new EntityCellGameMerchant(worldIn);
                    newMerchant.func_70107_b(pos.func_177958_n(), ((double) pos.func_177956_o()) + 1.5d, pos.func_177952_p());
                    newMerchant.func_70606_j(420.0f);
                    ((WorldServer) worldIn).func_175739_a(EnumParticleTypes.PORTAL, newMerchant.field_70165_t, newMerchant.field_70163_u, newMerchant.field_70161_v, 8, ((-0.5d) + rand.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + rand.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
                    newMerchant.setCellPositions(pos, 10, worldIn, playerIn);
                    worldIn.func_72838_d(newMerchant);
                }
                playerIn.func_184586_b(hand).func_190918_g(4);
            }
            if (validInput2(playerIn.func_184586_b(hand))) {
                if (!worldIn.field_72995_K) {
                    Random rand2 = worldIn.field_73012_v;
                    AxisAlignedBB checkBox2 = new AxisAlignedBB(pos.func_177982_a(-10, -10, -10), pos.func_177982_a(10, 10, 10));
                    for (EntityCellGameMerchant merch2 : worldIn.func_72872_a(EntityCellGameMerchant.class, checkBox2)) {
                        ((WorldServer) worldIn).func_175739_a(EnumParticleTypes.PORTAL, merch2.field_70165_t, merch2.field_70163_u, merch2.field_70161_v, 5, ((-0.5d) + rand2.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + rand2.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
                        merch2.func_70106_y();
                    }
                    worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187620_cL, SoundCategory.MASTER, 1.0f, 1.0f);
                    EntityCellGameMerchant newMerchant2 = new EntityCellGameMerchant(worldIn);
                    newMerchant2.func_70107_b(pos.func_177958_n(), ((double) pos.func_177956_o()) + 1.5d, pos.func_177952_p());
                    newMerchant2.func_70606_j(69.0f);
                    ((WorldServer) worldIn).func_175739_a(EnumParticleTypes.PORTAL, newMerchant2.field_70165_t, newMerchant2.field_70163_u, newMerchant2.field_70161_v, 8, ((-0.5d) + rand2.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + rand2.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
                    newMerchant2.setCellPositions(pos, 10, worldIn, playerIn);
                    worldIn.func_72838_d(newMerchant2);
                }
                playerIn.func_184586_b(hand).func_190918_g(1);
                return true;
            }
            return true;
        }
        return true;
    }
}
