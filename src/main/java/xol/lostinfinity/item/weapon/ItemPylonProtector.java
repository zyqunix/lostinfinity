package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
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
import xol.lostinfinity.mob.entity.misc.EntityTotemPylon;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemPylonProtector extends ItemCooldown implements ICustomRaytrace {
    public ItemPylonProtector(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult trace_result;
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K && (trace_result = simpleBlockTrace(worldIn, playerIn, 40)) != null) {
                BlockPos pylonPos = trace_result.getResultPos();
                EntityTotemPylon pylon = new EntityTotemPylon(worldIn);
                pylon.func_70107_b(pylonPos.func_177958_n(), pylonPos.func_177956_o(), pylonPos.func_177952_p());
                pylon.setOwner(playerIn);
                worldIn.func_72838_d(pylon);
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.FLAME_MEDIUM).setSpread(1.0d, 1.0d, 1.0d).setCount(7).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(worldIn, config1, pylonPos.func_177958_n(), pylonPos.func_177956_o(), pylonPos.func_177952_p());
                worldIn.func_184133_a((EntityPlayer) null, pylonPos, SoundInit.MAGIC_WEAPON_8, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 16000;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Summons a Pylon of Protection where you aim.");
        tooltip.add(TextFmt.Green + "Pylons destroy projectiles that are not fired by you.");
    }
}
