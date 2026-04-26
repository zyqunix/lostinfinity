package xol.lostinfinity.mob.render.titan;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanVex;
import xol.lostinfinity.mob.model.deviant.ModelDeviantVex;
public class RenderTitanVex extends RenderLiving<EntityTitanVex> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/titan/deviantvex_titan.png");
    private float scale;
    public RenderTitanVex(RenderManager manager) {
        super(manager, new ModelDeviantVex(), 0.5f);
        this.scale = 2.0f;
    }
    public void func_77041_b(EntityTitanVex entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(this.scale, this.scale, this.scale);
    }
    public ResourceLocation func_110775_a(EntityTitanVex entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityTitanVex entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
