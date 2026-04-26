package xol.lostinfinity.block.tileentity;

import java.util.ArrayList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityMelodicSequencer.class */
public class TileEntityMelodicSequencer extends TileEntity implements ITickable {
    private static int finalRound = 2;
    ArrayList<SoundEvent> notes = null;
    ArrayList<Integer> sequence = new ArrayList<>();
    private boolean roundStart = false;
    private int remainingFails = 0;
    private int round = 0;
    private int sequenceIndex = 0;
    private int noteTimer = 30;
    private int roundDelay = 0;
    private int time = 0;
    private EntityPlayer player = null;
    private boolean finishedRound = true;
    private int playerSequenceIndex = 0;
    private boolean game = false;

    public void func_73660_a() {
        if (this.game && this.notes != null && this.notes.size() == 5 && this.roundStart && this.finishedRound) {
            if (this.roundDelay > 0) {
                this.roundDelay--;
                return;
            }
            if (this.time < this.noteTimer) {
                this.time++;
                return;
            }
            this.time = 0;
            this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, this.notes.get(this.sequence.get(this.sequenceIndex).intValue()), SoundCategory.BLOCKS, 0.8f, 1.0f);
            this.sequenceIndex++;
            if (this.sequenceIndex == this.sequence.size()) {
                this.sequenceIndex = 0;
                this.roundStart = false;
                this.finishedRound = false;
            }
        }
    }

    public boolean playNote(int meta, BlockPos pos, EntityPlayer player) {
        this.player = player;
        if (!this.roundStart && this.notes != null && this.notes.size() > meta) {
            this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, this.notes.get(meta), SoundCategory.BLOCKS, 0.8f, 1.0f);
            if (meta == this.sequence.get(this.playerSequenceIndex).intValue()) {
                this.playerSequenceIndex++;
                if (this.playerSequenceIndex == this.sequence.size()) {
                    roundUp();
                    return true;
                }
                return true;
            }
            if (this.remainingFails > 0) {
                this.remainingFails--;
                this.playerSequenceIndex = 0;
                player.func_145747_a(new TextComponentString((this.remainingFails + 1) + " fails remaining. Try again!"));
                return true;
            }
            player.func_145747_a(new TextComponentString("Failed sequence. No attempts remaining"));
            this.game = false;
            return true;
        }
        return false;
    }

    private void roundUp() {
        this.finishedRound = true;
        if (this.round < finalRound) {
            if (this.player != null) {
                this.player.func_145747_a(new TextComponentString("Round complete! Repeat the next sequence."));
            }
            this.round++;
            generateSequence();
            this.roundStart = true;
            this.roundDelay = 40;
            this.playerSequenceIndex = 0;
            this.noteTimer -= 4;
            return;
        }
        if (this.player != null) {
            this.player.func_145747_a(new TextComponentString("All sequences complete! The disc has been configured."));
        }
        this.game = false;
        this.notes = null;
        EntityItem reward = new EntityItem(this.field_145850_b, this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p(), new ItemStack(ItemInit.audioSynchronizedDisc, 1));
        reward.field_70159_w = 0.0d;
        reward.field_70181_x = 0.0d;
        reward.field_70179_y = 0.0d;
        this.field_145850_b.func_72838_d(reward);
    }

    public static ArrayList<SoundEvent> getNotes() {
        ArrayList<SoundEvent> noteList = new ArrayList<>();
        noteList.add(SoundInit.NOTE_TYPE_1);
        noteList.add(SoundInit.NOTE_TYPE_2);
        noteList.add(SoundInit.NOTE_TYPE_3);
        noteList.add(SoundInit.NOTE_TYPE_4);
        noteList.add(SoundInit.NOTE_TYPE_5);
        return noteList;
    }

    private void initSounds() {
        this.notes = getNotes();
    }

    public void startGame() {
        this.remainingFails = 5;
        this.game = true;
        this.finishedRound = true;
        this.round = 0;
        this.playerSequenceIndex = 0;
        this.roundStart = true;
        this.noteTimer = 30;
        initSounds();
        generateSequence();
    }

    private void generateSequence() {
        if (this.notes != null) {
            this.sequence.clear();
            int length = (this.round * 1) + 3;
            for (int i = 0; i < length; i++) {
                int randSound = this.field_145850_b.field_73012_v.nextInt(this.notes.size());
                this.sequence.add(Integer.valueOf(randSound));
            }
        }
    }
}
