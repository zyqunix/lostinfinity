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
import xol.lostinfinity.item.armor.model.ModelArmorVampyreon;
import xol.lostinfinity.util.data.IMaxAttack;
public class ItemVampyreonArmor extends ItemLostArmor {
    private static final ItemArmor.ArmorMaterial vampyreanMaterial = EnumHelper.addArmorMaterial("vampyreonArmor", "lostinfinity:vampyreon_armor", -1, new int[]{12, 24, 32, 12}, 20, SoundEvents.field_187716_o, 3.0f);
    public ItemVampyreonArmor(String regName, EntityEquipmentSlot slot) {
        super(vampyreanMaterial, regName, slot);
    }
    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    protected void handleSpecialArmorBonus(EntityPlayer player) {
        World world = player.field_70170_p;
        if (!world.field_72995_K && player.field_70173_aa % 10 == 0) {
            int leeched = 0;
            List<EntityPlayer> entities = player.field_70170_p.func_72872_a(EntityPlayer.class, player.func_174813_aQ().func_72314_b(4.0d, 4.0d, 4.0d));
            for (EntityPlayer e : entities) {
                if (!e.equals(player)) {
                    leeched++;
                    IMaxAttack.dealMaxHealth(player, e, 20);
                }
            }
            if (leeched > 0) {
                player.func_70691_i(Math.min(leeched * (player.func_110138_aP() / 50.0f), player.func_110138_aP() / 10.0f));
            }
        }
    }
    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    public ArmorInit.ArmorSet getArmorSet() {
        return ArmorInit.vampyreonSet;
    }
    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    public boolean isPrimeSet() {
        return false;
    }
    public String getArmorTexture(ItemStack itemstack, Entity entity, EntityEquipmentSlot slot, String layer) {
        return "lostinfinity:textures/armor/vampyreon_armor.png";
    }
    @SideOnly(Side.CLIENT)
    @Nullable
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        if (!itemStack.func_190926_b() && (itemStack.func_77973_b() instanceof ItemVampyreonArmor)) {
            ModelArmorVampyreon vamp = new ModelArmorVampyreon();
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
        tooltip.add(TextFmt.Red + "Steal max life constantly from nearby players.");
    }
}
