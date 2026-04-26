package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityHypnosaur;
import xol.lostinfinity.mob.model.starforge.ModelHypnosaur;
public class RenderHypnosaur extends RenderLiving<EntityHypnosaur> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/hypnosaur.png");
    public RenderHypnosaur(RenderManager manager) {
        super(manager, new ModelHypnosaur(), 0.5f);
    }
    public void func_77041_b(EntityHypnosaur entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(1.5f, 1.5f, 1.5f);
    }
    public ResourceLocation func_110775_a(EntityHypnosaur entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityHypnosaur entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
