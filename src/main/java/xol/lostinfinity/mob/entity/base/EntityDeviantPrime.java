package xol.lostinfinity.mob.entity.base;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityDeviantPrime extends EntityMultipleLives implements IMaxAttack {
    public EntityDeviantPrime(World worldIn) {
        super(worldIn);
    }
    protected boolean func_70692_ba() {
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 25;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(25.0d, 25.0d, 25.0d))) {
                near_pl.func_145747_a(new TextComponentString(TextFmt.Dark_Aqua + "Prime Deviant " + primeName() + ": I'll return for you."));
            }
            func_145779_a(primeDrop(), 1);
        }
    }
    protected String primeName() {
        return "";
    }
    protected Item primeDrop() {
        return null;
    }
}
