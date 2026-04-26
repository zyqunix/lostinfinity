package xol.lostinfinity.mob.render.murk;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.murk.EntityTorpedon;
import xol.lostinfinity.mob.model.murk.ModelTorpedon;
public class RenderTorpedon extends RenderLiving<EntityTorpedon> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/murk/torpedon.png");
    public RenderTorpedon(RenderManager manager) {
        super(manager, new ModelTorpedon(), 0.5f);
    }
    public void func_77041_b(EntityTorpedon entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
    }
    public ResourceLocation func_110775_a(EntityTorpedon entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityTorpedon entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
