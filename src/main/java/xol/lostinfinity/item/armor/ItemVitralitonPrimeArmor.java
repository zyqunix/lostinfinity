package xol.lostinfinity.item.armor;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.common.packets.LostInfinityPacketHandler;
import xol.lostinfinity.common.packets.serverbound.PacketNBTSyncLong;
import xol.lostinfinity.init.ArmorInit;
import xol.lostinfinity.item.armor.model.ModelArmorVitralitonPrime;
import xol.lostinfinity.item.classify.ICooldown;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/armor/ItemVitralitonPrimeArmor.class */
public class ItemVitralitonPrimeArmor extends ItemLostArmor {
    private static final ItemArmor.ArmorMaterial VitralitonMaterial = EnumHelper.addArmorMaterial("vitralitonPrimeArmor", "lostinfinity:vitraliton_prime_armor", -1, new int[]{12, 24, 32, 12}, 20, SoundEvents.field_187716_o, 3.0f);

    public ItemVitralitonPrimeArmor(String regName, EntityEquipmentSlot slot) {
        super(VitralitonMaterial, regName, slot);
    }

    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    protected void handleSpecialArmorBonus(EntityPlayer player) {
        WorldServer worldServer = player.field_70170_p;
        if (!((World) worldServer).field_72995_K && player.field_70173_aa % 5 == 0) {
            float player_health = player.func_110143_aJ();
            float player_max = player.func_110138_aP();
            float percentage_health = (player_health / player_max) * 100.0f;
            int incremented_multiplier = (int) Math.round(Math.floor((100.0f - percentage_health) / 14.0f));
            float heal_amount = (incremented_multiplier * player.func_110138_aP()) / 100.0f;
            player.func_70691_i(heal_amount);
            if (player_health != player.func_110143_aJ() && player.func_110143_aJ() < player.func_110138_aP() / 2.0f) {
                int i = 0;
                while (true) {
                    if (i > 8) {
                        break;
                    }
                    ItemStack invyStack = player.field_71071_by.func_70301_a(i);
                    if (invyStack.func_77973_b() instanceof ICooldown) {
                        Item hotbarItem = invyStack.func_77973_b();
                        if (hotbarItem.showDurabilityBar(invyStack)) {
                            long storedTime = invyStack.func_77978_p().func_74763_f("lastUse");
                            long currentTime = System.currentTimeMillis();
                            double barProgress = 1.0d - hotbarItem.getDurabilityForDisplay(invyStack);
                            long passedTime = currentTime - storedTime;
                            int originalCD = MathHelper.func_76128_c(passedTime / barProgress);
                            int cooldownReduction = MathHelper.func_76128_c(((double) originalCD) * 0.1d);
                            long replaceLong = storedTime - ((long) cooldownReduction);
                            invyStack.func_77978_p().func_74772_a("lastUse", replaceLong);
                            LostInfinityPacketHandler.INSTANCE.sendTo(new PacketNBTSyncLong("lastUse", replaceLong, invyStack), (EntityPlayerMP) player);
                            break;
                        }
                    }
                    i++;
                }
            }
            if (incremented_multiplier > 0) {
                worldServer.func_175739_a(EnumParticleTypes.HEART, player.field_70165_t, player.field_70163_u, player.field_70161_v, incremented_multiplier, randPosDouble(((World) worldServer).field_73012_v), 1.0d, randPosDouble(((World) worldServer).field_73012_v), 0.15000000596046448d, new int[0]);
            }
        }
    }

    private double randPosDouble(Random rand) {
        return (-0.5d) + rand.nextDouble();
    }

    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    public boolean isPrimeSet() {
        return true;
    }

    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    public ArmorInit.ArmorSet getArmorSet() {
        return ArmorInit.vitralitonPrimeSet;
    }

    public String getArmorTexture(ItemStack itemstack, Entity entity, EntityEquipmentSlot slot, String layer) {
        int sprite = Math.abs(4 - (MathHelper.func_76141_d(entity.field_70173_aa / 3) % 8));
        return "lostinfinity:textures/armor/prime/vitraliton_prime_armor_" + sprite + ".png";
    }

    @SideOnly(Side.CLIENT)
    @Nullable
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        if (!itemStack.func_190926_b() && (itemStack.func_77973_b() instanceof ItemVitralitonPrimeArmor)) {
            ModelArmorVitralitonPrime vamp = new ModelArmorVitralitonPrime();
            vamp.field_78116_c.field_78806_j = armorSlot == EntityEquipmentSlot.HEAD;
            vamp.field_78115_e.field_78806_j = armorSlot == EntityEquipmentSlot.CHEST;
            vamp.field_178724_i.field_78806_j = armorSlot == EntityEquipmentSlot.CHEST;
            vamp.field_178723_h.field_78806_j = armorSlot == EntityEquipmentSlot.CHEST;
            vamp.field_178722_k.field_78806_j = armorSlot == EntityEquipmentSlot.LEGS;
            vamp.field_178721_j.field_78806_j = armorSlot == EntityEquipmentSlot.LEGS;
            vamp.field_178720_f.field_78807_k = true;
            vamp.field_78091_s = _default.field_78091_s;
            vamp.field_78117_n = _default.field_78117_n;
            vamp.field_78093_q = _default.field_78093_q;
            vamp.field_187075_l = _default.field_187075_l;
            vamp.field_187076_m = _default.field_187076_m;
            return vamp;
        }
        return null;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Immune to Normal Hits.");
        tooltip.add(TextFmt.Green + "2% Max HP Damage Reduction Per Piece");
        tooltip.add("");
        tooltip.add(TextFmt.Bold + "Set Bonuses:");
        tooltip.add(TextFmt.Green + "+22% Max HP Damage Reduction");
        tooltip.add(TextFmt.Green + "Increased % Health Regeneration Based on Missing Health");
        tooltip.add(TextFmt.Aqua + "75% Chance To Dodge (Infinity) Damage While On Full Life");
        tooltip.add("");
        tooltip.add(TextFmt.Italic + "While restoring life while below half health:");
        tooltip.add(TextFmt.Gold + "Rapidly restore cooldowns (prioritizing left to right) of items in your hotbar.");
    }
}
