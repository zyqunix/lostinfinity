package xol.lostinfinity.mob.entity.minion;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.misc.EntityTentacleTrap;
public class EntityAbyssalCrabulon extends EntityMinion {
    private static final double MAX_DISTANCE = 20.0d;
    private final Map<EntityLivingBase, EntityTentacleTrap> targetTrapMap;
    public EntityAbyssalCrabulon(World worldIn) {
        super(worldIn);
        this.targetTrapMap = new ConcurrentHashMap();
        func_70105_a(0.25f, 0.25f);
    }
    @Override // xol.lostinfinity.mob.entity.minion.EntityMinion
    protected void livingUpdate() {
        EntityLivingBase entity;
        EntityLivingBase owner = func_70902_q();
        if (owner == null) {
            return;
        }
        updatePosition();
        if (this.field_70170_p.field_72995_K || this.field_70173_aa % 40 != 0 || (entity = owner.func_110144_aD()) == null || (entity instanceof EntityAbyssalCrabulon) || (entity instanceof EntityTentacleTrap) || entity == owner || entity.func_70068_e(owner) > 400.0d) {
            return;
        }
        EntityTentacleTrap oldTrap = this.targetTrapMap.get(entity);
        if (oldTrap != null && !oldTrap.field_70128_L) {
            return;
        }
        EntityTentacleTrap trap = new EntityTentacleTrap(this.field_70170_p);
        trap.func_70107_b(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
        trap.setTarget(entity);
        trap.setOwner(owner);
        this.field_70170_p.func_72838_d(trap);
        this.field_70170_p.func_184133_a((EntityPlayer) null, entity.func_180425_c(), SoundInit.SKYCRAB_HURT, SoundCategory.HOSTILE, 1.5f, 0.8f + (this.field_70170_p.field_73012_v.nextFloat() * 0.4f));
        this.targetTrapMap.put(entity, trap);
        this.targetTrapMap.forEach((entityLivingBase, entityTentacleTrap) -> {
            if (entityLivingBase.field_70128_L || entityTentacleTrap.field_70128_L) {
                this.targetTrapMap.remove(entityLivingBase);
            }
        });
    }
    private void updatePosition() {
        float x = MathHelper.func_76126_a(this.field_70173_aa * 0.01f);
        float y = MathHelper.func_76126_a(this.field_70173_aa * 0.05f) * 0.5f;
        float z = MathHelper.func_76134_b(this.field_70173_aa * 0.01f);
        func_70080_a(this.owner.field_70165_t + ((double) x), this.owner.field_70163_u + 2.0d + ((double) y), this.owner.field_70161_v + ((double) z), 0.0f, 0.0f);
        this.field_70759_as = 0.0f;
        this.field_70761_aq = 0.0f;
    }
}
