package xol.lostinfinity.projectile.render;

import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.ResourceLocation;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/render/RenderInfinityThrowable.class */
public class RenderInfinityThrowable extends RenderProjectileBase<EntityThrowable> {
    private ResourceLocation texture;
    private boolean farRender;

    public RenderInfinityThrowable(RenderManager renderManager, ResourceLocation texture) {
        super(renderManager, 1.0f);
        this.farRender = false;
        this.texture = texture;
    }

    public RenderInfinityThrowable(RenderManager renderManager, float scaleFactor, ResourceLocation texture) {
        super(renderManager, scaleFactor);
        this.farRender = false;
        this.texture = texture;
    }

    public RenderInfinityThrowable(RenderManager renderManager, float scaleFactor, ResourceLocation texture, boolean farRender) {
        super(renderManager, scaleFactor);
        this.farRender = false;
        this.texture = texture;
        this.farRender = farRender;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // 
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityThrowable entity) {
        return this.texture;
    }

    /* JADX INFO: renamed from: shouldRender, reason: merged with bridge method [inline-methods] */
    public boolean func_177071_a(EntityThrowable livingEntity, ICamera camera, double camX, double camY, double camZ) {
        if (this.farRender) {
            double xDiff = camX - livingEntity.field_70165_t;
            double yDiff = camY - livingEntity.field_70163_u;
            double zDiff = camZ - livingEntity.field_70161_v;
            return ((xDiff * xDiff) + (yDiff * yDiff)) + (zDiff * zDiff) < 100000.0d;
        }
        return super.func_177071_a(livingEntity, camera, camX, camY, camZ);
    }
}
