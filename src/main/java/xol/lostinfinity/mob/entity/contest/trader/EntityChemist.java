package xol.lostinfinity.mob.entity.contest.trader;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityBaseMerchant;
public class EntityChemist extends EntityBaseMerchant implements INpc, IMerchant {
    public EntityChemist(World worldIn) {
        super(worldIn);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityBaseMerchant
    public MerchantRecipeList getRecipeList() {
        MerchantRecipeList list = new MerchantRecipeList();
        list.add(new MerchantRecipe(new ItemStack(ItemInit.goldToken, 1), new ItemStack(ItemInit.cureSampleBlue, 16)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.goldToken, 1), new ItemStack(ItemInit.cureSampleGreen, 16)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.goldToken, 1), new ItemStack(ItemInit.cureSampleOrange, 16)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.goldToken, 1), new ItemStack(ItemInit.cureSamplePink, 16)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.goldToken, 1), new ItemStack(ItemInit.cureSampleYellow, 16)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.amazoniteToken, 10), new ItemStack(ItemInit.magicBiopowder, 16)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.corruptedRoot, 60), new ItemStack(ItemInit.containerOfCollectionFull, 1), new ItemStack(ItemInit.colixiumCatenationPouch)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.ghostlyHusk, 60), new ItemStack(ItemInit.containerOfCollectionFull, 1), new ItemStack(ItemInit.phoroxiumCatenationPouch)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.luminescentCubes, 60), new ItemStack(ItemInit.containerOfCollectionFull, 1), new ItemStack(ItemInit.laraxiumCatenationPouch)));
        return list;
    }
}
