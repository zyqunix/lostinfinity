package xol.lostinfinity.mob.render.murk;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.murk.EntitySkyre;
import xol.lostinfinity.mob.model.murk.ModelSkyre;
public class RenderSkyre extends RenderLiving<EntitySkyre> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/murk/skyre.png");
    public RenderSkyre(RenderManager manager) {
        super(manager, new ModelSkyre(), 0.5f);
    }
    public void func_77041_b(EntitySkyre entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
    }
    public ResourceLocation func_110775_a(EntitySkyre entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntitySkyre entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
