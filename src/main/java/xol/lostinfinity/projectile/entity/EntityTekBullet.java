package xol.lostinfinity.projectile.entity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityTekBullet.class */
public class EntityTekBullet extends EntityBaseThrowable {
    public EntityTekBullet(World par1World) {
        super(par1World);
        func_70105_a(0.5f, 0.5f);
    }

    public EntityTekBullet(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        func_70105_a(0.5f, 0.5f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && result.field_72308_g != func_85052_h() && (result.field_72308_g instanceof EntityLivingBase)) {
                EntityLivingBase target = result.field_72308_g;
                List<Potion> potionList = (List) target.func_70651_bq().stream().map((v0) -> {
                    return v0.func_188419_a();
                }).collect(Collectors.toList());
                if (potionList.size() > 0) {
                    Collections.shuffle(potionList);
                    target.func_184589_d(potionList.get(0));
                }
                IMaxAttack.dealMaxHealth(this, result.field_72308_g, 5);
            }
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
