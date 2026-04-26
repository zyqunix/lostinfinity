package xol.lostinfinity.mob.entity.misc;
import com.google.common.base.Optional;
import java.util.UUID;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityPlayerLimb extends EntityImmaterial {
    private static final DataParameter<Integer> LIMB = EntityDataManager.func_187226_a(EntityPlayerLimb.class, DataSerializers.field_187192_b);
    private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.func_187226_a(EntityPlayerLimb.class, DataSerializers.field_187203_m);
    public EntityPlayerLimb(World worldIn) {
        super(worldIn);
        this.field_70145_X = true;
    }
    public EntityPlayerLimb(World worldIn, UUID targetUUID) {
        super(worldIn);
        this.field_70145_X = true;
        setSkinOwner(targetUUID);
    }
    protected void func_70088_a() {
        super.func_70088_a();
        func_184212_Q().func_187214_a(LIMB, Integer.valueOf(this.field_70170_p.field_73012_v.nextInt(4)));
        func_184212_Q().func_187214_a(OWNER, Optional.absent());
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70173_aa % 5 == 0) {
            CustomParticleConfig config = new CustomParticleConfig();
            config.createInstance().setSpread(0.5d, 0.5d, 0.5d).setCount(3).setParticle(EnumParticleTypes.REDSTONE);
            if (getLimb() == 0 || getLimb() == 1) {
                IParticleSpawner.spawnParticle(this.field_70170_p, config, this.field_70165_t, this.field_70163_u + 1.0d, this.field_70161_v);
            } else {
                IParticleSpawner.spawnParticle(this.field_70170_p, config, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            }
        }
        this.field_70181_x -= 0.03d;
        if (this.field_70173_aa > 40) {
            func_70106_y();
        }
    }
    public int getLimb() {
        return ((Integer) func_184212_Q().func_187225_a(LIMB)).intValue();
    }
    public void setSkinOwner(UUID uuid) {
        func_184212_Q().func_187227_b(OWNER, Optional.of(uuid));
    }
    @SideOnly(Side.CLIENT)
    public ResourceLocation getSkin() {
        if (((Optional) func_184212_Q().func_187225_a(OWNER)).isPresent()) {
            AbstractClientPlayer clientPlayer = this.field_70170_p.func_152378_a((UUID) ((Optional) func_184212_Q().func_187225_a(OWNER)).get());
            return clientPlayer.func_110306_p();
        }
        return new ResourceLocation("textures/entity/alex.png");
    }
}
