package xol.lostinfinity.block.activator;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.util.DimensionActivator;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.basics.ItemArenaToken;
import xol.lostinfinity.mob.entity.boss.EntityAlestria;
import xol.lostinfinity.mob.entity.boss.EntityArash;
import xol.lostinfinity.mob.entity.boss.EntityAtlasSpire;
import xol.lostinfinity.mob.entity.boss.EntityBarul;
import xol.lostinfinity.mob.entity.boss.EntityCryonus;
import xol.lostinfinity.mob.entity.boss.EntityDarrio;
import xol.lostinfinity.mob.entity.boss.EntityNuxuro;
import xol.lostinfinity.mob.entity.boss.EntityOzor;
import xol.lostinfinity.mob.entity.boss.EntityRikarus;
import xol.lostinfinity.mob.entity.boss.EntityThundyron;
import xol.lostinfinity.mob.entity.boss.EntityUrogo;
import xol.lostinfinity.mob.entity.boss.EntityVelo;
import xol.lostinfinity.mob.entity.boss.EntityVycellia;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockArenaBoss.class */
public class BlockArenaBoss extends BlockBasic {
    public BlockArenaBoss(String name) {
        super(name, Material.field_151576_e);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            if (pos.func_177958_n() < 350) {
                if (pos.func_177952_p() > 0) {
                    DimensionActivator.transferEntityWithCoords(playerIn, DimensionType.OVERWORLD, 0.0d, 0.0d, 0.0d);
                    return true;
                }
                ItemStack heldstack = playerIn.func_184586_b(hand);
                if (!(heldstack.func_77973_b() instanceof ItemArenaToken)) {
                    if (heldstack.func_77973_b() == ItemInit.arenaCard) {
                        playerIn.func_184185_a(SoundInit.ARENA_TELEPORT, 3.0f, 1.0f);
                        heldstack.func_190918_g(1);
                        if (!worldIn.field_72995_K) {
                            playerIn.func_70634_a(25.5d, 61.2d, -33.0d);
                            return true;
                        }
                        return true;
                    }
                    return true;
                }
                AxisAlignedBB aabb = getArenaAABB();
                boolean found_pl = false;
                for (EntityPlayer entityPlayer : worldIn.func_72872_a(EntityPlayer.class, aabb)) {
                    found_pl = true;
                }
                if (!found_pl) {
                    if (!worldIn.field_72995_K) {
                        playerIn.func_70634_a(25.5d, 61.2d, -73.0d);
                    }
                    worldIn.func_184148_a(playerIn, 25.5d, 61.2d, -73.0d, SoundInit.ARENA_CHALLENGE, SoundCategory.MASTER, 2.0f, 1.0f);
                    spawnMyBoss(heldstack.func_77973_b(), worldIn, playerIn);
                    heldstack.func_190918_g(1);
                    return true;
                }
                if (!worldIn.field_72995_K) {
                    playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "The arena is currently in use."));
                    return true;
                }
                return true;
            }
            DimensionActivator.transferEntityWithCoords(playerIn, DimensionType.OVERWORLD, 0.0d, 0.0d, 0.0d);
            return true;
        }
        return true;
    }

    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -145.0d), new BlockPos(52.0d, 85.0d, -40.0d));
    }

    private void spawnMyBoss(Item held, World world, EntityPlayer play) {
        AxisAlignedBB aabb = getArenaAABB();
        for (EntityMob mob_die : world.func_72872_a(EntityMob.class, aabb)) {
            mob_die.func_70106_y();
        }
        if (held == ItemInit.tokenUrogo) {
            if (!world.field_72995_K) {
                EntityUrogo urogo = new EntityUrogo(world);
                urogo.func_70107_b(25.5d, 62.2d, -90.0d);
                world.func_72838_d(urogo);
                return;
            } else {
                play.func_145747_a(new TextComponentString(TextFmt.Aqua + play.func_70005_c_() + " challenges Urogo!"));
                play.func_145747_a(new TextComponentString(TextFmt.Red + "Urogo: Let's make this quick."));
                return;
            }
        }
        if (held == ItemInit.tokenVelo) {
            if (!world.field_72995_K) {
                EntityVelo velo = new EntityVelo(world);
                velo.func_70107_b(25.5d, 62.2d, -90.0d);
                world.func_72838_d(velo);
                return;
            } else {
                play.func_145747_a(new TextComponentString(TextFmt.Aqua + play.func_70005_c_() + " challenges Velo!"));
                play.func_145747_a(new TextComponentString(TextFmt.Red + "Velo: I'll battle you here, I'll battle you anywhere..."));
                return;
            }
        }
        if (held == ItemInit.tokenNuxuro) {
            if (!world.field_72995_K) {
                EntityNuxuro velo2 = new EntityNuxuro(world);
                velo2.func_70107_b(25.5d, 62.2d, -90.0d);
                world.func_72838_d(velo2);
                return;
            } else {
                play.func_145747_a(new TextComponentString(TextFmt.Aqua + play.func_70005_c_() + " challenges Velo!"));
                play.func_145747_a(new TextComponentString(TextFmt.Red + "Nuxuro: Run little being, while you still have the chance."));
                return;
            }
        }
        if (held == ItemInit.tokenRikarus) {
            if (!world.field_72995_K) {
                EntityRikarus rikarus = new EntityRikarus(world);
                rikarus.func_70107_b(25.5d, 62.2d, -90.0d);
                world.func_72838_d(rikarus);
                return;
            } else {
                play.func_145747_a(new TextComponentString(TextFmt.Aqua + play.func_70005_c_() + " challenges Rikarus!"));
                play.func_145747_a(new TextComponentString(TextFmt.Red + "Rikarus: Your life now belongs to me."));
                return;
            }
        }
        if (held == ItemInit.tokenArash) {
            if (!world.field_72995_K) {
                EntityArash arash = new EntityArash(world);
                arash.func_70107_b(25.5d, 62.2d, -90.0d);
                world.func_72838_d(arash);
                return;
            } else {
                play.func_145747_a(new TextComponentString(TextFmt.Aqua + play.func_70005_c_() + " challenges Arash!"));
                play.func_145747_a(new TextComponentString(TextFmt.Red + "Arash: You? A challenge? We'll see about that."));
                return;
            }
        }
        if (held == ItemInit.tokenAlestria) {
            if (!world.field_72995_K) {
                EntityAlestria alestria = new EntityAlestria(world);
                alestria.func_70107_b(25.5d, 62.2d, -90.0d);
                world.func_72838_d(alestria);
                return;
            } else {
                play.func_145747_a(new TextComponentString(TextFmt.Aqua + play.func_70005_c_() + " challenges Alestria!"));
                play.func_145747_a(new TextComponentString(TextFmt.Red + "Alestria: Your life will soon be as empty as the void around us."));
                return;
            }
        }
        if (held == ItemInit.tokenDario) {
            if (!world.field_72995_K) {
                EntityDarrio darrio = new EntityDarrio(world);
                darrio.func_70107_b(25.5d, 62.2d, -90.0d);
                world.func_72838_d(darrio);
                return;
            } else {
                play.func_145747_a(new TextComponentString(TextFmt.Aqua + play.func_70005_c_() + " challenges Darrio!"));
                play.func_145747_a(new TextComponentString(TextFmt.Red + "Darrio: I'm already dead, what can you possibly do to me?"));
                return;
            }
        }
        if (held == ItemInit.tokenAtlasspire) {
            if (!world.field_72995_K) {
                EntityAtlasSpire spire = new EntityAtlasSpire(world);
                spire.func_70107_b(25.5d, 62.2d, -90.0d);
                world.func_72838_d(spire);
                return;
            } else {
                play.func_145747_a(new TextComponentString(TextFmt.Aqua + play.func_70005_c_() + " challenges The Atlas Spire!"));
                play.func_145747_a(new TextComponentString(TextFmt.Red + "Looming Voice: Interesting choice."));
                return;
            }
        }
        if (held == ItemInit.tokenVycellia) {
            if (!world.field_72995_K) {
                EntityVycellia vycel = new EntityVycellia(world);
                vycel.func_70107_b(25.5d, 62.2d, -90.0d);
                world.func_72838_d(vycel);
                return;
            } else {
                play.func_145747_a(new TextComponentString(TextFmt.Aqua + play.func_70005_c_() + " challenges Spider Queen Vycellia!"));
                play.func_145747_a(new TextComponentString(TextFmt.Red + "Vycellia: My army is eternal, and YOU. ARE. NOT."));
                return;
            }
        }
        if (held == ItemInit.tokenOzor) {
            if (!world.field_72995_K) {
                EntityOzor ozor = new EntityOzor(world);
                ozor.func_70107_b(25.5d, 62.2d, -90.0d);
                world.func_72838_d(ozor);
                return;
            } else {
                play.func_145747_a(new TextComponentString(TextFmt.Aqua + play.func_70005_c_() + " challenges Ozor!"));
                play.func_145747_a(new TextComponentString(TextFmt.Red + "Ozor: Your life is but an illusion... a mirage waiting to disappear."));
                return;
            }
        }
        if (held == ItemInit.tokenThundyron) {
            if (!world.field_72995_K) {
                EntityThundyron thund = new EntityThundyron(world);
                thund.func_70107_b(25.5d, 62.2d, -90.0d);
                world.func_72838_d(thund);
                return;
            } else {
                play.func_145747_a(new TextComponentString(TextFmt.Aqua + play.func_70005_c_() + " challenges Thundyron!"));
                play.func_145747_a(new TextComponentString(TextFmt.Red + "Thundyron: YOU... HAVE... NO... POWER..."));
                return;
            }
        }
        if (held == ItemInit.tokenBarul) {
            if (!world.field_72995_K) {
                EntityBarul barul = new EntityBarul(world);
                barul.func_70107_b(25.5d, 62.2d, -90.0d);
                world.func_72838_d(barul);
                return;
            } else {
                play.func_145747_a(new TextComponentString(TextFmt.Aqua + play.func_70005_c_() + " challenges Barul!"));
                play.func_145747_a(new TextComponentString(TextFmt.Red + "Barul: You like tiny and weak.... and like you're about to be dead."));
                return;
            }
        }
        if (held == ItemInit.tokenCryonus) {
            if (!world.field_72995_K) {
                EntityCryonus cryro = new EntityCryonus(world);
                cryro.func_70107_b(25.5d, 62.2d, -90.0d);
                world.func_72838_d(cryro);
            } else {
                play.func_145747_a(new TextComponentString(TextFmt.Aqua + play.func_70005_c_() + " challenges Cryonus!"));
                play.func_145747_a(new TextComponentString(TextFmt.Red + "Cryonus: What is it you fear " + play.func_70005_c_() + "?"));
            }
        }
    }
}
