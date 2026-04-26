package xol.lostinfinity.item.activate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.mob.entity.starforge.EntityCrawker;
import xol.lostinfinity.mob.entity.starforge.EntityEssenceDweller;
import xol.lostinfinity.mob.entity.starforge.EntityFlutterfyre;
import xol.lostinfinity.mob.entity.starforge.EntityGrappler;
import xol.lostinfinity.mob.entity.starforge.EntityGravhead;
import xol.lostinfinity.mob.entity.starforge.EntityHurler;
import xol.lostinfinity.mob.entity.starforge.EntityHypnosaur;
import xol.lostinfinity.mob.entity.starforge.EntityRavager;
import xol.lostinfinity.mob.entity.starforge.EntityShimmer;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemComplexLostMap.class */
public class ItemComplexLostMap extends Item {
    private String originatorName;

    public ItemComplexLostMap(String regName, String originator) {
        this.originatorName = "";
        func_77637_a(TabsInit.TAB_MAPS);
        setRegistryName(regName);
        func_77655_b(regName);
        func_77625_d(1);
        this.originatorName = originator;
        ItemInit.ITEMS.add(this);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        if (!worldIn.field_72995_K) {
            int map_stage = stack.func_77978_p().func_74762_e("MapProgress");
            if (map_stage == 0) {
                Class<? extends Entity> entityType = getEntityTest(stack.func_77978_p().func_74762_e("MapEntityType"));
                int requiredCount = stack.func_77978_p().func_74762_e("MapEntityNum");
                int detected = 0;
                for (Entity entity : worldIn.func_72872_a(entityType, playerIn.func_174813_aQ().func_186662_g(6.0d))) {
                    detected++;
                }
                if (detected >= requiredCount) {
                    stack.func_77978_p().func_74768_a("MapProgress", 1);
                    stack.func_77978_p().func_74778_a("MapBiome", randomBiome());
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Gold + "Map progress has been made!"));
                } else {
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "Required creatures not detected."));
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Light_Purple + "Use near " + stack.func_77978_p().func_74762_e("MapEntityNum") + " : " + getEntityTestName(stack.func_77978_p().func_74762_e("MapEntityType"))));
                }
            } else if (map_stage == 1) {
                Biome current_biome = playerIn.field_70170_p.func_180494_b(playerIn.func_180425_c());
                boolean success = false;
                Iterator it = BiomeDictionary.getTypes(current_biome).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    BiomeDictionary.Type bt = (BiomeDictionary.Type) it.next();
                    if (bt.getName().equals(stack.func_77978_p().func_74779_i("MapBiome"))) {
                        stack.func_77978_p().func_74768_a("MapProgress", 2);
                        stack.func_77978_p().func_74768_a("MapNorth", 30 + worldIn.field_73012_v.nextInt(40));
                        stack.func_77978_p().func_74768_a("MapEast", 30 + worldIn.field_73012_v.nextInt(40));
                        stack.func_77978_p().func_74768_a("MapSouth", 30 + worldIn.field_73012_v.nextInt(40));
                        stack.func_77978_p().func_74768_a("MapWest", 30 + worldIn.field_73012_v.nextInt(40));
                        success = true;
                        break;
                    }
                }
                if (success) {
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Gold + "Map progress has been made!"));
                } else {
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "Biome does not match the map."));
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Light_Purple + "Use in a " + stack.func_77978_p().func_74779_i("MapBiome") + " biome."));
                }
            }
            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187556_aj, SoundCategory.MASTER, 1.0f, 1.0f);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    private static Class<? extends Entity> getEntityTest(int type) {
        switch (type) {
            case 0:
                return EntityGrappler.class;
            case 1:
                return EntityShimmer.class;
            case 2:
                return EntityHurler.class;
            case 3:
                return EntityEssenceDweller.class;
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                return EntityCrawker.class;
            case 5:
                return EntityHypnosaur.class;
            case TileEntityFusionTable.BOARD_COLUMNS /* 6 */:
                return EntityGravhead.class;
            case 7:
                return EntityRavager.class;
            default:
                return EntityFlutterfyre.class;
        }
    }

    private String getEntityTestName(int type) {
        switch (type) {
            case 0:
                return "Grappler";
            case 1:
                return "Shimmer";
            case 2:
                return "Hurler";
            case 3:
                return "Essence Dweller";
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                return "Crawker";
            case 5:
                return "Hypnosaur";
            case TileEntityFusionTable.BOARD_COLUMNS /* 6 */:
                return "Gravhead";
            case 7:
                return "Ravager";
            default:
                return "Flutterfyre";
        }
    }

    private String randomBiome() {
        List<BiomeDictionary.Type> types_to_pick = new ArrayList<>();
        types_to_pick.add(BiomeDictionary.Type.COLD);
        types_to_pick.add(BiomeDictionary.Type.HOT);
        types_to_pick.add(BiomeDictionary.Type.MUSHROOM);
        types_to_pick.add(BiomeDictionary.Type.MESA);
        types_to_pick.add(BiomeDictionary.Type.MAGICAL);
        types_to_pick.add(BiomeDictionary.Type.SPOOKY);
        types_to_pick.add(BiomeDictionary.Type.SWAMP);
        types_to_pick.add(BiomeDictionary.Type.SPOOKY);
        types_to_pick.add(BiomeDictionary.Type.DENSE);
        types_to_pick.add(BiomeDictionary.Type.SPARSE);
        types_to_pick.add(BiomeDictionary.Type.NETHER);
        types_to_pick.add(BiomeDictionary.Type.END);
        types_to_pick.add(BiomeDictionary.Type.SNOWY);
        types_to_pick.add(BiomeDictionary.Type.OCEAN);
        types_to_pick.add(BiomeDictionary.Type.MOUNTAIN);
        types_to_pick.add(BiomeDictionary.Type.FOREST);
        types_to_pick.add(BiomeDictionary.Type.CONIFEROUS);
        Collections.shuffle(types_to_pick);
        return types_to_pick.get(0).getName();
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Complete the 3 stages of the map.");
        tooltip.add(TextFmt.Gray + "Creature Test, Biome Test, Alignment Test");
        tooltip.add(TextFmt.Red + "You will challenge " + this.originatorName);
    }
}
