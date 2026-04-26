package xol.lostinfinity.mob.render.labyrinth;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.mob.entity.labyrinth.EntitySentry;
import xol.lostinfinity.mob.model.labyrinth.ModelSentry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/labyrinth/RenderSentry.class */
public class RenderSentry extends RenderLiving<EntitySentry> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/labyrinth/sentry.png");
    public static final ResourceLocation ANIM1 = new ResourceLocation("lostinfinity:textures/entity/labyrinth/sentry_2.png");
    public static final ResourceLocation ANIM2 = new ResourceLocation("lostinfinity:textures/entity/labyrinth/sentry_3.png");
    public static final ResourceLocation ANIM3 = new ResourceLocation("lostinfinity:textures/entity/labyrinth/sentry_4.png");
    public static final ResourceLocation ANIM4 = new ResourceLocation("lostinfinity:textures/entity/labyrinth/sentry_5.png");
    public static final ResourceLocation ANIM5 = new ResourceLocation("lostinfinity:textures/entity/labyrinth/sentry_5.png");

    public RenderSentry(RenderManager manager) {
        super(manager, new ModelSentry(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntitySentry entity) {
        switch (entity.field_70173_aa % 20) {
            case 0:
                return ANIM1;
            case 1:
                return ANIM2;
            case 2:
                return ANIM3;
            case 3:
                return ANIM4;
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                return ANIM5;
            default:
                return TEXTURES;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntitySentry entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
