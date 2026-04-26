package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntitySpyker;
import xol.lostinfinity.mob.model.starforge.ModelSpyker;
public class RenderSpyker extends RenderLiving<EntitySpyker> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/spyker.png");
    public static final ResourceLocation TEXTURES_BLUE = new ResourceLocation("lostinfinity:textures/entity/starforge/spyker_blue.png");
    public RenderSpyker(RenderManager manager) {
        super(manager, new ModelSpyker(), 0.5f);
    }
    public void func_77041_b(EntitySpyker entitylivingbaseIn, float partialTickTime) {
        if (entitylivingbaseIn.isEnlarged()) {
            GlStateManager.func_179152_a(entitylivingbaseIn.getUltraScl(), entitylivingbaseIn.getUltraScl(), entitylivingbaseIn.getUltraScl());
        } else {
            GlStateManager.func_179152_a(1.0f, 1.0f, 1.0f);
        }
    }
    public ResourceLocation func_110775_a(EntitySpyker entity) {
        if (entity.isEnlarged()) {
            return TEXTURES_BLUE;
        }
        return TEXTURES;
    }
    public void func_77043_a(EntitySpyker entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
