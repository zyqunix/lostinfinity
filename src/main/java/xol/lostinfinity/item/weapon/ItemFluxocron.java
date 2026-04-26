package xol.lostinfinity.item.weapon;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.util.data.CustomRayTraceResult;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemFluxocron.class */
public class ItemFluxocron extends Item implements ICustomRaytrace {
    List<EntityLivingBase> storage_list = new ArrayList();

    public ItemFluxocron(String regName) {
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        func_77625_d(1);
        ItemInit.ITEMS.add(this);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74768_a("mode_style", 0);
        }
        if (playerIn.func_70093_af()) {
            int newMode = stack.func_77978_p().func_74762_e("mode_style") + 1;
            if (newMode == 4) {
                newMode = 0;
            }
            stack.func_77978_p().func_74768_a("mode_style", newMode);
            if (!worldIn.field_72995_K) {
                playerIn.func_145747_a(new TextComponentString(TextFmt.Gold + "Mode Switched: " + modeFromInt(newMode)));
            }
        } else {
            switch (stack.func_77978_p().func_74762_e("mode_style")) {
                case 0:
                    stack.func_77978_p().func_74780_a("flux_pos_x", playerIn.field_70165_t);
                    stack.func_77978_p().func_74780_a("flux_pos_y", playerIn.field_70163_u);
                    stack.func_77978_p().func_74780_a("flux_pos_z", playerIn.field_70161_v);
                    if (!worldIn.field_72995_K) {
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Purple + "Coordinate marked for fluxation."));
                        worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.FLUX_MARK, SoundCategory.MASTER, 2.0f, 1.0f);
                    }
                    break;
                case 1:
                    if (!worldIn.field_72995_K) {
                        CustomRayTraceResult trace_result = entityTrace(worldIn, playerIn, 45, EntityLivingBase.class);
                        if (trace_result != null && trace_result.getResultEntity() != null) {
                            EntityLivingBase target = trace_result.getResultEntity();
                            if (!this.storage_list.contains(target)) {
                                this.storage_list.add(target);
                                playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Purple + target.func_70005_c_() + " added to fluxation."));
                            }
                        }
                        worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.FLUX_MARK, SoundCategory.MASTER, 2.0f, 1.0f);
                    }
                    break;
                case 2:
                    if (!worldIn.field_72995_K && !this.storage_list.isEmpty()) {
                        performTeleport(worldIn, playerIn, playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v);
                    }
                    break;
                case 3:
                    if (!worldIn.field_72995_K && !this.storage_list.isEmpty() && stack.func_77978_p().func_74764_b("flux_pos_x")) {
                        double goX = stack.func_77978_p().func_74769_h("flux_pos_x");
                        double goY = stack.func_77978_p().func_74769_h("flux_pos_y");
                        double goZ = stack.func_77978_p().func_74769_h("flux_pos_z");
                        performTeleport(worldIn, playerIn, goX, goY, goZ);
                    }
                    break;
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    private void performTeleport(World worldIn, EntityPlayer playerIn, double goX, double goY, double goZ) {
        worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187534_aX, SoundCategory.MASTER, 1.0f, 1.0f);
        for (EntityLivingBase entity : this.storage_list) {
            entity.func_70634_a(goX, goY, goZ);
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Dark_Purple + "Mass Teleports Entities To Your/Marked Position");
        if (stack.func_77942_o()) {
            int style = stack.func_77978_p().func_74762_e("mode_style");
            switch (style) {
                case 0:
                    tooltip.add(TextFmt.Light_Purple + "Mode: " + modeFromInt(style));
                    break;
                case 1:
                    tooltip.add(TextFmt.Light_Purple + "Mode: " + modeFromInt(style));
                    break;
                case 2:
                    tooltip.add(TextFmt.Light_Purple + "Mode: " + modeFromInt(style));
                    break;
                case 3:
                    tooltip.add(TextFmt.Light_Purple + "Mode: " + modeFromInt(style));
                    break;
            }
        }
    }

    private String modeFromInt(int mode) {
        switch (mode) {
            case 0:
                return "Mark Location";
            case 1:
                return "Mark Entity";
            case 2:
                return "Teleport To You";
            case 3:
                return "Teleport To Marked Location";
            default:
                return "";
        }
    }
}
