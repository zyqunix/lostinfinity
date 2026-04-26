package xol.lostinfinity.mob.render.contest;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.contest.trader.EntityArcheologist;
import xol.lostinfinity.mob.model.contest.ModelArcheologist;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/contest/RenderArcheologist.class */
public class RenderArcheologist extends RenderLiving<EntityArcheologist> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/archeologist.png");

    public RenderArcheologist(RenderManager manager) {
        super(manager, new ModelArcheologist(), 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityArcheologist entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityArcheologist entity) {
        return TEXTURES;
    }
}
