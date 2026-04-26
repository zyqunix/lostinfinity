package xol.lostinfinity.gui.guis;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.gui.containers.ContainerAugmentor;
import xol.lostinfinity.item.misc.ItemAugmentSlide;
import xol.lostinfinity.util.Reference;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/guis/GuiAugmentor.class */
public class GuiAugmentor extends GuiContainer {
    public static final int WIDTH = 175;
    public static final int HEIGHT = 165;
    private static final ResourceLocation background = new ResourceLocation(Reference.MODID, "textures/gui/augmentor.png");

    public GuiAugmentor(IInventory inventory) {
        super(new ContainerAugmentor(inventory));
        this.field_146999_f = 175;
        this.field_147000_g = 165;
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        super.func_73863_a(mouseX, mouseY, partialTicks);
        func_191948_b(mouseX, mouseY);
    }

    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
        this.field_146297_k.func_110434_K().func_110577_a(background);
        func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
        func_73729_b(this.field_147003_i + 59, this.field_147009_r + 20, 0, this.field_147000_g + 1, 110, 16);
    }

    protected void func_146979_b(int mouseX, int mouseY) {
        ItemStack slideStack = this.field_147002_h.func_75139_a(1).func_75211_c();
        if (slideStack.func_77973_b() instanceof ItemAugmentSlide) {
            ItemAugmentSlide slide = (ItemAugmentSlide) slideStack.func_77973_b();
            this.field_146289_q.func_78276_b(slide.type.description, 62, 24, 4210752);
        }
        this.field_146289_q.func_78276_b("Augmentor", 60, 6, 4210752);
        this.field_146289_q.func_78276_b("Inventory", 8, (this.field_147000_g - 96) + 2, 4210752);
    }
}
