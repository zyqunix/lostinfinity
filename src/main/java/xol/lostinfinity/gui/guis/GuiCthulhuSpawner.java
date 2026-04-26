package xol.lostinfinity.gui.guis;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.block.tileentity.TileEntityCthulhuSpawner;
import xol.lostinfinity.gui.containers.ContainerCthulhuSpawner;
import xol.lostinfinity.util.Reference;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/guis/GuiCthulhuSpawner.class */
public class GuiCthulhuSpawner extends GuiContainer {
    public static final int WIDTH = 175;
    public static final int HEIGHT = 165;
    private static final ResourceLocation background = new ResourceLocation(Reference.MODID, "textures/gui/cthulhu_spawner.png");
    private final InventoryPlayer player;
    private final TileEntityCthulhuSpawner tileentity;

    public GuiCthulhuSpawner(InventoryPlayer player, TileEntityCthulhuSpawner tileEntityCthulhuSpawner) {
        super(new ContainerCthulhuSpawner(player, tileEntityCthulhuSpawner));
        this.player = player;
        this.tileentity = tileEntityCthulhuSpawner;
        this.field_146999_f = 175;
        this.field_147000_g = 165;
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        func_146276_q_();
        super.func_73863_a(mouseX, mouseY, partialTicks);
        func_191948_b(mouseX, mouseY);
    }

    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
        this.field_146297_k.func_110434_K().func_110577_a(background);
        func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
    }

    protected void func_146979_b(int mouseX, int mouseY) {
        this.field_146289_q.func_78276_b("I.D.R.", 42, 7, 121075);
    }

    private int getBurnLeftScaled(int pixels) {
        int i = this.tileentity.func_174887_a_(1);
        return i == 0 ? 0 : 0;
    }

    private int getCookProgressScaled(int pixels) {
        return 0;
    }
}
