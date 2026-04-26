package xol.lostinfinity.item.weapon;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.serverbound.PacketTextTitle;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.data.IMaxAttack;
public class ItemLifeVessel extends Item implements IMaxAttack {
    public ItemLifeVessel(String regName) {
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }
    private void sendSubtitleToPlayer(EntityPlayer player, String string) {
        lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(true, false, 40, 20, 20, ""), player);
        lostinfinity.instance.packetHandler.sendTitleToPlayer(new PacketTextTitle(false, true, 40, 20, 20, string + "."), player);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        double zboost;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74776_a("charge", 0.0f);
        }
        boolean charged = stack.func_77978_p().func_74760_g("charge") == 100.0f;
        if (charged) {
            if (playerIn.func_70093_af()) {
                worldIn.func_184133_a((EntityPlayer) null, new BlockPos(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v), SoundInit.LIFEVESSEL_DAMAGE, SoundCategory.MASTER, 3.0f, 1.0f);
                for (EntityLivingBase detected_player : worldIn.func_72872_a(EntityLivingBase.class, playerIn.func_174813_aQ().func_72314_b(20.0d, 10.0d, 20.0d))) {
                    if (!detected_player.func_110124_au().equals(playerIn.func_110124_au())) {
                        IMaxAttack.dealMaxHealth((Entity) playerIn, detected_player, 10, 7.0f);
                        if (detected_player.field_70159_w <= 7.0d && detected_player.field_70159_w >= -7.0d && detected_player.field_70179_y <= 7.0d && detected_player.field_70179_y >= -7.0d) {
                            double xboost = Math.signum(playerIn.field_70165_t - detected_player.field_70165_t) * (-5.0d);
                            double dSignum = Math.signum(playerIn.field_70161_v - detected_player.field_70161_v);
                            double d = -5.0d;
                            while (true) {
                                zboost = dSignum * d;
                                if (xboost <= 7.0d && zboost <= 7.0d && xboost >= -7.0d && zboost >= -7.0d) {
                                    break;
                                }
                                xboost *= 0.98d;
                                dSignum = zboost;
                                d = 0.98d;
                            }
                            detected_player.func_70024_g(xboost, 1.0d, zboost);
                            detected_player.field_70133_I = true;
                        }
                    }
                }
                stack.func_77978_p().func_74776_a("charge", 0.0f);
            } else {
                worldIn.func_184133_a((EntityPlayer) null, new BlockPos(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v), SoundInit.LIFEVESSEL_HEAL, SoundCategory.MASTER, 3.0f, 1.0f);
                if (playerIn.func_110143_aJ() != playerIn.func_110138_aP()) {
                    playerIn.func_70606_j(playerIn.func_110138_aP());
                    for (int i = 0; i < 20; i++) {
                        Vec3d pos = playerIn.func_174791_d();
                        Random rand = playerIn.field_70170_p.field_73012_v;
                        if (playerIn.field_70170_p.field_72995_K) {
                            playerIn.field_70170_p.func_175688_a(EnumParticleTypes.HEART, (pos.field_72450_a + ((double) ((rand.nextFloat() * playerIn.field_70130_N) * 2.0f))) - ((double) playerIn.field_70130_N), pos.field_72448_b + 0.5d + ((double) (rand.nextFloat() * playerIn.field_70131_O)), (pos.field_72449_c + ((double) ((rand.nextFloat() * playerIn.field_70130_N) * 2.0f))) - ((double) playerIn.field_70130_N), 0.0d, 0.0d, 0.0d, new int[0]);
                        }
                    }
                    if (playerIn.field_70170_p.field_72995_K) {
                        playerIn.func_146105_b(new TextComponentString(TextFmt.Light_Purple + "Your life force has been replenished"), true);
                    }
                    stack.func_77978_p().func_74776_a("charge", 0.0f);
                } else if (playerIn.field_70170_p.field_72995_K) {
                    playerIn.func_146105_b(new TextComponentString(TextFmt.Red + "You are already at full health."), true);
                }
            }
        } else {
            sendSubtitleToPlayer(playerIn, TextFmt.Red + "You do not have enough charge to perform this action.");
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74776_a("charge", 0.0f);
        }
        if (entity instanceof EntityPlayer) {
            boolean charged = stack.func_77978_p().func_74760_g("charge") == 100.0f;
            boolean hasTarget = stack.func_77978_p().func_186855_b("target");
            if (!hasTarget && charged) {
                EntityPlayer target = (EntityPlayer) entity;
                EntityPlayer[] players = {player, target};
                for (int i = 0; i < players.length; i++) {
                    Vec3d playerVector = players[i].func_174791_d();
                    for (int f = 0; f < 360; f++) {
                        double angle = (((double) f) * 3.141592653589793d) / 180.0d;
                        double x = ((double) 1) * Math.cos(angle);
                        double z = ((double) 1) * Math.sin(angle);
                        Vec3d loc = playerVector.func_72441_c(x, 1.0d, z);
                        players[i].field_70170_p.func_175688_a(EnumParticleTypes.REDSTONE, loc.field_72450_a, loc.field_72448_b, loc.field_72449_c, 0.0d, 0.0d, 0.0d, new int[0]);
                    }
                }
                stack.func_77978_p().func_186854_a("target", target.func_110124_au());
                stack.func_77978_p().func_74778_a("targetName", target.getDisplayNameString());
                stack.func_77978_p().func_74776_a("charge", 0.0f);
                player.field_70170_p.func_184133_a((EntityPlayer) null, new BlockPos(player.field_70165_t, player.field_70163_u, player.field_70161_v), SoundInit.LIFEVESSEL_BIND, SoundCategory.MASTER, 3.0f, 1.0f);
                if (player.field_70170_p.field_72995_K) {
                    player.func_146105_b(new TextComponentString(TextFmt.Light_Purple + target.getDisplayNameString() + " has been bound to this vessel"), true);
                } else {
                    target.func_146105_b(new TextComponentString(TextFmt.getFormatting(TextFmt.Black, TextFmt.Bold, TextFmt.Obfuscated) + "&& " + TextFmt.Reset + TextFmt.getFormatting(TextFmt.Dark_Red, TextFmt.Bold) + "Your soul has been bound to " + player.getDisplayNameString() + TextFmt.getFormatting(TextFmt.Black, TextFmt.Bold, TextFmt.Obfuscated) + " &&"), true);
                }
                player.func_184185_a(SoundEvents.field_193781_bp, 1.0f, 1.0f);
                target.func_184185_a(SoundEvents.field_193781_bp, 1.0f, 1.0f);
                return true;
            }
            if (hasTarget) {
                if (player.field_70170_p.field_72995_K) {
                    sendSubtitleToPlayer(player, TextFmt.Red + "You already have a victim bound to you.");
                    return false;
                }
                return false;
            }
            if (!charged && player.field_70170_p.field_72995_K) {
                sendSubtitleToPlayer(player, TextFmt.Red + "You do not have enough charge to perform this action.");
                return false;
            }
            return false;
        }
        return false;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "While in hotbar this charges when you take max health damage.");
        tooltip.add(TextFmt.Gold + "Current Charge: 0%");
        tooltip.add(TextFmt.Green + "Right Click: Heal to full.");
        tooltip.add(TextFmt.Dark_Purple + "Shift Right Click: Deal 70% max hp to anything in range, uses all charge.");
        tooltip.add(TextFmt.Yellow + "Attack: Bind a player. They take damage on your behalf until their untimely passing.");
        tooltip.add(TextFmt.Gray + "Currently Bound To: Nobody");
        if (stack.func_77942_o() && worldIn != null) {
            for (int i = 0; i < tooltip.size(); i++) {
                String str = tooltip.get(i);
                if (str.contains("Current Charge:")) {
                    tooltip.set(i, TextFmt.Gold + "Current Charge: " + Math.round(stack.func_77978_p().func_74760_g("charge")) + "%");
                } else if (str.contains("Bound To:") && stack.func_77978_p().func_186855_b("target")) {
                    tooltip.set(i, TextFmt.Red + "Currently Bound To: " + stack.func_77978_p().func_74779_i("targetName"));
                }
            }
        }
    }
}
