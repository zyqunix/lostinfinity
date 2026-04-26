package xol.lostinfinity.mob.entity.sea;

import java.util.ArrayList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/sea/EntityPearlCollector.class */
public class EntityPearlCollector extends EntityLiving {
    private boolean game;
    private boolean win;
    private int stage;
    private int time;
    private int stageLength;
    private ArrayList<Item> pearlList;
    private boolean lose;
    Item curPearl;
    private static final int finalStage = 5;

    public EntityPearlCollector(World worldIn) {
        super(worldIn);
        this.game = false;
        this.win = false;
        this.stage = 0;
        this.time = 0;
        this.stageLength = 2000;
        this.pearlList = null;
        this.lose = false;
        this.curPearl = null;
    }

    public void startGame() {
        this.game = true;
    }

    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.func_184586_b(hand);
        if (stack.func_77973_b() == ItemInit.silverToken) {
            initPearls(player);
            if (!this.field_70170_p.field_72995_K) {
                this.game = true;
                this.stage = 0;
                this.time = 0;
                this.lose = false;
                player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Gold) + "Collect pearls for me to receive a reward!"));
            }
            stack.func_190918_g(1);
            return true;
        }
        if (!this.field_70170_p.field_72995_K && this.win) {
            player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "Here is your reward!"));
            func_145779_a(ItemInit.magicConch, 1);
            this.win = false;
            this.game = false;
            this.stage = 0;
            this.time = 0;
            return true;
        }
        if (this.lose) {
            player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Red) + "You have taken too long to find the pearl! Try again"));
            this.game = false;
            return true;
        }
        if (this.game && this.curPearl != null) {
            if (stack.func_77973_b() == this.curPearl) {
                if (!this.field_70170_p.field_72995_K) {
                    stageUp(player);
                }
                stack.func_190918_g(1);
                return true;
            }
            if (!this.field_70170_p.field_72995_K) {
                player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Red) + "You haven't given me the correct pearl!"));
                return true;
            }
            return true;
        }
        return true;
    }

    private void stageUp(EntityPlayer player) {
        if (this.stage <= finalStage) {
            this.stage++;
            nextPearl(player);
            this.time = 0;
        } else {
            this.win = true;
            player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Green) + "You have collected all the pearls, come get your reward!"));
        }
    }

    private void nextPearl(EntityPlayer player) {
        int nextPearl = 0;
        if (this.pearlList != null && !this.pearlList.isEmpty()) {
            nextPearl = this.field_70170_p.field_73012_v.nextInt(this.pearlList.size());
            this.curPearl = this.pearlList.get(nextPearl);
        }
        String pearlName = "Igneous Pearl";
        switch (nextPearl) {
            case 0:
                pearlName = "Igneous Pearl";
                break;
            case 1:
                pearlName = "Twilight Pearl";
                break;
            case 2:
                pearlName = "Bioluminescent Pearl";
                break;
        }
        if (!this.field_70170_p.field_72995_K) {
            player.func_145747_a(new TextComponentString(TextFmt.Green + pearlName + " is needed next!"));
        }
    }

    private void initPearls(EntityPlayer player) {
        this.pearlList = new ArrayList<>();
        this.pearlList.add(ItemInit.pearlIgneous);
        this.pearlList.add(ItemInit.pearlTwilight);
        this.pearlList.add(ItemInit.pearlBioluminescent);
        nextPearl(player);
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.game && this.time < this.stageLength) {
                this.time++;
                return;
            }
            if (this.game && !this.lose) {
                this.lose = true;
                for (EntityPlayer player : this.field_70170_p.func_72872_a(EntityPlayer.class, new AxisAlignedBB(func_180425_c()).func_186662_g(10.0d))) {
                    player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Bold, TextFmt.Red) + "The pearls have not been collected fast enough!"));
                }
            }
        }
    }
}
