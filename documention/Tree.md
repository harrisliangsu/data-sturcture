## **树**
1. 定义: 一棵树是一些节点的集合.这个集合可以是空集;若不是空集,则树由称作根(root)的节点r以及0个或多个非空的(子)树T1,T2,...,Tk组成,这些子树中每一颗的根都被来自根r的一条有向的边(edge)所连接.
2. 性质: n个节点,n-1条边(除了根节点都一条连向父节点的边)
3. 名词定义:

    - 树叶: 没有儿子的节点
    - 兄弟: 具有相同父亲节点(类似定义祖父,孙子)
    - 路径: 从节点n1到nk的路径定义为节点n1,n2,...,nk的一个序列,使得对于1<=i<k 节点ni是n(i+1)的父亲
    
        - 路径长: 该条路径上的边的条数
        - ni的深度: 从根到ni的唯一路径的长
        - ni的高: 从ni到一片树叶的最长路径的长

4. 图例
    [一颗树](https://github.com/mmflys/data-sturcture/blob/master/document/Tree.md)
    <p align="center">
        <img src="https://github.com/mmflys/data-sturcture/blob/master/info/graph/tree.png">
    </p>
        
5. 代码实现:

    - 子链+兄弟链: 记录第一个孩子和第一兄弟
    
            class TreeNode {
                Object element;
                TreeNode firstChild;
                TreeNode nextSibling;
            }
    - 父链: 记录父节点
    
            class TreeNode {
                Object element;
                TreeNode parent;
            }
            
6. 遍历

    - 先序遍历: 在子节点遍历前先遍历自己,子节点从左到右依次遍历
        - 时间复杂度: O(n)
        - 例子: 列出分级文件系统中的目录
        - 伪代码: 
        
                private void listAll(int depth) {
                    printName(depth); // Print the name of the object
                    if(isDirectory())
                        for each file c in this directory // For each each child 
                            c.listAll(depth+1);
                }
                
                public void listAll() {
                    listAll(0);
                }
            
    - 后续遍历: 在子节点遍历后再遍历自己,子节点从左到右依次遍历
        - 时间复杂度: O(n)
        - 例子: 计算一个目录大小
        - 伪代码: 
        
                public int size() {
                    int totalSize = sizeOfThisFile();
                    if(isDirectory())
                        for each file c in this directory
                            totalSize += c.size();
                    
                    return totalSize;
                }
    - 中序遍历:                 
    - 深度优先遍历: 
    - 广度优先遍历: 
    
### **二叉树**
1. 定义: 二叉树是一颗树,其中每个节点都不能有多余两个子节点
2. 实现:

        class BinaryNode {
            Object element;
            BinaryNode left;
            BinaryNode right;
        }
3. 图例:
    [二叉树](https://github.com/mmflys/data-sturcture/blob/master/document/Tree.md)
    <p align="center">
        <img src="https://github.com/mmflys/data-sturcture/blob/master/info/graph/binaryTree.png">
    </p>            
4. 例子:
    - **表达式树**: 树叶是操作数,其他节点是操作符
    - **完全二叉树**: 对于一颗二叉树,假设其深度为d(d>1),除了第d层外,其它各层的节点数目均已达最大值,且第d层所有节点从左向右连续地紧密排列.
    - **查找树ADT**(二叉查找树): 
        - 定义: 对于二叉树中每个节点X,它的左子树中所有项的值都小于X中的项,它的右子树中所有项的值都大于X中的项
        - 时间复杂度: O(N)
        - 图例:
            [查找树](https://github.com/mmflys/data-sturcture/blob/master/document/Tree.md)
            <p align="center">
                <img src="https://github.com/mmflys/data-sturcture/blob/master/info/graph/binarySearchTree.png">
            </p>
        - [实现](https://github.com/mmflys/data-sturcture/blob/master/src/main/java/com/mmflys/datastructure/tree/search/SearchNode.java)
        
### **AVL树**
AVL(Adelson-Velskii和Landis)树是带有平衡条件的二叉树查找树.这个平衡条件必须容易保持,而且它保证树的深度必须是O(log N).
1. 平衡条件eg: 
    - 左右子树具有相同高度(容易满足)
    - 每个节点都必须有相同高度的左子树和右子树(严格)
2. 定义: 一颗AVL树是其每个节点的左子树和右子树的高度最多差1的二叉查找树(空树高度定义为-1)
3. 时间复杂度: O(log N)
4. 图例:
    [AVL树](https://github.com/mmflys/data-sturcture/blob/master/document/Tree.md)
     <p align="center">
        <img src="https://github.com/mmflys/data-sturcture/blob/master/info/graph/AVLTree.png">
     </p>
5. 性质: 在高度为h的AVL树中,最少节点数S(h): S(h)=S(h-1)+S(h-2)+1 --> 一个AVL树的高度最多为1.44log(N+2)-1.328(实际上的高度只略大于log N).
6. 旋转
    - 背景: 插入新节点时可能会破坏原节点的平衡条件
    - 情形: 
        - 对α的左儿子的左子树进行一次插入(左左)
        - 对α的左儿子的右子树进行一次插入(左右)
        - 对α的右儿子的左子树进行一次插入(右左)
        - 对α的右儿子的右子树进行一次插入(右右)
    - 单旋转: 
        - 图例:
            [单旋转](https://github.com/mmflys/data-sturcture/blob/master/document/Tree.md)
            <p align="center">
                <img src="https://github.com/mmflys/data-sturcture/blob/master/info/graph/singleRotate.png">
            </p>
        - 步骤: 
            - 插入新节点
            - 以插入点为起点向上知道被破坏平衡条件的节点A
            - 以A的高度大的一个子树根节点B为中心旋转到A的位置,此时A为B的子节点
            - 若B原本就有两个孩子,若B为A的右儿子,则把B的左儿子移到A上作为右儿子;若B为A的左儿子,则把B的又儿子移到A上作为左儿子
    - 双旋转:
        - 图例:
            [双旋转](https://github.com/mmflys/data-sturcture/blob/master/document/Tree.md)
            <p align="center">
                <img src="https://github.com/mmflys/data-sturcture/blob/master/info/graph/rightLeftDoubleRotate.png">
                <p align="center">右-左双旋</p>
                <img src="https://github.com/mmflys/data-sturcture/blob/master/info/graph/leftRightDoubleRotate.png">
                <p align="center">左-右双旋</p>
            </p>
7. [实现](https://github.com/mmflys/data-sturcture/blob/master/src/main/java/com/mmflys/datastructure/tree/binary/BinaryNode.java)

### **伸展树**
1. 定义: 一种简单的数据结构,它保证从空树开始连续M次对数的操作最多花费O(M log N)时间.
2. 时间复杂度: M次连续操作最多花费O(M log N)时间.
3. 设计思想: 当一个节点被访问后,通过一系列AVL树的旋转被推到根的位置,使后续访问时间减小.
4. 实现:
    - 单旋转该节点一直到根: 虽然使得该节点访问时间减少,但是并没有明显改善原先访问路径上其他节点的情况
    - 展开: 
        - 若该节点只有父节点,无祖父,则只用旋转该节点与父节点,结束
        - 若有祖父节点
            - 若呈现之字型,则用AVL双旋
                - 图例:
                [之字型](https://github.com/mmflys/data-sturcture/blob/master/document/Tree.md)
                <p align="center">
                    <img src="https://github.com/mmflys/data-sturcture/blob/master/info/graph/zhiShape.png">
                </p>
            - 若呈现一字型,则用一字变换
                - 图例: 
                [一字型](https://github.com/mmflys/data-sturcture/blob/master/document/Tree.md)
                <p align="center">
                    <img src="https://github.com/mmflys/data-sturcture/blob/master/info/graph/yiShape.png">
                    <p align="center">右-左双旋</p>
                </p>

### **B树**
1. 背景: 内存操作很快,但是磁盘存取慢,设计一种更加优化的结构,增大计算时间,较小磁盘访问次数
2. 思路:  
    - 减小深度: 使用m叉树查找树
    - 比log N更小的时间复杂度: 基于查找树更好的平衡条件
3. 定义: 阶为M的B树是一颗具有下列特性的树(下面的[]表示取上限)
    - 数据项存储在树叶上
    - 非叶节点存储直到M-1个关键字以指示搜索的方向;关键字i代表子树i+1中的最小的关键字
    - 树的根或者是一片树叶,或者其儿子数在2和M之间
    - 除根外,所有非树叶节点的儿子树在[M/2]和M之间
    - 所有的树叶都在相同的深度上并有[L/2]和L之间个数据项,L的确定稍后描述
4. 图例: 
    [B树](https://github.com/mmflys/data-sturcture/blob/master/document/Tree.md)
    <p align="center">
        <img src="https://github.com/mmflys/data-sturcture/blob/master/info/graph/BTree.png">
        <p align="center">M=5,L=5的B树</p>
    </p>
5. 插入: 
    - 若叶节点数据项未满直接插入数据项,否则把该叶节点分裂成两个叶节点,相当于给父节点增加一个关键字;
    - 若关键字未满直接添加,否则分裂成两个非叶节点,依次类推知道不再分裂为止,若最后根节点分裂成两个非叶节点,则增加一个节点作为根节点.
6. 删除:
    - 若删除后叶节点数据项个数未小于[L/2],删除结束
    - 若删除后叶节点数据项个数小于[L/2]
        - 若领节点数据项个数等于[L/2],则与被删除数据项节点联合形成一片满叶,会导致父节点少一个儿子节点,
            再用类似删除一个数据项的策略处理少一个关键字的情况,如果导致根只有一个儿子则删除该根节点,用儿子节点作为根节点.
        - 若领节点数据项个数大于[L/2],则从领节点领养一个数据项
[wiki](https://zh.wikipedia.org/wiki/B%E6%A0%91)