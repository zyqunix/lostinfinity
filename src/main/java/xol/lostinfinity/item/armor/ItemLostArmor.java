package xol.lostinfinity.item.armor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import xol.lostinfinity.init.ArmorInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.ConfigurationHandler;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.player.PlayerManager;
public abstract class ItemLostArmor extends ItemArmor implements IMaxAttack {
    private static final Field FLY_SPEED = ObfuscationReflectionHelper.findField(PlayerCapabilities.class, "field_75096_f");
    public abstract boolean isPrimeSet();
    public abstract ArmorInit.ArmorSet getArmorSet();
    protected abstract void handleSpecialArmorBonus(EntityPlayer entityPlayer);
    static {
        FLY_SPEED.setAccessible(true);
    }
    public ItemLostArmor(ItemArmor.ArmorMaterial material, String regName, EntityEquipmentSlot slot) {
        super(material, 0, slot);
        func_77637_a(TabsInit.TAB_ARMORS);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ARMORS.add(this);
    }
    public boolean isArmorActive(EntityPlayer player, ItemStack itemStack) {
        if (player.func_70644_a(PotionInit.NULLIFIED)) {
            return false;
        }
        boolean hasSet = PlayerManager.isPlayerWearingFullSet(player, getArmorSet());
        if (hasSet && equals(getArmorSet().helmet)) {
            if (itemStack.func_77978_p() == null) {
                itemStack.func_77982_d(new NBTTagCompound());
            }
            NBTTagCompound tag = itemStack.func_77978_p();
            if (!tag.func_74764_b("setBonus") || tag.func_74767_n("setBonus")) {
                return true;
            }
            return false;
        }
        return false;
    }
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        boolean armorActive = isArmorActive(player, itemStack);
        if (armorActive) {
            handleSpecialArmorBonus(player);
        }
        if (!world.field_72995_K && !player.func_70644_a(PotionInit.NULLIFIED)) {
            player.func_70690_d(new PotionEffect(PotionInit.ARMORED, 10, 0, true, false));
        }
    }
    private static void setPlayerFlySpeed(EntityPlayer player, float newSpeed) {
        try {
            FLY_SPEED.setFloat(player.field_71075_bZ, newSpeed);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to modify player fly speed!");
        }
    }
    public static void handleStandardArmorBonus(EntityPlayer player, boolean enable) {
        if (enable) {
            if (player.func_70644_a(PotionInit.ARMORED)) {
                if (ConfigurationHandler.armor_flight) {
                    setPlayerFlySpeed(player, 0.2f);
                    player.field_71075_bZ.field_75101_c = true;
                }
                player.field_70138_W = 2.0f;
                player.func_71024_bL().func_75122_a(20, 20.0f);
                List<Potion> potionList = (List) player.func_70651_bq().stream().map((v0) -> {
                    return v0.func_188419_a();
                }).filter((v0) -> {
                    return v0.func_76398_f();
                }).collect(Collectors.toList());
                for (Potion potion : potionList) {
                    player.func_184589_d(potion);
                }
                return;
            }
            return;
        }
        if (player.func_70644_a(PotionInit.ARMORED)) {
            player.field_70138_W = 0.6f;
            if (ConfigurationHandler.armor_flight) {
                player.field_71075_bZ.field_75101_c = player.func_184812_l_();
                player.field_71075_bZ.field_75100_b = player.func_184812_l_();
                setPlayerFlySpeed(player, 0.05f);
            }
        }
    }
}
