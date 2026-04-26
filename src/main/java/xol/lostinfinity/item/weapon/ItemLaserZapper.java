package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerLaserTag;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
import xol.lostinfinity.util.data.CustomRayTraceResult;
public class ItemLaserZapper extends ItemCooldown implements ICustomRaytrace, ICustomHoldPose {
    public ItemLaserZapper(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        EntityPlayer resultEntity;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                CustomRayTraceResult trace_result = entityTrace(worldIn, playerIn, 60, EntityPlayer.class);
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.LASER_WEAPON_7, SoundCategory.PLAYERS, 1.0f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
                if (trace_result != null && (resultEntity = trace_result.getResultEntity()) != null) {
                    EntityPlayer target = resultEntity;
                    AxisAlignedBB arena = ContestCoordinates.laserTagControllerAABB();
                    for (EntityControllerLaserTag controller : worldIn.func_72872_a(EntityControllerLaserTag.class, arena)) {
                        controller.hitPlayer(playerIn, target, 1);
                    }
                }
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        } else if (!worldIn.field_72995_K) {
            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.WEAPON_ERROR, SoundCategory.PLAYERS, 1.0f, 0.9f + (worldIn.field_73012_v.nextFloat() * 0.2f));
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 3000;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    }
}
