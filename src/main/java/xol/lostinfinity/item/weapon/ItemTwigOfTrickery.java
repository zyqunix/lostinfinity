package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantCreeper;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantSkeleton;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantSpider;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemTwigOfTrickery.class */
public class ItemTwigOfTrickery extends ItemCooldown {
    public ItemTwigOfTrickery(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_DEVIANTWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K) {
                worldIn.func_184133_a((EntityPlayer) null, new BlockPos(playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v), SoundInit.ITEM_SPACESLINGER, SoundCategory.MASTER, 1.0f, 1.0f);
                EntityDeviantCreeper creep = new EntityDeviantCreeper(worldIn);
                creep.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u + 0.2d, playerIn.field_70161_v);
                creep.setMutation(1);
                worldIn.func_72838_d(creep);
                EntityDeviantSkeleton skele = new EntityDeviantSkeleton(worldIn);
                skele.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u + 0.2d, playerIn.field_70161_v);
                skele.setMutation(1);
                worldIn.func_72838_d(skele);
                EntityDeviantSpider spid = new EntityDeviantSpider(worldIn);
                spid.func_70107_b(playerIn.field_70165_t, playerIn.field_70163_u + 0.2d, playerIn.field_70161_v);
                spid.setMutation(1);
                worldIn.func_72838_d(spid);
                boolean canStop = false;
                BlockPos telePos = new BlockPos(playerIn.field_70165_t, playerIn.field_70163_u + 2.0d, playerIn.field_70161_v);
                int ypos = 3;
                while (!canStop) {
                    int multX = worldIn.field_73012_v.nextBoolean() ? 1 : -1;
                    int multZ = worldIn.field_73012_v.nextBoolean() ? 1 : -1;
                    BlockPos test = telePos.func_177982_a((15 + worldIn.field_73012_v.nextInt(15)) * multX, ypos, (15 + worldIn.field_73012_v.nextInt(15)) * multZ);
                    if (worldIn.func_175623_d(test)) {
                        canStop = true;
                        worldIn.func_184133_a((EntityPlayer) null, test, SoundEvents.field_187534_aX, SoundCategory.MASTER, 2.0f, 1.0f);
                        playerIn.func_70634_a(test.func_177958_n(), test.func_177956_o(), test.func_177952_p());
                    } else {
                        ypos++;
                    }
                }
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 8000;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Teleports you out of dire situations.");
        tooltip.add(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Dark_Red) + "Leaves behind super-mutated monsters.");
    }
}
