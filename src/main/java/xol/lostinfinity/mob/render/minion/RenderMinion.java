package xol.lostinfinity.mob.render.minion;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import xol.lostinfinity.mob.entity.minion.EntityMinion;
public abstract class RenderMinion<T extends EntityMinion> extends RenderLiving<T> {
    public RenderMinion(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }
    public boolean func_177071_a(T livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return livingEntity.shouldRender() && super.func_177071_a(livingEntity, camera, camX, camY, camZ);
    }
}
