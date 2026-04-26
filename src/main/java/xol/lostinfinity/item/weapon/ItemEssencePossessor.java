package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemBasic;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhu;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuPart;
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
public class ItemEssencePossessor extends ItemBasic implements ICustomRaytrace, IMaxAttack {
    public ItemEssencePossessor(String regName) {
        super(regName, TabsInit.TAB_AUXWEP);
        func_77625_d(1);
    }
    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (!attacker.field_70170_p.field_72995_K) {
            CustomDamageResult dr = IMaxAttack.dealTrueDamage(attacker, target, target.func_110138_aP() * 2.0f);
            if (dr.wasTargetKilled()) {
                killBound(target, stack, attacker);
            }
            attacker.field_70170_p.func_184133_a((EntityPlayer) null, attacker.func_180425_c(), SoundInit.SWING_HIT, SoundCategory.PLAYERS, 1.5f, 0.6f + (attacker.field_70170_p.field_73012_v.nextFloat() * 0.4f));
            return true;
        }
        return true;
    }
    public void killBound(EntityLivingBase killed, ItemStack stack, EntityLivingBase attacker) {
        int killedId = killed.func_145782_y();
        NBTTagCompound compound = stack.func_77978_p();
        boolean killedBound = false;
        int curEssence = compound.func_74762_e("essence");
        if (curEssence >= 10) {
            return;
        }
        if (killedId == compound.func_74762_e("id1")) {
            if (System.currentTimeMillis() < compound.func_74763_f("time1") + 30000) {
                killedBound = true;
                compound.func_74768_a("id1", -1);
            }
        } else if (killedId == compound.func_74762_e("id2")) {
            if (System.currentTimeMillis() < compound.func_74763_f("time2") + 30000) {
                killedBound = true;
                compound.func_74768_a("id2", -1);
            }
        } else if (killedId == compound.func_74762_e("id3") && System.currentTimeMillis() < compound.func_74763_f("time3") + 30000) {
            killedBound = true;
            compound.func_74768_a("id3", -1);
        }
        if (killedBound) {
            attacker.func_145747_a(new TextComponentString(TextFmt.Green + "Essence charge stored."));
            compound.func_74768_a("essence", curEssence + 1);
        }
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!worldIn.field_72995_K) {
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
            }
            NBTTagCompound compound = stack.func_77978_p();
            int id1 = -1;
            int id2 = -1;
            int id3 = -1;
            if (compound.func_74764_b("id1")) {
                id1 = compound.func_74762_e("id1");
            }
            if (compound.func_74764_b("id2")) {
                id2 = compound.func_74762_e("id2");
            }
            if (compound.func_74764_b("id3")) {
                id3 = compound.func_74762_e("id3");
            }
            CustomRayTraceResult result = entityTrace(worldIn, playerIn, 30, EntityLivingBase.class);
            Entity entity = null;
            if (result != null) {
                entity = result.getResultEntity();
            }
            if (entity != null) {
                EntityCthulhu boss = null;
                if (entity instanceof EntityCthulhuPart) {
                    EntityCthulhuPart part = (EntityCthulhuPart) entity;
                    boss = part.getOwner();
                }
                if (entity instanceof EntityCthulhu) {
                    boss = (EntityCthulhu) entity;
                }
                if (compound.func_74762_e("essence") >= 10 && boss != null) {
                    int lives = boss.remainingLives();
                    if (lives <= 15) {
                        boss.essenceKill();
                        compound.func_74768_a("essence", 0);
                        return super.func_77659_a(worldIn, playerIn, handIn);
                    }
                }
                int id = entity.func_145782_y();
                if (id == id1) {
                    compound.func_74768_a("id1", -1);
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Green + "Unbound target."));
                } else if (id == id2) {
                    compound.func_74768_a("id2", -1);
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Green + "Unbound target."));
                } else if (id == id3) {
                    compound.func_74768_a("id3", -1);
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Green + "Unbound target."));
                } else if (id1 == -1) {
                    compound.func_74768_a("id1", id);
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Green + "Bound target."));
                    compound.func_74772_a("time1", System.currentTimeMillis());
                } else if (id2 == -1) {
                    compound.func_74768_a("id2", id);
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Green + "Bound target."));
                    compound.func_74772_a("time2", System.currentTimeMillis());
                } else if (id3 == -1) {
                    compound.func_74768_a("id3", id);
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Green + "Bound target."));
                    compound.func_74772_a("time3", System.currentTimeMillis());
                }
                playerIn.func_184611_a(handIn, stack);
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        tooltip.add(TextFmt.Aqua + "Binds up to 3 entities.");
        tooltip.add(TextFmt.Green + "You can not be damaged by bound entities. The bind lasts 30 seconds.");
        tooltip.add(TextFmt.Red + "Killing a bound unit gives you an essence charge, up to 10.");
        tooltip.add(TextFmt.Dark_Purple + String.format("%d Essence stored", Integer.valueOf(stack.func_77978_p().func_74762_e("essence"))));
    }
}
