package xol.lostinfinity.mob.entity.contest.trader;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityBaseMerchant;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/trader/EntityContraderMarket.class */
public class EntityContraderMarket extends EntityBaseMerchant implements INpc, IMerchant {
    public EntityContraderMarket(World worldIn) {
        super(worldIn);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityBaseMerchant
    public MerchantRecipeList getRecipeList() {
        MerchantRecipeList list = new MerchantRecipeList();
        list.add(new MerchantRecipe(new ItemStack(ItemInit.masterCraftedAlloy, 15), new ItemStack(ItemInit.containerOfCollectionFull, 1), new ItemStack(ItemInit.dualWeaponConvertor)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.crystallizedAlloy, 15), new ItemStack(ItemInit.containerOfCollectionFull, 1), new ItemStack(ItemInit.directWeaponConvertor)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.starcrystalCapacitor, 10), new ItemStack(ItemInit.containerOfCollectionFull, 1), new ItemStack(ItemInit.ultrapoweredCapacitor)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.gigachargeSolutions, 10), new ItemStack(ItemInit.containerOfCollectionFull, 1), new ItemStack(ItemInit.minorMultiversalDevice)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.unstableIngot, 20), new ItemStack(ItemInit.containerOfCollectionFull, 1), new ItemStack(ItemInit.radioactiveIsotopes)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.deviantFragmentBL, 1), new ItemStack(ItemInit.broachOfDeviancy)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.deviantFragmentBR, 1), new ItemStack(ItemInit.broachOfDeviancy)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.deviantFragmentTR, 1), new ItemStack(ItemInit.broachOfDeviancy)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.deviantFragmentTL, 1), new ItemStack(ItemInit.broachOfDeviancy)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.silverToken, 10), new ItemStack(ItemInit.hypersonicDriveChamber)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.amazoniteToken, 4), new ItemStack(ItemInit.reactiveMushroom, 1), new ItemStack(ItemInit.superCell, 2)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.amazoniteToken, 4), new ItemStack(ItemInit.ghostlyHusk, 60), new ItemStack(ItemInit.etherstockSeeds, 1)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.boxOfLife, 1), new ItemStack(ItemInit.goldToken, 10)));
        return list;
    }
}
