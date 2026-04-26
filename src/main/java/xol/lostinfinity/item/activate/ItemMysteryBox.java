package xol.lostinfinity.item.activate;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
public class ItemMysteryBox extends Item {
    public ItemMysteryBox(String regName) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77637_a(TabsInit.TAB_AUXMATS);
        ItemInit.ITEMS.add(this);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.field_72995_K) {
            LootTable mysteryBox = worldIn.func_184146_ak().func_186521_a(new ResourceLocation("lostinfinity:mysterybox"));
            LootContext ctx = new LootContext.Builder(worldIn.func_73046_m().func_71218_a(playerIn.field_71093_bK)).func_186471_a();
            List<ItemStack> loot = mysteryBox.func_186462_a(worldIn.field_73012_v, ctx);
            if (!playerIn.func_191521_c(loot.get(0))) {
                BlockPos playerPos = playerIn.func_180425_c();
                EntityItem lootDrop = new EntityItem(worldIn, playerPos.func_177958_n(), playerPos.func_177956_o(), playerPos.func_177952_p(), loot.get(0));
                playerIn.func_184586_b(handIn).func_190918_g(1);
                lootDrop.func_145779_a(loot.get(0).func_77973_b(), loot.get(0).func_190916_E());
            }
            playerIn.func_184586_b(handIn).func_190918_g(1);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Green + "Open to get a random amount of a random Zirconia type.");
    }
}
