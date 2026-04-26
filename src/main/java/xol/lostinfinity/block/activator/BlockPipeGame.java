package xol.lostinfinity.block.activator;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import xol.lostinfinity.block.basic.BlockBasicPillar;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.misc.EntityPipeGameMerchant;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockPipeGame.class */
public class BlockPipeGame extends BlockBasicPillar {
    private boolean hard_mode;
    private Vec3i dir;

    public BlockPipeGame(String name, boolean hard) {
        super(name, Material.field_151573_f);
        this.hard_mode = false;
        func_149715_a(1.0f);
    }

    private boolean validInput(ItemStack stack) {
        return stack.func_77973_b().equals(ItemInit.florocite) && stack.func_190916_E() >= 10;
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af() && validInput(playerIn.func_184586_b(hand))) {
            if (!worldIn.field_72995_K) {
                Random rand = worldIn.field_73012_v;
                AxisAlignedBB checkBox = new AxisAlignedBB(pos.func_177982_a(-10, -10, -10), pos.func_177982_a(10, 10, 10));
                for (EntityPipeGameMerchant merch : worldIn.func_72872_a(EntityPipeGameMerchant.class, checkBox)) {
                    ((WorldServer) worldIn).func_175739_a(EnumParticleTypes.PORTAL, merch.field_70165_t, merch.field_70163_u, merch.field_70161_v, 5, ((-0.5d) + rand.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + rand.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
                    merch.func_70106_y();
                }
                worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187620_cL, SoundCategory.MASTER, 1.0f, 1.0f);
                EntityPipeGameMerchant newMerchant = new EntityPipeGameMerchant(worldIn);
                BlockPos spawnPos = findSpawnPos(worldIn, pos);
                newMerchant.func_70107_b(spawnPos.func_177958_n(), ((double) spawnPos.func_177956_o()) + 1.5d, spawnPos.func_177952_p());
                ((WorldServer) worldIn).func_175739_a(EnumParticleTypes.PORTAL, newMerchant.field_70165_t, newMerchant.field_70163_u, newMerchant.field_70161_v, 8, ((-0.5d) + rand.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + rand.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
                newMerchant.setGridSize(29, 29);
                newMerchant.genPipeGame(worldIn, pos);
                worldIn.func_72838_d(newMerchant);
                newMerchant.startGame();
            }
            playerIn.func_184586_b(hand).func_190918_g(10);
            return true;
        }
        return true;
    }

    private BlockPos findSpawnPos(World world, BlockPos pos) {
        BlockPos pos2;
        BlockPos pos1 = findNearestPipe(world, pos);
        if (pos1 != null && (pos2 = findNearestPipe(world, pos1)) != null) {
            if (pos2.func_177958_n() == pos1.func_177958_n()) {
                boolean zdir = pos2.func_177952_p() > pos1.func_177952_p();
                this.dir = new Vec3i(zdir ? -2 : 2, 0, zdir ? 2 : -2);
            } else {
                boolean xdir = pos2.func_177958_n() > pos1.func_177958_n();
                this.dir = new Vec3i(xdir ? 2 : -2, 0, xdir ? 2 : -2);
            }
        }
        if (this.dir != null) {
            return pos.func_177971_a(this.dir);
        }
        return pos;
    }

    private BlockPos findNearestPipe(World world, BlockPos pos) {
        ArrayList<BlockPos> positions = new ArrayList<>();
        positions.add(pos.func_177982_a(1, 0, 0));
        positions.add(pos.func_177982_a(-1, 0, 0));
        positions.add(pos.func_177982_a(0, 0, 1));
        positions.add(pos.func_177982_a(0, 0, -1));
        positions.add(pos.func_177982_a(1, 0, 1));
        positions.add(pos.func_177982_a(1, 0, -1));
        positions.add(pos.func_177982_a(-1, 0, 1));
        positions.add(pos.func_177982_a(-1, 0, -1));
        for (BlockPos position : positions) {
            Block block = world.func_180495_p(position).func_177230_c();
            if (block instanceof BlockPipe) {
                return position;
            }
        }
        return pos;
    }
}
