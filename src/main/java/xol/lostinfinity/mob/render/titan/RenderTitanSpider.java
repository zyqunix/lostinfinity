package xol.lostinfinity.mob.render.titan;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanSpider;
import xol.lostinfinity.mob.model.deviant.ModelDeviantSpider;
public class RenderTitanSpider extends RenderLiving<EntityTitanSpider> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/titan/deviantspider_titan.png");
    private float scale;
    public RenderTitanSpider(RenderManager manager) {
        super(manager, new ModelDeviantSpider(), 0.5f);
        this.scale = 4.0f;
    }
    public void func_77041_b(EntityTitanSpider entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(this.scale, this.scale, this.scale);
    }
    public ResourceLocation func_110775_a(EntityTitanSpider entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityTitanSpider entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
