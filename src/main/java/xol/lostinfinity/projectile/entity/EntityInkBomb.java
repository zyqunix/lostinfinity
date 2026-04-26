package xol.lostinfinity.projectile.entity;

import java.util.Iterator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.block.misc.BlockInkable;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerInkBattle;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityInkBomb.class */
public class EntityInkBomb extends EntityBaseThrowable {
    public EntityInkBomb(World par1World) {
        super(par1World);
        func_70105_a(0.75f, 0.75f);
    }

    public EntityInkBomb(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        func_70105_a(0.75f, 0.75f);
    }

    public EntityInkBomb(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        func_70105_a(0.75f, 0.75f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72313_a == RayTraceResult.Type.BLOCK) {
                if (this.field_70192_c != null) {
                    BlockPos pos = result.func_178782_a();
                    if (this.field_70170_p.func_180495_p(pos).func_177230_c() instanceof BlockInkable) {
                        Iterator it = this.field_70170_p.func_72872_a(EntityControllerInkBattle.class, ContestCoordinates.inkBattleControllerAABB()).iterator();
                        if (it.hasNext()) {
                            EntityControllerInkBattle controller = (EntityControllerInkBattle) it.next();
                            for (BlockPos nearPos : BlockPos.func_177980_a(pos.func_177982_a(-6, -6, -6), pos.func_177982_a(6, 6, 6))) {
                                controller.inkBlock(this.field_70192_c.func_110124_au(), nearPos);
                            }
                        }
                    }
                    this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.MAGIC_WEAPON_7, SoundCategory.PLAYERS, 0.7f, 0.7f + (this.field_70170_p.field_73012_v.nextFloat() * 0.6f));
                }
            } else if (result.field_72308_g != null && (result.field_72308_g instanceof EntityPlayer)) {
                Iterator it2 = this.field_70170_p.func_72872_a(EntityControllerInkBattle.class, ContestCoordinates.inkBattleControllerAABB()).iterator();
                if (it2.hasNext()) {
                    EntityControllerInkBattle controller2 = (EntityControllerInkBattle) it2.next();
                    controller2.inkPlayer((EntityPlayer) result.field_72308_g);
                }
            }
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.08f;
    }
}
