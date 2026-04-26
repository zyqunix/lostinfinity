package xol.lostinfinity.dimension.util;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/util/BasicTeleporter.class */
public class BasicTeleporter extends Teleporter {
    private final Long2ObjectMap<Teleporter.PortalPosition> destinationCoordinateCache;
    private double goToX;
    private double goToY;
    private double goToZ;
    private boolean strict;

    public BasicTeleporter(WorldServer par1WorldServer, double xpos, double ypos, double zpos) {
        super(par1WorldServer);
        this.destinationCoordinateCache = new Long2ObjectOpenHashMap(4096);
        this.strict = false;
        new Random(par1WorldServer.func_72905_C());
        this.goToX = xpos;
        this.goToY = ypos;
        this.goToZ = zpos;
    }

    public BasicTeleporter(WorldServer par1WorldServer, double xpos, double ypos, double zpos, boolean strictSet) {
        super(par1WorldServer);
        this.destinationCoordinateCache = new Long2ObjectOpenHashMap(4096);
        this.strict = false;
        new Random(par1WorldServer.func_72905_C());
        this.goToX = xpos;
        this.goToY = ypos;
        this.goToZ = zpos;
        this.strict = strictSet;
    }

    public boolean func_85188_a(Entity p_85188_1_) {
        return false;
    }

    public boolean func_180620_b(Entity entityIn, float p_180620_2_) {
        return false;
    }

    public void func_180266_a(Entity entityIn, float rotationYaw) {
        if (entityIn.field_70170_p.field_73011_w.func_186058_p() == DimensionType.OVERWORLD || this.strict) {
            entityIn.func_70012_b(this.goToX, this.goToY, this.goToZ, entityIn.field_70177_z, 0.0f);
        } else {
            if (!(entityIn instanceof EntityPlayer)) {
                return;
            }
            EntityPlayer player = (EntityPlayer) entityIn;
            BlockPos bed = player.getBedLocation(0);
            if (bed == null) {
                bed = this.field_85192_a.func_175694_M();
            }
            for (int i = bed.func_177956_o(); i < 255; i++) {
                if (this.field_85192_a.func_180495_p(new BlockPos(bed.func_177958_n(), i, bed.func_177952_p())).func_177230_c() == Blocks.field_150350_a && (this.field_85192_a.func_180495_p(new BlockPos(bed.func_177958_n(), i - 1, bed.func_177952_p())) != Blocks.field_150350_a || i == bed.func_177956_o())) {
                    player.func_70634_a(bed.func_177958_n(), i, bed.func_177952_p());
                    break;
                }
            }
        }
        entityIn.field_70179_y = 0.0d;
        entityIn.field_70181_x = 0.0d;
        entityIn.field_70159_w = 0.0d;
    }

    public void func_85189_a(long worldTime) {
        if (worldTime % 100 == 0) {
            long i = worldTime - 300;
            ObjectIterator<Teleporter.PortalPosition> objectiterator = this.destinationCoordinateCache.values().iterator();
            while (objectiterator.hasNext()) {
                Teleporter.PortalPosition teleporter$portalposition = (Teleporter.PortalPosition) objectiterator.next();
                if (teleporter$portalposition == null || teleporter$portalposition.field_85087_d < i) {
                    objectiterator.remove();
                }
            }
        }
    }
}
