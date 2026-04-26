package xol.lostinfinity.mob.entity.fungal;

import java.util.Random;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/fungal/EntityMushmerraClone.class */
public class EntityMushmerraClone extends EntityMob {
    private float alpha;

    public EntityMushmerraClone(World worldIn) {
        super(worldIn);
        if (new Random().nextBoolean()) {
            this.alpha = 0.0f;
        } else {
            this.alpha = 1.0f;
        }
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.alpha < 1.0f && this.field_70173_aa > 120 && this.field_70173_aa < 160) {
            fadeIn();
            return;
        }
        if (this.alpha > 0.0f && this.field_70173_aa > 500) {
            fadeOut();
        } else if (this.field_70173_aa > 540) {
            func_70106_y();
        }
    }

    protected void func_184651_r() {
        super.func_184651_r();
        this.field_70714_bg.func_75776_a(0, new EntityAIWanderAvoidWater(this, 1.0d));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23000000417232513d);
    }

    public float getAlpha() {
        return this.alpha;
    }

    private void fadeIn() {
        this.alpha += 0.05f;
    }

    private void fadeOut() {
        this.alpha -= 0.05f;
    }
}
