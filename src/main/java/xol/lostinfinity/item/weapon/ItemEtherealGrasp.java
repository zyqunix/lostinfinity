package xol.lostinfinity.item.weapon;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemChanneling;
import xol.lostinfinity.item.classify.IMovingSoundSource;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.data.RayTraceBuilder;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemEtherealGrasp.class */
public class ItemEtherealGrasp extends ItemChanneling implements IMaxAttack, IMovingSoundSource {
    private static final float PULL_STRENGTH = 0.5f;

    public ItemEtherealGrasp(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    @Override // xol.lostinfinity.item.basics.ItemChanneling
    public void chargeTick(World worldIn, EntityPlayer player, EnumHand hand, ItemStack stack, int chargeTime) {
        if (worldIn.field_72995_K) {
            return;
        }
        if (chargeTime % 30 == 0) {
            worldIn.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.GENERIC_WEAPON_12, SoundCategory.PLAYERS, 0.7f, PULL_STRENGTH);
        }
        CustomRayTraceResult result = RayTraceBuilder.entity(Entity.class, 75).raySize(1.5f).trace(player, true);
        if (result == null) {
            return;
        }
        Iterator<Entity> it = result.getResultEntities().iterator();
        while (it.hasNext()) {
            EntityLivingBase entityLivingBase = (Entity) it.next();
            if (chargeTime % 10 == 0 && (entityLivingBase instanceof EntityLivingBase)) {
                Math.max(1, 8 - (MathHelper.func_76141_d(chargeTime) / 60));
                IMaxAttack.dealMaxHealth((Entity) player, entityLivingBase, 6, (List<String>) Arrays.asList("Aquatic"));
            }
            float holdDistance = ((Entity) entityLivingBase).field_70130_N * ((Entity) entityLivingBase).field_70130_N * 2.25f;
            Vec3d entityMiddle = entityLivingBase.func_174791_d().func_72441_c(0.0d, ((Entity) entityLivingBase).field_70131_O / 2.0f, 0.0d);
            Vec3d playerEye = player.func_174824_e(1.0f);
            Vec3d dir = entityMiddle.func_178788_d(playerEye);
            if (dir.func_189985_c() > holdDistance) {
                Vec3d dir2 = LMath.fastNormalize(dir).func_186678_a(0.5d);
                ((Entity) entityLivingBase).field_70159_w = -dir2.field_72450_a;
                ((Entity) entityLivingBase).field_70181_x = -dir2.field_72448_b;
                ((Entity) entityLivingBase).field_70179_y = -dir2.field_72449_c;
            } else {
                ((Entity) entityLivingBase).field_70159_w = 0.0d;
                ((Entity) entityLivingBase).field_70181_x = 0.0d;
                ((Entity) entityLivingBase).field_70179_y = 0.0d;
            }
            ((Entity) entityLivingBase).field_70133_I = true;
            if (chargeTime % 5 == 0) {
                CustomParticleConfig config = new CustomParticleConfig();
                config.createInstance().setParticle(ParticleInit.GRASP).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(worldIn, config, entityMiddle);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "Can be channelled to pull all entities where you are looking.");
        tooltip.add(TextFmt.Gold + "Channeling can last forever.");
        tooltip.add(TextFmt.Red + "Deals 12.5%-100% Max Health Damage twice per second.");
        tooltip.add(TextFmt.Italic + "Damage ramps up the longer you channel.");
        tooltip.add(TextFmt.Aqua + "Aquatic");
    }
}
