package xol.lostinfinity.mob.render.galaxy;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.galaxy.EntityGalaxySpire;
import xol.lostinfinity.mob.model.galaxy.ModelGalaxySpire;
public class RenderGalaxySpire extends RenderLiving<EntityGalaxySpire> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/galaxyspire.png");
    public RenderGalaxySpire(RenderManager manager) {
        super(manager, new ModelGalaxySpire(), 0.5f);
    }
    public void func_77041_b(EntityGalaxySpire entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
    }
    public ResourceLocation func_110775_a(EntityGalaxySpire entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityGalaxySpire entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
