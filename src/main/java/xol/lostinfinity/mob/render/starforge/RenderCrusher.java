package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityCrusher;
import xol.lostinfinity.mob.model.starforge.ModelCrusher;
public class RenderCrusher extends RenderLiving<EntityCrusher> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/crusher.png");
    public RenderCrusher(RenderManager manager) {
        super(manager, new ModelCrusher(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityCrusher entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityCrusher entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
