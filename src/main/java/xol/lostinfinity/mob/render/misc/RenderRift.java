package xol.lostinfinity.mob.render.misc;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.mob.entity.misc.EntityRift;
import xol.lostinfinity.mob.model.ModelRift;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/misc/RenderRift.class */
public class RenderRift extends RenderLiving<EntityRift> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/rift/rift1.png");
    public static final ResourceLocation TEXTURES2 = new ResourceLocation("lostinfinity:textures/entity/rift/rift2.png");
    public static final ResourceLocation TEXTURES3 = new ResourceLocation("lostinfinity:textures/entity/rift/rift3.png");

    public RenderRift(RenderManager manager) {
        super(manager, new ModelRift(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityRift entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(5.0f, 5.0f, 5.0f);
    }

    /* JADX INFO: renamed from: shouldRender, reason: merged with bridge method [inline-methods] */
    public boolean func_177071_a(EntityRift livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityRift entity) {
        switch (entity.field_70173_aa % 15) {
            case 0:
                return TEXTURES2;
            case 1:
                return TEXTURES3;
            case 2:
                return TEXTURES2;
            case 3:
                return TEXTURES;
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                return TEXTURES2;
            case 5:
                return TEXTURES3;
            case TileEntityFusionTable.BOARD_COLUMNS /* 6 */:
                return TEXTURES2;
            default:
                return TEXTURES;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityRift entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
