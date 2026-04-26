package xol.lostinfinity.projectile.render;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import xol.lostinfinity.mob.model.item.ModelAvengerHammer;
import xol.lostinfinity.projectile.entity.EntityAvenger;
@SideOnly(Side.CLIENT)
public class RenderAvenger<T extends EntityAvenger> extends Render<T> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/3d/avenger.png");
    public static final ResourceLocation TEXTURES_ALTERNATE = new ResourceLocation("lostinfinity:textures/3d/avenger_alternate.png");
    private ModelBase model;
    public RenderAvenger(RenderManager renderManagerIn) {
        super(renderManagerIn);
        this.model = new ModelAvengerHammer();
    }
    public ResourceLocation func_110775_a(T entity) {
        return getCustomTexture(entity);
    }
    private ResourceLocation getCustomTexture(EntityAvenger entity) {
        return TEXTURES;
    }
    public void func_76986_a(T entity, double x, double y, double z, float yaw, float partialTick) {
        GL11.glPushMatrix();
        if (((EntityAvenger) entity).field_70173_aa % 10 < 5) {
            func_110776_a(TEXTURES);
        } else {
            func_110776_a(TEXTURES_ALTERNATE);
        }
        GL11.glTranslated(x, y - 0.75d, z);
        GL11.glRotated(MathHelper.func_151238_b(((EntityAvenger) entity).field_70173_aa - 1, ((EntityAvenger) entity).field_70173_aa, partialTick) * 32.0d, 0.0d, 1.0d, 0.0d);
        this.model.func_78088_a(entity, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }
}
