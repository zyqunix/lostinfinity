package xol.lostinfinity.block.basic;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/basic/BlockBasicBoolState.class */
public class BlockBasicBoolState extends BlockBasic {
    public static final PropertyBool ACTIVE = PropertyBool.func_177716_a("active");

    public BlockBasicBoolState(String name) {
        super(name);
        func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(ACTIVE, false));
    }

    public IBlockState func_176203_a(int meta) {
        switch (meta) {
            case 0:
                return func_176223_P().func_177226_a(ACTIVE, false);
            case 1:
                return func_176223_P().func_177226_a(ACTIVE, true);
            default:
                return func_176223_P();
        }
    }

    public int func_176201_c(IBlockState state) {
        if (!state.equals(func_176223_P().func_177226_a(ACTIVE, false)) && state.equals(func_176223_P().func_177226_a(ACTIVE, true))) {
            return 1;
        }
        return 0;
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer(this, new IProperty[]{ACTIVE});
    }
}
