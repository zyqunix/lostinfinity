package xol.lostinfinity.item.weapon;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.mob.entity.misc.EntitySkycrab;
import xol.lostinfinity.util.data.CustomRayTraceResult;
public class ItemSkycrabController extends ItemCooldown implements IModeSelect, ICustomRaytrace {
    public ItemSkycrabController(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        EntitySkycrab existing;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K) {
                int itemMode = stack.func_77978_p().func_74762_e("use_mode");
                UUID crab_uuid = stack.func_77978_p().func_186857_a("crabID");
                switch (itemMode) {
                    case 0:
                        if (isExistingCrab(crab_uuid) && (existing = worldIn.func_73046_m().func_175576_a(crab_uuid)) != null) {
                            existing.func_70106_y();
                        }
                        EntitySkycrab crab = new EntitySkycrab(worldIn);
                        crab.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u + 10.0d, playerIn.field_70161_v);
                        crab.func_193101_c(playerIn);
                        BlockPos homePosition = new BlockPos(playerIn.field_70165_t, playerIn.field_70163_u + 10.0d, playerIn.field_70161_v);
                        crab.setHomePos(homePosition);
                        worldIn.func_72838_d(crab);
                        stack.func_77978_p().func_186854_a("crabID", crab.func_110124_au());
                        worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.SKYCRAB_AMBIENT, SoundCategory.PLAYERS, 1.0f, 1.0f);
                        break;
                    case 1:
                        if (isExistingCrab(crab_uuid)) {
                            EntitySkycrab existing2 = worldIn.func_73046_m().func_175576_a(crab_uuid);
                            boolean aggressive = !existing2.isAggressive();
                            existing2.setTarget(null);
                            existing2.setTargeting(false);
                            existing2.setGoalPosition(null);
                            existing2.setAggressive(aggressive);
                            playerIn.func_145747_a(new TextComponentString(TextFmt.Gold + "Skycrab is now " + (aggressive ? "aggressive." : "passive.")));
                        }
                        break;
                    case 2:
                        if (isExistingCrab(crab_uuid)) {
                            EntitySkycrab existing3 = worldIn.func_73046_m().func_175576_a(crab_uuid);
                            existing3.setAggressive(true);
                            existing3.setTargeting(true);
                            CustomRayTraceResult trace_result = entityTrace(worldIn, playerIn, 60, EntityLivingBase.class);
                            if (trace_result != null && trace_result.getResultEntity() != null && !(trace_result.getResultEntity() instanceof EntitySkycrab)) {
                                EntityLivingBase trace_entity = (EntityLivingBase) trace_result.getResultEntity();
                                existing3.setTarget(trace_entity);
                                BlockPos targetPos = trace_entity.func_180425_c().func_177963_a(0.0d, trace_entity.field_70131_O + 10.0f, 0.0d);
                                playerIn.func_145747_a(new TextComponentString(TextFmt.Gold + "Skycrab is now targeting " + trace_entity.func_70005_c_()));
                                existing3.setGoalPosition(targetPos);
                                existing3.playTargetSound();
                            }
                        }
                        break;
                }
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    private boolean isExistingCrab(UUID idToCheck) {
        return (idToCheck == null || idToCheck.equals(UUID.fromString("00000000-0000-0000-0000-000000000000"))) ? false : true;
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 750;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Summons a Sky Crab to defend an area.");
        tooltip.add(TextFmt.Aqua + "Switch modes to control your Sky Crab.");
        tooltip.add(TextFmt.Italic + "The Sky Crab obliterates targets that it detects.");
    }
    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        int current_mode = stack.func_77978_p().func_74762_e("use_mode") + 1;
        if (current_mode == 3) {
            current_mode = 0;
        }
        stack.func_77978_p().func_74768_a("use_mode", current_mode);
        if (!player.field_70170_p.field_72995_K) {
            switch (current_mode) {
                case 0:
                    player.func_145747_a(new TextComponentString(TextFmt.Gold + "Summoning Mode Enabled"));
                    break;
                case 1:
                    player.func_145747_a(new TextComponentString(TextFmt.Gold + "Stance Switching Mode Enabled"));
                    break;
                case 2:
                    player.func_145747_a(new TextComponentString(TextFmt.Gold + "Targeting Mode Enabled"));
                    break;
            }
        }
    }
}
