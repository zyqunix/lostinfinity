package xol.lostinfinity.mob.render.murk;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.murk.EntityWhisper;
import xol.lostinfinity.mob.model.murk.ModelWhisper;
public class RenderWhisper extends RenderLiving<EntityWhisper> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/murk/whisper.png");
    public static final ResourceLocation TEXTURES2 = new ResourceLocation("lostinfinity:textures/entity/murk/whisper_sacrificed.png");
    public RenderWhisper(RenderManager manager) {
        super(manager, new ModelWhisper(), 0.5f);
    }
    public void func_77041_b(EntityWhisper entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
    }
    public ResourceLocation func_110775_a(EntityWhisper entity) {
        if (entity.isSacrificed()) {
            return TEXTURES2;
        }
        return TEXTURES;
    }
    public void func_77043_a(EntityWhisper entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
