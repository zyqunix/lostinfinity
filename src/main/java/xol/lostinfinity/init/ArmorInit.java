package xol.lostinfinity.init;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import xol.lostinfinity.item.armor.ItemBionicVeggitronArmor;
import xol.lostinfinity.item.armor.ItemBlightcystArmor;
import xol.lostinfinity.item.armor.ItemBlightcystPrimeArmor;
import xol.lostinfinity.item.armor.ItemCelestialHeadguard;
import xol.lostinfinity.item.armor.ItemFiltrationMask;
import xol.lostinfinity.item.armor.ItemGraviteriumArmor;
import xol.lostinfinity.item.armor.ItemLostArmor;
import xol.lostinfinity.item.armor.ItemPlasmythicArmor;
import xol.lostinfinity.item.armor.ItemSpectrosArmor;
import xol.lostinfinity.item.armor.ItemSpectrosPrimeArmor;
import xol.lostinfinity.item.armor.ItemVampyreonArmor;
import xol.lostinfinity.item.armor.ItemVampyreonPrimeArmor;
import xol.lostinfinity.item.armor.ItemVitralitonArmor;
import xol.lostinfinity.item.armor.ItemVitralitonPrimeArmor;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/init/ArmorInit.class */
@Mod.EventBusSubscriber
public class ArmorInit {
    public static final ItemArmor.ArmorMaterial ARMOUR_CELESTIAL = EnumHelper.addArmorMaterial("armor_celestial", "lostinfinity:celestial", -1, new int[]{3, 6, 8, 3}, 10, SoundEvents.field_187716_o, 2.0f);
    public static final ArmorSet vampyreonSet = new ArmorSet("Vampyreon", ItemVampyreonArmor.class);
    public static final ArmorSet graviteriumSet = new ArmorSet("Graviterium", ItemGraviteriumArmor.class);
    public static final ArmorSet plasmythicSet = new ArmorSet("Plasmythic", ItemPlasmythicArmor.class);
    public static final ArmorSet spectrosSet = new ArmorSet("Spectros", ItemSpectrosArmor.class);
    public static final ArmorSet vitralitonSet = new ArmorSet("Vitraliton", ItemVitralitonArmor.class);
    public static final ArmorSet blightcystSet = new ArmorSet("Blightcyst", ItemBlightcystArmor.class);
    public static final ArmorSet bionicveggitronSet = new ArmorSet("Bionic_veggitron", ItemBionicVeggitronArmor.class);
    public static final ArmorSet vampyreonPrimeSet = new ArmorSet("Vampyreon_prime", ItemVampyreonPrimeArmor.class);
    public static final ArmorSet spectrosPrimeSet = new ArmorSet("Spectros_prime", ItemSpectrosPrimeArmor.class);
    public static final ArmorSet vitralitonPrimeSet = new ArmorSet("Vitraliton_prime", ItemVitralitonPrimeArmor.class);
    public static final ArmorSet blightcystPrimeSet = new ArmorSet("Blightcyst_prime", ItemBlightcystPrimeArmor.class);
    public static final Item celestialHeadguard = new ItemCelestialHeadguard(ARMOUR_CELESTIAL, 1, EntityEquipmentSlot.HEAD, "celestialheadguard");
    public static final Item filtrationMask = new ItemFiltrationMask(ARMOUR_CELESTIAL, 1, EntityEquipmentSlot.HEAD, "filtrationmask");

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll((IForgeRegistryEntry[]) BlockInit.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll((IForgeRegistryEntry[]) ItemInit.ITEMS.toArray(new Item[0]));
        registerArmorSets(event.getRegistry(), vampyreonSet);
        registerArmorSets(event.getRegistry(), graviteriumSet);
        registerArmorSets(event.getRegistry(), plasmythicSet);
        registerArmorSets(event.getRegistry(), spectrosSet);
        registerArmorSets(event.getRegistry(), blightcystSet);
        registerArmorSets(event.getRegistry(), bionicveggitronSet);
        registerArmorSets(event.getRegistry(), vitralitonSet);
        registerArmorSets(event.getRegistry(), vampyreonPrimeSet);
        registerArmorSets(event.getRegistry(), spectrosPrimeSet);
        registerArmorSets(event.getRegistry(), vitralitonPrimeSet);
        registerArmorSets(event.getRegistry(), blightcystPrimeSet);
    }

    public static void registerArmorSets(IForgeRegistry<Item> registry, ArmorSet... sets) {
        Arrays.stream(sets).forEach(set -> {
            registry.registerAll((IForgeRegistryEntry[]) set.getArmor().toArray(new Item[0]));
        });
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/init/ArmorInit$ArmorSet.class */
    public static class ArmorSet {
        public ItemLostArmor helmet;
        public ItemLostArmor chestplate;
        public ItemLostArmor leggings;
        public ItemLostArmor boots;

        /* JADX INFO: Access modifiers changed from: private */
        public List<? extends Item> getArmor() {
            return Arrays.asList(this.helmet, this.chestplate, this.leggings, this.boots);
        }

        private ArmorSet(String name, Class<? extends ItemLostArmor> armor) {
            this.helmet = null;
            this.chestplate = null;
            this.leggings = null;
            this.boots = null;
            ItemLostArmor helm = null;
            ItemLostArmor chest = null;
            ItemLostArmor legs = null;
            ItemLostArmor boot = null;
            try {
                try {
                    Constructor<? extends ItemLostArmor> c = armor.getConstructor(String.class, EntityEquipmentSlot.class);
                    helm = c.newInstance(name.toLowerCase() + "_helmet", EntityEquipmentSlot.HEAD);
                    chest = c.newInstance(name.toLowerCase() + "_chestplate", EntityEquipmentSlot.CHEST);
                    legs = c.newInstance(name.toLowerCase() + "_leggings", EntityEquipmentSlot.LEGS);
                    boot = c.newInstance(name.toLowerCase() + "_boots", EntityEquipmentSlot.FEET);
                    this.helmet = helm;
                    this.chestplate = chest;
                    this.leggings = legs;
                    this.boots = boot;
                } catch (Exception e) {
                    e.printStackTrace();
                    this.helmet = helm;
                    this.chestplate = chest;
                    this.leggings = legs;
                    this.boots = boot;
                }
            } catch (Throwable th) {
                this.helmet = helm;
                this.chestplate = chest;
                this.leggings = legs;
                this.boots = boot;
                throw th;
            }
        }
    }
}
