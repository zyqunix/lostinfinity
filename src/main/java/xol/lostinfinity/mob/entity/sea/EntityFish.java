package xol.lostinfinity.mob.entity.sea;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/sea/EntityFish.class */
public class EntityFish extends EntitySeaCreature {
    BlockPos targetPos;
    private int timer;
    private boolean fed;
    private int recentFood;

    public EntityFish(World worldIn) {
        super(worldIn);
        this.targetPos = null;
        this.timer = 0;
        this.fed = false;
        this.recentFood = 0;
    }

    public void setTargetPos(BlockPos pos) {
        this.targetPos = pos;
        this.recentFood = 120;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            this.recentFood--;
            if (this.fed) {
                this.timer++;
                if (this.timer >= 400) {
                    func_145779_a(ItemInit.organicShadowMatter, 1);
                    this.fed = false;
                    this.targetPos = null;
                    this.timer = 0;
                    return;
                }
                return;
            }
            if (this.targetPos != null && this.recentFood > 0) {
                func_70605_aq().func_75642_a(this.targetPos.func_177958_n(), this.targetPos.func_177956_o(), this.targetPos.func_177952_p(), 2.0d);
            }
            if (this.field_70173_aa % 20 == 19 && this.recentFood > 0) {
                for (BlockPos check : BlockPos.func_177980_a(func_180425_c().func_177982_a(-3, -3, -3), func_180425_c().func_177982_a(3, 3, 3))) {
                    if (this.field_70170_p.func_180495_p(check).func_177230_c().equals(BlockInit.fishChow)) {
                        this.field_70170_p.func_175698_g(check);
                        this.targetPos = null;
                        this.fed = true;
                        this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.FISH_EAT, SoundCategory.HOSTILE, 1.5f, 1.0f);
                        return;
                    }
                }
            }
        }
    }
}
