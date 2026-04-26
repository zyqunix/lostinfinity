package xol.lostinfinity.item.weapon;

import java.util.List;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.projectile.entity.EntityTormentorChain;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemMirrorOfConsumption.class */
public class ItemMirrorOfConsumption extends ItemCooldown implements IMaxAttack, ICustomRaytrace, ICustomHoldPose, IModeSelect, ISwitchModels {
    public ItemMirrorOfConsumption(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setModelSwitch("mirrortype", this, 2);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                boolean teleMode = stack.func_77978_p().func_74762_e("mirrortype_data") == 1;
                CustomRayTraceResult trace_result = entityTrace(worldIn, playerIn, 60, EntityLivingBase.class);
                if (trace_result != null) {
                    if (trace_result.getResultEntity() != null) {
                        EntityLivingBase hit_entity = trace_result.getResultEntity();
                        EntityTormentorChain chain = new EntityTormentorChain(worldIn);
                        chain.setTarget(hit_entity);
                        chain.setOwner(playerIn);
                        if (teleMode) {
                            double spawnX = stack.func_77978_p().func_74769_h("MarkedX");
                            double spawnY = stack.func_77978_p().func_74769_h("MarkedY");
                            double spawnZ = stack.func_77978_p().func_74769_h("MarkedZ");
                            if (spawnX != 0.0d || spawnY != 0.0d || spawnZ != 0.0d) {
                                chain.setRespawnPosition(new BlockPos(spawnX, spawnY, spawnZ));
                            }
                        }
                        Vec3d lookVec = playerIn.func_70040_Z().func_178785_b(1.5707964f).func_72432_b();
                        chain.func_70107_b(playerIn.field_70165_t - (lookVec.field_72450_a / 2.0d), playerIn.field_70163_u + (((double) playerIn.field_70131_O) / 2.2d), playerIn.field_70161_v - (lookVec.field_72449_c / 2.0d));
                        worldIn.func_72838_d(chain);
                        worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MAGIC_WEAPON_18, SoundCategory.PLAYERS, 1.0f, 0.9f + (worldIn.field_73012_v.nextFloat() * 0.2f));
                    } else if (teleMode && worldIn.field_73011_w.func_186058_p() == DimensionType.OVERWORLD) {
                        BlockPos resultPos = trace_result.getResultPos();
                        stack.func_77978_p().func_74780_a("MarkedX", resultPos.func_177958_n());
                        stack.func_77978_p().func_74780_a("MarkedY", resultPos.func_177956_o());
                        stack.func_77978_p().func_74780_a("MarkedZ", resultPos.func_177952_p());
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Purple + "Coordinate: " + resultPos.func_177958_n() + "," + resultPos.func_177956_o() + "," + resultPos.func_177952_p() + " marked."));
                        worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.FLUX_MARK, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    }
                }
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 5000;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "A very magical mirror that consumes enemies.");
        tooltip.add(TextFmt.Gold + "Slowly pull an enemy into the mirror to consume them.");
        tooltip.add(TextFmt.Red + "Consuming an enemy deals true damage equal to their max health to all nearby creatures.");
        tooltip.add(TextFmt.Aqua + "Can enable a mode which alters targets respawn location.");
    }

    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        int attack_style = stack.func_77978_p().func_74762_e("mirrortype_data");
        if (attack_style == 0) {
            stack.func_77978_p().func_74768_a("mirrortype_data", 1);
        } else {
            stack.func_77978_p().func_74768_a("mirrortype_data", 0);
        }
    }
}
