package xol.lostinfinity.mob.render.cthulhu;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuRift;
import xol.lostinfinity.mob.model.ModelRift;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/cthulhu/RenderCthulhuRift.class */
public class RenderCthulhuRift extends RenderLiving<EntityCthulhuRift> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/rift/boss_rift1.png");
    public static final ResourceLocation TEXTURES2 = new ResourceLocation("lostinfinity:textures/entity/rift/boss_rift2.png");
    public static final ResourceLocation TEXTURES3 = new ResourceLocation("lostinfinity:textures/entity/rift/boss_rift3.png");

    public RenderCthulhuRift(RenderManager manager) {
        super(manager, new ModelRift(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityCthulhuRift entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(5.0f, 5.0f, 5.0f);
    }

    /* JADX INFO: renamed from: shouldRender, reason: merged with bridge method [inline-methods] */
    public boolean func_177071_a(EntityCthulhuRift livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityCthulhuRift entity) {
        switch (entity.field_70173_aa % 15) {
            case 0:
            case 2:
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
            case TileEntityFusionTable.BOARD_COLUMNS /* 6 */:
                return TEXTURES2;
            case 1:
            case 5:
                return TEXTURES3;
            case 3:
                return TEXTURES;
            default:
                return TEXTURES;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityCthulhuRift entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
