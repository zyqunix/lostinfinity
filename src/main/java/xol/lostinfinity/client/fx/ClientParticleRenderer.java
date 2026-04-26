package xol.lostinfinity.client.fx;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.misc.BlockCthulhuSpawner;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.item.weapon.ItemHeadCollector;
import xol.lostinfinity.util.data.CustomParticleConfig;
@SideOnly(Side.CLIENT)
public class ClientParticleRenderer {
    public static void renderSimple(int id, int extra, double posX, double posY, double posZ) {
        World world = Minecraft.func_71410_x().field_71439_g.field_70170_p;
        Random rand = world.field_73012_v;
        switch (id) {
            case 0:
                for (int i = 0; i < 10; i++) {
                    world.func_175682_a(ParticleInit.VENOM, true, posX + getROD(rand, 4.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 4.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                for (int i2 = 0; i2 < 5; i2++) {
                    world.func_175682_a(ParticleInit.VENOM_RING, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 1:
                for (int i3 = 0; i3 < 4; i3++) {
                    world.func_175682_a(ParticleInit.PLASMA_EXPLOSION, true, posX, posY, posZ, 0.0d, 0.0d, 0.0d, new int[]{2});
                }
                break;
            case 2:
                for (int i4 = 0; i4 < 5 + extra; i4++) {
                    world.func_175682_a(EnumParticleTypes.DRAGON_BREATH, true, posX + getROD(rand, 1.0d), posY + 0.75d, posZ + getROD(rand, 1.0d), getROD(rand, 1.0d), Math.abs(getROD(rand, 1.0d)), getROD(rand, 1.0d), new int[0]);
                }
                if (extra != 0) {
                    world.func_175682_a(EnumParticleTypes.EXPLOSION_HUGE, true, posX, posY, posZ, 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 3:
                for (int i5 = 0; i5 < 10; i5++) {
                    world.func_175682_a(ParticleInit.QUANTUM_MARK, true, posX + getROD(rand, 2.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 2.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case TileEntityFusionTable.BOARD_ROWS :
                for (int i6 = 0; i6 < 3; i6++) {
                    world.func_175682_a(ParticleInit.ATTRACT_FIELD, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 5:
                for (int i7 = 0; i7 < 3; i7++) {
                    world.func_175682_a(ParticleInit.REPEL_FIELD, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case TileEntityFusionTable.BOARD_COLUMNS :
                for (int i8 = 0; i8 < 2; i8++) {
                    world.func_175682_a(ParticleInit.SLAM, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 7:
                world.func_175682_a(ParticleInit.WARP, true, posX, posY, posZ, 0.0d, 0.0d, 0.0d, new int[0]);
                break;
            case 8:
                for (int i9 = 0; i9 < 5; i9++) {
                    world.func_175682_a(ParticleInit.GOO_RING, true, posX + getROD(rand, 2.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 2.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 9:
                world.func_175682_a(ParticleInit.EXPLOSION, true, posX, posY, posZ, 0.0d, 0.0d, 0.0d, new int[0]);
                break;
            case ItemHeadCollector.CHARGE_LIMIT :
                for (int i10 = 0; i10 < 3; i10++) {
                    world.func_175682_a(ParticleInit.EXPLOSION_RING, true, posX + getROD(rand, 2.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 2.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 11:
                for (int i11 = 0; i11 < 5; i11++) {
                    world.func_175682_a(ParticleInit.ZAP, true, posX + getROD(rand, 3.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 3.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 12:
                world.func_175682_a(ParticleInit.DARK_MAGIC, true, posX, posY, posZ, 0.0d, 0.0d, 0.0d, new int[0]);
                world.func_175682_a(ParticleInit.PLAGUE, true, posX, posY, posZ, 0.0d, 0.0d, 0.0d, new int[0]);
                break;
            case 13:
                for (int i12 = 0; i12 < 3; i12++) {
                    world.func_175682_a(ParticleInit.GLOOM_BURST, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 14:
                for (int i13 = 0; i13 < 10; i13++) {
                    world.func_175682_a(ParticleInit.LIGHT_FLASH, true, posX + getROD(rand, 4.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 4.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 15:
                for (int i14 = 0; i14 < 10; i14++) {
                    world.func_175682_a(ParticleInit.DARK_FLASH, true, posX + getROD(rand, 4.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 4.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 16:
                for (int i15 = 0; i15 < 1 + extra; i15++) {
                    world.func_175682_a(ParticleInit.FLAME_SMALL, true, posX + getROD(rand, 1.0d), posY, posZ + getROD(rand, 1.0d), 0.30000001192092896d * getROD(rand, 1.0d), 0.0d, 0.30000001192092896d * getROD(rand, 1.0d), new int[0]);
                }
                break;
            case 17:
                for (int i16 = 0; i16 < 1 + (2 * extra); i16++) {
                    world.func_175682_a(ParticleInit.FLAME_MEDIUM, true, posX + getROD(rand, 1.0d), posY, posZ + getROD(rand, 1.0d), 0.30000001192092896d * getROD(rand, 1.0d), 0.0d, 0.30000001192092896d * getROD(rand, 1.0d), new int[0]);
                }
                break;
            case 18:
                for (int i17 = 0; i17 < 1 + (4 * extra); i17++) {
                    world.func_175682_a(ParticleInit.FLAME_LARGE, true, posX + getROD(rand, 1.0d), posY, posZ + getROD(rand, 1.0d), 0.30000001192092896d * getROD(rand, 1.0d), 0.0d, 0.30000001192092896d * getROD(rand, 1.0d), new int[0]);
                }
                break;
            case 19:
                for (int i18 = 0; i18 < 8; i18++) {
                    world.func_175688_a(ParticleInit.FIREGOO, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), getROD(rand, 1.0d), getROD(rand, 1.0d), getROD(rand, 1.0d), new int[0]);
                }
                break;
            case 20:
                world.func_175682_a(ParticleInit.BOMBER_EXPLOSION, true, posX + 0.5d, posY, posZ + 0.5d, 0.0d, 0.0d, 0.0d, new int[0]);
                break;
            case 21:
                world.func_175682_a(ParticleInit.GLOMITE_WARP, true, posX, posY, posZ, 0.0d, 0.0d, 0.0d, new int[0]);
                break;
            case 22:
                for (int i19 = 0; i19 < 8; i19++) {
                    world.func_175682_a(ParticleInit.SPACE_MAGIC, true, posX + getROD(rand, 4.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 4.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 23:
                world.func_175682_a(ParticleInit.POWER_FIELD, true, posX, posY, posZ, 0.0d, 0.0d, 0.0d, new int[0]);
                break;
            case TileEntityFusionTable.BOARD_SIZE :
                world.func_175682_a(ParticleInit.POWER_LOSS, true, posX, posY, posZ, 0.0d, 0.0d, 0.0d, new int[0]);
                break;
            case 25:
                for (int i20 = 0; i20 < 8; i20++) {
                    world.func_175682_a(ParticleInit.PLAGUE, true, posX, posY, posZ, 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 26:
                for (int i21 = 0; i21 < 14; i21++) {
                    world.func_175682_a(ParticleInit.ANCIENT_SPELL, true, posX + getROD(rand, 1.0d), posY, posZ + getROD(rand, 1.0d), 0.30000001192092896d * getROD(rand, 1.0d), 0.0d, 0.30000001192092896d * getROD(rand, 1.0d), new int[0]);
                }
                break;
            case 27:
                for (int i22 = 0; i22 < 8; i22++) {
                    world.func_175682_a(ParticleInit.CLAW_MARKS, true, posX + getROD(rand, 4.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 4.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 28:
                for (int i23 = 0; i23 < 3; i23++) {
                    world.func_175682_a(rand.nextBoolean() ? ParticleInit.ELECTRIC_EXPLOSION_BLUE : ParticleInit.ELECTRIC_EXPLOSION_YELLOW, true, posX + getROD(rand, 2.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 2.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 29:
                for (int i24 = 0; i24 < 10; i24++) {
                    world.func_175682_a(ParticleInit.CORRUPTION_MAGIC, true, posX + getROD(rand, 4.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 4.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case BlockCthulhuSpawner.BLOCK_DISTANCE :
                for (int i25 = 0; i25 < 3; i25++) {
                    world.func_175682_a(ParticleInit.NATURE_RING, true, posX, posY, posZ, 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 31:
                for (int i26 = 0; i26 < 10; i26++) {
                    world.func_175682_a(ParticleInit.NATURE_MAGIC, true, posX + getROD(rand, 4.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 4.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 32:
                for (int i27 = 0; i27 < 10; i27++) {
                    world.func_175682_a(ParticleInit.CRYSTAL_MAGIC, true, posX + getROD(rand, 4.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 4.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 33:
                for (int i28 = 0; i28 < 2; i28++) {
                    world.func_175682_a(ParticleInit.VENOM, true, posX + getROD(rand, 4.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 4.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                world.func_175682_a(ParticleInit.VENOM_RING, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                break;
            case 34:
                world.func_175682_a(ParticleInit.SHADOW_BLAST, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                break;
            case 35:
                for (int i29 = 0; i29 < 10; i29++) {
                    world.func_175682_a(ParticleInit.POISON_BUBBLE, true, posX + getROD(rand, 4.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 4.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 36:
                for (int i30 = 0; i30 < 12; i30++) {
                    world.func_175682_a(ParticleInit.PURPLE_SKULL, true, posX + getROD(rand, 4.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 4.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 37:
                for (int i31 = 0; i31 < 4; i31++) {
                    world.func_175682_a(ParticleInit.BLIGHT_SPELL_GREEN, true, posX + getROD(rand, 4.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 4.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 38:
                for (int i32 = 0; i32 < 5; i32++) {
                    world.func_175682_a(ParticleInit.DARK_MAGIC, true, posX + getROD(rand, 4.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 4.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 39:
                world.func_175682_a(ParticleInit.BLUE_SKULL, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                break;
            case 40:
                for (int i33 = 0; i33 < 5; i33++) {
                    world.func_175682_a(ParticleInit.ION_BLAST, true, posX + getROD(rand, 6.0d), posY + getROD(rand, 4.0d), posZ + getROD(rand, 6.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 41:
                for (int i34 = 0; i34 < 5; i34++) {
                    world.func_175682_a(ParticleInit.NUCLEAR_BLAST, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 42:
                for (int i35 = 0; i35 < 10; i35++) {
                    world.func_175682_a(ParticleInit.EXPLOSION, true, posX + getROD(rand, 8.0d), posY + getROD(rand, 2.0d), posZ + getROD(rand, 8.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 43:
                for (int i36 = 0; i36 < 8; i36++) {
                    world.func_175682_a(ParticleInit.PLASMA_RIFT, true, posX + getROD(rand, 3.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 3.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 44:
                for (int i37 = 0; i37 < 20; i37++) {
                    world.func_175682_a(ParticleInit.MURK, true, posX + getROD(rand, 5.0d), posY + getROD(rand, 2.0d), posZ + getROD(rand, 5.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 45:
                world.func_175682_a(ParticleInit.SPECTRAL, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                break;
            case 46:
                for (int i38 = 0; i38 < 5; i38++) {
                    switch (rand.nextInt(3)) {
                        case 0:
                            world.func_175682_a(ParticleInit.PRISMATIC_EXPLOSION_TYPE1, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                            break;
                        case 1:
                            world.func_175682_a(ParticleInit.PRISMATIC_EXPLOSION_TYPE2, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                            break;
                        case 2:
                            world.func_175682_a(ParticleInit.PRISMATIC_EXPLOSION_TYPE3, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                            break;
                    }
                }
                break;
            case 47:
                for (int i39 = 0; i39 < 5; i39++) {
                    world.func_175682_a(ParticleInit.PRISMATIC_EXPLOSION_TYPE1, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 48:
                for (int i40 = 0; i40 < 5; i40++) {
                    world.func_175682_a(ParticleInit.PRISMATIC_EXPLOSION_TYPE2, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 49:
                for (int i41 = 0; i41 < 5; i41++) {
                    world.func_175682_a(ParticleInit.PRISMATIC_EXPLOSION_TYPE3, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 50:
                for (int i42 = 0; i42 < 5; i42++) {
                    world.func_175682_a(ParticleInit.EXPLOSION_RING_DARK, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 51:
                for (int i43 = 0; i43 < 3; i43++) {
                    if (rand.nextBoolean()) {
                        world.func_175682_a(ParticleInit.EXPLOSION_BLUE, true, posX + getROD(rand, 5.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 5.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                    } else {
                        world.func_175682_a(ParticleInit.EXPLOSION_TEAL, true, posX + getROD(rand, 5.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 5.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                    }
                }
                for (int i44 = 0; i44 < 7; i44++) {
                    world.func_175682_a(ParticleInit.MURK, true, posX + getROD(rand, 10.0d), posY + getROD(rand, 2.0d), posZ + getROD(rand, 10.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 52:
                for (int i45 = 0; i45 < 2; i45++) {
                    world.func_175682_a(ParticleInit.CLAW_MARKS, true, posX + getROD(rand, 2.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 2.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                    world.func_175682_a(ParticleInit.BLOOD_DROP, true, posX + getROD(rand, 2.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 2.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 53:
                for (int i46 = 0; i46 < 10; i46++) {
                    world.func_175682_a(ParticleInit.ION_BLAST, true, posX + getROD(rand, 6.0d), posY + getROD(rand, 4.0d), posZ + getROD(rand, 6.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                    world.func_175682_a(ParticleInit.EXPLOSION_LAVENDER, true, posX + getROD(rand, 5.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 5.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 54:
                for (int i47 = 0; i47 < 15; i47++) {
                    world.func_175682_a(ParticleInit.POISON_EXPLOSION, true, posX + getROD(rand, 15.0d), posY + getROD(rand, 2.0d), posZ + getROD(rand, 15.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
            case 55:
                switch (rand.nextInt(4)) {
                    case 0:
                        world.func_175682_a(ParticleInit.COSMIC_EXPLOSION_TYPE1, true, posX + getROD(rand, 5.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 5.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                        break;
                    case 1:
                        world.func_175682_a(ParticleInit.COSMIC_EXPLOSION_TYPE2, true, posX + getROD(rand, 5.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 5.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                        break;
                    case 2:
                        world.func_175682_a(ParticleInit.COSMIC_EXPLOSION_TYPE3, true, posX + getROD(rand, 5.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 5.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                        break;
                    case 3:
                        world.func_175682_a(ParticleInit.COSMIC_EXPLOSION_TYPE4, true, posX + getROD(rand, 5.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 5.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                        break;
                }
                for (int i48 = 0; i48 < 4; i48++) {
                    switch (rand.nextInt(4)) {
                        case 0:
                            world.func_175682_a(ParticleInit.BASIC_STAR_TYPE1, true, posX + getROD(rand, 5.0d), posY + getROD(rand, 3.0d), posZ + getROD(rand, 5.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                            break;
                        case 1:
                            world.func_175682_a(ParticleInit.BASIC_STAR_TYPE2, true, posX + getROD(rand, 5.0d), posY + getROD(rand, 3.0d), posZ + getROD(rand, 5.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                            break;
                        case 2:
                            world.func_175682_a(ParticleInit.BASIC_STAR_TYPE3, true, posX + getROD(rand, 5.0d), posY + getROD(rand, 3.0d), posZ + getROD(rand, 5.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                            break;
                        case 3:
                            world.func_175682_a(ParticleInit.BASIC_STAR_TYPE4, true, posX + getROD(rand, 5.0d), posY + getROD(rand, 3.0d), posZ + getROD(rand, 5.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                            break;
                    }
                }
                break;
            case 56:
                for (int i49 = 0; i49 < 4; i49++) {
                    world.func_175682_a(ParticleInit.VENOM_RING, true, posX + getROD(rand, 15.0d), posY + getROD(rand, 2.0d), posZ + getROD(rand, 15.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                    world.func_175682_a(ParticleInit.EXPLOSION_RING_DARK, true, posX + getROD(rand, 15.0d), posY + getROD(rand, 2.0d), posZ + getROD(rand, 15.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
            case 57:
                for (int i50 = 0; i50 < 5; i50++) {
                    world.func_175682_a(ParticleInit.MIASMA, true, posX + getROD(rand, 4.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 4.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 58:
                if (rand.nextBoolean()) {
                    for (int i51 = 0; i51 < 2; i51++) {
                        world.func_175682_a(ParticleInit.SUPERSONIC_BLUE, true, posX, posY, posZ, 0.0d, 0.0d, 0.0d, new int[0]);
                    }
                } else {
                    for (int i52 = 0; i52 < 2; i52++) {
                        world.func_175682_a(ParticleInit.SUPERSONIC_RED, true, posX, posY, posZ, 0.0d, 0.0d, 0.0d, new int[0]);
                    }
                }
                break;
            case 59:
                for (int i53 = 0; i53 < 15; i53++) {
                    if (rand.nextBoolean()) {
                        world.func_175682_a(ParticleInit.EXPLOSION_RED, true, posX + getROD(rand, 25.0d), posY + getROD(rand, 4.0d), posZ + getROD(rand, 25.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                    } else {
                        world.func_175682_a(ParticleInit.EXPLOSION_ORANGE, true, posX + getROD(rand, 25.0d), posY + getROD(rand, 4.0d), posZ + getROD(rand, 25.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                    }
                }
                break;
            case 60:
                world.func_175682_a(ParticleInit.POWER_FIELD, true, posX, posY, posZ, 0.0d, 0.0d, 0.0d, new int[0]);
                for (int i54 = 0; i54 < 12; i54++) {
                    world.func_175682_a(rand.nextBoolean() ? ParticleInit.EXPLOSION_BLUE : ParticleInit.EXPLOSION_LAVENDER, true, posX + getROD(rand, 18.0d), posY + getROD(rand, 2.0d), posZ + getROD(rand, 18.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
            case 61:
                for (int i55 = 0; i55 < 4 + extra; i55++) {
                    switch (rand.nextInt(4)) {
                        case 0:
                            world.func_175682_a(ParticleInit.BASIC_STAR_TYPE1, true, posX + getROD(rand, 5.0d), posY + getROD(rand, 3.0d), posZ + getROD(rand, 5.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                            break;
                        case 1:
                            world.func_175682_a(ParticleInit.BASIC_STAR_TYPE2, true, posX + getROD(rand, 5.0d), posY + getROD(rand, 3.0d), posZ + getROD(rand, 5.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                            break;
                        case 2:
                            world.func_175682_a(ParticleInit.BASIC_STAR_TYPE3, true, posX + getROD(rand, 5.0d), posY + getROD(rand, 3.0d), posZ + getROD(rand, 5.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                            break;
                        case 3:
                            world.func_175682_a(ParticleInit.BASIC_STAR_TYPE4, true, posX + getROD(rand, 5.0d), posY + getROD(rand, 3.0d), posZ + getROD(rand, 5.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                            break;
                    }
                }
                break;
            case 62:
                for (int i56 = 0; i56 < 5; i56++) {
                    world.func_175682_a(ParticleInit.LASER_FIZZLE, true, posX + getROD(rand, 1.0d), posY + getROD(rand, 1.0d), posZ + getROD(rand, 1.0d), 0.0d, 0.0d, 0.0d, new int[0]);
                }
                break;
        }
    }
    public static void renderComplex(CustomParticleConfig config) {
        World world = Minecraft.func_71410_x().field_71439_g.field_70170_p;
        Random rand = world.field_73012_v;
        if (config.particles.size() == 1) {
            renderInstance(world, config.origin, rand, config.particles.get(0));
            return;
        }
        int totalWeight = 0;
        Iterator<CustomParticleConfig.Instance> it = config.particles.iterator();
        while (it.hasNext()) {
            totalWeight += it.next().weight;
        }
        for (int i = 0; i < config.count; i++) {
            int landed = rand.nextInt(totalWeight);
            Iterator<CustomParticleConfig.Instance> it2 = config.particles.iterator();
            while (true) {
                if (it2.hasNext()) {
                    CustomParticleConfig.Instance instance = it2.next();
                    landed -= instance.weight;
                    if (landed < 0) {
                        renderInstance(world, config.origin, rand, instance);
                        break;
                    }
                }
            }
        }
    }
    private static void renderInstance(World world, Vec3d origin, Random rand, CustomParticleConfig.Instance instance) {
        Vec3d offset = instance.offset;
        Vec3d speed = instance.speed;
        Vec3d spread = instance.spread;
        Vec3d velSpread = instance.velSpread;
        for (int i = 0; i < instance.count; i++) {
            world.func_175682_a(instance.particleType, instance.ignoreRange, origin.field_72450_a + offset.field_72450_a + getROD(rand, spread.field_72450_a), origin.field_72448_b + offset.field_72448_b + getROD(rand, spread.field_72448_b), origin.field_72449_c + offset.field_72449_c + getROD(rand, spread.field_72449_c), speed.field_72450_a * getROD(rand, velSpread.field_72450_a), speed.field_72448_b * getROD(rand, velSpread.field_72448_b), speed.field_72449_c * getROD(rand, velSpread.field_72449_c), instance.args);
        }
    }
    private static double getROD(Random rand, double multi) {
        return ((-0.5d) + rand.nextDouble()) * multi;
    }
}
