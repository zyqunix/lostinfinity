package xol.lostinfinity.projectile.entity;
import java.util.ArrayList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.tool.ItemDeviantRelocator;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.mob.entity.base.EntityFloatingDeviant;
public class EntityDeviantSucker extends EntityBaseThrowable {
    public EntityDeviantSucker(World par1World) {
        super(par1World);
    }
    public EntityDeviantSucker(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            ArrayList arrayList = new ArrayList();
            for (EntityLiving found_creature : this.field_70170_p.func_72872_a(EntityLiving.class, func_174813_aQ().func_72314_b(20.0d, 20.0d, 20.0d))) {
                if ((found_creature instanceof EntityDeviantMob) || (found_creature instanceof EntityFloatingDeviant)) {
                    arrayList.add(found_creature.getClass());
                    found_creature.func_70106_y();
                }
            }
            if (func_85052_h() != null) {
                ItemStack held = func_85052_h().func_184586_b(EnumHand.MAIN_HAND);
                if (!held.func_190926_b() && held.func_77973_b().equals(ItemInit.deviantRelocator)) {
                    ItemDeviantRelocator heldRel = (ItemDeviantRelocator) held.func_77973_b();
                    heldRel.passDeviantList(arrayList);
                }
            }
            func_70106_y();
        }
        func_184185_a(SoundInit.SCANNER, 1.0f, 1.0f);
    }
    protected float func_70185_h() {
        return 0.05f;
    }
}
