package xol.lostinfinity.mob.render.deviant;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.boss.EntityDeviantCrystal;
import xol.lostinfinity.mob.model.boss.ModelRestorationCrystal;
public class RenderDeviantCrystal extends RenderLiving<EntityDeviantCrystal> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/crystal_deviant.png");
    public RenderDeviantCrystal(RenderManager manager) {
        super(manager, new ModelRestorationCrystal(), 0.5f);
    }
    public void func_77041_b(EntityDeviantCrystal entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
    }
    public ResourceLocation func_110775_a(EntityDeviantCrystal entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityDeviantCrystal entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
