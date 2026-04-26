package xol.lostinfinity.gui.guis;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.block.tileentity.TileEntityNebulousBeacon;
import xol.lostinfinity.gui.containers.ContainerNebulousBeacon;
import xol.lostinfinity.util.Reference;
import xol.lostinfinity.util.client.GuiUtil;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/guis/GuiNebulousBeacon.class */
public class GuiNebulousBeacon extends GuiContainer {
    private static final boolean DEBUG = false;
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/nebulous_beacon.png");
    private final TileEntityNebulousBeacon tileEntity;

    public GuiNebulousBeacon(InventoryPlayer invPlayer, TileEntityNebulousBeacon tileEntity) {
        super(new ContainerNebulousBeacon(invPlayer, tileEntity));
        this.tileEntity = tileEntity;
    }

    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
        this.field_146297_k.func_110434_K().func_110577_a(TEXTURE);
        func_73729_b(this.field_147003_i, this.field_147009_r, DEBUG, DEBUG, this.field_146999_f, this.field_147000_g);
        int healthX = this.field_147003_i + 17;
        int healthY = this.field_147009_r + 24;
        func_73729_b(healthX, healthY, DEBUG, 182, (int) (141 * (this.tileEntity.getCurrentHealth() / 100.0f)), 9);
        int progressX = this.field_147003_i + 17;
        int progressY = this.field_147009_r + 58;
        func_73729_b(progressX, progressY, DEBUG, 166, (int) (141 * (this.tileEntity.getCurrentDuration() / 4800.0f)), 15);
        if (GuiUtil.isInRect(healthX, healthY, 141, 9, mouseX, mouseY)) {
            func_146279_a(this.tileEntity.getCurrentHealth() + "/100", mouseX, mouseY);
        }
        if (GuiUtil.isInRect(progressX, progressY, 141, 15, mouseX, mouseY)) {
            func_146279_a(this.tileEntity.getCurrentDuration() + "/" + TileEntityNebulousBeacon.DURATION_MAX + " J", mouseX, mouseY);
        }
    }
}
