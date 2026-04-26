package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityReflectal;
import xol.lostinfinity.mob.model.starforge.ModelReflectal;
public class RenderReflectal extends RenderLiving<EntityReflectal> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/reflectal.png");
    public static final ResourceLocation TEXTURES_BLUE = new ResourceLocation("lostinfinity:textures/entity/starforge/reflectal_blue.png");
    public RenderReflectal(RenderManager manager) {
        super(manager, new ModelReflectal(), 0.5f);
    }
    public void func_77041_b(EntityReflectal entitylivingbaseIn, float partialTickTime) {
        if (entitylivingbaseIn.isEnlarged()) {
            GlStateManager.func_179152_a(entitylivingbaseIn.getUltraScl(), entitylivingbaseIn.getUltraScl(), entitylivingbaseIn.getUltraScl());
        } else {
            GlStateManager.func_179152_a(1.0f, 1.0f, 1.0f);
        }
    }
    public ResourceLocation func_110775_a(EntityReflectal entity) {
        if (entity.isEnlarged()) {
            return TEXTURES_BLUE;
        }
        return TEXTURES;
    }
    public void func_77043_a(EntityReflectal entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
