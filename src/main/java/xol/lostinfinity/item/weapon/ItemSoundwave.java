package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.projectile.entity.EntitySoundwaveBullet;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemSoundwave.class */
public class ItemSoundwave extends ItemCooldown implements ICustomHoldPose {
    public ItemSoundwave(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                EntitySoundwaveBullet bullet = new EntitySoundwaveBullet(worldIn, playerIn);
                bullet.setThrower(playerIn);
                bullet.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70759_as, 0.0f, 0.5f, 0.0f);
                worldIn.func_72838_d(bullet);
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.SOUND_GUN, SoundCategory.PLAYERS, 1.0f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 600;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Italic + "Shoots echo projectiles.");
        tooltip.add(TextFmt.Gold + "Echo projectiles pierce entities, dealing damage periodically.");
        tooltip.add(TextFmt.Gold + "Echoes can bounce off terrain.");
        tooltip.add(TextFmt.Red + "Deals 33% Max Health Damage Every 0.5 Seconds");
        tooltip.add(TextFmt.Red + "Projectiels Deal True Damage After Bouncing");
    }
}
