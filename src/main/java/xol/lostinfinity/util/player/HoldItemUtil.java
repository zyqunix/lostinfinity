package xol.lostinfinity.util.player;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.projectile.entity.EntityCelestialFire;
public class HoldItemUtil {
    public static void axiomProtection(EntityPlayer playerIn) {
        World world = playerIn.field_70170_p;
        for (Entity proj : world.func_72872_a(Entity.class, playerIn.func_174813_aQ().func_72314_b(10.0d, 5.0d, 10.0d))) {
            if ((proj instanceof EntityThrowable) || (proj instanceof EntityArrow) || (proj instanceof EntityFireball) || (proj instanceof EntityShulkerBullet)) {
                if (playerIn.func_184614_ca().func_77973_b().equals(ItemInit.axiomCelestarium)) {
                    ItemStack stack = playerIn.func_184614_ca();
                    if (!stack.func_77942_o()) {
                        stack.func_77982_d(new NBTTagCompound());
                        stack.func_77978_p().func_74768_a("Charge", 0);
                    }
                    if (!(proj instanceof EntityCelestialFire)) {
                        stack.func_77978_p().func_74768_a("Charge", Math.min(stack.func_77978_p().func_74762_e("Charge") + 1, 25));
                    }
                }
                vaporizeProjectile(world, proj);
            }
        }
    }
    private static void vaporizeProjectile(World world, Entity projectile) {
        world.func_184133_a((EntityPlayer) null, new BlockPos(projectile.field_70165_t, projectile.field_70163_u, projectile.field_70161_v), SoundInit.ITEM_AXIOMAVORUM, SoundCategory.MASTER, 2.0f, 1.0f);
        if (!world.field_72995_K) {
            projectile.func_70106_y();
            return;
        }
        for (int i = 0; i < 2; i++) {
            world.func_175688_a(EnumParticleTypes.LAVA, projectile.field_70165_t, projectile.field_70163_u, projectile.field_70161_v, (world.field_73012_v.nextDouble() - 0.5d) * 2.0d, -world.field_73012_v.nextDouble(), (world.field_73012_v.nextDouble() - 0.5d) * 2.0d, new int[0]);
        }
    }
}
