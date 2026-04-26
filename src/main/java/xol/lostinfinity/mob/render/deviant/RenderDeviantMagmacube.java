package xol.lostinfinity.mob.render.deviant;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantMagmacube;
import xol.lostinfinity.mob.model.deviant.ModelDeviantMagmacube;
public class RenderDeviantMagmacube extends RenderLiving<EntityDeviantMagmacube> {
    public static final ResourceLocation TEXTURES_NORMAL = new ResourceLocation("lostinfinity:textures/entity/deviant/deviantmagmacube.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_1 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantmagmacube_1.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_2 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantmagmacube_2.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_3 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantmagmacube_3.png");
    public RenderDeviantMagmacube(RenderManager manager) {
        super(manager, new ModelDeviantMagmacube(), 0.5f);
    }
    public void func_77041_b(EntityDeviantMagmacube entitylivingbaseIn, float partialTickTime) {
        float scl = 2.5f + MathHelper.func_76126_a(entitylivingbaseIn.field_70173_aa * (0.1f + (0.1f * entitylivingbaseIn.getMutation())));
        GlStateManager.func_179152_a(scl, scl, scl);
    }
    public ResourceLocation func_110775_a(EntityDeviantMagmacube entity) {
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
    public void func_77043_a(EntityDeviantMagmacube entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
