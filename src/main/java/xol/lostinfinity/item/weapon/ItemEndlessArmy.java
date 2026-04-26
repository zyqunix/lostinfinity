package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemBasic;
import xol.lostinfinity.item.classify.IHotbarTick;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.mob.entity.misc.EntityTNTZombie;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemEndlessArmy.class */
public class ItemEndlessArmy extends ItemBasic implements IHotbarTick, ISwitchModels, IModeSelect {
    public ItemEndlessArmy(String regName) {
        super(regName, TabsInit.TAB_AUXWEP);
        setModelSwitch("activated", this, 2);
    }

    @Override // xol.lostinfinity.item.classify.IHotbarTick
    public void hotbarTick(EntityPlayer player, int slot, ItemStack stack) {
        World world = player.field_70170_p;
        if (!world.field_72995_K) {
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
            }
            if (stack.func_77978_p().func_74762_e("activated_data") == 0 && player.field_70173_aa % 60 == 0 && player.func_110144_aD() != null) {
                EntityLivingBase entity = player.func_110144_aD();
                if (player.field_70173_aa - player.func_142013_aG() < 200 && entity.func_70032_d(player) < 80.0f) {
                    EntityTNTZombie zombie = new EntityTNTZombie(world);
                    zombie.func_70107_b(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
                    zombie.func_193101_c(player);
                    zombie.changeDamage(1.5f);
                    zombie.func_70624_b(entity);
                    world.func_72838_d(zombie);
                    world.func_184133_a((EntityPlayer) null, zombie.func_180425_c(), SoundInit.BIG_WARP, SoundCategory.HOSTILE, 2.0f, 1.0f);
                    CustomParticleConfig config = new CustomParticleConfig();
                    config.createInstance().setParticle(ParticleInit.GLOMITE_WARP).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(world, config, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
                }
            }
        }
    }

    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        int attack_style = stack.func_77978_p().func_74762_e("activated_data");
        if (attack_style == 0) {
            stack.func_77978_p().func_74768_a("activated_data", 1);
        } else {
            stack.func_77978_p().func_74768_a("activated_data", 0);
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Red + "While in the hotbar, passively summon TnT-Strapped Zombies on targets.");
        tooltip.add(TextFmt.Green + "Zombies deal 150% Max Health on Explosion Instead");
        tooltip.add(TextFmt.Gold + "Can be turned off.");
    }
}
