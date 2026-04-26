package xol.lostinfinity.mob.render.contest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.contest.EntityBloodhunter;
import xol.lostinfinity.mob.model.contest.ModelBloodhunter;
public class RenderBloodhunter extends RenderLiving<EntityBloodhunter> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/bloodhunter.png");
    public RenderBloodhunter(RenderManager manager) {
        super(manager, new ModelBloodhunter(), 0.5f);
    }
    public void func_77041_b(EntityBloodhunter entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(1.75f, 1.75f, 1.75f);
    }
    public ResourceLocation func_110775_a(EntityBloodhunter entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityBloodhunter entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
