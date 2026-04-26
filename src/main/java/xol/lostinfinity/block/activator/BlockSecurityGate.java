package xol.lostinfinity.block.activator;

import java.util.Map;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.tool.ItemSecurityPass;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockSecurityGate.class */
public class BlockSecurityGate extends BlockBasic {
    private int securityLevel;

    public BlockSecurityGate(String name, int security) {
        super(name);
        this.securityLevel = 0;
        this.securityLevel = security;
        func_149715_a(1.0f);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            Item heldItem = playerIn.func_184614_ca().func_77973_b();
            if (heldItem instanceof ItemSecurityPass) {
                ItemSecurityPass pass = (ItemSecurityPass) heldItem;
                int passLevel = pass.getSecurityLevel();
                if (passLevel >= this.securityLevel) {
                    playerIn.func_70690_d(new PotionEffect(PotionInit.SECURITY_CLEARANCE, 300, this.securityLevel));
                    worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.SECURITY_CLEAR, SoundCategory.MASTER, 1.0f, 1.0f);
                    Map<BlockPos, String> specialGates = GalaxyCoordinates.securityWithMSG();
                    for (Map.Entry<BlockPos, String> entry : specialGates.entrySet()) {
                        if (posNearAABB(pos, entry.getKey())) {
                            playerIn.func_145747_a(new TextComponentString(entry.getValue()));
                        }
                    }
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }

    private boolean posNearAABB(BlockPos posGate, BlockPos check) {
        AxisAlignedBB aabb = new AxisAlignedBB(check).func_186662_g(2.0d);
        Vec3d vec = new Vec3d(posGate);
        return aabb.func_72318_a(vec);
    }
}
