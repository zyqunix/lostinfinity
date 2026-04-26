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
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.mob.entity.misc.EntityNuclearExplosion;
import xol.lostinfinity.util.data.CustomRayTraceResult;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemNuclearReceiver.class */
public class ItemNuclearReceiver extends ItemCooldown implements IModeSelect, ICustomRaytrace {
    private static final int MAX_BOMB = 3;

    public ItemNuclearReceiver(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult trace_result;
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            ItemStack stack = playerIn.func_184586_b(handIn);
            if (!worldIn.field_72995_K && (trace_result = simpleBlockTrace(worldIn, playerIn, 60)) != null) {
                if (!stack.func_77978_p().func_74764_b("BombY0")) {
                    clearBombs(stack);
                }
                int foundEmpty = -1;
                int i = 0;
                while (true) {
                    if (i >= MAX_BOMB) {
                        break;
                    }
                    int yValue = stack.func_77978_p().func_74762_e("BombY" + i);
                    if (yValue == -10) {
                        foundEmpty = i;
                        break;
                    }
                    int xValue = stack.func_77978_p().func_74762_e("BombX" + i);
                    int zValue = stack.func_77978_p().func_74762_e("BombZ" + i);
                    BlockPos pos = new BlockPos(xValue, yValue, zValue);
                    if (worldIn.func_180495_p(pos).func_177230_c() == BlockInit.nuclearBomb) {
                        i++;
                    } else {
                        foundEmpty = i;
                        break;
                    }
                }
                if (foundEmpty == -1) {
                    BlockPos firstBomb = new BlockPos(stack.func_77978_p().func_74762_e("BombX0"), stack.func_77978_p().func_74762_e("BombY0"), stack.func_77978_p().func_74762_e("BombZ0"));
                    worldIn.func_175698_g(firstBomb);
                    foundEmpty = 0;
                }
                BlockPos resultPos = trace_result.getResultPos();
                stack.func_77978_p().func_74768_a("BombX" + foundEmpty, resultPos.func_177958_n());
                stack.func_77978_p().func_74768_a("BombY" + foundEmpty, resultPos.func_177956_o());
                stack.func_77978_p().func_74768_a("BombZ" + foundEmpty, resultPos.func_177952_p());
                worldIn.func_175656_a(resultPos, BlockInit.nuclearBomb.func_176223_P());
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187772_dn, SoundCategory.MASTER, 1.5f, 0.9f + (worldIn.field_73012_v.nextFloat() * 0.2f));
            }
            stack.func_77978_p().func_74768_a("ComplexCooldown", 150);
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    private boolean areNoBombs(ItemStack stack) {
        if (stack.func_77978_p().func_74764_b("BombY0")) {
            boolean foundBomb = false;
            for (int i = 0; i < MAX_BOMB; i++) {
                int bombY = stack.func_77978_p().func_74762_e("BombY" + i);
                if (bombY != -10) {
                    foundBomb = true;
                }
            }
            return !foundBomb;
        }
        return true;
    }

    private void clearBombs(ItemStack stack) {
        for (int i = 0; i < MAX_BOMB; i++) {
            stack.func_77978_p().func_74768_a("BombX" + i, -10);
            stack.func_77978_p().func_74768_a("BombY" + i, -10);
            stack.func_77978_p().func_74768_a("BombZ" + i, -10);
        }
    }

    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!showDurabilityBar(stack)) {
            World worldIn = player.field_70170_p;
            if (!worldIn.field_72995_K) {
                if (areNoBombs(stack)) {
                    player.func_145747_a(new TextComponentString(TextFmt.Red + "No bombs are placed."));
                } else {
                    for (int i = 0; i < MAX_BOMB; i++) {
                        int yValue = stack.func_77978_p().func_74762_e("BombY" + i);
                        if (yValue != -10) {
                            int xValue = stack.func_77978_p().func_74762_e("BombX" + i);
                            int zValue = stack.func_77978_p().func_74762_e("BombZ" + i);
                            BlockPos pos = new BlockPos(xValue, yValue, zValue);
                            if (worldIn.func_180495_p(pos).func_177230_c() == BlockInit.nuclearBomb) {
                                EntityNuclearExplosion bomb = new EntityNuclearExplosion(worldIn);
                                bomb.setOwner(player.func_110124_au());
                                bomb.func_70107_b(((double) pos.func_177958_n()) + 0.5d, ((double) pos.func_177956_o()) + 0.5d, ((double) pos.func_177952_p()) + 0.5d);
                                worldIn.func_72838_d(bomb);
                                worldIn.func_175698_g(pos);
                            }
                        }
                    }
                    clearBombs(stack);
                    worldIn.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.GENERIC_WEAPON_3, SoundCategory.MASTER, 1.5f, 0.9f + (worldIn.field_73012_v.nextFloat() * 0.2f));
                }
            }
            stack.func_77978_p().func_74768_a("ComplexCooldown", 14000);
            startCooldown(stack);
        }
    }

    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Yellow + "Can place up to 3 Nuclear Bombs which can be detonated.");
        tooltip.add(TextFmt.Red + "Deals 200% Health True Damage To Nearby Enemies");
        tooltip.add(TextFmt.Red + "Deals 400% Max Health Damage In a Giant Radius");
        tooltip.add(TextFmt.Gold + "Nearby Multi-Life Creatures Lose 8 lives");
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected boolean hasSimpleCooldown() {
        return false;
    }
}
