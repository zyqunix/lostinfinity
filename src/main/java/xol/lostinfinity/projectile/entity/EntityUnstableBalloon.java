package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.deviant.prime.EntityZenon;
public class EntityUnstableBalloon extends EntityBaseThrowable {
    private EntityPlayer recentPlayer;
    private int hitCount;
    int graceTimer;
    public EntityUnstableBalloon(World par1World) {
        super(par1World);
        this.recentPlayer = null;
        this.hitCount = 0;
        this.graceTimer = 10;
        func_70105_a(0.95f, 0.95f);
    }
    public EntityUnstableBalloon(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.recentPlayer = null;
        this.hitCount = 0;
        this.graceTimer = 10;
        func_70105_a(0.95f, 0.95f);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected boolean willDespawn() {
        return false;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && (result.field_72308_g instanceof EntityPlayer) && this.graceTimer <= 0) {
                EntityPlayer player = (EntityPlayer) result.field_72308_g;
                if (this.recentPlayer == null) {
                    bounce(player);
                    return;
                } else if (this.recentPlayer.func_110124_au().equals(player.func_110124_au())) {
                    player.func_145747_a(new TextComponentString(TextFmt.Red + "You popped the balloon!"));
                    return;
                } else {
                    bounce(player);
                    return;
                }
            }
            func_70106_y();
        }
    }
    private void bounce(EntityPlayer player) {
        this.graceTimer = 10;
        this.recentPlayer = player;
        this.field_70181_x = 0.0d;
        func_70024_g((this.field_70146_Z.nextDouble() * 0.5d) - 0.25d, 1.4d, (this.field_70146_Z.nextDouble() * 0.5d) - 0.25d);
        this.field_70133_I = true;
        this.hitCount++;
        if (this.hitCount >= 8 && this.field_70192_c != null && player.func_110124_au().equals(this.field_70192_c.func_110124_au())) {
            func_145779_a(ItemInit.gasFilledBalloon, 1);
            EntityZenon zenon = new EntityZenon(this.field_70170_p);
            zenon.func_70634_a(this.field_70165_t, this.field_70163_u + 4.0d, this.field_70161_v);
            this.field_70170_p.func_72838_d(zenon);
            player.func_145747_a(new TextComponentString(TextFmt.Dark_Aqua + "Prime Deviant Zenon: Your atrocities against the Deviants will not be tolerated " + player.func_70005_c_() + "!"));
            func_70106_y();
            return;
        }
        player.func_145747_a(new TextComponentString((8 - this.hitCount) + " Hits Remaining"));
    }
    public void func_70030_z() {
        super.func_70030_z();
        this.graceTimer--;
    }
    protected float func_70185_h() {
        return 0.03f;
    }
}
