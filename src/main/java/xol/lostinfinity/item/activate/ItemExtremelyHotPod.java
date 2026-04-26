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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemExtremelyHotPod.class */
public class ItemExtremelyHotPod extends Item implements IMaxAttack {
    public ItemExtremelyHotPod(String regName) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77637_a(TabsInit.TAB_AUXMATS);
        ItemInit.ITEMS.add(this);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.field_72995_K) {
            Iterable<BlockPos> nearblocks = BlockPos.func_177980_a(playerIn.func_180425_c().func_177982_a(-3, -3, -3), playerIn.func_180425_c().func_177982_a(3, 3, 3));
            for (BlockPos pos : nearblocks) {
                if (worldIn.func_180495_p(pos).func_177230_c() == BlockInit.fungalBlockage) {
                    worldIn.func_175656_a(pos, BlockInit.fungalBlockage.func_176203_a(1));
                }
            }
            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.HOT_POD, SoundCategory.PLAYERS, 1.0f, 1.0f);
            IMaxAttack.dealTrueDamage(null, playerIn, playerIn.func_110138_aP() * 0.33f);
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.FLAME_LARGE).setSpread(1.0d, 0.0d, 1.0d).setSpeed(0.3d, 0.0d, 0.3d).setVelSpread(1.0d, 0.0d, 1.0d).setCount(13).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(worldIn, config1, playerIn.field_70165_t, playerIn.field_70163_u + ((double) (playerIn.field_70131_O / 2.0f)), playerIn.field_70161_v);
        }
        playerIn.func_184586_b(handIn).func_190918_g(1);
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "An extremely hot pod good for melting very thick goo-ey substances.");
    }
}
