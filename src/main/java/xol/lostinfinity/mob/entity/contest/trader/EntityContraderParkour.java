package xol.lostinfinity.mob.entity.contest.trader;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityBaseMerchant;
public class EntityContraderParkour extends EntityBaseMerchant implements INpc, IMerchant {
    public EntityContraderParkour(World worldIn) {
        super(worldIn);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityBaseMerchant
    public MerchantRecipeList getRecipeList() {
        MerchantRecipeList list = new MerchantRecipeList();
        list.add(new MerchantRecipe(new ItemStack(ItemInit.masterCutDiamond, 5), new ItemStack(ItemInit.eliteContenderPass)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.masterCutDiamond, 10), new ItemStack(ItemInit.mysteryBox, 4)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.zirconiaMusky, 20), new ItemStack(ItemInit.voidBox)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.zirconiaMusky, 50), new ItemStack(ItemInit.zirconiaMusky, 50), new ItemStack(ItemInit.superBooster)));
        return list;
    }
}
