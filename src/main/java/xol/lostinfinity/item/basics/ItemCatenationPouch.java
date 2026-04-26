package xol.lostinfinity.item.basics;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.dimension.murk.BiomeInfiniteMurk;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
public class ItemCatenationPouch extends ItemBasic {
    private Item item1;
    private Item item2;
    private Item item3;
    private Item result;
    public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
        ItemStack held = player.func_184586_b(hand);
        if (world.func_180494_b(player.func_180425_c()) instanceof BiomeInfiniteMurk) {
            ItemCatenationPouch pouch = (ItemCatenationPouch) held.func_77973_b();
            BlockPos itemPos = player.func_180425_c();
            boolean shards = false;
            boolean tissue = false;
            boolean sac = false;
            EntityItem shardsEntity = null;
            EntityItem tissueEntity = null;
            EntityItem sacEntity = null;
            for (EntityItem itemEntity : world.func_72872_a(EntityItem.class, new AxisAlignedBB(itemPos.func_177982_a(-4, -2, -4), itemPos.func_177982_a(4, 2, 4)))) {
                Item testItem = itemEntity.func_92059_d().func_77973_b();
                BlockPos dropPos = itemEntity.func_180425_c();
                if (world.func_180495_p(dropPos).func_177230_c().equals(BlockInit.concentratedAcid)) {
                    if (testItem == pouch.getFirstIngredient() && !shards) {
                        if (itemEntity.func_92059_d().func_190916_E() >= 5) {
                            shardsEntity = itemEntity;
                            shards = true;
                        }
                    } else if (testItem == pouch.getSecondIngredient() && !tissue) {
                        if (itemEntity.func_92059_d().func_190916_E() >= 5) {
                            tissueEntity = itemEntity;
                            tissue = true;
                        }
                    } else if (testItem == pouch.getThirdIngredient() && !sac && itemEntity.func_92059_d().func_190916_E() >= 5) {
                        sacEntity = itemEntity;
                        sac = true;
                    }
                }
                if (shards && tissue && sac) {
                    if (!world.field_72995_K) {
                        EntityItem resultItem = new EntityItem(world, itemPos.func_177958_n(), itemPos.func_177956_o() + 2, itemPos.func_177952_p(), new ItemStack(pouch.getResult()));
                        resultItem.field_70159_w = 0.0d;
                        resultItem.field_70181_x = 0.0d;
                        resultItem.field_70179_y = 0.0d;
                        world.func_72838_d(resultItem);
                        world.func_184133_a((EntityPlayer) null, itemPos.func_177984_a(), SoundInit.SPECIAL_CRAFT, SoundCategory.BLOCKS, 2.0f, 1.0f);
                        shardsEntity.func_92059_d().func_190918_g(5);
                        tissueEntity.func_92059_d().func_190918_g(5);
                        sacEntity.func_92059_d().func_190918_g(5);
                    }
                    held.func_190918_g(1);
                }
            }
        }
        return super.func_77659_a(world, player, hand);
    }
    public ItemCatenationPouch(String regName) {
        super(regName, TabsInit.TAB_AUXMATS);
    }
    public void setRecipe(Item item1, Item item2, Item item3, Item result) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.result = result;
    }
    public Item getFirstIngredient() {
        return this.item1;
    }
    public Item getSecondIngredient() {
        return this.item2;
    }
    public Item getThirdIngredient() {
        return this.item3;
    }
    public Item getResult() {
        return this.result;
    }
}
