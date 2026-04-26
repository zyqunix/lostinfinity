package xol.lostinfinity.mob.entity.starforge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityEssenceIdol.class */
public class EntityEssenceIdol extends EntityCreature {
    private BlockPos gameReference;
    private List<BlockPos> oreLocations;
    private int gameLife;
    private int gameDifficulty;

    public EntityEssenceIdol(World worldIn) {
        super(worldIn);
        this.gameReference = null;
        this.oreLocations = new ArrayList();
        this.gameLife = 1000;
        this.gameDifficulty = 0;
        func_70105_a(2.75f, 5.0f);
    }

    protected void func_184651_r() {
    }

    public void setGameRef(BlockPos pos, int difficulty) {
        this.gameDifficulty = difficulty;
        this.gameReference = pos;
        fillOreLocations();
    }

    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.func_184586_b(hand);
        if (stack.func_77973_b().equals(getHealItem())) {
            this.gameLife = Integer.min(this.gameLife + (this.gameDifficulty == 0 ? 250 : 125), 1000);
            stack.func_190918_g(1);
            func_184185_a(SoundInit.ESSENCE_IDOL_RESTORE, 1.0f, 0.75f + (this.field_70146_Z.nextFloat() * 0.5f));
            return true;
        }
        return true;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa > 5) {
            if (this.gameReference == null || this.gameLife <= 0) {
                deathEffect();
                return;
            }
            if (this.field_70173_aa >= 3600) {
                win();
                return;
            }
            if (this.field_70173_aa % 140 == 130) {
                randomlyLight();
            }
            if (this.field_70173_aa % 20 == 0) {
                func_70691_i(200.0f);
            }
            if (this.field_70173_aa % 40 == 0) {
                this.gameLife -= 25;
            }
            if (this.field_70173_aa % 100 == 0) {
                messagePlayers((this.gameLife > 250 ? TextFmt.Gold : TextFmt.Red) + "The idol has " + this.gameLife + " energy remaining.");
            }
        }
    }

    public void fillOreLocations() {
        for (int xoff = -32; xoff < 32; xoff++) {
            for (int zoff = -32; zoff < 32; zoff++) {
                BlockPos testpos = this.gameReference.func_177982_a(xoff, 0, zoff);
                if (this.field_70170_p.func_180495_p(testpos).func_177230_c() == getOffBlock()) {
                    this.oreLocations.add(testpos);
                } else if (this.field_70170_p.func_180495_p(testpos).func_177230_c() == getOnBlock()) {
                    this.oreLocations.add(testpos);
                }
            }
        }
    }

    private void messagePlayers(String message) {
        AxisAlignedBB aabb = new AxisAlignedBB(this.gameReference.func_177982_a(-32, -5, -32), this.gameReference.func_177982_a(32, 15, 32));
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, aabb)) {
            contender.func_145747_a(new TextComponentString(message));
        }
    }

    private void randomlyLight() {
        int currently_lit = 0;
        Collections.shuffle(this.oreLocations);
        for (BlockPos pos : this.oreLocations) {
            if ((currently_lit <= 4 || this.field_70146_Z.nextInt(4) == 0) && currently_lit <= 8) {
                this.field_70170_p.func_175656_a(pos, getOnBlock().func_176223_P());
                currently_lit++;
            } else {
                this.field_70170_p.func_175656_a(pos, getOffBlock().func_176223_P());
            }
        }
    }

    private Block getOffBlock() {
        if (this.gameDifficulty == 0) {
            return BlockInit.lumioOreEmpty;
        }
        return BlockInit.gloominessenceOreEmpty;
    }

    private Block getOnBlock() {
        if (this.gameDifficulty == 0) {
            return BlockInit.lumioOre;
        }
        return BlockInit.gloominessenceOre;
    }

    private Item getHealItem() {
        if (this.gameDifficulty == 0) {
            return ItemInit.luminessence;
        }
        return ItemInit.gloominessence;
    }

    public void darkenAll() {
        for (BlockPos pos : this.oreLocations) {
            if (this.field_70170_p.func_180495_p(pos).func_177230_c() == getOnBlock()) {
                this.field_70170_p.func_175656_a(pos, getOffBlock().func_176223_P());
            }
        }
    }

    private void deathEffect() {
        func_70106_y();
        if (this.gameReference != null) {
            darkenAll();
        }
    }

    private void win() {
        deathEffect();
        if (this.gameDifficulty == 0) {
            func_145779_a(ItemInit.luminescentCubes, this.field_70146_Z.nextInt(10) + 5);
        } else {
            func_145779_a(ItemInit.gloominessenceCubes, this.field_70146_Z.nextInt(10) + 5);
        }
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2000.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0d);
    }

    protected SoundEvent func_184615_bR() {
        return null;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return null;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.ESSENCE_IDOL_AMBIENT;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public int func_70641_bl() {
        return 1;
    }
}
