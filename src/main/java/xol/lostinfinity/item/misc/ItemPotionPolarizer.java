package xol.lostinfinity.item.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemBasic;
import xol.lostinfinity.item.classify.IHotbarTick;
import xol.lostinfinity.util.PotionBasic;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/misc/ItemPotionPolarizer.class */
public class ItemPotionPolarizer extends ItemBasic implements IHotbarTick {
    public ItemPotionPolarizer(String regName) {
        super(regName, TabsInit.TAB_AUXWEP);
    }

    @Override // xol.lostinfinity.item.classify.IHotbarTick
    public void hotbarTick(EntityPlayer player, int itemSlot, ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        Collection<PotionEffect> effectList = player.func_70651_bq();
        ArrayList<Integer> potionIDs = new ArrayList<>();
        for (NBTTagInt nBTTagInt : stack.func_77978_p().func_150295_c("potionID", 3)) {
            if (nBTTagInt instanceof NBTTagInt) {
                NBTTagInt intTag = nBTTagInt;
                potionIDs.add(Integer.valueOf(intTag.func_150287_d()));
            }
        }
        ArrayList<Integer> toRemove = new ArrayList<>();
        Iterator<Integer> it = potionIDs.iterator();
        while (it.hasNext()) {
            int id = it.next().intValue();
            boolean found = false;
            Iterator<PotionEffect> it2 = effectList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                if (Potion.func_188409_a(it2.next().func_188419_a()) == id) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                toRemove.add(Integer.valueOf(potionIDs.indexOf(Integer.valueOf(id))));
            }
        }
        NBTTagList tagList = stack.func_77978_p().func_150295_c("potionID", 3);
        Iterator<Integer> it3 = toRemove.iterator();
        while (it3.hasNext()) {
            int index = it3.next().intValue();
            if (index < potionIDs.size()) {
                potionIDs.remove(index);
                tagList.func_74744_a(index);
            }
        }
        stack.func_77978_p().func_74782_a("potionID", tagList);
        ArrayList<Potion> potionsToRemove = new ArrayList<>();
        ArrayList<PotionEffect> effectsToAdd = new ArrayList<>();
        for (PotionEffect effect : effectList) {
            Potion potion = effect.func_188419_a();
            int id2 = Potion.func_188409_a(potion);
            if (!potionIDs.contains(Integer.valueOf(id2))) {
                stack.func_77978_p().func_74768_a("tempID", id2);
                NBTBase idTag = stack.func_77978_p().func_74781_a("tempID");
                NBTTagList newTagList = stack.func_77978_p().func_150295_c("potionID", 3);
                newTagList.func_74742_a(idTag);
                stack.func_77978_p().func_74782_a("potionID", newTagList);
                int amp = effect.func_76458_c();
                int duration = effect.func_76459_b();
                if (((potion instanceof PotionBasic) && !((PotionBasic) potion).negativeLostEffect()) || (!(potion instanceof PotionBasic) && !potion.func_76398_f())) {
                    potionsToRemove.add(potion);
                    int newAmp = (int) Math.ceil(Math.min(((double) amp) * 1.5d, 10.0d));
                    int newDur = duration * 2;
                    effectsToAdd.add(new PotionEffect(potion, newDur, newAmp));
                } else {
                    int newAmp2 = (int) Math.floor(Math.max(((double) amp) * 0.5d, 1.0d));
                    int newDur2 = duration / 2;
                    potionsToRemove.add(potion);
                    effectsToAdd.add(new PotionEffect(potion, newDur2, newAmp2));
                }
            }
        }
        Iterator<Potion> it4 = potionsToRemove.iterator();
        while (it4.hasNext()) {
            player.func_184589_d(it4.next());
        }
        Iterator<PotionEffect> it5 = effectsToAdd.iterator();
        while (it5.hasNext()) {
            player.func_70690_d(it5.next());
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "While in the hotbar, adjusts potion effects added to you.");
        tooltip.add(TextFmt.Green + "Positive potion effects become 50% stronger and last 50% longer.");
        tooltip.add(TextFmt.Red + "Negative potion effects become half as strong and last half as long.");
    }
}
