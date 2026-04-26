package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.weapon.droid.ItemDroidRelocatorStorage;
import xol.lostinfinity.mob.entity.misc.EntityDroid;
public class EntityDroidSucker extends EntityBaseThrowable {
    public EntityDroidSucker(World par1World) {
        super(par1World);
    }
    public EntityDroidSucker(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            int count = 0;
            for (EntityDroid droid : this.field_70170_p.func_72872_a(EntityDroid.class, func_174813_aQ().func_72314_b(30.0d, 30.0d, 30.0d))) {
                droid.func_70106_y();
                count++;
            }
            if (func_85052_h() != null) {
                ItemStack held = func_85052_h().func_184586_b(EnumHand.MAIN_HAND);
                if (!held.func_190926_b() && (held.func_77973_b() instanceof ItemDroidRelocatorStorage) && held.func_77942_o()) {
                    int current = held.func_77978_p().func_74762_e("Stored");
                    held.func_77978_p().func_74768_a("Stored", current + count);
                }
            }
            func_70106_y();
        }
        func_184185_a(SoundInit.LARGE_TELEPORT, 1.0f, 1.0f);
    }
    protected float func_70185_h() {
        return 0.05f;
    }
}
