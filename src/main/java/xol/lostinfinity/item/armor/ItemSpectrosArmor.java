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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ArmorInit;
import xol.lostinfinity.item.armor.model.ModelArmorSpectros;
import xol.lostinfinity.mob.entity.misc.EntitySpectre;
public class ItemSpectrosArmor extends ItemLostArmor {
    private static final ItemArmor.ArmorMaterial SpectrosMaterial = EnumHelper.addArmorMaterial("SpectrosArmor", "lostinfinity:spectros_armor", -1, new int[]{12, 24, 32, 12}, 20, SoundEvents.field_187716_o, 3.0f);
    private static final int spectreLimit = 10;
    private static final int radius = 10;
    public ItemSpectrosArmor(String regName, EntityEquipmentSlot slot) {
        super(SpectrosMaterial, regName, slot);
    }
    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    protected void handleSpecialArmorBonus(EntityPlayer player) {
    }
    public void spectreEffect(World world, EntityPlayer player, EntityLivingBase target, ItemStack stack) {
        if (!world.field_72995_K && isArmorActive(player, stack) && world.field_73012_v.nextInt(5) == 1) {
            int count = 0;
            for (EntitySpectre nearSpectre : world.func_72872_a(EntitySpectre.class, new AxisAlignedBB(player.func_180425_c()).func_186662_g(10.0d))) {
                if (nearSpectre.func_70902_q() != null && nearSpectre.func_184753_b().equals(player.func_110124_au())) {
                    count++;
                }
            }
            if (count >= 10) {
                return;
            }
            EntitySpectre spectre = new EntitySpectre(world);
            spectre.func_70107_b(target.field_70165_t, target.field_70163_u, target.field_70161_v);
            spectre.setTamedBy(player);
            spectre.func_70624_b(target);
            world.func_72838_d(spectre);
        }
    }
    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    public ArmorInit.ArmorSet getArmorSet() {
        return ArmorInit.spectrosSet;
    }
    @Override // xol.lostinfinity.item.armor.ItemLostArmor
    public boolean isPrimeSet() {
        return false;
    }
    public String getArmorTexture(ItemStack itemstack, Entity entity, EntityEquipmentSlot slot, String layer) {
        return "lostinfinity:textures/armor/spectros_armor.png";
    }
    @SideOnly(Side.CLIENT)
    @Nullable
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        if (!itemStack.func_190926_b() && (itemStack.func_77973_b() instanceof ItemSpectrosArmor)) {
            ModelArmorSpectros vamp = new ModelArmorSpectros();
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
        tooltip.add(TextFmt.Red + "20% Chance to Summon a Spectre When You Deal Max HP Damage");
    }
}
