package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemBasic;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemAttractorBeam extends ItemBasic implements ICustomRaytrace, ICustomHoldPose, ISwitchModels {
    public ItemAttractorBeam(String regName) {
        super(regName, TabsInit.TAB_AUXWEP);
        setModelSwitch("beamtype", this, 2);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult trace_result;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74768_a("beamtype_data", 0);
        }
        if (playerIn.func_70093_af()) {
            if (stack.func_77978_p().func_74762_e("beamtype_data") == 0) {
                stack.func_77978_p().func_74768_a("beamtype_data", 1);
            } else {
                stack.func_77978_p().func_74768_a("beamtype_data", 0);
            }
            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187556_aj, SoundCategory.MASTER, 1.0f, 1.0f);
        } else if (!worldIn.field_72995_K && (trace_result = complexTrace(worldIn, playerIn, 90, null, 3, 1, false, Entity.class, 1, 1.5f)) != null && trace_result.getResultEntity() != null) {
            Entity beamed_entity = trace_result.getResultEntity();
            boolean repelling = stack.func_77978_p().func_74762_e("beamtype_data") == 1;
            double x_attract = 0.2d;
            double z_attract = 0.2d;
            double yDiff = Math.signum(playerIn.field_70163_u - beamed_entity.field_70163_u);
            boolean y_condition = (yDiff > 0.0d && !repelling) || (yDiff < 0.0d && repelling);
            if (repelling) {
                x_attract = 0.2d * (-1.0d);
                z_attract = 0.2d * (-1.0d);
            }
            double xdifference = Math.signum(playerIn.field_70165_t - beamed_entity.field_70165_t);
            double zdifference = Math.signum(playerIn.field_70161_v - beamed_entity.field_70161_v);
            if (Math.abs(xdifference) < 2.0d) {
                x_attract *= 0.5d;
            }
            if (Math.abs(zdifference) < 2.0d) {
                z_attract *= 0.5d;
            }
            double xvelo = xdifference > 0.0d ? x_attract : -x_attract;
            double zvelo = zdifference > 0.0d ? z_attract : -z_attract;
            if ((xvelo > 0.0d && beamed_entity.field_70159_w < 0.2d) || (xvelo < 0.0d && beamed_entity.field_70159_w > 0.2d)) {
                xvelo *= 4.0d;
            }
            if ((zvelo > 0.0d && beamed_entity.field_70179_y < 0.2d) || (zvelo < 0.0d && beamed_entity.field_70179_y > 0.2d)) {
                zvelo *= 4.0d;
            }
            beamed_entity.func_70024_g(xvelo, y_condition ? 0.5d : 0.0d, zvelo);
            beamed_entity.field_70133_I = true;
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(repelling ? ParticleInit.REPEL_FIELD : ParticleInit.ATTRACT_FIELD).setSpread(1.0d, 1.0d, 1.0d).setCount(3).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(worldIn, config1, beamed_entity.field_70165_t, beamed_entity.field_70163_u + ((double) (beamed_entity.field_70131_O / 2.0f)), beamed_entity.field_70161_v);
            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), repelling ? SoundInit.ENTITY_PUSH : SoundInit.ENTITY_PULL, SoundCategory.MASTER, 1.0f, 1.0f);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "Can be switched between a beam that pulls entities and pushes entities.");
    }
}
