package xol.lostinfinity.block.activator;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.serverbound.PacketTextTitle;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.activate.ItemTrial;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTrialObserver;
public class BlockTrialGate extends BlockBasic {
    public BlockTrialGate(String name) {
        super(name, Material.field_151576_e);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            ItemStack heldstack = playerIn.func_184586_b(hand);
            if (heldstack.func_77973_b() instanceof ItemTrial) {
                if (!worldIn.field_72995_K) {
                    for (EntityLiving mob_die : worldIn.func_72872_a(EntityLiving.class, getArenaAABB())) {
                        mob_die.func_70106_y();
                    }
                    EntityTrialObserver trialob = new EntityTrialObserver(worldIn);
                    trialob.func_70107_b(522.0d, 75.0d, 438.0d);
                    trialob.setEventType(trialType(heldstack.func_77973_b()));
                    worldIn.func_72838_d(trialob);
                    playerIn.func_70634_a(521.5d, 62.0d, 450.0d);
                } else {
                    lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 80, 30, 30, TextFmt.Dark_Aqua + "Trial of the " + getCreatureName(heldstack.func_77973_b())), playerIn);
                }
                heldstack.func_190918_g(1);
                return true;
            }
            if (heldstack.func_77973_b().equals(ItemInit.arenaCard)) {
                playerIn.func_184185_a(SoundInit.ARENA_TELEPORT, 3.0f, 1.0f);
                heldstack.func_190918_g(1);
                if (!worldIn.field_72995_K) {
                    playerIn.func_70634_a(521.5d, 62.0d, 464.0d);
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }
    private byte trialType(Item item) {
        if (item.equals(ItemInit.trialBlaze)) {
            return (byte) 0;
        }
        if (item.equals(ItemInit.trialSkeleton)) {
            return (byte) 1;
        }
        if (item.equals(ItemInit.trialSpider)) {
            return (byte) 2;
        }
        if (item.equals(ItemInit.trialCreeper)) {
            return (byte) 3;
        }
        if (item.equals(ItemInit.trialEnderman)) {
            return (byte) 4;
        }
        if (item.equals(ItemInit.trialLlama)) {
            return (byte) 5;
        }
        if (item.equals(ItemInit.trialMagmacube)) {
            return (byte) 6;
        }
        if (item.equals(ItemInit.trialPigman)) {
            return (byte) 7;
        }
        if (item.equals(ItemInit.trialStray)) {
            return (byte) 8;
        }
        if (item.equals(ItemInit.trialVex)) {
            return (byte) 9;
        }
        if (item.equals(ItemInit.trialZombie)) {
            return (byte) 10;
        }
        if (item.equals(ItemInit.trialShulker)) {
            return (byte) 11;
        }
        return (byte) 1;
    }
    private String getCreatureName(Item item) {
        if (item.equals(ItemInit.trialBlaze)) {
            return "Blaze";
        }
        if (item.equals(ItemInit.trialSkeleton)) {
            return "Skeleton";
        }
        if (item.equals(ItemInit.trialSpider)) {
            return "Spider";
        }
        if (item.equals(ItemInit.trialCreeper)) {
            return "Creeper";
        }
        if (item.equals(ItemInit.trialEnderman)) {
            return "Enderman";
        }
        if (item.equals(ItemInit.trialLlama)) {
            return "Llama";
        }
        if (item.equals(ItemInit.trialMagmacube)) {
            return "Magma Cube";
        }
        if (item.equals(ItemInit.trialPigman)) {
            return "Pigman";
        }
        if (item.equals(ItemInit.trialStray)) {
            return "Stray";
        }
        if (item.equals(ItemInit.trialVex)) {
            return "Vex";
        }
        if (item.equals(ItemInit.trialZombie)) {
            return "Zombie";
        }
        if (item.equals(ItemInit.trialShulker)) {
            return "Shulker";
        }
        return "Nothing";
    }
    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-493.0d, 60.0d, 407.0d), new BlockPos(548.0d, 85.0d, 460.0d));
    }
}
