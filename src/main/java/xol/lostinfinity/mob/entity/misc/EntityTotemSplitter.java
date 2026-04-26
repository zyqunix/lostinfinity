package xol.lostinfinity.mob.entity.misc;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityTotemSplitter.class */
public class EntityTotemSplitter extends EntityLiving {
    private EntityPlayer owner;

    public EntityTotemSplitter(World worldIn) {
        super(worldIn);
        this.owner = null;
        func_70105_a(1.0f, 1.0f);
        func_184224_h(true);
    }

    public void setOwner(EntityPlayer play) {
        this.owner = play;
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            if (this.owner != null) {
                if (this.field_70173_aa % 4 == 0) {
                    for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_186662_g(20.0d))) {
                        if (!near_pl.func_184812_l_() && !near_pl.equals(this.owner)) {
                            near_pl.func_70690_d(new PotionEffect(PotionInit.ULTRAHEAVY, 10, 2));
                            near_pl.func_70690_d(new PotionEffect(PotionInit.INTANGIBLE, 10));
                            near_pl.func_70690_d(new PotionEffect(PotionInit.PHASED, 10));
                        }
                    }
                }
                if (this.field_70173_aa % 40 == 0) {
                    this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.INFUSER, SoundCategory.BLOCKS, 1.5f, 0.5f + this.field_70170_p.field_73012_v.nextFloat());
                }
                if (this.field_70173_aa > 400) {
                    func_70106_y();
                    return;
                }
                return;
            }
            func_70106_y();
        }
    }
}
