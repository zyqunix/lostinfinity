package xol.lostinfinity.mob.render.deviant;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantZombie;
import xol.lostinfinity.mob.model.deviant.ModelDeviantZombie;
public class RenderDeviantZombie extends RenderLiving<EntityDeviantZombie> {
    public static final ResourceLocation TEXTURES_NORMAL = new ResourceLocation("lostinfinity:textures/entity/deviant/deviantzombie.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_1 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantzombie_1.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_2 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantzombie_2.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_3 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantzombie_3.png");
    public RenderDeviantZombie(RenderManager manager) {
        super(manager, new ModelDeviantZombie(), 0.5f);
    }
    public void func_77041_b(EntityDeviantZombie entitylivingbaseIn, float partialTickTime) {
        float scale = 1.5f;
        if (entitylivingbaseIn.field_70173_aa < 30 || !entitylivingbaseIn.isSummoner()) {
            scale = (1.5f * entitylivingbaseIn.field_70173_aa) / 30.0f;
        }
        GlStateManager.func_179152_a(scale, scale, scale);
    }
    public ResourceLocation func_110775_a(EntityDeviantZombie entity) {
        switch (entity.getMutation()) {
            case 1:
                return TEXTURES_SUPER_MUTATION_1;
            case 2:
                return TEXTURES_SUPER_MUTATION_2;
            case 3:
                return TEXTURES_SUPER_MUTATION_3;
            default:
                return TEXTURES_NORMAL;
        }
    }
    public void func_77043_a(EntityDeviantZombie entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
