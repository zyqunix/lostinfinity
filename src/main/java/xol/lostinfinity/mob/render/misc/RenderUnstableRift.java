package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.mob.entity.misc.EntityUnstableRift;
import xol.lostinfinity.mob.model.ModelRift;
public class RenderUnstableRift extends RenderLiving<EntityUnstableRift> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/rift/unstable_rift1.png");
    public static final ResourceLocation TEXTURES2 = new ResourceLocation("lostinfinity:textures/entity/rift/unstable_rift2.png");
    public static final ResourceLocation TEXTURES3 = new ResourceLocation("lostinfinity:textures/entity/rift/unstable_rift3.png");
    public RenderUnstableRift(RenderManager manager) {
        super(manager, new ModelRift(), 0.5f);
    }
    public void func_77041_b(EntityUnstableRift entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(2.0f, 2.0f, 2.0f);
    }
    public boolean func_177071_a(EntityUnstableRift livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return true;
    }
    public ResourceLocation func_110775_a(EntityUnstableRift entity) {
        switch (entity.field_70173_aa % 15) {
            case 0:
                return TEXTURES2;
            case 1:
                return TEXTURES3;
            case 2:
                return TEXTURES2;
            case 3:
                return TEXTURES;
            case TileEntityFusionTable.BOARD_ROWS :
                return TEXTURES2;
            case 5:
                return TEXTURES3;
            case TileEntityFusionTable.BOARD_COLUMNS :
                return TEXTURES2;
            default:
                return TEXTURES;
        }
    }
    public void func_77043_a(EntityUnstableRift entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
