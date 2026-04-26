package xol.lostinfinity.item.activate;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.mob.entity.starforge.EntityWisp;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemDarkbinder.class */
public class ItemDarkbinder extends Item {
    public ItemDarkbinder(String regName) {
        setRegistryName(regName);
        func_77655_b(regName);
        func_77637_a(TabsInit.TAB_AUXMATS);
        ItemInit.ITEMS.add(this);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.field_72995_K) {
            boolean foundLeaves = false;
            Iterable<BlockPos> nearblocks = BlockPos.func_177980_a(playerIn.func_180425_c().func_177982_a(-6, -6, -6), playerIn.func_180425_c().func_177982_a(6, 6, 6));
            Iterator<BlockPos> it = nearblocks.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BlockPos pos = it.next();
                if (worldIn.func_180495_p(pos).func_177230_c() == BlockInit.leavesDarkborn) {
                    foundLeaves = true;
                    break;
                }
            }
            if (foundLeaves) {
                boolean didWisp = false;
                for (EntityWisp creature : worldIn.func_72872_a(EntityWisp.class, playerIn.func_174813_aQ().func_72314_b(10.0d, 10.0d, 10.0d))) {
                    if (!creature.field_70128_L) {
                        EntityItem heart = new EntityItem(worldIn, creature.field_70165_t, creature.field_70163_u, creature.field_70161_v, new ItemStack(ItemInit.heartOfDarkness));
                        heart.field_70159_w = 0.0d;
                        heart.field_70181_x = 0.0d;
                        heart.field_70179_y = 0.0d;
                        worldIn.func_72838_d(heart);
                        creature.func_70106_y();
                        didWisp = true;
                    }
                }
                if (didWisp) {
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Light_Purple + "Yes! Extinguish the light... Grow my power..."));
                }
            }
            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.DARKBIND, SoundCategory.PLAYERS, 1.0f, 1.0f);
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.CORRUPTION_MAGIC).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(worldIn, config1, playerIn.field_70165_t, playerIn.field_70163_u + ((double) (playerIn.field_70131_O / 2.0f)), playerIn.field_70161_v);
        }
        playerIn.func_184586_b(handIn).func_190918_g(1);
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Binds darkness to a creature of light.");
    }
}
