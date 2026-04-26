package xol.lostinfinity.mob.entity.contest.trader;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityBaseMerchant;
public class EntityContraderTargets extends EntityBaseMerchant implements INpc, IMerchant {
    public EntityContraderTargets(World worldIn) {
        super(worldIn);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityBaseMerchant
    public MerchantRecipeList getRecipeList() {
        MerchantRecipeList list = new MerchantRecipeList();
        list.add(new MerchantRecipe(new ItemStack(ItemInit.masterCutDiamond, 5), new ItemStack(ItemInit.eliteContenderPass)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.masterCutDiamond, 10), new ItemStack(ItemInit.mysteryBox, 4)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.zirconiaRosewood, 20), new ItemStack(ItemInit.polyswitch)));
        list.add(new MerchantRecipe(new ItemStack(ItemInit.zirconiaRosewood, 50), new ItemStack(ItemInit.zirconiaRosewood, 50), new ItemStack(ItemInit.sparkChamber)));
        return list;
    }
}
