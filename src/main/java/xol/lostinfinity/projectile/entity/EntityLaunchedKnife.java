package xol.lostinfinity.projectile.entity;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.util.PotionBasic;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityLaunchedKnife extends EntityBaseThrowable {
    public EntityLaunchedKnife(World par1World) {
        super(par1World);
        func_70105_a(0.75f, 0.75f);
    }
    public EntityLaunchedKnife(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        func_70105_a(0.75f, 0.75f);
    }
    public EntityLaunchedKnife(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        func_70105_a(0.75f, 0.75f);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && (result.field_72308_g instanceof EntityLivingBase) && func_85052_h() != null) {
                EntityLivingBase hit = result.field_72308_g;
                if (!hit.equals(func_85052_h()) && IMaxAttack.dealMaxHealth((Entity) this, result.field_72308_g, 5, 2.0f).didSuccessfulHit()) {
                    List<Potion> potionList = (List) hit.func_70651_bq().stream().map((v0) -> {
                        return v0.func_188419_a();
                    }).collect(Collectors.toList());
                    for (Potion potion : potionList) {
                        boolean flag = false;
                        if (potion instanceof PotionBasic) {
                            PotionBasic lost_potion = (PotionBasic) potion;
                            if (lost_potion.negativeLostEffect()) {
                                flag = true;
                            }
                        } else if (potion.func_76398_f()) {
                            flag = true;
                        }
                        if (flag) {
                            int amplifier = Math.min(9, hit.func_70660_b(potion).func_76458_c() + 1);
                            int duration = hit.func_70660_b(potion).func_76459_b();
                            hit.func_70690_d(new PotionEffect(potion, duration + 100, amplifier));
                        }
                    }
                }
            }
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.0f;
    }
}
