package xol.lostinfinity.dimension.data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
public class LabPathGenerator {
    private LabPathNode[][] pathMap;
    private class LabPath {
        ArrayList<Integer> connectedDoors;
        int startDoor;
        boolean isDeadEnd;
        ArrayList<LabPathNode> visited = new ArrayList<>();
        ArrayList<LabPathNode> endNodes = new ArrayList<>();
        ArrayList<LabPathNode> doorNodes = new ArrayList<>();
        public LabPath(ArrayList<Integer> connectedDoors, boolean isDeadEnd, int startDoor) {
            this.startDoor = startDoor;
            this.connectedDoors = connectedDoors;
            this.isDeadEnd = isDeadEnd;
        }
        public int getStartDoor() {
            return this.startDoor;
        }
        public ArrayList<LabPathNode> getDoorNodes() {
            return this.doorNodes;
        }
        public void setDoorNodes(ArrayList<LabPathNode> doorNodes) {
            this.doorNodes = doorNodes;
        }
        public void setEndNodes(ArrayList<LabPathNode> endNodes) {
            this.endNodes = endNodes;
        }
        public ArrayList<LabPathNode> getEndNodes() {
            return this.endNodes;
        }
        public void setVisited(ArrayList<LabPathNode> visited) {
            this.visited = visited;
        }
        public ArrayList<LabPathNode> getVisited() {
            return this.visited;
        }
        public void addVisited(LabPathNode node) {
            if (this.visited != null) {
                this.visited.add(node);
            }
        }
        public boolean isDeadEnd() {
            return this.isDeadEnd;
        }
        public ArrayList<Integer> getConnectedDoors() {
            return this.connectedDoors;
        }
    }
    public LabPathGenerator(int rows, int columns) {
        int randDoor;
        Random rand = new Random();
        boolean complete = false;
        while (!complete) {
            this.pathMap = new LabPathNode[columns][rows];
            for (int i = 0; i < columns; i++) {
                for (int j = 0; j < rows; j++) {
                    this.pathMap[i][j] = new LabPathNode(i, j);
                }
            }
            ArrayList<ArrayList<LabPathNode>> doors = new ArrayList<>();
            ArrayList<LabPath> paths = new ArrayList<>();
            doors.add(get2x2(0, 6));
            doors.add(get2x2(6, 0));
            doors.add(get2x2(12, 6));
            doors.add(get2x2(6, 12));
            Collections.shuffle(doors);
            Iterator<ArrayList<LabPathNode>> it = doors.iterator();
            while (it.hasNext()) {
                visit(it.next());
            }
            int numDeadEnds = 0;
            int numToConnect = 0;
            ArrayList<Integer> needsConnection = new ArrayList<>();
            needsConnection.add(0);
            needsConnection.add(1);
            needsConnection.add(2);
            needsConnection.add(3);
            for (int i2 = 0; i2 < 4; i2++) {
                ArrayList<Integer> connectedDoors = new ArrayList<>();
                int startDoor = i2;
                boolean isDeadEnd = rand.nextBoolean();
                if (isDeadEnd && numDeadEnds <= 2) {
                    paths.add(new LabPath(connectedDoors, true, startDoor));
                    numDeadEnds++;
                } else {
                    int numConnected = rand.nextInt(3);
                    for (int j2 = 0; j2 < numConnected; j2++) {
                        if (!needsConnection.isEmpty()) {
                            if (needsConnection.size() == 1 && needsConnection.get(0).intValue() == startDoor) {
                                break;
                            }
                            int iIntValue = needsConnection.get(rand.nextInt(needsConnection.size())).intValue();
                            while (true) {
                                randDoor = iIntValue;
                                if (randDoor != startDoor) {
                                    break;
                                } else {
                                    iIntValue = needsConnection.get(rand.nextInt(needsConnection.size())).intValue();
                                }
                            }
                            connectedDoors.add(Integer.valueOf(randDoor));
                            numToConnect++;
                            needsConnection.remove(needsConnection.indexOf(Integer.valueOf(randDoor)));
                        }
                    }
                    if (connectedDoors.size() > 1) {
                        paths.add(new LabPath(connectedDoors, true, startDoor));
                    } else {
                        if (numDeadEnds > 2) {
                            break;
                        }
                        paths.add(new LabPath(connectedDoors, false, startDoor));
                        numDeadEnds++;
                    }
                }
            }
            if (numDeadEnds < 2 && numToConnect < 2) {
                System.out.println("Failed");
            }
            for (LabPath path : paths) {
                ArrayList<Integer> connectedDoors2 = path.getConnectedDoors();
                ArrayList<LabPathNode> doorNodes = path.getDoorNodes();
                ArrayList<LabPathNode> endNodes = path.getEndNodes();
                ArrayList<LabPathNode> visited = path.getVisited();
                Iterator<Integer> it2 = connectedDoors2.iterator();
                while (it2.hasNext()) {
                    int connectedDoor = it2.next().intValue();
                    ArrayList<LabPathNode> door = doors.get(connectedDoor);
                    if (door != null) {
                        doorNodes.addAll(door);
                    }
                }
                endNodes.addAll(doors.get(path.getStartDoor()));
                visited.addAll(doors.get(path.getStartDoor()));
                visit(doors.get(path.getStartDoor()));
                boolean end = true;
                if (drawPath(path) ? end : false) {
                    complete = true;
                }
            }
        }
    }
    private boolean drawPath(LabPath path) {
        ArrayList<LabPathNode> endNodes = path.getEndNodes();
        ArrayList<LabPathNode> visited = path.getVisited();
        ArrayList<LabPathNode> doorNodes = path.getDoorNodes();
        boolean pathComplete = false;
        int iterations = 0;
        while (!pathComplete && iterations < 20) {
            iterations++;
            Collections.shuffle(endNodes);
            LabPathNode toAdd = null;
            ArrayList<LabPathNode> check = null;
            for (LabPathNode node : endNodes) {
                int i = 0;
                while (true) {
                    if (i >= 2) {
                        break;
                    }
                    for (int j = 0; j < 2; j++) {
                        check = get2x2(i + node.getX(), j + node.getZ());
                        boolean connected = false;
                        boolean canAdd = true;
                        Iterator<LabPathNode> it = check.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            LabPathNode subNode = it.next();
                            if (subNode != null) {
                                ArrayList<LabPathNode> adjacents = getAdjacents(subNode);
                                Iterator<LabPathNode> it2 = adjacents.iterator();
                                while (true) {
                                    if (!it2.hasNext()) {
                                        break;
                                    }
                                    LabPathNode adjacent = it2.next();
                                    if (adjacent != null && !visited.contains(adjacent) && adjacent.visited() && !doorNodes.contains(adjacent)) {
                                        canAdd = false;
                                        break;
                                    }
                                }
                                if (endNodes.contains(subNode)) {
                                    connected = true;
                                } else if (visited.contains(subNode) && subNode.visited()) {
                                    canAdd = false;
                                    break;
                                }
                            } else {
                                canAdd = false;
                                break;
                            }
                        }
                        if (connected && canAdd) {
                            toAdd = getNodeAtLocation(i + node.getX(), j + node.getZ());
                            break;
                        }
                    }
                    i++;
                }
                if (toAdd != null) {
                    break;
                }
            }
            if (toAdd != null && check != null) {
                ArrayList<LabPathNode> toRemove = new ArrayList<>();
                for (LabPathNode node2 : check) {
                    if (endNodes.contains(node2)) {
                        toRemove.add(node2);
                    }
                    if (doorNodes.contains(node2)) {
                        pathComplete = true;
                    }
                }
                check.removeAll(toRemove);
                visit(check);
                endNodes.clear();
                endNodes.addAll(check);
                visited.addAll(check);
                if (pathComplete) {
                    return true;
                }
            }
        }
        return false;
    }
    private ArrayList<LabPathNode> getAdjacents(LabPathNode subNode) {
        ArrayList<LabPathNode> adjacents = new ArrayList<>();
        adjacents.add(getNodeAtLocation(subNode.getX() + 1, subNode.getZ()));
        adjacents.add(getNodeAtLocation(subNode.getX(), subNode.getZ() + 1));
        adjacents.add(getNodeAtLocation(subNode.getX(), subNode.getZ() - 1));
        adjacents.add(getNodeAtLocation(subNode.getX() - 1, subNode.getZ()));
        return adjacents;
    }
    public static void visit(ArrayList<LabPathNode> nodes) {
        for (LabPathNode node : nodes) {
            if (node != null) {
                node.setVisited();
            }
        }
    }
    public static void main(String[] args) {
        LabPathGenerator pathMap = new LabPathGenerator(14, 14);
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                LabPathNode node = pathMap.getNodeAtLocation(j, i);
                if (node != null) {
                    boolean visited = node.visited();
                    if (visited) {
                        System.out.print("[]");
                    } else {
                        System.out.print("{}");
                    }
                }
            }
            System.out.print("\r\n");
        }
    }
    public LabPathNode getNodeAtLocation(int i, int j) {
        if (this.pathMap != null && i >= 0 && j >= 0 && this.pathMap.length > i && this.pathMap[i].length > j) {
            return this.pathMap[i][j];
        }
        return null;
    }
    private ArrayList<LabPathNode> get2x2(int i, int j) {
        ArrayList<LabPathNode> nodes = new ArrayList<>();
        nodes.add(getNodeAtLocation(i, j));
        nodes.add(getNodeAtLocation(i + 1, j));
        nodes.add(getNodeAtLocation(i, j + 1));
        nodes.add(getNodeAtLocation(i + 1, j + 1));
        return nodes;
    }
}
