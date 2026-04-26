package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityGalacticTerror;
import xol.lostinfinity.mob.model.starforge.ModelGalacticTerror;
public class RenderGalacticTerror extends RenderLiving<EntityGalacticTerror> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/galacticterror.png");
    public RenderGalacticTerror(RenderManager manager) {
        super(manager, new ModelGalacticTerror(), 0.5f);
    }
    public void func_77041_b(EntityGalacticTerror entity, float partialTickTime) {
        GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
    }
    public ResourceLocation func_110775_a(EntityGalacticTerror entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityGalacticTerror entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
