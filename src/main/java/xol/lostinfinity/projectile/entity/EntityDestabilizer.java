package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantAmalgam;
import xol.lostinfinity.mob.entity.deviant.EntityLostDeviant;
public class EntityDestabilizer extends EntityBaseThrowable {
    private int type;
    public EntityDestabilizer(World par1World) {
        super(par1World);
        this.type = 0;
    }
    public EntityDestabilizer(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.type = 0;
    }
    public void setType(int t) {
        this.type = t;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            EntityPlayer attack = null;
            if (result.field_72308_g != null && (result.field_72308_g instanceof EntityPlayer)) {
                attack = (EntityPlayer) result.field_72308_g;
            }
            if (this.type == 0) {
                impactType0(attack);
            } else if (this.type == 1) {
                impactType1(attack);
            }
            func_70106_y();
        }
        func_184185_a(SoundInit.DEVIATION, 3.0f, 1.0f);
    }
    private void impactType0(EntityPlayer targeted) {
        for (EntityMob creature : this.field_70170_p.func_72872_a(EntityMob.class, func_174813_aQ().func_72314_b(10.0d, 10.0d, 10.0d))) {
            creature.func_70106_y();
            summonLostDeviant(targeted, creature);
        }
    }
    private void impactType1(EntityPlayer targeted) {
        int count = 0;
        for (EntityLiving entityLiving : this.field_70170_p.func_72872_a(EntityLiving.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
            count++;
        }
        for (EntityLiving creature : this.field_70170_p.func_72872_a(EntityLiving.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
            creature.func_70106_y();
            if (count >= 4) {
                if (count % 4 == 0) {
                    summonAmalgam(targeted);
                }
            } else {
                summonLostDeviant(targeted, creature);
            }
            count--;
        }
    }
    private void summonLostDeviant(EntityPlayer target, EntityLiving replace_creature) {
        EntityLostDeviant dev = new EntityLostDeviant(this.field_70170_p);
        dev.func_70107_b(replace_creature.field_70165_t, replace_creature.field_70163_u + 0.2d, replace_creature.field_70161_v);
        if (target != null) {
            dev.func_70624_b(target);
        }
        this.field_70170_p.func_72838_d(dev);
    }
    private void summonAmalgam(EntityPlayer target) {
        EntityDeviantAmalgam dev = new EntityDeviantAmalgam(this.field_70170_p);
        dev.func_70107_b(this.field_70165_t, this.field_70163_u + 0.2d, this.field_70161_v);
        dev.setMutation(1);
        if (target != null) {
            dev.func_70624_b(target);
        }
        this.field_70170_p.func_72838_d(dev);
    }
    protected float func_70185_h() {
        return 0.05f;
    }
}
