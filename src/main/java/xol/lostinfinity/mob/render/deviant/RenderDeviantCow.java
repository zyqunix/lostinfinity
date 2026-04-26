package xol.lostinfinity.mob.render.deviant;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantCow;
import xol.lostinfinity.mob.model.deviant.ModelDeviantCow;
public class RenderDeviantCow extends RenderLiving<EntityDeviantCow> {
    public static final ResourceLocation TEXTURES_NORMAL = new ResourceLocation("lostinfinity:textures/entity/deviant/deviantcow.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_1 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantcow_1.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_2 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantcow_2.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_3 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantcow_3.png");
    private float scale;
    public RenderDeviantCow(RenderManager manager) {
        super(manager, new ModelDeviantCow(), 0.5f);
        this.scale = 1.5f;
    }
    public void func_77041_b(EntityDeviantCow entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(this.scale, this.scale, this.scale);
    }
    public ResourceLocation func_110775_a(EntityDeviantCow entity) {
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
    public void func_77043_a(EntityDeviantCow entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
