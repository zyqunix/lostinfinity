package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityGiantRockslug;
import xol.lostinfinity.mob.model.starforge.ModelRockslug;
public class RenderGiantRockslug extends RenderLiving<EntityGiantRockslug> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/giant_rockslug.png");
    public RenderGiantRockslug(RenderManager manager) {
        super(manager, new ModelRockslug(), 0.5f);
    }
    public void func_77041_b(EntityGiantRockslug entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(4.0f, 4.0f, 4.0f);
    }
    public ResourceLocation func_110775_a(EntityGiantRockslug entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityGiantRockslug entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
