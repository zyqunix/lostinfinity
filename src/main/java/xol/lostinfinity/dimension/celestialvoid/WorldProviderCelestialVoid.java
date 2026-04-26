package xol.lostinfinity.dimension.celestialvoid;

import javax.annotation.Nullable;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.DimensionInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/celestialvoid/WorldProviderCelestialVoid.class */
public class WorldProviderCelestialVoid extends WorldProvider {
    public void func_76572_b() {
        this.field_76578_c = new BiomeProviderCelestialVoid();
        this.field_76576_e = false;
        this.field_191067_f = false;
    }

    public IChunkGenerator func_186060_c() {
        return new ChunkGeneratorCelestialVoid(this.field_76579_a, this.field_76579_a.func_72905_C() + ((long) getDimension()));
    }

    public float func_76563_a(long worldTime, float partialTicks) {
        return 0.1f;
    }

    public boolean func_76567_e() {
        return false;
    }

    public boolean func_76569_d() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public float func_76571_f() {
        return 8.0f;
    }

    public int func_76557_i() {
        return 70;
    }

    @SideOnly(Side.CLIENT)
    public Vec3d func_76562_b(float f, float f1) {
        return new Vec3d(0.10000000149011612d, 0.10000000149011612d, 0.10000000149011612d);
    }

    public DimensionType func_186058_p() {
        return DimensionInit.celestialVoid;
    }

    @Nullable
    public String getSaveFolder() {
        return "CelestialVoid";
    }
}
