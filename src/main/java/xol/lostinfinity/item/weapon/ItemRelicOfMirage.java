package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
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
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemRelicOfMirage.class */
public class ItemRelicOfMirage extends ItemCooldown implements IMaxAttack, ICustomRaytrace {
    public ItemRelicOfMirage(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 3000;
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult trace_result;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K && (trace_result = entityTrace(worldIn, playerIn, 60, EntityPlayer.class)) != null && trace_result.getResultEntity() != null) {
                EntityPlayer tracedEntity = trace_result.getResultEntity();
                if (!tracedEntity.func_184614_ca().func_190926_b()) {
                    ItemStack heldByTarget = tracedEntity.func_184614_ca();
                    ItemStack stolenCopy = heldByTarget.func_77946_l();
                    if (!stolenCopy.func_77942_o()) {
                        stolenCopy.func_77982_d(new NBTTagCompound());
                    }
                    stolenCopy.func_77978_p().func_74768_a("InfinityMiraged", playerIn.field_70173_aa);
                    stolenCopy.func_190920_e(1);
                    EntityItem stolenItem = new EntityItem(worldIn, playerIn.field_70165_t, playerIn.field_70163_u + 1.0d, playerIn.field_70161_v, stolenCopy);
                    stolenItem.field_70159_w = 0.0d;
                    stolenItem.field_70181_x = 0.0d;
                    stolenItem.field_70179_y = 0.0d;
                    worldIn.func_72838_d(stolenItem);
                    CustomParticleConfig config1 = new CustomParticleConfig();
                    config1.createInstance().setParticle(ParticleInit.ANCIENT_SPELL).setSpread(1.0d, 1.0d, 1.0d).setSpeed(0.3d, 0.0d, 0.3d).setVelSpread(1.0d, 0.0d, 1.0d).setCount(14).setIgnoreRange(true);
                    CustomParticleConfig config2 = new CustomParticleConfig();
                    config2.createInstance().setParticle(ParticleInit.ATTRACT_FIELD).setSpread(1.0d, 1.0d, 1.0d).setCount(3).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(worldIn, config1, playerIn.field_70165_t, playerIn.field_70163_u + 1.0d, playerIn.field_70161_v);
                    IParticleSpawner.spawnParticle(worldIn, config2, tracedEntity.field_70165_t, tracedEntity.field_70163_u + 1.0d, tracedEntity.field_70161_v);
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MAGIC_WEAPON_12, SoundCategory.PLAYERS, 1.0f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
                }
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Creates a miraged copy of target player's held item.");
        tooltip.add(TextFmt.Aqua + "Miraged items are fully functional but vanish when held after some time.");
    }
}
