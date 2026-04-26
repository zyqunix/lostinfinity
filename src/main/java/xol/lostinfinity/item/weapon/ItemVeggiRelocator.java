package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.mob.entity.misc.EntityPickleMan;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemVeggiRelocator.class */
public class ItemVeggiRelocator extends ItemCooldown implements ICustomRaytrace {
    public ItemVeggiRelocator(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult trace_result;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K && (trace_result = entityTrace(worldIn, playerIn, 45, EntityLivingBase.class)) != null) {
                boolean isDeploying = stack.func_77978_p().func_74767_n("Deploying");
                BlockPos resultPos = trace_result.getResultPos();
                if (!isDeploying) {
                    int count = 0;
                    for (EntityPickleMan pickle : worldIn.func_72872_a(EntityPickleMan.class, new AxisAlignedBB(resultPos).func_186662_g(10.0d))) {
                        pickle.func_70106_y();
                        count++;
                    }
                    stack.func_77978_p().func_74768_a("StoredPickles", count);
                    stack.func_77978_p().func_74757_a("Deploying", true);
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.LASER_WEAPON_6, SoundCategory.PLAYERS, 0.7f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
                } else {
                    stack.func_77978_p().func_74757_a("Deploying", false);
                    int pickles = stack.func_77978_p().func_74762_e("StoredPickles");
                    if (pickles > 0) {
                        for (int i = 0; i < pickles; i++) {
                            EntityPickleMan pickleman = new EntityPickleMan(worldIn);
                            pickleman.func_70107_b(resultPos.func_177958_n(), ((double) resultPos.func_177956_o()) + 0.5d, resultPos.func_177952_p());
                            pickleman.func_193101_c(playerIn);
                            worldIn.func_72838_d(pickleman);
                        }
                        CustomParticleConfig config1 = new CustomParticleConfig();
                        config1.createInstance().setParticle(ParticleInit.NATURE_MAGIC).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
                        IParticleSpawner.spawnParticle(worldIn, config1, resultPos.func_177958_n(), ((double) resultPos.func_177956_o()) + 0.5d, resultPos.func_177952_p());
                        worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MAGIC_WEAPON_8, SoundCategory.PLAYERS, 0.7f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
                    }
                }
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 500;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Stores nearby Pickle Men where you aim.");
        tooltip.add(TextFmt.Gold + "Firing again redploys the Pickle Men.");
    }
}
