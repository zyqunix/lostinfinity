package xol.lostinfinity.mob.entity.sea;

import java.util.Arrays;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/sea/EntityOctobrella.class */
public class EntityOctobrella extends EntitySeaCreature {
    public EntityOctobrella(World worldIn) {
        super(worldIn);
        this.rawFlySpeed = 0.8f;
        func_70105_a(2.5f, 2.5f);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealTrueDamage(this, func_70638_az(), func_70638_az().func_110138_aP() * 0.25f, Arrays.asList("Aquatic"));
            return true;
        }
        return false;
    }

    protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.func_184586_b(hand);
        if (stack.func_77973_b() == ItemInit.deviantEgg) {
            if (!this.field_70170_p.field_72995_K) {
                boolean found = false;
                int i = 0;
                while (true) {
                    if (i >= player.field_71071_by.func_70302_i_()) {
                        break;
                    }
                    ItemStack playerStack = player.field_71071_by.func_70301_a(i);
                    if (playerStack.func_77973_b() != ItemInit.pearlTwilight) {
                        i++;
                    } else {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    player.func_145747_a(new TextComponentString(TextFmt.Red + "A strange magical property in the pearl prevents you from collecting another."));
                } else {
                    player.func_191521_c(new ItemStack(ItemInit.pearlTwilight));
                }
            }
            stack.func_190918_g(1);
            return true;
        }
        return true;
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.OCTOBRELLA_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.OCTOBRELLA_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.OCTOBRELLA_AMBIENT;
    }
}
