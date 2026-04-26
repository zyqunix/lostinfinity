package xol.lostinfinity.mob.render.galaxy;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.galaxy.EntityGalaxyGulper;
import xol.lostinfinity.mob.model.galaxy.ModelGalaxyGulper;
public class RenderGalaxyGulper extends RenderLiving<EntityGalaxyGulper> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/galaxygulper.png");
    public RenderGalaxyGulper(RenderManager manager) {
        super(manager, new ModelGalaxyGulper(), 0.5f);
    }
    public void func_77041_b(EntityGalaxyGulper entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(2.0f, 2.0f, 2.0f);
    }
    public ResourceLocation func_110775_a(EntityGalaxyGulper entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityGalaxyGulper entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
