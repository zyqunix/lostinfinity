package xol.lostinfinity.mob.entity.contest.operator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase;
public class EntityOperatorBase extends EntityCreature {
    protected int style;
    protected int timeSinceSwitch;
    protected int gameStartCountdown;
    protected List<UUID> contenders;
    public EntityOperatorBase(World worldIn) {
        super(worldIn);
        this.timeSinceSwitch = 0;
        this.gameStartCountdown = -1;
        this.contenders = new ArrayList();
    }
    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0f, 1.0f));
    }
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74768_a("ArenaStyle", this.style);
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        this.style = tag.func_74762_e("ArenaStyle");
    }
    public boolean func_70104_M() {
        return false;
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.timeSinceSwitch > 0) {
                this.timeSinceSwitch--;
            }
            if (this.gameStartCountdown >= 0) {
                if (this.gameStartCountdown == 0) {
                    if (canStartGame()) {
                        startGame();
                    } else {
                        notEnoughPlayers();
                    }
                } else if (this.gameStartCountdown % 20 == 0) {
                    gameStartWarning();
                }
                this.gameStartCountdown--;
            }
        }
    }
    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (!this.field_70170_p.field_72995_K) {
            if (isGameInProgress() || this.gameStartCountdown >= 0) {
                player.func_145747_a(new TextComponentString(TextFmt.Red + "A match is currently in progress."));
                return true;
            }
            if (this.timeSinceSwitch == 0) {
                Item held = player.func_184586_b(hand).func_77973_b();
                if (held.equals(Items.field_190931_a)) {
                    player.func_145747_a(new TextComponentString("Arena Randomized"));
                    incrementStyle();
                    generateArena();
                    this.timeSinceSwitch = 120;
                    return true;
                }
                if (held.equals(ItemInit.contenderPass)) {
                    this.gameStartCountdown = 200;
                    arenaClear();
                    generateArena();
                    return true;
                }
                return true;
            }
            player.func_145747_a(new TextComponentString(TextFmt.Red + "Operator: The arena was just changed. Be patient mortal."));
            return true;
        }
        return true;
    }
    protected AxisAlignedBB getArenaAABB() {
        return null;
    }
    protected AxisAlignedBB getLobbyAABB() {
        return null;
    }
    protected void generateArena() {
    }
    protected void incrementStyle() {
    }
    protected void arenaClear() {
    }
    protected boolean isGameInProgress() {
        Iterator it = this.field_70170_p.func_72872_a(EntityControllerBase.class, getArenaAABB()).iterator();
        if (it.hasNext()) {
            return true;
        }
        return false;
    }
    protected void gameStartWarning() {
        func_184185_a(SoundInit.GENERIC_WEAPON_3, 1.0f, 1.0f);
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getLobbyAABB().func_72314_b(5.0d, 0.0d, 5.0d))) {
            near_pl.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Gold) + "A match will begin in: " + Math.floorDiv(this.gameStartCountdown, 20)));
        }
    }
    protected boolean canStartGame() {
        int pl_count = 0;
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getLobbyAABB())) {
            int i = 0;
            while (true) {
                if (i >= near_pl.field_71071_by.func_70302_i_()) {
                    break;
                }
                if (!near_pl.field_71071_by.func_70301_a(i).func_77973_b().equals(ItemInit.contenderPass)) {
                    i++;
                } else {
                    pl_count++;
                    this.contenders.add(near_pl.func_110124_au());
                    near_pl.field_71071_by.func_70299_a(i, ItemStack.field_190927_a);
                    break;
                }
            }
        }
        return pl_count >= 2;
    }
    private void notEnoughPlayers() {
        UUID lonerUUID = this.contenders.get(0);
        EntityPlayer loner = this.field_70170_p.func_152378_a(lonerUUID);
        loner.field_71071_by.func_70299_a(loner.field_71071_by.func_70447_i(), new ItemStack(ItemInit.contenderPass));
        this.contenders.clear();
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getLobbyAABB())) {
            near_pl.func_145747_a(new TextComponentString(TextFmt.Red + "Not enough players."));
        }
    }
    protected void startGame() {
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
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20000.0d);
        this.field_70144_Y = 1.0f;
        func_174813_aQ().func_186664_h(100.0d);
        func_184224_h(true);
    }
    public boolean func_70692_ba() {
        return false;
    }
    public boolean func_70601_bi() {
        return true;
    }
}
