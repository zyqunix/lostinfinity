package xol.lostinfinity.projectile.entity;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerDuelArena;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class EntityFountainPellet extends EntityBaseThrowable {
    public EntityFountainPellet(World par1World) {
        super(par1World);
        func_70105_a(0.75f, 0.75f);
    }
    public EntityFountainPellet(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }
    public EntityFountainPellet(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K && result.field_72313_a == RayTraceResult.Type.ENTITY) {
            if (result.field_72308_g instanceof EntityPlayer) {
                AxisAlignedBB arena = ContestCoordinates.duelArenaAABB();
                List<EntityPlayer> inAABB = this.field_70170_p.func_72872_a(EntityPlayer.class, arena);
                if (inAABB.contains(result.field_72308_g)) {
                    for (EntityControllerDuelArena controller : this.field_70170_p.func_72872_a(EntityControllerDuelArena.class, arena)) {
                        controller.hitPlayer((EntityPlayer) result.field_72308_g, 1);
                    }
                }
            }
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.2f;
    }
}
