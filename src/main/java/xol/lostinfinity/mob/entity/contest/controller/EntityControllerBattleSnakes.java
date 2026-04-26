package xol.lostinfinity.mob.entity.contest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import xol.lostinfinity.block.misc.BlockBattleSnake;
import xol.lostinfinity.block.misc.BlockBattleSnakePowerup;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.util.coordinates.ContestCoordinates;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/controller/EntityControllerBattleSnakes.class */
public class EntityControllerBattleSnakes extends EntityControllerBase {
    private static final Vec3i dirRight = new Vec3i(1, 0, 0);
    private static final Vec3i dirUp = new Vec3i(0, 0, 1);
    private static final int powerupTimer = 200;
    private static final int numPowerups = 2;
    ArrayList<BattleSnake> snakes;
    HashMap<UUID, BattleSnake> snakeMap;
    HashMap<UUID, BlockPos> controllerMap;

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/controller/EntityControllerBattleSnakes$BattleSnake.class */
    private class BattleSnake {
        private UUID playerID;
        private int num;
        private int dir;
        private int timer;
        private int time;
        private SnakeNode head;
        private SnakeNode tail;
        private boolean isDead;
        private boolean turned;

        /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/contest/controller/EntityControllerBattleSnakes$BattleSnake$SnakeNode.class */
        private class SnakeNode {
            private BlockPos pos;
            private SnakeNode next;
            private SnakeNode prev;

            private SnakeNode(BlockPos pos) {
                this.pos = null;
                this.next = null;
                this.prev = null;
                setPos(pos);
            }

            public void setPos(BlockPos pos) {
                this.pos = new BlockPos(pos);
            }

            public BlockPos getPos() {
                return this.pos;
            }

            public void setNext(SnakeNode next) {
                this.next = next;
                if (next != null) {
                    next.setPrev(this);
                }
            }

            public void setPrev(SnakeNode prev) {
                this.prev = prev;
            }

            public SnakeNode getPrev() {
                return this.prev;
            }

            public SnakeNode getNext() {
                return this.next;
            }
        }

        private BattleSnake(UUID playerID, int num) {
            this.playerID = null;
            this.num = -1;
            this.dir = 0;
            this.timer = 13;
            this.time = 0;
            this.head = null;
            this.tail = null;
            this.isDead = false;
            this.turned = false;
            this.playerID = playerID;
            this.num = num;
            this.dir = num - 1;
        }

        public UUID getPlayerID() {
            return this.playerID;
        }

        public boolean isDead() {
            return this.isDead;
        }

        public void setTimer(int timer) {
            this.timer = timer;
        }

        public int getTimer() {
            return this.timer;
        }

        private Vec3i getDirectionVec() {
            switch (this.dir) {
                case 0:
                    return EntityControllerBattleSnakes.dirUp;
                case 1:
                    return EntityControllerBattleSnakes.dirRight;
                case EntityControllerBattleSnakes.numPowerups /* 2 */:
                    return new Vec3i(EntityControllerBattleSnakes.dirUp.func_177958_n() * (-1), 0, EntityControllerBattleSnakes.dirUp.func_177952_p() * (-1));
                case 3:
                    return new Vec3i(EntityControllerBattleSnakes.dirRight.func_177958_n() * (-1), 0, EntityControllerBattleSnakes.dirRight.func_177952_p() * (-1));
                default:
                    return EntityControllerBattleSnakes.dirUp;
            }
        }

        public void turn(boolean right) {
            if (!this.turned) {
                if (!right) {
                    if (this.dir < 3) {
                        this.dir++;
                    } else {
                        this.dir = 0;
                    }
                } else if (this.dir > 0) {
                    this.dir--;
                } else {
                    this.dir = 4;
                }
                this.turned = true;
            }
        }

        public void createSnake(BlockPos pos, World world) {
            BlockPos centrePos;
            BlockPos headPos;
            switch (this.num) {
                case 1:
                    centrePos = pos.func_177971_a(EntityControllerBattleSnakes.dirUp);
                    headPos = centrePos.func_177971_a(EntityControllerBattleSnakes.dirUp);
                    break;
                case EntityControllerBattleSnakes.numPowerups /* 2 */:
                    centrePos = pos.func_177971_a(EntityControllerBattleSnakes.dirRight);
                    headPos = centrePos.func_177971_a(EntityControllerBattleSnakes.dirRight);
                    break;
                case 3:
                    centrePos = pos.func_177973_b(EntityControllerBattleSnakes.dirUp);
                    headPos = centrePos.func_177973_b(EntityControllerBattleSnakes.dirUp);
                    break;
                case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                    centrePos = pos.func_177973_b(EntityControllerBattleSnakes.dirRight);
                    headPos = centrePos.func_177973_b(EntityControllerBattleSnakes.dirRight);
                    break;
                default:
                    return;
            }
            if (centrePos != null && headPos != null) {
                SnakeNode tail = new SnakeNode(pos);
                SnakeNode centre = new SnakeNode(centrePos);
                SnakeNode head = new SnakeNode(headPos);
                head.setNext(centre);
                centre.setNext(tail);
                this.head = head;
                this.tail = tail;
                world.func_175656_a(headPos, BlockInit.battleSnake.func_176203_a(this.num - 1));
                world.func_175656_a(centrePos, BlockInit.battleSnake.func_176203_a(this.num - 1));
                world.func_175656_a(pos, BlockInit.battleSnake.func_176203_a(this.num - 1));
            }
        }

