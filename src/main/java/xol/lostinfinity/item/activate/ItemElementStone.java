package xol.lostinfinity.item.activate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
public class ItemElementStone extends Item {
    public ItemElementStone(String regName) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77637_a(TabsInit.TAB_AUXMATS);
        func_77625_d(1);
        ItemInit.ITEMS.add(this);
    }
    private void populateRequirements(ItemStack stack) {
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
        types_to_pick.add(BiomeDictionary.Type.MOUNTAIN);
        types_to_pick.add(BiomeDictionary.Type.NETHER);
        types_to_pick.add(BiomeDictionary.Type.END);
        types_to_pick.add(BiomeDictionary.Type.FOREST);
        types_to_pick.add(BiomeDictionary.Type.CONIFEROUS);
        types_to_pick.add(BiomeDictionary.Type.SNOWY);
        types_to_pick.add(BiomeDictionary.Type.OCEAN);
        Collections.shuffle(types_to_pick);
        for (int i = 0; i < 5; i++) {
            stack.func_77978_p().func_74778_a("BiomeName" + i, types_to_pick.get(i).getName());
            stack.func_77978_p().func_74757_a("BiomeComplete" + i, false);
        }
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        if (stack.func_77978_p().func_74764_b("BiomeName1")) {
            Biome current_biome = playerIn.field_70170_p.func_180494_b(playerIn.func_180425_c());
            for (BiomeDictionary.Type bt : BiomeDictionary.getTypes(current_biome)) {
                for (int i = 0; i < 5; i++) {
                    if (stack.func_77978_p().func_74779_i("BiomeName" + i).equals(bt.getName())) {
                        stack.func_77978_p().func_74757_a("BiomeComplete" + i, true);
                        worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187556_aj, SoundCategory.MASTER, 1.0f, 1.0f);
                    }
                }
            }
            int count = 0;
            for (int check = 0; check < 5; check++) {
                if (stack.func_77978_p().func_74767_n("BiomeComplete" + check)) {
                    count++;
                }
            }
            if (count == 5) {
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187626_cN, SoundCategory.MASTER, 1.0f, 1.0f);
                playerIn.func_184611_a(handIn, new ItemStack(ItemInit.elementStone));
            }
        } else {
            populateRequirements(stack);
            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187766_dk, SoundCategory.MASTER, 1.0f, 1.0f);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    private String biomeName(ItemStack stack, int slot) {
        return stack.func_77978_p().func_74779_i("BiomeName" + slot);
    }
    private boolean hasBiome(ItemStack stack, int slot) {
        return stack.func_77978_p().func_74767_n("BiomeComplete" + slot);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Needs to be taken to the following biome types:");
        boolean flag = false;
        if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("BiomeName1")) {
            tooltip.add((hasBiome(stack, 0) ? TextFmt.Green : TextFmt.Red) + "1:" + biomeName(stack, 0));
            tooltip.add((hasBiome(stack, 1) ? TextFmt.Green : TextFmt.Red) + "2:" + biomeName(stack, 1));
            tooltip.add((hasBiome(stack, 2) ? TextFmt.Green : TextFmt.Red) + "3:" + biomeName(stack, 2));
            tooltip.add((hasBiome(stack, 3) ? TextFmt.Green : TextFmt.Red) + "4:" + biomeName(stack, 3));
            tooltip.add((hasBiome(stack, 4) ? TextFmt.Green : TextFmt.Red) + "5:" + biomeName(stack, 4));
            flag = true;
        }
        if (!flag) {
            tooltip.add(TextFmt.Light_Purple + "*Activate the stone to find out the locations*");
        }
    }
}
