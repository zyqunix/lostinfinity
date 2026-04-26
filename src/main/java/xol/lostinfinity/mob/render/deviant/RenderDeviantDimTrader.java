package xol.lostinfinity.mob.render.deviant;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantDimTrader;
import xol.lostinfinity.mob.model.deviant.ModelDeviantDimTrader;
public class RenderDeviantDimTrader extends RenderLiving<EntityDeviantDimTrader> {
    public static final ResourceLocation TEXTURES_NORMAL = new ResourceLocation("lostinfinity:textures/entity/deviant/deviantdimtrader.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_1 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantdimtrader_1.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_2 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantdimtrader_2.png");
    public static final ResourceLocation TEXTURES_SUPER_MUTATION_3 = new ResourceLocation("lostinfinity:textures/entity/deviant/super_deviantdimtrader_3.png");
    public RenderDeviantDimTrader(RenderManager manager) {
        super(manager, new ModelDeviantDimTrader(), 0.5f);
    }
    public void func_77041_b(EntityDeviantDimTrader entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(entitylivingbaseIn.getMyScale(), entitylivingbaseIn.getMyScale(), entitylivingbaseIn.getMyScale());
    }
    public ResourceLocation func_110775_a(EntityDeviantDimTrader entity) {
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
    public void func_77043_a(EntityDeviantDimTrader entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
