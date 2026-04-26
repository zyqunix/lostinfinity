package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityIonExplosion;
import xol.lostinfinity.mob.model.ModelMirrorZombie;
public class RenderIonExplosion extends RenderLiving<EntityIonExplosion> {
    public RenderIonExplosion(RenderManager manager) {
        super(manager, new ModelMirrorZombie(), 0.1f);
    }
    public ResourceLocation func_110775_a(EntityIonExplosion entity) {
        return null;
    }
    public void func_77043_a(EntityIonExplosion entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
