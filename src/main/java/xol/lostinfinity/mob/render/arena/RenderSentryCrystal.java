package xol.lostinfinity.mob.render.arena;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.boss.EntitySentryCrystal;
import xol.lostinfinity.mob.model.boss.ModelRestorationCrystal;
public class RenderSentryCrystal extends RenderLiving<EntitySentryCrystal> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/crystal_sentry.png");
    public RenderSentryCrystal(RenderManager manager) {
        super(manager, new ModelRestorationCrystal(), 0.5f);
    }
    public void func_77041_b(EntitySentryCrystal entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
    }
    public ResourceLocation func_110775_a(EntitySentryCrystal entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntitySentryCrystal entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
