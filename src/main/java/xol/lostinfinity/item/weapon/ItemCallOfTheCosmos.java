package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.projectile.entity.EntityAsteroid;
import xol.lostinfinity.util.data.CustomRayTraceResult;
public class ItemCallOfTheCosmos extends ItemCooldown implements ICustomRaytrace, ISwitchModels {
    private static double height = 150.0d;
    public ItemCallOfTheCosmos(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setModelSwitch("cosmostype", this, 2);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult result;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (playerIn.func_70093_af()) {
            if (stack.func_77942_o()) {
                stack.func_77978_p().func_74768_a("cosmostype_data", 0);
                if (stack.func_77978_p().func_74764_b("startX") && stack.func_77978_p().func_74764_b("startY") && stack.func_77978_p().func_74764_b("startZ")) {
                    stack.func_77978_p().func_82580_o("startX");
                    stack.func_77978_p().func_82580_o("startY");
                    stack.func_77978_p().func_82580_o("startZ");
                    if (!worldIn.field_72995_K) {
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "Removed target position."));
                        worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.FLUX_MARK, SoundCategory.PLAYERS, 0.7f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
                    }
                }
            }
        } else if (!showDurabilityBar(stack)) {
            int type = stack.func_77978_p().func_74762_e("cosmostype_data");
            if (type == 0) {
                if (!worldIn.field_72995_K && (result = entityTrace(worldIn, playerIn, 40, EntityLivingBase.class)) != null) {
                    BlockPos pos = result.getResultPos();
                    stack.func_77978_p().func_74768_a("startX", pos.func_177958_n());
                    stack.func_77978_p().func_74768_a("startY", pos.func_177956_o());
                    stack.func_77978_p().func_74768_a("startZ", pos.func_177952_p());
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Green + "Marked target position at " + pos.func_177958_n() + "," + pos.func_177956_o() + "," + pos.func_177952_p()));
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.FLUX_MARK, SoundCategory.PLAYERS, 0.7f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
                }
                stack.func_77978_p().func_74768_a("cosmostype_data", 1);
            } else {
                if (stack.func_77978_p().func_74764_b("startX") && stack.func_77978_p().func_74764_b("startY") && stack.func_77978_p().func_74764_b("startZ")) {
                    if (!worldIn.field_72995_K) {
                        double startX = stack.func_77978_p().func_74762_e("startX");
                        double startY = stack.func_77978_p().func_74762_e("startY");
                        double startZ = stack.func_77978_p().func_74762_e("startZ");
                        double randX = (((double) playerIn.func_180425_c().func_177958_n()) + (worldIn.field_73012_v.nextDouble() * 32.0d)) - 16.0d;
                        double randZ = (((double) playerIn.func_180425_c().func_177952_p()) + (worldIn.field_73012_v.nextDouble() * 32.0d)) - 16.0d;
                        Vec3d spawn = new Vec3d(randX, 300.0d, randZ);
                        Vec3d dir = spawn.func_178786_a(startX, startY, startZ);
                        Vec3d asteroidDir = dir.func_72432_b();
                        EntityAsteroid asteroid = new EntityAsteroid(worldIn);
                        asteroid.setThrower(playerIn);
                        asteroid.func_70107_b(spawn.field_72450_a, spawn.field_72448_b, spawn.field_72449_c);
                        asteroid.func_70186_c(-asteroidDir.field_72450_a, -asteroidDir.field_72448_b, -asteroidDir.field_72449_c, 4.4f, 0.0f);
                        worldIn.func_72838_d(asteroid);
                        worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.ASTEROID_SUMMON, SoundCategory.PLAYERS, 0.7f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
                    }
                    stack.func_77978_p().func_82580_o("startX");
                    stack.func_77978_p().func_82580_o("startY");
                    stack.func_77978_p().func_82580_o("startZ");
                    playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
                } else if (!worldIn.field_72995_K) {
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "No target Position Set."));
                }
                stack.func_77978_p().func_74768_a("cosmostype_data", 0);
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 8000;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Marks a location for a giant asteroid.");
        tooltip.add(TextFmt.Gold + "Activating again summons a giant asteroid to collide into the ground.");
        tooltip.add(TextFmt.Red + "Asteroid deals 125% Health True Damage to very close enemies.");
        tooltip.add(TextFmt.Red + "Asteroid deals 75% Health True Damage to close enemies.");
        tooltip.add(TextFmt.Italic + "Small meteors split off the asteroid, dealing 75% Maximum Health Damage");
        tooltip.add(TextFmt.Underline + "Asteroid takes 8 additional lives away from multi-life creatures.");
    }
}
