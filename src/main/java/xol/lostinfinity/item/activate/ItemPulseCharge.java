package xol.lostinfinity.item.activate;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.mob.entity.sea.leviathan.EntityLeviathanController;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemPulseCharge extends Item {
    public ItemPulseCharge(String regName) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77637_a(TabsInit.TAB_AUXMATS);
        ItemInit.ITEMS.add(this);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.field_72995_K && worldIn.field_73011_w.func_186058_p() == DimensionInit.shadowSea) {
            EntityLeviathanController levi = new EntityLeviathanController(worldIn);
            levi.func_70634_a(playerIn.field_70165_t, playerIn.field_70163_u - 50.0d, playerIn.field_70161_v);
            levi.func_70624_b(playerIn);
            levi.setLeviathanSize(5);
            worldIn.func_72838_d(levi);
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.TESLA_RING_BLUE).setSpread(1.0d, 1.0d, 1.0d).setCount(5).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(worldIn, config1, playerIn.field_70165_t, playerIn.field_70163_u + ((double) (playerIn.field_70131_O / 2.0f)), playerIn.field_70161_v);
            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.GLOW_BOMB, SoundCategory.PLAYERS, 1.0f, 1.0f);
        }
        playerIn.func_184586_b(handIn).func_190918_g(1);
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Release a shockwave when used in the Shadow Sea.");
    }
}
