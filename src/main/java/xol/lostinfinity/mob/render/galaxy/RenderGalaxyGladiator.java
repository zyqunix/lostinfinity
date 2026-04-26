package xol.lostinfinity.mob.render.galaxy;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.mob.entity.galaxy.EntityGalaxyGladiator;
import xol.lostinfinity.mob.model.galaxy.ModelGalaxyGladiator;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/galaxy/RenderGalaxyGladiator.class */
public class RenderGalaxyGladiator extends RenderLiving<EntityGalaxyGladiator> {
    public static final ResourceLocation TEXTURE_BLUE = new ResourceLocation("lostinfinity:textures/entity/galaxygladiator_blue.png");
    public static final ResourceLocation TEXTURE_GREEN = new ResourceLocation("lostinfinity:textures/entity/galaxygladiator_green.png");
    public static final ResourceLocation TEXTURE_YELLOW = new ResourceLocation("lostinfinity:textures/entity/galaxygladiator_yellow.png");
    public static final ResourceLocation TEXTURE_PURPLE = new ResourceLocation("lostinfinity:textures/entity/galaxygladiator_purple.png");
    private float scale;

    public RenderGalaxyGladiator(RenderManager manager) {
        super(manager, new ModelGalaxyGladiator(), 0.5f);
        this.scale = 2.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityGalaxyGladiator entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(this.scale, this.scale, this.scale);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityGalaxyGladiator entity) {
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
    public void func_77043_a(EntityGalaxyGladiator entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
