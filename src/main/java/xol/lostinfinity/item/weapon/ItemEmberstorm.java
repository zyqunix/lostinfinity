package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemChanneling;
import xol.lostinfinity.projectile.entity.EntityEmberShot;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.RayTraceBuilder;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemEmberstorm.class */
public class ItemEmberstorm extends ItemChanneling {
    public ItemEmberstorm(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    @Override // xol.lostinfinity.item.basics.ItemChanneling
    public void chargeTick(World worldIn, EntityPlayer player, EnumHand hand, ItemStack stack, int chargeTime) {
        if (worldIn.field_72995_K || chargeTime % 2 != 0 || chargeTime > 100) {
            return;
        }
        EntityEmberShot bullet = new EntityEmberShot(worldIn);
        bullet.setThrower(player);
        bullet.setItemStack(stack);
        worldIn.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.FAST_CHARGE, SoundCategory.PLAYERS, 1.0f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        do {
            pos.func_189532_c((player.field_70165_t + ((double) field_77697_d.nextInt(20))) - 10.0d, (player.field_70163_u + ((double) field_77697_d.nextInt(20))) - 10.0d, (player.field_70161_v + ((double) field_77697_d.nextInt(20))) - 10.0d);
        } while (!worldIn.func_175623_d(pos));
        bullet.func_70107_b(((double) pos.func_177958_n()) + 0.5d, ((double) pos.func_177956_o()) + 0.5d, ((double) pos.func_177952_p()) + 0.5d);
        worldIn.func_72838_d(bullet);
    }

    @Override // xol.lostinfinity.item.basics.ItemChanneling
    public void chargeStop(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int chargeTime) {
        CustomRayTraceResult result;
        super.chargeStop(stack, worldIn, entityLiving, chargeTime);
        if (!worldIn.field_72995_K && (result = RayTraceBuilder.entity(EntityLivingBase.class, 50).force(true).trace(entityLiving, true)) != null) {
            if (result.getResultEntity() != null) {
                Vec3d target = LMath.getEntityMiddle(result.getResultEntity());
                setTargetLocation(stack, target);
            } else {
                setTargetLocation(stack, result.getResultVector());
                worldIn.func_184133_a((EntityPlayer) null, entityLiving.func_180425_c(), SoundInit.MAGIC_WEAPON_19, SoundCategory.PLAYERS, 1.5f, 1.0f);
            }
        }
    }

    private void setTargetLocation(ItemStack stack, Vec3d target) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_74776_a("targetX", (float) target.field_72450_a);
        stack.func_77978_p().func_74776_a("targetY", (float) target.field_72448_b);
        stack.func_77978_p().func_74776_a("targetZ", (float) target.field_72449_c);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "Can be channelled to summon fireballs around you.");
        tooltip.add(TextFmt.Gold + "When you finish channeling, unleash the fireballs where you are looking.");
        tooltip.add(TextFmt.Red + "Each Fireball does 25% Health True Damage");
        tooltip.add(TextFmt.Yellow + "Fireballs Also Take Away 3 Lives From Multi-Life Creatures");
        tooltip.add(TextFmt.Dark_Aqua + "Darkborn");
    }
}
