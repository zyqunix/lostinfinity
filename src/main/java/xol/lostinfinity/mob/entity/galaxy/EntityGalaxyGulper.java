package xol.lostinfinity.mob.entity.galaxy;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/galaxy/EntityGalaxyGulper.class */
public class EntityGalaxyGulper extends EntityMultipleLives implements IMaxAttack, IBasicAI {
    private int pinkFeed;
    private int blueFeed;
    private int purpleFeed;
    private int pinkFeedMax;
    private int blueFeedMax;
    private int purpleFeedMax;

    public EntityGalaxyGulper(World worldIn) {
        super(worldIn);
        this.pinkFeed = 0;
        this.blueFeed = 0;
        this.purpleFeed = 0;
        func_70105_a(1.75f, 2.5f);
        Random rand = new Random();
        this.pinkFeedMax = rand.nextInt(3) + 1;
        this.blueFeedMax = rand.nextInt(3) + 1;
        this.purpleFeedMax = rand.nextInt(3) + 1;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        initBasicTasks(this);
    }

    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (!this.field_70170_p.field_72995_K) {
            ItemStack itemstack = player.func_184586_b(hand);
            String itemName = itemstack.func_77977_a();
            if (itemName.contains("watchful_eye")) {
                itemstack.func_190918_g(1);
                if (itemName.contains("pink")) {
                    if (this.pinkFeed < this.pinkFeedMax) {
                        player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Underline, TextFmt.Red) + "Nom Nom Nom"));
                        this.pinkFeed++;
                        return true;
                    }
                    player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Underline, TextFmt.Red) + "Blleeecchhh"));
                    return true;
                }
                if (itemName.contains("blue")) {
                    if (this.blueFeed < this.blueFeedMax) {
                        player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Underline, TextFmt.Red) + "Nom Nom Nom"));
                        this.blueFeed++;
                        return true;
                    }
                    player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Underline, TextFmt.Red) + "Blleeecchhh"));
                    return true;
                }
                if (itemName.contains("purple")) {
                    if (this.purpleFeed < this.purpleFeedMax) {
                        player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Underline, TextFmt.Red) + "Nom Nom Nom"));
                        this.purpleFeed++;
                        return true;
                    }
                    player.func_145747_a(new TextComponentString(TextFmt.getFormatting(TextFmt.Underline, TextFmt.Red) + "Blleeecchhh"));
                    return true;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            int degreeAge = this.field_70173_aa % 360;
            int multi = 1;
            if (degreeAge > 200 && degreeAge < 340) {
                multi = 6;
            }
            IMaxAttack.dealMaxHealth((Entity) this, func_70638_az(), 3, multi);
            return true;
        }
        return false;
    }

    public void func_70636_d() {
        if (!this.field_70170_p.field_72995_K && this.pinkFeed >= this.pinkFeedMax && this.blueFeed >= this.blueFeedMax && this.purpleFeed >= this.purpleFeedMax) {
            func_145779_a(ItemInit.acidbloodSolution, 1);
            this.pinkFeed = 0;
            this.blueFeed = 0;
            this.purpleFeed = 0;
            Random rand = new Random();
            this.pinkFeedMax = rand.nextInt(3) + 1;
            this.blueFeedMax = rand.nextInt(3) + 1;
            this.purpleFeedMax = rand.nextInt(3) + 1;
        }
        super.func_70636_d();
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5500.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.GALAXY_GULPER_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.GALAXY_GULPER_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.GALAXY_GULPER_AMBIENT;
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_GALAXYGULPER;
    }
}
