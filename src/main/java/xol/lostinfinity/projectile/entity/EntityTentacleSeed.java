package xol.lostinfinity.projectile.entity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
public class EntityTentacleSeed extends EntityBaseThrowable {
    public EntityTentacleSeed(World par1World) {
        super(par1World);
    }
    public EntityTentacleSeed(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }
    public EntityTentacleSeed(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72313_a == RayTraceResult.Type.BLOCK) {
                BlockPos startPos = result.func_178782_a();
                ArrayList<BlockPos> visited = new ArrayList<>();
                visited.add(startPos);
                for (int count = 0; count < 30; count++) {
                    ArrayList<BlockPos> neighbours = getNeighbours(startPos);
                    Collections.shuffle(neighbours);
                    Iterator<BlockPos> it = neighbours.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            BlockPos neighbour = it.next();
                            if (!visited.contains(neighbour)) {
                                Boolean canAdd = true;
                                Iterator<BlockPos> it2 = getNeighbours(neighbour).iterator();
                                while (true) {
                                    if (!it2.hasNext()) {
                                        break;
                                    }
                                    BlockPos subNeighbour = it2.next();
                                    if (visited.contains(subNeighbour)) {
                                        canAdd = false;
                                        break;
                                    }
                                }
                                if (canAdd.booleanValue()) {
                                    this.field_70170_p.func_175656_a(neighbour, BlockInit.viralGrowth.func_176223_P());
                                    startPos = neighbour;
                                    visited.add(neighbour);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            func_70106_y();
        }
    }
    private ArrayList<BlockPos> getNeighbours(BlockPos pos) {
        ArrayList<BlockPos> neighbours = new ArrayList<>();
        ArrayList<BlockPos> positions = new ArrayList<>();
        positions.add(pos.func_177982_a(1, 0, 0));
        positions.add(pos.func_177982_a(-1, 0, 0));
        positions.add(pos.func_177982_a(0, 1, 0));
        positions.add(pos.func_177982_a(0, -1, 0));
        positions.add(pos.func_177982_a(0, 0, 1));
        positions.add(pos.func_177982_a(0, 0, -1));
        for (BlockPos position : positions) {
            if (this.field_70170_p.func_175623_d(position)) {
                neighbours.add(position);
            }
        }
        return neighbours;
    }
    protected float func_70185_h() {
        return 0.05f;
    }
}
