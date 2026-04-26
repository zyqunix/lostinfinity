package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityNightshyre;
import xol.lostinfinity.mob.model.starforge.ModelNightshyre;
public class RenderNightshyre extends RenderLiving<EntityNightshyre> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/nightshyre.png");
    public RenderNightshyre(RenderManager manager) {
        super(manager, new ModelNightshyre(), 0.5f);
    }
    public void func_77041_b(EntityNightshyre entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(0.75f, 0.75f, 0.75f);
    }
    public ResourceLocation func_110775_a(EntityNightshyre entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityNightshyre entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
