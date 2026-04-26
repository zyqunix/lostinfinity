package xol.lostinfinity.item.armor;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ArmorInit;
import xol.lostinfinity.item.armor.model.ModelArmorGraviterium;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/armor/ItemGraviteriumArmor.class */
public class ItemGraviteriumArmor extends ItemLostArmor {
    private static final ItemArmor.ArmorMaterial graviteriumMaterial = EnumHelper.addArmorMaterial("graviteriumArmor", "lostinfinity:graviterium_armor", -1, new int[]{12, 24, 32, 12}, 20, SoundEvents.field_187716_o, 3.0f);

    public ItemGraviteriumArmor(String regName, EntityEquipmentSlot slot) {
        super(graviteriumMaterial, regName, slot);
    }

    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    protected void handleSpecialArmorBonus(EntityPlayer player) {
        World world = player.field_70170_p;
        if (!world.field_72995_K && player.field_70173_aa % 5 == 0 && player.func_110144_aD() != null) {
            EntityLivingBase entity = player.func_110144_aD();
            if (player.field_70173_aa - player.func_142013_aG() < 200 && entity.func_70032_d(player) > 7.0f && entity.func_70104_M()) {
                entity.func_70024_g(Math.signum(player.field_70165_t - entity.field_70165_t) * 0.5d, Math.signum(player.field_70163_u - entity.field_70163_u) * 0.6d, Math.signum(player.field_70161_v - entity.field_70161_v) * 0.5d);
                entity.field_70133_I = true;
            }
        }
    }

    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    public ArmorInit.ArmorSet getArmorSet() {
        return ArmorInit.graviteriumSet;
    }

    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    public boolean isPrimeSet() {
        return false;
    }

    public String getArmorTexture(ItemStack itemstack, Entity entity, EntityEquipmentSlot slot, String layer) {
        return "lostinfinity:textures/armor/graviterium_armor.png";
    }

    @SideOnly(Side.CLIENT)
    @Nullable
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        if (!itemStack.func_190926_b() && (itemStack.func_77973_b() instanceof ItemGraviteriumArmor)) {
            ModelArmorGraviterium vamp = new ModelArmorGraviterium();
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
        tooltip.add(TextFmt.Gold + "Immune to normal hits.");
        tooltip.add(TextFmt.Green + "2% Max HP Damage Reduction Per Piece");
        tooltip.add(TextFmt.Bold + "Set Bonuses:");
        tooltip.add(TextFmt.Green + "+7% Max HP Damage Reduction");
        tooltip.add(TextFmt.Red + "Enemies you attack with melee hits are marked for 10s.");
        tooltip.add(TextFmt.Red + "Marked enemies are pulled towards you if they get too far.");
    }
}
