package xol.lostinfinity.mob.render.titan;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanStray;
import xol.lostinfinity.mob.model.deviant.ModelDeviantStray;
public class RenderTitanStray extends RenderLiving<EntityTitanStray> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/titan/deviantstray_titan.png");
    private float scale;
    public RenderTitanStray(RenderManager manager) {
        super(manager, new ModelDeviantStray(), 0.5f);
        this.scale = 4.0f;
    }
    public void func_77041_b(EntityTitanStray entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(this.scale, this.scale, this.scale);
    }
    public ResourceLocation func_110775_a(EntityTitanStray entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityTitanStray entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
