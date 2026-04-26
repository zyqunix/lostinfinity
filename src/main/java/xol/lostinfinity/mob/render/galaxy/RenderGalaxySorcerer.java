package xol.lostinfinity.mob.render.galaxy;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.mob.entity.galaxy.EntityGalaxySorcerer;
import xol.lostinfinity.mob.model.galaxy.ModelGalaxySorcerer;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/galaxy/RenderGalaxySorcerer.class */
public class RenderGalaxySorcerer extends RenderLiving<EntityGalaxySorcerer> {
    public static final ResourceLocation TEXTURE_BLUE = new ResourceLocation("lostinfinity:textures/entity/galaxysorcerer_blue.png");
    public static final ResourceLocation TEXTURE_GREEN = new ResourceLocation("lostinfinity:textures/entity/galaxysorcerer_green.png");
    public static final ResourceLocation TEXTURE_YELLOW = new ResourceLocation("lostinfinity:textures/entity/galaxysorcerer_yellow.png");
    public static final ResourceLocation TEXTURE_PURPLE = new ResourceLocation("lostinfinity:textures/entity/galaxysorcerer_purple.png");
    private float scale;

    public RenderGalaxySorcerer(RenderManager manager) {
        super(manager, new ModelGalaxySorcerer(), 0.5f);
        this.scale = 2.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityGalaxySorcerer entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(this.scale, this.scale, this.scale);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityGalaxySorcerer entity) {
        switch (entity.getColor()) {
            case 1:
                return TEXTURE_BLUE;
            case 2:
                return TEXTURE_GREEN;
            case 3:
                return TEXTURE_YELLOW;
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                return TEXTURE_PURPLE;
            default:
                return TEXTURE_BLUE;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityGalaxySorcerer entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
