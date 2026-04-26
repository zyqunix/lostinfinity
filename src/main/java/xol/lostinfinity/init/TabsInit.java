package xol.lostinfinity.init;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
public class TabsInit {
    public static final CreativeTabs TAB_STONES = new CreativeTabs("tab_stones") { // from class: xol.lostinfinity.init.TabsInit.1
        @SideOnly(Side.CLIENT)
        public ItemStack func_78016_d() {
            return new ItemStack(ItemInit.corruptionCube);
        }
    };
    public static final CreativeTabs TAB_MAPS = new CreativeTabs("tab_maps") { // from class: xol.lostinfinity.init.TabsInit.2
        @SideOnly(Side.CLIENT)
        public ItemStack func_78016_d() {
            return new ItemStack(ItemInit.corruptionMap);
        }
    };
    public static final CreativeTabs TAB_DEVIANT = new CreativeTabs("tab_deviant") { // from class: xol.lostinfinity.init.TabsInit.3
        @SideOnly(Side.CLIENT)
        public ItemStack func_78016_d() {
            return new ItemStack(ItemInit.deviantEnderPearl);
        }
    };
    public static final CreativeTabs TAB_AUXMATS = new CreativeTabs("tab_auxillary") { // from class: xol.lostinfinity.init.TabsInit.4
        @SideOnly(Side.CLIENT)
        public ItemStack func_78016_d() {
            return new ItemStack(ItemInit.celestialDiamond);
        }
    };
    public static final CreativeTabs TAB_INFINITYWEP = new CreativeTabs("tab_infinity") { // from class: xol.lostinfinity.init.TabsInit.5
        @SideOnly(Side.CLIENT)
        public ItemStack func_78016_d() {
            return new ItemStack(ItemInit.bladesOfDuality);
        }
    };
    public static final CreativeTabs TAB_AUXWEP = new CreativeTabs("tab_auxweps") { // from class: xol.lostinfinity.init.TabsInit.6
        @SideOnly(Side.CLIENT)
        public ItemStack func_78016_d() {
            return new ItemStack(ItemInit.celestialMinersPickaxe);
        }
    };
    public static final CreativeTabs TAB_BLOCKS = new CreativeTabs("tab_blocks") { // from class: xol.lostinfinity.init.TabsInit.7
        @SideOnly(Side.CLIENT)
        public ItemStack func_78016_d() {
            return new ItemStack(Item.func_150898_a(BlockInit.arenaBrickBlue));
        }
    };
    public static final CreativeTabs TAB_ARMORS = new CreativeTabs("tab_armors") { // from class: xol.lostinfinity.init.TabsInit.8
        @SideOnly(Side.CLIENT)
        public ItemStack func_78016_d() {
            return new ItemStack(ArmorInit.celestialHeadguard);
        }
    };
    public static final CreativeTabs TAB_DEVIANTWEP = new CreativeTabs("tab_deviantweapons") { // from class: xol.lostinfinity.init.TabsInit.9
        @SideOnly(Side.CLIENT)
        public ItemStack func_78016_d() {
            return new ItemStack(ItemInit.deviantRelocator);
        }
    };
    public static final CreativeTabs TAB_STARFORGE = new CreativeTabs("tab_starforge") { // from class: xol.lostinfinity.init.TabsInit.10
        @SideOnly(Side.CLIENT)
        public ItemStack func_78016_d() {
            return new ItemStack(ItemInit.astralliumIngot);
        }
    };
    public static final CreativeTabs TAB_MASTERCRAFT = new CreativeTabs("tab_mastercraft") { // from class: xol.lostinfinity.init.TabsInit.11
        @SideOnly(Side.CLIENT)
        public ItemStack func_78016_d() {
            return new ItemStack(ItemInit.masterForgedIngot);
        }
    };
    public static final CreativeTabs TAB_SUPERMUTATED = new CreativeTabs("tab_supermutated") { // from class: xol.lostinfinity.init.TabsInit.12
        @SideOnly(Side.CLIENT)
        public ItemStack func_78016_d() {
            return new ItemStack(ItemInit.superMutatedPearl);
        }
    };
    public static final CreativeTabs TAB_GALAXY = new CreativeTabs("tab_galaxydungeon") { // from class: xol.lostinfinity.init.TabsInit.13
        @SideOnly(Side.CLIENT)
        public ItemStack func_78016_d() {
            return new ItemStack(ItemInit.galaxyBeacon);
        }
    };
    public static final CreativeTabs TAB_CELESTIALARENA = new CreativeTabs("tab_celestialarena") { // from class: xol.lostinfinity.init.TabsInit.14
        @SideOnly(Side.CLIENT)
        public ItemStack func_78016_d() {
            return new ItemStack(ItemInit.tokenUrogo);
        }
    };
}
