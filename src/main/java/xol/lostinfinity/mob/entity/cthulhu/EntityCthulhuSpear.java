package xol.lostinfinity.mob.entity.cthulhu;
import com.google.common.base.Optional;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.serverbound.PacketCthulhuBarrier;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.item.basics.ItemChanneling;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.math.LMath;
public class EntityCthulhuSpear extends EntityImmaterial {
    private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.func_187226_a(EntityCthulhuSpear.class, DataSerializers.field_187203_m);
    private EntityPlayer owner;
    private double laserDistance;
    private int barrierTick;
    public EntityCthulhuSpear(World worldIn) {
        super(worldIn);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (getOwner() == null) {
            func_70106_y();
            return;
        }
        updatePosition();
        if (!this.field_70170_p.field_72995_K) {
            if (getOwner().func_184614_ca().func_77973_b() != ItemInit.cthulhuSpear || !ItemChanneling.isChanneling(getOwner(), getOwner().func_184614_ca())) {
                func_70106_y();
                return;
            }
            return;
        }
        Vec3d normVec = getOwner().func_70040_Z();
        Vec3d laserEnd = getOwner().func_174824_e(1.0f);
        double accumDist = 0.0d;
        double safeDistance = 256.0d;
        int closest = -1;
        for (int i = 0; i < 100; i++) {
            for (EntityCthulhu cthulhu : EntityCthulhu.CTHULHUS.values()) {
                if (cthulhu.isBarrierActive() && cthulhu.getPhase() == 2) {
                    double x = cthulhu.field_70165_t - laserEnd.field_72450_a;
                    double z = cthulhu.field_70161_v - laserEnd.field_72449_c;
                    double d = LMath.fastSqrt((x * x) + (z * z)) - 48.0d;
                    if (d < safeDistance) {
                        closest = cthulhu.func_145782_y();
                        safeDistance = d;
                    }
                }
            }
            accumDist += safeDistance;
            if (safeDistance < 0.25d || accumDist > 256.0d) {
                break;
            }
            laserEnd = laserEnd.func_178787_e(normVec.func_186678_a(safeDistance));
            safeDistance = 256.0d;
        }
        if (accumDist < 256.0d) {
            this.laserDistance = accumDist;
            this.barrierTick++;
            if (this.barrierTick == 40 && closest != -1) {
                lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketCthulhuBarrier(5, 100, closest));
                return;
            }
            return;
        }
        this.laserDistance = 256.0d;
        this.barrierTick = 0;
    }
    protected void func_82167_n(Entity entityIn) {
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(OWNER, Optional.absent());
    }
    public void setOwner(EntityPlayer player) {
        this.owner = player;
        this.field_70180_af.func_187227_b(OWNER, Optional.of(player.func_110124_au()));
    }
    public EntityPlayer getOwner() {
        if (this.owner == null) {
            Optional<UUID> uuidOptional = (Optional) this.field_70180_af.func_187225_a(OWNER);
            if (uuidOptional.isPresent()) {
                this.owner = this.field_70170_p.func_152378_a((UUID) uuidOptional.get());
            }
        }
        return this.owner;
    }
    public void updatePosition() {
        Vec3d dir = getOwner().func_70040_Z().func_186678_a(3.0d);
        func_70107_b(getOwner().field_70165_t + dir.field_72450_a, getOwner().field_70163_u + dir.field_72448_b, getOwner().field_70161_v + dir.field_72449_c);
    }
    public double getLaserDistance() {
        return this.laserDistance;
    }
}
