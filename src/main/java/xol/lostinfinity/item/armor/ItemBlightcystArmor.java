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
import xol.lostinfinity.item.armor.model.ModelArmorBlightcyst;
public class ItemBlightcystArmor extends ItemLostArmor {
    private static final ItemArmor.ArmorMaterial BlightcystMaterial = EnumHelper.addArmorMaterial("blightcystArmor", "lostinfinity:blightcyst_armor", -1, new int[]{12, 24, 32, 12}, 20, SoundEvents.field_187716_o, 3.0f);
    public ItemBlightcystArmor(String regName, EntityEquipmentSlot slot) {
        super(BlightcystMaterial, regName, slot);
    }
    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    protected void handleSpecialArmorBonus(EntityPlayer player) {
    }
    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    public boolean isPrimeSet() {
        return false;
    }
    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    public ArmorInit.ArmorSet getArmorSet() {
        return ArmorInit.blightcystSet;
    }
    public String getArmorTexture(ItemStack itemstack, Entity entity, EntityEquipmentSlot slot, String layer) {
        return "lostinfinity:textures/armor/blightcyst_armor.png";
    }
    @SideOnly(Side.CLIENT)
    @Nullable
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        if (!itemStack.func_190926_b() && (itemStack.func_77973_b() instanceof ItemBlightcystArmor)) {
            ModelArmorBlightcyst vamp = new ModelArmorBlightcyst();
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
        tooltip.add(TextFmt.Bold + "Set Bonuses:");
        tooltip.add(TextFmt.Green + "+7% Max HP Damage Reduction");
        tooltip.add(TextFmt.Gold + "25% of Max Health Damage Taken is Stored");
        tooltip.add(TextFmt.Gold + "Your next Max Health attack deals ALL the stored damage as True Damage.");
        tooltip.add(TextFmt.Red + "Attacking with no damage stored takes life from you to store instead.");
    }
}
