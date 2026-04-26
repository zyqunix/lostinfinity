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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ArmorInit;
import xol.lostinfinity.item.armor.model.ModelArmorBlightcyst;
public class ItemBlightcystPrimeArmor extends ItemLostArmor {
    private static final ItemArmor.ArmorMaterial BlightcystMaterial = EnumHelper.addArmorMaterial("blightcystPrimeArmor", "lostinfinity:blightcyst_prime_armor", -1, new int[]{12, 24, 32, 12}, 20, SoundEvents.field_187716_o, 3.0f);
    public ItemBlightcystPrimeArmor(String regName, EntityEquipmentSlot slot) {
        super(BlightcystMaterial, regName, slot);
    }
    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    protected void handleSpecialArmorBonus(EntityPlayer player) {
    }
    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    public boolean isPrimeSet() {
        return true;
    }
    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    public ArmorInit.ArmorSet getArmorSet() {
        return ArmorInit.blightcystPrimeSet;
    }
    public String getArmorTexture(ItemStack itemstack, Entity entity, EntityEquipmentSlot slot, String layer) {
        int sprite = Math.abs(4 - (MathHelper.func_76141_d(entity.field_70173_aa / 3) % 8));
        return "lostinfinity:textures/armor/prime/blightcyst_prime_armor_" + sprite + ".png";
    }
    @SideOnly(Side.CLIENT)
    @Nullable
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        if (!itemStack.func_190926_b() && (itemStack.func_77973_b() instanceof ItemBlightcystPrimeArmor)) {
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
        tooltip.add(TextFmt.Green + "+22% Max HP Damage Reduction");
        tooltip.add(TextFmt.Gold + "100% of Max Health Damage Taken is Stored");
        tooltip.add(TextFmt.Gold + "50% Chance To Store True Damage Instead Of Taking It");
        tooltip.add(TextFmt.Gold + "Your next Max Health attack deals ALL the stored damage as True Damage.");
    }
}
