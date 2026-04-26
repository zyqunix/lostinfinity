package xol.lostinfinity.item.activate;

import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nullable;
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
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemRemoteControl;
import xol.lostinfinity.projectile.entity.EntityMicroRocket;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemMicroRemote.class */
public class ItemMicroRemote extends ItemRemoteControl {
    private static final int GROUP_RADIUS = 20;
    private static final int TARGETING_RADIUS = 30;

    public ItemMicroRemote(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXMATS);
    }

    @Override // xol.lostinfinity.item.basics.ItemRemoteControl
    @Nullable
    public BlockRemoteControl getControlBlock() {
        return (BlockRemoteControl) BlockInit.microRocket;
    }

    @Override // xol.lostinfinity.item.basics.ItemRemoteControl
    public void tickEffect(TileEntityRemoteControl te, World world, BlockPos pos, EntityPlayer owner) {
        if (!world.field_72995_K && te.getExisted() % 60 == 0) {
            boolean toShoot = false;
            Iterator it = ((ArrayList) world.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(pos.func_177958_n() - 30, pos.func_177956_o(), pos.func_177952_p() - 30, pos.func_177958_n() + 30, pos.func_177956_o() + 30, pos.func_177952_p() + 30))).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                EntityLivingBase target = (EntityLivingBase) it.next();
                if (!target.func_110124_au().equals(owner.func_110124_au())) {
                    toShoot = true;
                    break;
                }
            }
            if (toShoot) {
                Iterable<BlockPos> blocks = BlockPos.func_177980_a(pos.func_177982_a(-20, -10, -20), pos.func_177982_a(GROUP_RADIUS, GROUP_RADIUS, GROUP_RADIUS));
                for (BlockPos blockPos : blocks) {
                    if (world.func_180495_p(blockPos).func_177230_c() == BlockInit.microRocket) {
                        EntityMicroRocket shot2 = new EntityMicroRocket(world, ((double) blockPos.func_177958_n()) + 0.5d, ((double) blockPos.func_177956_o()) + 1.5d, ((double) blockPos.func_177952_p()) + 0.5d);
                        shot2.setThrower(owner);
                        shot2.setTargetingHeight(pos.func_177956_o());
                        world.func_72838_d(shot2);
                        world.func_184133_a((EntityPlayer) null, blockPos, SoundInit.MISSILE_LAUNCH, SoundCategory.BLOCKS, 1.0f, 0.7f + (world.field_73012_v.nextFloat() * 0.6f));
                    }
                }
            }
        }
    }

    @Override // xol.lostinfinity.item.basics.ItemRemoteControl
    public void toggleEffect(TileEntityRemoteControl te, World worldIn, BlockPos checkpos, EntityPlayer owner, boolean active) {
    }
}