        public void advance(World world) {
            if (this.head == null || this.tail == null) {
                return;
            }
            BlockPos next = this.head.getPos().func_177971_a(getDirectionVec());
            boolean fed = false;
            boolean poisoned = false;
            if (!world.func_175623_d(next)) {
                IBlockState state = world.func_180495_p(next);
                Block block = state.func_177230_c();
                if (block instanceof BlockBattleSnake) {
                    die(world);
                    return;
                }
                if (block instanceof BlockBattleSnakePowerup) {
                    int meta = ((BlockBattleSnakePowerup) block).func_176201_c(state);
                    switch (meta) {
                        case 0:
                            fed = true;
                            break;
                        case 1:
                            if (getTimer() > 8) {
                                setTimer(getTimer() - EntityControllerBattleSnakes.numPowerups);
                            }
                            break;
                        case EntityControllerBattleSnakes.numPowerups /* 2 */:
                            poisoned = true;
                            break;
                    }
                }
            }
            SnakeNode oldHead = this.head;
            this.head = null;
            this.head = new SnakeNode(next);
            this.head.setNext(oldHead);
            world.func_175656_a(next, BlockInit.battleSnakeHead.func_176203_a(this.num - 1));
            world.func_175656_a(oldHead.getPos(), BlockInit.battleSnake.func_176203_a(this.num - 1));
            SnakeNode prevTail = this.tail.getPrev();
            if (prevTail == null) {
                return;
            }
            SnakeNode prevTail2 = prevTail.getPrev();
            if (prevTail2 != null && poisoned) {
                world.func_175698_g(this.tail.getPos());
                world.func_175698_g(prevTail.getPos());
                this.tail = null;
                this.tail = prevTail2;
                prevTail2.setNext(null);
                if (this.tail.equals(this.head)) {
                    die(world);
                    return;
                }
                return;
            }
            if (!fed) {
                world.func_175698_g(this.tail.getPos());
                this.tail = null;
                this.tail = prevTail;
                prevTail.setNext(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void die(World world) {
            this.isDead = true;
            SnakeNode snakeNode = this.head;
            while (true) {
                SnakeNode node = snakeNode;
                if (node != null) {
                    SnakeNode next = node.getNext();
                    world.func_175698_g(node.getPos());
                    snakeNode = next;
                } else {
                    return;
                }
            }
        }

        public SnakeNode getHead() {
            return this.head;
        }

        public void update(World world) {
            if (this.time < this.timer) {
                this.time++;
                return;
            }
            this.time = 0;
            this.turned = false;
            advance(world);
        }
    }

    public EntityControllerBattleSnakes(World worldIn) {
        super(worldIn);
        this.snakes = null;
        this.snakeMap = new HashMap<>();
        this.controllerMap = null;
        func_70105_a(1.0f, 1.0f);
    }

    public void setControllerMap(HashMap<UUID, BlockPos> controllerMap) {
        this.controllerMap = controllerMap;
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    public AxisAlignedBB getArenaAABB() {
        return ContestCoordinates.battleSnakesArenaAABB();
    }

    protected AxisAlignedBB getBoardAABB() {
        return ContestCoordinates.battleSnakesBoardAABB();
    }

    public void turnSnake(BlockPos pos, boolean right) {
        BattleSnake snake;
        if (this.snakeMap == null || this.controllerMap == null) {
            return;
        }
        UUID playerID = null;
        Iterator<UUID> it = getPlayerList().iterator();
        loop0: while (true) {
            if (!it.hasNext()) {
                break;
            }
            UUID pl_id = it.next();
            BlockPos controlPos = this.controllerMap.get(pl_id);
            if (controlPos != null) {
                for (int i = -2; i <= numPowerups; i++) {
                    for (int j = -2; j <= numPowerups; j++) {
                        BlockPos checkPos = pos.func_177982_a(i, 0, j);
                        if (checkPos.equals(controlPos)) {
                            playerID = pl_id;
                            break loop0;
                        }
                    }
                }
            }
        }
        if (playerID != null && (snake = this.snakeMap.get(playerID)) != null) {
            snake.turn(right);
        }
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    public void removePlayerByUUID(UUID playerID) {
        super.removePlayerByUUID(playerID);
        this.snakeMap.remove(playerID);
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase, xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if ((this.field_70173_aa > numPowerups && this.snakes == null) || this.controllerMap == null || getPlayerList().isEmpty()) {
                func_70106_y();
            }
            if (this.snakes != null) {
                ArrayList<BattleSnake> dead = new ArrayList<>();
                for (BattleSnake snake : this.snakes) {
                    if (snake != null) {
                        if (snake.isDead()) {
                            removePlayerByUUID(snake.getPlayerID());
                            dead.add(snake);
                        } else {
                            snake.update(this.field_70170_p);
                            if (!inBoard(snake)) {
                                snake.die(this.field_70170_p);
                                removePlayerByUUID(snake.getPlayerID());
                                dead.add(snake);
                            }
                        }
                    }
                }
                this.snakes.removeAll(dead);
                if (this.field_70173_aa % powerupTimer == 0) {
                    AxisAlignedBB board = getBoardAABB();
                    int col = ((int) board.field_72336_d) - ((int) board.field_72340_a);
                    int row = ((int) board.field_72334_f) - ((int) board.field_72339_c);
                    for (int i = 0; i < numPowerups; i++) {
                        int randX = this.field_70170_p.field_73012_v.nextInt(col);
                        int randZ = this.field_70170_p.field_73012_v.nextInt(row);
                        int roll = this.field_70170_p.field_73012_v.nextInt(10);
                        BlockPos powerupPos = new BlockPos(((int) board.field_72340_a) + randX, ((int) board.field_72338_b) + numPowerups, ((int) board.field_72339_c) + randZ);
                        if (this.field_70170_p.func_175623_d(powerupPos)) {
                            if (roll == 0) {
                                this.field_70170_p.func_175656_a(powerupPos, BlockInit.battleSnakePowerup.func_176203_a(numPowerups));
                            } else if (roll <= numPowerups) {
                                this.field_70170_p.func_175656_a(powerupPos, BlockInit.battleSnakePowerup.func_176203_a(1));
                            } else {
                                this.field_70170_p.func_175656_a(powerupPos, BlockInit.battleSnakePowerup.func_176203_a(0));
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean inBoard(BattleSnake snake) {
        if (snake.getHead() != null) {
            int x = snake.getHead().getPos().func_177958_n();
            int z = snake.getHead().getPos().func_177952_p();
            AxisAlignedBB board = getBoardAABB();
            if (x < ((int) Math.floor(board.field_72340_a)) || x > ((int) board.field_72336_d) || z < ((int) board.field_72339_c) || z > ((int) board.field_72334_f)) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void endGame() {
        if (this.snakes != null) {
            for (BattleSnake snake : this.snakes) {
                if (!snake.isDead()) {
                    snake.die(this.field_70170_p);
                }
            }
        }
        func_70106_y();
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected BlockPos getSnapPos() {
        return ContestCoordinates.battleSnakesControllerPos();
    }

    @Override // xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase
    protected void rewardPlayer(EntityPlayer player, int placement) {
        BlockPos teleTo = ContestCoordinates.battleSnakesLobbyPos();
        player.func_70634_a(teleTo.func_177958_n(), teleTo.func_177956_o(), teleTo.func_177952_p());
        int reward_count = Math.min(10 + (20 * placement) + (placement == this.contenderCount - 1 ? this.contenderCount * 10 : 0), 50);
        player.func_191521_c(new ItemStack(ItemInit.zirconiaCeladon, reward_count));
    }

    public void setupSnakes() {
        int x1 = (int) getBoardAABB().field_72340_a;
        int z1 = (int) getBoardAABB().field_72339_c;
        int x2 = (int) getBoardAABB().field_72340_a;
        int z2 = (int) getBoardAABB().field_72334_f;
        int x3 = (int) getBoardAABB().field_72336_d;
        int z3 = (int) getBoardAABB().field_72334_f;
        int x4 = (int) getBoardAABB().field_72336_d;
        int z4 = (int) getBoardAABB().field_72339_c;
        int y = ((int) getBoardAABB().field_72338_b) + numPowerups;
        List<UUID> players = getPlayerList();
        if (players.size() != this.contenderCount) {
            return;
        }
        this.snakes = new ArrayList<>();
        BattleSnake snake1 = new BattleSnake(players.get(0), 1);
        BattleSnake snake2 = new BattleSnake(players.get(1), numPowerups);
        snake1.createSnake(new BlockPos(x1, y, z1), this.field_70170_p);
        snake2.createSnake(new BlockPos(x2, y, z2), this.field_70170_p);
        this.snakes.add(snake1);
        this.snakes.add(snake2);
        this.snakeMap.put(players.get(0), snake1);
        this.snakeMap.put(players.get(1), snake2);
        if (this.contenderCount > numPowerups) {
            BattleSnake snake3 = new BattleSnake(players.get(numPowerups), 3);
            snake3.createSnake(new BlockPos(x3, y, z3), this.field_70170_p);
            this.snakes.add(snake3);
            this.snakeMap.put(players.get(numPowerups), snake3);
        }
        if (this.contenderCount == 4) {
            BattleSnake snake4 = new BattleSnake(players.get(3), 4);
            snake4.createSnake(new BlockPos(x4, y, z4), this.field_70170_p);
            this.snakes.add(snake4);
            this.snakeMap.put(players.get(3), snake4);
        }
        if (this.snakes.size() != this.snakeMap.size()) {
            System.out.println("Snake mapping does not match number of snakes!");
        }
    }
}
