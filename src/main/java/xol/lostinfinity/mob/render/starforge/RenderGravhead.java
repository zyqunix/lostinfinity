package xol.lostinfinity.mob.render.starforge;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityGravhead;
import xol.lostinfinity.mob.model.starforge.ModelGravhead;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/starforge/RenderGravhead.class */
public class RenderGravhead extends RenderLiving<EntityGravhead> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/gravhead.png");
    public static final ResourceLocation TEXTURES_RED = new ResourceLocation("lostinfinity:textures/entity/starforge/gravhead_pulling.png");

    public RenderGravhead(RenderManager manager) {
        super(manager, new ModelGravhead(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityGravhead entity) {
        if (entity.field_70173_aa % 100 < 30) {
            return TEXTURES_RED;
        }
        return TEXTURES;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityGravhead entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
