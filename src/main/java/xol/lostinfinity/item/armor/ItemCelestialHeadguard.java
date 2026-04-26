package xol.lostinfinity.item.armor;

import javax.annotation.Nullable;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.armor.model.ModelArmorHeadguard;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/armor/ItemCelestialHeadguard.class */
public class ItemCelestialHeadguard extends ItemArmor {
    public ItemCelestialHeadguard(ItemArmor.ArmorMaterial mat, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String regName) {
        super(mat, renderIndexIn, equipmentSlotIn);
        func_77637_a(TabsInit.TAB_AUXMATS);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }

    public String getArmorTexture(ItemStack itemstack, Entity entity, EntityEquipmentSlot slot, String layer) {
        return "lostinfinity:textures/armor/celestial_headguard.png";
    }

    @SideOnly(Side.CLIENT)
    @Nullable
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        if (!itemStack.func_190926_b() && (itemStack.func_77973_b() instanceof ItemArmor)) {
            ModelArmorHeadguard guard = new ModelArmorHeadguard();
            guard.field_78116_c.field_78806_j = armorSlot == EntityEquipmentSlot.HEAD;
            guard.field_78091_s = _default.field_78091_s;
            guard.field_78117_n = _default.field_78117_n;
            guard.field_78093_q = _default.field_78093_q;
            guard.field_187075_l = _default.field_187075_l;
            guard.field_187076_m = _default.field_187076_m;
            return guard;
        }
        return null;
    }
}
