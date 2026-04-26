package xol.lostinfinity.item.weapon;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemChanneling;
import xol.lostinfinity.mob.entity.misc.EntityRisingPhantom;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemRiseOfPhantoms extends ItemChanneling {
    private static final float MIN_VELOCITY = 0.1f;
    private static final float MAX_VELOCITY = 0.5f;
    private static final float MIN_DAMAGE = 0.0f;
    private static final float MAX_DAMAGE = 10.0f;
    public ItemRiseOfPhantoms(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    @Override // xol.lostinfinity.item.basics.ItemChanneling
    public void chargeTick(World worldIn, EntityPlayer player, EnumHand hand, ItemStack stack, int chargeTime) {
        if (worldIn.field_72995_K) {
        }
        float chance = spawnChance(chargeTime);
        if (chargeTime % 2 == 0 && worldIn.field_73012_v.nextFloat() <= chance) {
            int spawnX = (-10) + worldIn.field_73012_v.nextInt(21) + ((int) player.field_70165_t);
            int spawnZ = (-10) + worldIn.field_73012_v.nextInt(21) + ((int) player.field_70161_v);
            double spawnY = (player.field_70122_E ? worldIn.func_189649_b(spawnX, spawnZ) : player.field_70163_u) - 1.0d;
            EntityRisingPhantom phantom = new EntityRisingPhantom(worldIn, player);
            Vec3d loc = new Vec3d(((double) spawnX) + 0.5d, spawnY, ((double) spawnZ) + 0.5d);
            phantom.func_70080_a(loc.field_72450_a, loc.field_72448_b - ((double) phantom.field_70131_O), loc.field_72449_c, worldIn.field_73012_v.nextFloat() * 360.0f, MIN_DAMAGE);
            phantom.setVelocity((float) MathHelper.func_151238_b(0.10000000149011612d, MathHelper.func_151238_b(0.10000000149011612d, 0.5d, chance), worldIn.field_73012_v.nextFloat()));
            phantom.setLivesTaken(MathHelper.func_76128_c(MathHelper.func_151238_b(0.0d, 10.0d, chance)));
            worldIn.func_72838_d(phantom);
            CustomParticleConfig config = new CustomParticleConfig();
            config.createInstance().setParticle(worldIn.field_73012_v.nextBoolean() ? ParticleInit.NIGHTMARE_MAGIC : ParticleInit.GLOOM_SPELL).setCount(3).setSpread(4.0d, 2.0d, 4.0d).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(worldIn, config, loc.field_72450_a, loc.field_72448_b + 1.0d, loc.field_72449_c);
            switch (worldIn.field_73012_v.nextInt(5)) {
                case 0:
                    worldIn.func_184133_a((EntityPlayer) null, phantom.func_180425_c(), SoundInit.WHISPER_1, SoundCategory.PLAYERS, 0.25f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
                    break;
                case 1:
                    worldIn.func_184133_a((EntityPlayer) null, phantom.func_180425_c(), SoundInit.WHISPER_2, SoundCategory.PLAYERS, 0.25f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
                    break;
                case 2:
                    worldIn.func_184133_a((EntityPlayer) null, phantom.func_180425_c(), SoundInit.WHISPER_3, SoundCategory.PLAYERS, 0.25f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
                    break;
                case 3:
                    worldIn.func_184133_a((EntityPlayer) null, phantom.func_180425_c(), SoundInit.WHISPER_4, SoundCategory.PLAYERS, 0.25f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
                    break;
                case TileEntityFusionTable.BOARD_ROWS :
                    worldIn.func_184133_a((EntityPlayer) null, phantom.func_180425_c(), SoundInit.WHISPER_5, SoundCategory.PLAYERS, 0.25f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
                    break;
            }
        }
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Raises phantoms around you.");
        tooltip.add(TextFmt.Gold + "The longer you channel, the more phantoms that spawn.");
        tooltip.add(TextFmt.Red + "Phantoms deal 150% Health True Damage.");
        tooltip.add(TextFmt.Italic + "Phantoms take an extra lives off of multi-life creatures.");
        tooltip.add(TextFmt.Red + "The longer you channel, the more lives phantoms take (max 10).");
        tooltip.add(TextFmt.Dark_Aqua + "Darkborn");
    }
    private float spawnChance(int chargeTime) {
        return Math.min(1.0f, chargeTime / 200.0f);
    }
}
