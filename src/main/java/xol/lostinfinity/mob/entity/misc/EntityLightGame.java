package xol.lostinfinity.mob.entity.misc;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.data.LightSwitchGameGenerator;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityLightGame.class */
public class EntityLightGame extends EntityLiving {
    private boolean lit;
    private List<BlockPos> lights;
    private List<BlockPos> switches;
    private List<Boolean> switchStates;
    private LightSwitchGameGenerator game;

    public EntityLightGame(World worldIn) {
        super(worldIn);
        this.lit = false;
        this.lights = new ArrayList();
        this.switches = new ArrayList();
        this.switchStates = new ArrayList();
    }

    public EntityLightGame(World worldIn, List<BlockPos> lights, List<BlockPos> switches) {
        super(worldIn);
        this.lit = false;
        this.lights = new ArrayList();
        this.switches = new ArrayList();
        this.switchStates = new ArrayList();
        this.lights = lights;
        this.switches = switches;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.game == null) {
                int numSwitches = this.switches.size();
                int numLights = this.lights.size();
                if (numSwitches == 0 || numLights == 0) {
                    func_70106_y();
                    return;
                }
                this.game = new LightSwitchGameGenerator(numSwitches, numLights);
                for (int j = 0; j < numSwitches; j++) {
                    boolean switched = this.game.getSwitch(j).isSwitched();
                    if (switched) {
                        this.field_70170_p.func_175656_a(this.switches.get(j), BlockInit.lightSwitchOn.func_176223_P());
                        this.switchStates.add(true);
                    } else {
                        this.field_70170_p.func_175656_a(this.switches.get(j), BlockInit.lightSwitchOff.func_176223_P());
                        this.switchStates.add(false);
                    }
                }
                for (int j2 = 0; j2 < numLights; j2++) {
                    boolean lightLit = this.game.getLight(j2).isLit();
                    if (lightLit) {
                        this.field_70170_p.func_175656_a(this.lights.get(j2), BlockInit.switchableLightOn.func_176223_P());
                    } else {
                        this.field_70170_p.func_175656_a(this.lights.get(j2), BlockInit.switchableLightOff.func_176223_P());
                    }
                }
                return;
            }
            int numSwitches2 = this.switches.size();
            int numLights2 = this.lights.size();
            if (numSwitches2 == 0 || numLights2 == 0) {
                func_70106_y();
                return;
            }
            for (int j3 = 0; j3 < numSwitches2; j3++) {
                boolean switched2 = this.switchStates.get(j3).booleanValue();
                boolean toggled = false;
                if (switched2) {
                    if (this.field_70170_p.func_180495_p(this.switches.get(j3)) == BlockInit.lightSwitchOff.func_176223_P()) {
                        this.switchStates.set(j3, false);
                        toggled = true;
                    }
                } else if (this.field_70170_p.func_180495_p(this.switches.get(j3)) == BlockInit.lightSwitchOn.func_176223_P()) {
                    this.switchStates.set(j3, true);
                    toggled = true;
                }
                if (toggled) {
                    boolean[] lightsToToggle = this.game.getSwitch(j3).getLights();
                    toggle(lightsToToggle);
                }
            }
        }
    }

    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        ItemStack held = player.func_184586_b(hand);
        if (held.func_77973_b() == ItemInit.digitalPanel) {
            if (win()) {
                if (!this.field_70170_p.field_72995_K) {
                    player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "Light Watcher: I have left you a reward!"));
                    func_145779_a(ItemInit.holoscreen, 1);
                    func_70106_y();
                }
                held.func_190918_g(1);
                return true;
            }
            if (!this.field_70170_p.field_72995_K) {
                player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Red) + "Light Watcher: The lights are not yet lit. Try again."));
                return true;
            }
            return true;
        }
        return true;
    }

    private boolean win() {
        boolean won = true;
        for (int i = 0; i < this.lights.size(); i++) {
            IBlockState lightState = this.field_70170_p.func_180495_p(this.lights.get(i));
            if (lightState != BlockInit.switchableLightOn.func_176223_P()) {
                won = false;
            }
        }
        return won;
    }

    private void toggle(boolean[] lightsToToggle) {
        for (int i = 0; i < lightsToToggle.length; i++) {
            BlockPos lightPos = this.lights.get(i);
            IBlockState lightState = this.field_70170_p.func_180495_p(lightPos);
            if (lightsToToggle[i]) {
                if (lightState == BlockInit.switchableLightOff.func_176223_P()) {
                    this.field_70170_p.func_175656_a(lightPos, BlockInit.switchableLightOn.func_176223_P());
                } else {
                    this.field_70170_p.func_175656_a(lightPos, BlockInit.switchableLightOff.func_176223_P());
                }
            }
        }
    }

    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187910_gj;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187912_gl;
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187911_gk;
    }
}
