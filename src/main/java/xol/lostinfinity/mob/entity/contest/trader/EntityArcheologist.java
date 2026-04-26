package xol.lostinfinity.mob.entity.contest.trader;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityBaseMerchant;
public class EntityArcheologist extends EntityBaseMerchant implements INpc, IMerchant {
    public EntityArcheologist(World worldIn) {
        super(worldIn);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityBaseMerchant
    public MerchantRecipeList getRecipeList() {
        MerchantRecipeList list = new MerchantRecipeList();
        list.add(new MerchantRecipe(new ItemStack(ItemInit.remainsPelicanEel, 1), new ItemStack(ItemInit.silverToken, 16)));
        return list;
    }
}
