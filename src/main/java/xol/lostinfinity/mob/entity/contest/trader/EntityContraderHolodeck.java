package xol.lostinfinity.mob.entity.contest.trader;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityBaseMerchant;
public class EntityContraderHolodeck extends EntityBaseMerchant implements INpc, IMerchant {
    public EntityContraderHolodeck(World worldIn) {
        super(worldIn);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityBaseMerchant
    public MerchantRecipeList getRecipeList() {
        MerchantRecipeList list = new MerchantRecipeList();
        list.add(new MerchantRecipe(new ItemStack(ItemInit.celestialDiamond, 10), new ItemStack(ItemInit.contenderPass)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.celestialDiamond, 15), new ItemStack(ItemInit.mysteryBox)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.zirconiaCerulean, 20), new ItemStack(ItemInit.powerPlug)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.zirconiaCerulean, 50), new ItemStack(ItemInit.zirconiaCerulean, 50), new ItemStack(ItemInit.ceruleanLens)));
        return list;
    }
}
