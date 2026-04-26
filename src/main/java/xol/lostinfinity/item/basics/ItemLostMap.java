package xol.lostinfinity.item.basics;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.util.DimensionActivator;
import xol.lostinfinity.init.ArmorInit;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.player.PlayerManager;
public class ItemLostMap extends Item {
    private String loc_clue;
    private String originatorName;
    private String biomeType;
    public ItemLostMap(String regName, String clue, String orname) {
        this.loc_clue = "";
        this.originatorName = "";
        this.biomeType = "";
        func_77637_a(TabsInit.TAB_MAPS);
        setRegistryName(regName);
        func_77655_b(regName);
        this.loc_clue = clue;
        this.originatorName = orname;
        this.biomeType = regName.replace("map_", "");
        ItemInit.ITEMS.add(this);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (biomeMatchMap(playerIn)) {
            Item spawnItem = getSpawnItem();
            boolean foundSpawner = false;
            boolean wearingHeadguard = false;
            for (int i = 0; i < playerIn.field_71071_by.func_70302_i_(); i++) {
                if (playerIn.field_71071_by.func_70301_a(i).func_77973_b() == spawnItem) {
                    foundSpawner = true;
                    ItemStack helm = playerIn.field_71071_by.func_70301_a(39);
                    if (helm.func_77973_b() == ArmorInit.celestialHeadguard || PlayerManager.isWearingAnySet(playerIn)) {
                        wearingHeadguard = true;
                        playerIn.field_71071_by.func_70301_a(i).func_190918_g(1);
                        playerIn.field_71071_by.func_70299_a(i, getBossToken());
                        DimensionActivator.transferEntityWithCoords(playerIn, DimensionInit.celestialVoid, 25.5d, 31.5d, 31.5d);
                        if (worldIn.field_72995_K) {
                            playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Blue + "Welcome to the Grand Celestial Arena."));
                        }
                    }
                }
            }
            if (!foundSpawner) {
                if (worldIn.field_72995_K) {
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Red + "No encounter spawn item found."));
                }
            } else if (!wearingHeadguard && worldIn.field_72995_K) {
                playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Red + "You must wear a Celestial Headguard to enter the arena."));
            }
        } else if (worldIn.field_72995_K) {
            playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Red + "This biome does not match the clue."));
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    private boolean biomeMatchMap(EntityPlayer player) {
        BiomeDictionary.Type biotype;
        switch (this.biomeType) {
            case "corruption":
                biotype = BiomeDictionary.Type.MESA;
                break;
            case "duality":
                biotype = BiomeDictionary.Type.OCEAN;
                break;
            case "aspiration":
                biotype = BiomeDictionary.Type.MOUNTAIN;
                break;
            case "ingenuity":
                biotype = BiomeDictionary.Type.VOID;
                break;
            case "misdirection":
                biotype = BiomeDictionary.Type.MUSHROOM;
                break;
            case "vengeance":
                biotype = BiomeDictionary.Type.END;
                break;
            case "dread":
                biotype = BiomeDictionary.Type.HOT;
                break;
            case "imposition":
                biotype = BiomeDictionary.Type.SWAMP;
                break;
            case "anxiety":
                biotype = BiomeDictionary.Type.SNOWY;
                break;
            case "retrospection":
                biotype = BiomeDictionary.Type.SAVANNA;
                break;
            default:
                biotype = BiomeDictionary.Type.PLAINS;
                break;
        }
        return BiomeDictionary.getBiomes(biotype).contains(player.field_70170_p.func_180494_b(player.func_180425_c()));
    }
    private Item getSpawnItem() {
        switch (this.biomeType) {
        }
        return ItemInit.elarasNecklace;
    }
    private ItemStack getBossToken() {
        switch (this.biomeType) {
        }
        return new ItemStack(ItemInit.tokenUrogo);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "A map " + this.loc_clue);
        tooltip.add(TextFmt.Red + "You will challenge " + this.originatorName);
    }
}
