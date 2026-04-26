package xol.lostinfinity.projectile.render;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.ResourceLocation;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/render/RenderInfinityFireball.class */
public class RenderInfinityFireball extends RenderProjectileBase<EntityFireball> {
    private ResourceLocation texture;
    private float scaleFactor;

    public RenderInfinityFireball(RenderManager renderManager, ResourceLocation texture) {
        super(renderManager, 1.0f);
        this.texture = texture;
    }

    public RenderInfinityFireball(RenderManager renderManager, float scaleFactor, ResourceLocation texture) {
        super(renderManager, scaleFactor);
        this.texture = texture;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityFireball entity) {
        return this.texture;
    }
}
