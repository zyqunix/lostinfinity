package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.basic.BlockBasicCrop;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.mob.entity.misc.EntityPickleMan;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemVeggiEnigmatic extends ItemCooldown implements IMaxAttack, ICustomRaytrace, ICustomHoldPose {
    public ItemVeggiEnigmatic(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                CustomRayTraceResult trace_result = immaterialTrace(worldIn, playerIn, 15);
                if (trace_result != null) {
                    Vec3d tracePos = trace_result.getGrabbedVector();
                    IBlockState grabbedState = worldIn.func_180495_p(new BlockPos(tracePos));
                    if (grabbedState.func_177230_c() == BlockInit.spacePickle) {
                        BlockBasicCrop pickleplant = grabbedState.func_177230_c();
                        if (pickleplant.getCropAge(grabbedState) == 7) {
                            EntityPickleMan pickleman = new EntityPickleMan(worldIn);
                            pickleman.func_70107_b(tracePos.field_72450_a, tracePos.field_72448_b + 0.5d, tracePos.field_72449_c);
                            pickleman.func_193101_c(playerIn);
                            worldIn.func_72838_d(pickleman);
                            worldIn.func_175698_g(new BlockPos(tracePos));
                            CustomParticleConfig config1 = new CustomParticleConfig();
                            config1.createInstance().setParticle(ParticleInit.NATURE_MAGIC).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
                            IParticleSpawner.spawnParticle(worldIn, config1, tracePos.field_72450_a, tracePos.field_72448_b + 0.5d, tracePos.field_72449_c);
                        }
                    }
                }
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.LASER_WEAPON_3, SoundCategory.PLAYERS, 0.7f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 100;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Shocks a target that you are looking at.");
        tooltip.add(TextFmt.Gold + "Increases level of shock is target is already shocked.");
        tooltip.add(TextFmt.Gold + "Deals 10% Max Health Damage per stack of shock.");
    }
}
