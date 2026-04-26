package xol.lostinfinity.block.basic;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/basic/BlockBasicMoistured.class */
public class BlockBasicMoistured extends BlockBasic {
    private Block replacementBlock;
    private boolean requiresMoisture;
    private BlockFluidBase mositureLiquid;

    public BlockBasicMoistured(String name, boolean moisture) {
        super(name);
        this.replacementBlock = null;
        this.requiresMoisture = false;
        this.mositureLiquid = null;
        func_149675_a(true);
        this.requiresMoisture = moisture;
    }

    public void setReplacementAndLiquid(Block replace, BlockFluidBase liquid) {
        this.replacementBlock = replace;
        this.mositureLiquid = liquid;
    }

    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        boolean hasMoisture = false;
        if (worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() == this.mositureLiquid) {
            hasMoisture = true;
        } else {
            for (int x = -2; x <= 2; x++) {
                for (int z = -2; z <= 2; z++) {
                    if (worldIn.func_180495_p(pos.func_177982_a(x, 0, z)).func_177230_c() == this.mositureLiquid) {
                        hasMoisture = true;
                    }
                }
            }
        }
        if ((hasMoisture && !this.requiresMoisture) || (!hasMoisture && this.requiresMoisture)) {
            worldIn.func_175656_a(pos, this.replacementBlock.func_176223_P());
        }
    }
}
