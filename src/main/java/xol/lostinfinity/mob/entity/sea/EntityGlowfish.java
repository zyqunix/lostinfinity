package xol.lostinfinity.mob.entity.sea;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
public class EntityGlowfish extends EntitySeaCreature {
    public EntityGlowfish(World worldIn) {
        super(worldIn);
        func_70105_a(1.0f, 1.0f);
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.LONGFIN_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.LONGFIN_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.LONGFIN_AMBIENT;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        EntityPlayer player;
        if (!this.field_70170_p.field_72995_K && (player = this.field_70170_p.func_184137_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, 15.0d, false)) != null) {
            boolean found = false;
            int i = 0;
            while (true) {
                if (i >= player.field_71071_by.func_70302_i_()) {
                    break;
                }
                ItemStack playerStack = player.field_71071_by.func_70301_a(i);
                if (playerStack.func_77973_b() != ItemInit.pearlBioluminescent) {
                    i++;
                } else {
                    found = true;
                    break;
                }
            }
            if (found) {
                player.func_145747_a(new TextComponentString(TextFmt.Red + "A strange magical property in the pearl prevents you from collecting another."));
            } else {
                player.func_191521_c(new ItemStack(ItemInit.pearlBioluminescent));
            }
        }
        super.trueDeathAction();
    }
}
