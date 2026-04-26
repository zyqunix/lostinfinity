package xol.lostinfinity.mob.render.deviant;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantMooshroom;
import xol.lostinfinity.mob.model.deviant.ModelDeviantMooshroom;
public class RenderDeviantMooshroom extends RenderLiving<EntityDeviantMooshroom> {
    public static final ResourceLocation TEXTURES_NORMAL = new ResourceLocation("lostinfinity:textures/entity/deviant/deviantmooshroom.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_1 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantmooshroom_1.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_2 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantmooshroom_2.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_3 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantmooshroom_3.png");
    private float scale;
    public RenderDeviantMooshroom(RenderManager manager) {
        super(manager, new ModelDeviantMooshroom(), 0.5f);
        this.scale = 2.0f;
    }
    public void func_77041_b(EntityDeviantMooshroom entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(this.scale, this.scale, this.scale);
    }
    public ResourceLocation func_110775_a(EntityDeviantMooshroom entity) {
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
    public void func_77043_a(EntityDeviantMooshroom entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
