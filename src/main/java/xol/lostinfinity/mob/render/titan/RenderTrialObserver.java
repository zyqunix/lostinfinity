package xol.lostinfinity.mob.render.titan;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTrialObserver;
import xol.lostinfinity.mob.model.ModelTrialObserver;
public class RenderTrialObserver extends RenderLiving<EntityTrialObserver> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/trialobserver.png");
    private float scale;
    public RenderTrialObserver(RenderManager manager) {
        super(manager, new ModelTrialObserver(), 0.5f);
        this.scale = 6.0f;
    }
    public void func_77041_b(EntityTrialObserver entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(this.scale, this.scale, this.scale);
    }
    public ResourceLocation func_110775_a(EntityTrialObserver entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityTrialObserver entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
