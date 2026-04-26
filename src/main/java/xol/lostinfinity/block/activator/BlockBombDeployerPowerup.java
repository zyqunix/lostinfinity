package xol.lostinfinity.block.activator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicLight;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.activate.ItemBombDeployer;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockBombDeployerPowerup.class */
public class BlockBombDeployerPowerup extends BlockBasicLight {
    private int power_type;

    public BlockBombDeployerPowerup(String name, int type) {
        super(name);
        this.power_type = 0;
        func_149715_a(1.0f);
        this.power_type = type;
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack held = playerIn.func_184586_b(hand);
        if (held.func_77973_b() instanceof ItemBombDeployer) {
            if (!held.func_77942_o()) {
                held.func_77982_d(new NBTTagCompound());
            }
            switch (this.power_type) {
                case 0:
                    int speedVal = held.func_77978_p().func_74762_e("BombSpeed") + 1;
                    if (speedVal > 5) {
                        speedVal = 5;
                        if (!worldIn.field_72995_K) {
                            playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "Bomb Deployer at max speed."));
                        }
                    }
                    held.func_77978_p().func_74768_a("BombSpeed", speedVal);
                    break;
                case 1:
                    int sizeVal = held.func_77978_p().func_74762_e("BombSize") + 1;
                    if (sizeVal > 9) {
                        sizeVal = 9;
                        if (!worldIn.field_72995_K) {
                            playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "Bomb Deployer at max size."));
                        }
                    }
                    held.func_77978_p().func_74768_a("BombSize", sizeVal);
                    break;
                case 2:
                    int cdVal = held.func_77978_p().func_74762_e("BombCooldown") + 1;
                    if (cdVal > 5) {
                        cdVal = 5;
                        if (!worldIn.field_72995_K) {
                            playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "Bomb Deployer at max cooldown."));
                        }
                    }
                    held.func_77978_p().func_74768_a("BombCooldown", cdVal);
                    break;
                case 3:
                    if (!worldIn.field_72995_K) {
                        playerIn.func_70690_d(new PotionEffect(PotionInit.PROTECTED, 400));
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "You temporarily are immune to bombs."));
                    }
                    break;
            }
            if (!worldIn.field_72995_K) {
                worldIn.func_175656_a(pos, BlockInit.bombersPowerupClosed.func_176223_P());
                worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.MINIGAME_POWERUP, SoundCategory.PLAYERS, 1.0f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
                return true;
            }
            return true;
        }
        return true;
    }
}
