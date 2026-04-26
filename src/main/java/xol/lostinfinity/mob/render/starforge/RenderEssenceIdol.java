package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityEssenceIdol;
import xol.lostinfinity.mob.model.starforge.ModelEssenceIdol;
public class RenderEssenceIdol extends RenderLiving<EntityEssenceIdol> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/essenceidol.png");
    public RenderEssenceIdol(RenderManager manager) {
        super(manager, new ModelEssenceIdol(), 0.5f);
    }
    public void func_77041_b(EntityEssenceIdol entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
    }
    public ResourceLocation func_110775_a(EntityEssenceIdol entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityEssenceIdol entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
