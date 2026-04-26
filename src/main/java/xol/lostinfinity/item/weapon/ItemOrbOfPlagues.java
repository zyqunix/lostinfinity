package xol.lostinfinity.item.weapon;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.util.PotionBasic;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemOrbOfPlagues extends ItemCooldown {
    public ItemOrbOfPlagues(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K) {
                for (EntityPlayerMP playerMP : worldIn.func_73046_m().func_184103_al().func_181057_v()) {
                    if (!playerMP.func_110124_au().equals(playerIn.func_110124_au())) {
                        int removed_potions = 0;
                        List<Potion> potionList = (List) playerMP.func_70651_bq().stream().map((v0) -> {
                            return v0.func_188419_a();
                        }).collect(Collectors.toList());
                        for (Potion potion : potionList) {
                            if (potion instanceof PotionBasic) {
                                PotionBasic lost_potion = (PotionBasic) potion;
                                if (!lost_potion.negativeLostEffect()) {
                                    playerMP.func_184589_d(potion);
                                    removed_potions++;
                                }
                            } else if (!potion.func_76398_f()) {
                                playerMP.func_184589_d(potion);
                                removed_potions++;
                            }
                        }
                        playerMP.func_70690_d(new PotionEffect(PotionInit.PLAGUE, 200, removed_potions));
                        playerMP.func_145747_a(new TextComponentString(TextFmt.Dark_Green + "You begin to feel very sick."));
                        playerMP.field_70170_p.func_184133_a((EntityPlayer) null, playerMP.func_180425_c(), SoundInit.IMPENDING_DOOM, SoundCategory.PLAYERS, 1.5f, 1.0f);
                    }
                }
                float f = 0.0f;
                while (true) {
                    float angle = f;
                    if (angle > 6.283185307179586d) {
                        break;
                    }
                    double velocity_x = 3.0d * Math.cos(angle);
                    double velocity_z = 3.0d * Math.sin(angle);
                    CustomParticleConfig config1 = new CustomParticleConfig();
                    config1.createInstance().setParticle(ParticleInit.DARK_MAGIC).setIgnoreRange(true);
                    CustomParticleConfig config2 = new CustomParticleConfig();
                    config2.createInstance().setParticle(ParticleInit.PLAGUE).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(worldIn, config1, playerIn.field_70165_t + velocity_x, playerIn.field_70163_u + 0.5d, playerIn.field_70161_v + velocity_z);
                    IParticleSpawner.spawnParticle(worldIn, config2, playerIn.field_70165_t + velocity_x, playerIn.field_70163_u + 0.5d, playerIn.field_70161_v + velocity_z);
                    f = (float) (((double) angle) + 0.3141592653589793d);
                }
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.IMPENDING_DOOM, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 30000;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Plagues EVERY other player on the server.");
        tooltip.add(TextFmt.Green + "Plague replaces all positive potion effects.");
        tooltip.add(TextFmt.Red + "Plague severity is increased by number of effects replaced.");
    }
}
