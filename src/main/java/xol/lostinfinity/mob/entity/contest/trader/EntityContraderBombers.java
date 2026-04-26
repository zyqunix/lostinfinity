package xol.lostinfinity.mob.entity.contest.trader;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityBaseMerchant;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/trader/EntityContraderBombers.class */
public class EntityContraderBombers extends EntityBaseMerchant implements INpc, IMerchant {
    public EntityContraderBombers(World worldIn) {
        super(worldIn);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityBaseMerchant
    public MerchantRecipeList getRecipeList() {
        MerchantRecipeList list = new MerchantRecipeList();
        list.add(new MerchantRecipe(new ItemStack(ItemInit.celestialDiamond, 10), new ItemStack(ItemInit.contenderPass)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.celestialDiamond, 15), new ItemStack(ItemInit.mysteryBox)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.zirconiaCitrine, 20), new ItemStack(ItemInit.citrineThyratron)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.zirconiaCitrine, 50), new ItemStack(ItemInit.zirconiaCitrine, 50), new ItemStack(ItemInit.citrineSupercharger)));
        return list;
    }
}
