package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerTrampolineDodgeball;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityChampionDodgeball.class */
public class EntityChampionDodgeball extends EntityBaseThrowable {
    private static final DataParameter<Integer> TYPE = EntityDataManager.func_187226_a(EntityChampionDodgeball.class, DataSerializers.field_187192_b);

    public EntityChampionDodgeball(World par1World) {
        super(par1World);
        func_70105_a(0.75f, 0.75f);
    }

    public EntityChampionDodgeball(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        func_70105_a(0.75f, 0.75f);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(TYPE, 0);
    }

    public EntityChampionDodgeball(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        func_70105_a(0.75f, 0.75f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && (result.field_72308_g instanceof EntityPlayer)) {
                EntityPlayer playerEntity = result.field_72308_g;
                for (EntityControllerTrampolineDodgeball controller : this.field_70170_p.func_72872_a(EntityControllerTrampolineDodgeball.class, ContestCoordinates.dodgeballArenaAABB())) {
                    controller.eliminatePlayerFromRound(playerEntity);
                }
            }
            if (getType() == 0) {
                EntityItem item = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, new ItemStack(ItemInit.championDodgeball, 1));
                item.field_70159_w = 0.0d;
                item.field_70181_x = 0.0d;
                item.field_70179_y = 0.0d;
                this.field_70170_p.func_72838_d(item);
            }
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.07f;
    }

    public void setType(int type) {
        this.field_70180_af.func_187227_b(TYPE, Integer.valueOf(type));
    }

    public int getType() {
        return ((Integer) this.field_70180_af.func_187225_a(TYPE)).intValue();
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K && getType() != 0) {
            this.field_70170_p.func_175688_a(getType() == 1 ? ParticleInit.GENERIC_DOT_GREEN : ParticleInit.FLAME_SMALL, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
        }
    }
}
