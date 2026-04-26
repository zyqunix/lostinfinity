package xol.lostinfinity.projectile.render;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import xol.lostinfinity.mob.model.item.ModelIonicChakram;
import xol.lostinfinity.projectile.entity.EntityMiniChakram;
@SideOnly(Side.CLIENT)
public class RenderMiniChakram<T extends EntityMiniChakram> extends Render<T> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/3d/ionic_chakram.png");
    private ModelBase model;
    public RenderMiniChakram(RenderManager renderManagerIn) {
        super(renderManagerIn);
        this.model = new ModelIonicChakram();
    }
    public ResourceLocation func_110775_a(T entity) {
        return getCustomTexture(entity);
    }
    private ResourceLocation getCustomTexture(EntityMiniChakram entity) {
        return TEXTURES;
    }
    public void func_76986_a(T entity, double x, double y, double z, float yaw, float partialTick) {
        GL11.glPushMatrix();
        func_110776_a(TEXTURES);
        GL11.glTranslated(x, y - 0.75d, z);
        GL11.glRotated(MathHelper.func_151238_b(((EntityMiniChakram) entity).field_70173_aa - 1, ((EntityMiniChakram) entity).field_70173_aa, partialTick) * 8.0d, 0.0d, 1.0d, 0.0d);
        this.model.func_78088_a(entity, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }
}
