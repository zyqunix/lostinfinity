package xol.lostinfinity.item.basics;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.mob.entity.boss.EntityElara;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/basics/ItemOldLostMap.class */
public class ItemOldLostMap extends Item {
    private String loc_clue;
    private String originatorName;
    private String biomeType;

    public ItemOldLostMap(String regName, String clue, String orname) {
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
            for (int i = 0; i < playerIn.field_71071_by.func_70302_i_(); i++) {
                if (playerIn.field_71071_by.func_70301_a(i) != null && playerIn.field_71071_by.func_70301_a(i).func_77973_b() == spawnItem) {
                    foundSpawner = true;
                    worldIn.func_184148_a(playerIn, playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v, SoundInit.ARENA_CHALLENGE, SoundCategory.MASTER, 2.0f, 1.0f);
                    if (!worldIn.field_72995_K) {
                        if (BiomeDictionary.getBiomes(BiomeDictionary.Type.OCEAN).contains(playerIn.field_70170_p.func_180494_b(playerIn.func_180425_c()))) {
                            EntityElara siren = new EntityElara(worldIn);
                            siren.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u + 5.0d, playerIn.field_70161_v);
                            worldIn.func_72838_d(siren);
                        } else {
                            EntityElara boundless = new EntityElara(worldIn);
                            boundless.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u + 5.0d, playerIn.field_70161_v);
                            boundless.setForm((byte) 1);
                            worldIn.func_72838_d(boundless);
                        }
                    }
                    playerIn.field_71071_by.func_70301_a(i).func_190918_g(1);
                }
            }
            if (!foundSpawner && worldIn.field_72995_K) {
                playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Red + "No encounter spawn item found."));
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
                biotype = BiomeDictionary.Type.END;
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

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "A map " + this.loc_clue);
        tooltip.add(TextFmt.Red + "You will challenge " + this.originatorName);
    }
}
