package xol.lostinfinity.gui.guis;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.block.tileentity.TileEntityNicroniumInfuser;
import xol.lostinfinity.gui.containers.ContainerNicroniumInfuser;
import xol.lostinfinity.util.Reference;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/guis/GuiNicroniumInfuser.class */
public class GuiNicroniumInfuser extends GuiContainer {
    public static final int WIDTH = 175;
    public static final int HEIGHT = 165;
    private static final ResourceLocation background = new ResourceLocation(Reference.MODID, "textures/gui/nicronium_infuser.png");
    private final InventoryPlayer player;
    private final TileEntityNicroniumInfuser tileentity;

    public GuiNicroniumInfuser(InventoryPlayer player, TileEntityNicroniumInfuser tileEntityNicroniumInfuser) {
        super(new ContainerNicroniumInfuser(player, tileEntityNicroniumInfuser));
        this.player = player;
        this.tileentity = tileEntityNicroniumInfuser;
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
        if (TileEntityNicroniumInfuser.isBurning(this.tileentity)) {
            int k = getBurnLeftScaled(13);
            func_73729_b(this.field_147003_i + 18, ((this.field_147009_r + 40) + 12) - k, 176, 12 - k, 14, k + 1);
        }
        int l = getCookProgressScaled(24);
        func_73729_b(this.field_147003_i + 67, this.field_147009_r + 39, 176, 14, l + 1, 16);
    }

    protected void func_146979_b(int mouseX, int mouseY) {
        this.field_146289_q.func_78276_b("Infuser", 60, 8, 4210752);
    }

    private int getBurnLeftScaled(int pixels) {
        int i = this.tileentity.func_174887_a_(1);
        if (i == 0) {
            i = 200;
        }
        return (this.tileentity.func_174887_a_(0) * pixels) / i;
    }

    private int getCookProgressScaled(int pixels) {
        int i = this.tileentity.func_174887_a_(2);
        int j = this.tileentity.func_174887_a_(3);
        if (j == 0 || i == 0) {
            return 0;
        }
        return (i * pixels) / j;
    }
}
