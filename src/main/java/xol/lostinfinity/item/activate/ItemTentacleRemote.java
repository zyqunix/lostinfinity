package xol.lostinfinity.item.activate;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.misc.BlockRemoteControl;
import xol.lostinfinity.block.tileentity.TileEntityRemoteControl;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.basics.ItemRemoteControl;
import xol.lostinfinity.mob.entity.misc.EntityTentacleTrap;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemTentacleRemote.class */
public class ItemTentacleRemote extends ItemRemoteControl {
    public ItemTentacleRemote(String regName) {
        super(regName);
    }

    @Override // xol.lostinfinity.item.basics.ItemRemoteControl
    public BlockRemoteControl getControlBlock() {
        return (BlockRemoteControl) BlockInit.tentacleSynthesizer;
    }

    @Override // xol.lostinfinity.item.basics.ItemRemoteControl
    public void tickEffect(TileEntityRemoteControl te, World world, BlockPos pos, EntityPlayer owner) {
        if (!world.field_72995_K && te.getExisted() % 20 == 0) {
            AxisAlignedBB aabb = new AxisAlignedBB(pos).func_186662_g(40.0d);
            List<EntityLivingBase> detected = world.func_72872_a(EntityLivingBase.class, aabb);
            List<EntityLivingBase> trapped = new ArrayList<>();
            detected.remove(owner);
            for (EntityTentacleTrap tent : world.func_72872_a(EntityTentacleTrap.class, aabb)) {
                if (tent.getTarget() != null) {
                    trapped.add(tent.getTarget());
                }
            }
            for (EntityLivingBase entity : detected) {
                if (!(entity instanceof EntityTentacleTrap) && !trapped.contains(entity)) {
                    EntityTentacleTrap trap = new EntityTentacleTrap(world);
                    trap.func_70107_b(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
                    trap.setTarget(entity);
                    trap.setOwner(owner);
                    world.func_72838_d(trap);
                    world.func_184133_a((EntityPlayer) null, entity.func_180425_c(), SoundInit.SKYCRAB_HURT, SoundCategory.HOSTILE, 1.5f, 0.8f + (world.field_73012_v.nextFloat() * 0.4f));
                }
            }
        }
    }

    @Override // xol.lostinfinity.item.basics.ItemRemoteControl
    public void toggleEffect(TileEntityRemoteControl te, World world, BlockPos pos, EntityPlayer owner, boolean active) {
    }
}
