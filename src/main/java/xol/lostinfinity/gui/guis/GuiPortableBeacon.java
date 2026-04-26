package xol.lostinfinity.gui.guis;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import xol.lostinfinity.gui.containers.ContainerPortableBeacon;
import xol.lostinfinity.util.Reference;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/guis/GuiPortableBeacon.class */
public class GuiPortableBeacon extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/portable_beacon.png");
    private final ContainerPortableBeacon beacon;

    public GuiPortableBeacon(InventoryPlayer invPlayer, World worldIn) {
        super(new ContainerPortableBeacon(invPlayer, worldIn, invPlayer.func_70448_g()));
        this.beacon = (ContainerPortableBeacon) this.field_147002_h;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        Keyboard.enableRepeatEvents(true);
        int i = (this.field_146294_l - this.field_146999_f) / 2;
        int i2 = (this.field_146295_m - this.field_147000_g) / 2;
    }

    public void func_146281_b() {
        super.func_146281_b();
    }

    protected void func_146979_b(int mouseX, int mouseY) {
        GlStateManager.func_179140_f();
        GlStateManager.func_179084_k();
        this.field_146289_q.func_78276_b("Portabeacon", 57, 3, 4210752);
        GlStateManager.func_179145_e();
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        func_146276_q_();
        super.func_73863_a(mouseX, mouseY, partialTicks);
        func_191948_b(mouseX, mouseY);
        GlStateManager.func_179140_f();
        GlStateManager.func_179084_k();
    }

    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(texture);
        int i = (this.field_146294_l - this.field_146999_f) / 2;
        int j = (this.field_146295_m - this.field_147000_g) / 2;
        func_73729_b(i, j, 0, 0, this.field_146999_f, this.field_147000_g);
    }
}
