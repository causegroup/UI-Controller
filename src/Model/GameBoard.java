package Model;

import java.util.ArrayList;

public class GameBoard{
    public Node[] nodes = new Node[31];
    final private Node[][] movableNodeTable = new Node[31][];

    public ArrayList<Node> movableNodes(Node currentNode, ArrayList<Integer> yutNums){
        ArrayList <Node> nodeList = new ArrayList<Node>();
        for(int yutNum : yutNums) {
            if (yutNum < 0)
                yutNum = 0;
            if (currentNode == null || currentNode == nodes[0]){
                nodeList.add(movableNodeTable[0][yutNum]);
            }
            else{
                nodeList.add(movableNodeTable[currentNode.nodeID][yutNum]);
            }
        }
        return nodeList;
    }
    /*30일때 안씀*/
    public int reCalculateYutNum(Node currentNode, Node nextNode){
        ArrayList<Node> nodes = new ArrayList<Node>();
        for(Node node : movableNodeTable[currentNode.nodeID]){
            nodes.add(node);
        }
        //int yutNum = nodes.indexOf(nextNode);

        int yutNum;
        for(yutNum = 0; yutNum < nodes.size(); yutNum++){
            if(nodes.get(yutNum) == nextNode){
                break;
            }
        }
        if(yutNum == 0) yutNum = -1;
        return yutNum;
    }

    public GameBoard(){
        for(int i = 0; i < nodes.length; i++){
            nodes[i] = new Node(i);
        }
        /*
        게임판 위에 있는 것은 1번부터 29번째 노드
        보드에 아직 안올라간상태:0
        완주한 상태: 30
        */
        /*0일때 백도의 경우 예외처리 해줘야함*/
        movableNodeTable[0] = new Node[]{nodes[0],nodes[1],nodes[2],nodes[3],nodes[4],nodes[5]};

        movableNodeTable[1] = new Node[]{nodes[20],nodes[2],nodes[3],nodes[4],nodes[5],nodes[6]};
        movableNodeTable[2] = new Node[]{nodes[1],nodes[3],nodes[4],nodes[5],nodes[6],nodes[7]};
        movableNodeTable[3] = new Node[]{nodes[2],nodes[4],nodes[5],nodes[6],nodes[7],nodes[8]};
        movableNodeTable[4] = new Node[]{nodes[3],nodes[5],nodes[6],nodes[7],nodes[8],nodes[9]};
        movableNodeTable[5] = new Node[]{nodes[4],nodes[21],nodes[22],nodes[25],nodes[26],nodes[27]};
        movableNodeTable[6] = new Node[]{nodes[5],nodes[7],nodes[8],nodes[9],nodes[10],nodes[11]};
        movableNodeTable[7] = new Node[]{nodes[6],nodes[8],nodes[9],nodes[10],nodes[11],nodes[12]};
        movableNodeTable[8] = new Node[]{nodes[7],nodes[9],nodes[10],nodes[11],nodes[12],nodes[13]};
        movableNodeTable[9] = new Node[]{nodes[8],nodes[10],nodes[11],nodes[12],nodes[13],nodes[14]};
        movableNodeTable[10] = new Node[]{nodes[9],nodes[23],nodes[24],nodes[25],nodes[28],nodes[29]};
        movableNodeTable[11] = new Node[]{nodes[10],nodes[12],nodes[13],nodes[14],nodes[15],nodes[16]};
        movableNodeTable[12] = new Node[]{nodes[11],nodes[13],nodes[14],nodes[15],nodes[16],nodes[17]};
        movableNodeTable[13] = new Node[]{nodes[12],nodes[14],nodes[15],nodes[16],nodes[17],nodes[18]};
        movableNodeTable[14] = new Node[]{nodes[13],nodes[15],nodes[16],nodes[17],nodes[18],nodes[19]};
        movableNodeTable[15] = new Node[]{nodes[14],nodes[16],nodes[17],nodes[18],nodes[19],nodes[20]};
        movableNodeTable[16] = new Node[]{nodes[15],nodes[17],nodes[18],nodes[19],nodes[20],nodes[30]};
        movableNodeTable[17] = new Node[]{nodes[16],nodes[18],nodes[19],nodes[20],nodes[30],nodes[30]};
        movableNodeTable[18] = new Node[]{nodes[17],nodes[19],nodes[20],nodes[30],nodes[30],nodes[30]};
        movableNodeTable[19] = new Node[]{nodes[18],nodes[20],nodes[30],nodes[30],nodes[30],nodes[30]};
        movableNodeTable[20] = new Node[]{nodes[19],nodes[30],nodes[30],nodes[30],nodes[30],nodes[30]};

        movableNodeTable[21] = new Node[]{nodes[5],nodes[22],nodes[25],nodes[26],nodes[27],nodes[15]};
        movableNodeTable[22] = new Node[]{nodes[21],nodes[25],nodes[26],nodes[27],nodes[15],nodes[16]};
        movableNodeTable[23] = new Node[]{nodes[10],nodes[24],nodes[25],nodes[28],nodes[29],nodes[20]};
        movableNodeTable[24] = new Node[]{nodes[23],nodes[25],nodes[28],nodes[29],nodes[20],nodes[30]};
        movableNodeTable[25] = new Node[]{nodes[22],nodes[28],nodes[29],nodes[20],nodes[30],nodes[30]};
        movableNodeTable[26] = new Node[]{nodes[25],nodes[27],nodes[15],nodes[16],nodes[17],nodes[18]};
        movableNodeTable[27] = new Node[]{nodes[26],nodes[15],nodes[16],nodes[17],nodes[18],nodes[19]};
        movableNodeTable[28] = new Node[]{nodes[25],nodes[29],nodes[20],nodes[30],nodes[30],nodes[30]};
        movableNodeTable[29] = new Node[]{nodes[28],nodes[20],nodes[30],nodes[30],nodes[30],nodes[30]};
    }
}

