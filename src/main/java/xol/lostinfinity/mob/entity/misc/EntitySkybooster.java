package xol.lostinfinity.mob.entity.misc;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.util.DimensionActivator;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class EntitySkybooster extends EntityLiving {
    private float scale;
    private EntityPlayer owner;
    private int timer;
    public float getMyScale() {
        return this.scale;
    }
    public EntitySkybooster(World worldIn) {
        super(worldIn);
        this.scale = 1.0f;
        this.owner = null;
        this.timer = 120;
        func_70105_a(1.0f, 1.0f);
        func_184224_h(true);
    }
    public void setOwner(EntityPlayer play) {
        this.owner = play;
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (this.scale < 3.0f) {
            this.scale += 0.05f;
        }
        if (!this.field_70170_p.field_72995_K) {
            if (this.owner == null || this.owner.field_70128_L) {
                func_70106_y();
                return;
            }
            if (this.timer % 10 == 0) {
                func_184185_a(SoundEvents.field_191244_bn, 2.0f, 0.5f + this.field_70146_Z.nextFloat());
            }
            this.owner.field_70181_x = 2.0d;
            this.owner.field_70133_I = true;
            func_70634_a(this.owner.field_70165_t, this.owner.field_70163_u + 1.5d, this.owner.field_70161_v);
            this.timer--;
            if (this.timer == 0) {
                if (this.field_70170_p.field_73011_w.func_186058_p() != DimensionInit.grandmasterOutpost) {
                    BlockPos teleto = ContestCoordinates.grandEntryPos();
                    DimensionActivator.transferEntityWithCoords(this.owner, DimensionInit.grandmasterOutpost, teleto.func_177958_n(), teleto.func_177956_o(), teleto.func_177952_p());
                    this.owner.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Gold + "Mmm, get a look at you. You'll fit right in here at the Contest of Champions."));
                    this.owner.func_145747_a(new TextComponentString(TextFmt.Aqua + "The Grandmaster: " + TextFmt.Light_Purple + "Here you can join the fun and compete to the death for our entertainment. All proceeds go to me so I can create more games."));
                }
                func_70106_y();
                return;
            }
            return;
        }
        for (int i = 0; i < 2; i++) {
            this.field_70170_p.func_175688_a(EnumParticleTypes.LAVA, this.field_70165_t, this.field_70163_u - 1.5d, this.field_70161_v, (this.field_70170_p.field_73012_v.nextDouble() - 0.5d) * 5.0d, -1.0d, (this.field_70170_p.field_73012_v.nextDouble() - 0.5d) * 5.0d, new int[0]);
        }
    }
}
