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
    - 表达式树: 树叶是操作数,其他节点是操作符
    - 查找树ADT(二叉查找树): 
        - 定义: 对于二叉树中每个节点X,它的左子树中所有项的值都小于X中的项,它的右子树中所有项的值都大于X中的项
        - 图例:
            [查找树](https://github.com/mmflys/data-sturcture/blob/master/document/Tree.md)
            <p align="center">
                <img src="https://github.com/mmflys/data-sturcture/blob/master/info/graph/binarySearchTree.png">
            </p>
        - 伪代码:
        
                public class BinarySearchTree<AnyType extends Comparable<? super AnyType>{
                
                    private static class BinaryNode<AnyType> {
                        AnyType element;
                        BinaryNode<AnyType> left;
                        BinaryNode<AnyType> right;
                                    
                        BinaryNode(AnyType theElement) {
                            this(theElement,null,null);
                        }
                                        
                        BinaryNode(AnyType theElement,BinaryNode<AnyType> lt,BinaryNode<AnyType> rt) {
                            this.element = theElement;
                            this.left = lt;
                            this.right = rt;
                        }
                    }
                    
                    private BinaryNode<AnyType> root;
                    
                    public BinarySearchTree(){
                        root = null;
                    }
                    
                    public void makeEmpty(){
                        root = null;
                    }
                    
                    public boolean isEmpty(){
                        return root == null;
                    }
                    
                    public boolean contains(AnyType x){
                        return contains(x,root);
                    }
                    
                    public AnyType findMin(){
                        if(isEmpty()){
                            throw new UnderflowException();
                        }else{
                            return findMin(root).element;
                        }
                    }
                    
                    public AnyType findMax(){
                        if(isEmpty()){
                            throw new UnderflowException();
                        }else{
                            return findMax(root).element;
                        }
                    } 
                   
                    public void insert(AnyType x){
                        root = insert(x,root);
                    }
                    
                    public void remove(AnyType x){
                        root = remove(x,root); 
                    }
                    
                    public void printTree(){
                         
                    }
                    
                    private boolean contains(AnyType x,BinaryNode<AnyType> t){
                        if(t == null){
                            return false;
                        }else{
                            int compareResult = x.compareTo(t.element);
                            if(compareResult < 0){
                                return contains(x,left);
                            }else if(compareResult > 0){
                                return contains(x,right);
                            }else{
                                return true;
                            }
                        }
                    }
                    
                    // 在左子树中搜索
                    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t){
                        if(t == null){
                            return null;
                        }else if(t.left == null){
                            return t;
                        }else{
                            return findMin(t.left);
                        }
                    }
                    
                    // 在右子树中搜索
                    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t){
                        if(t != null){
                            if(t.right != null){
                                return findMax(t.right);
                            }else{
                                return t;
                            }
                        }else{
                            return null;
                        }
                    }
                    
                    // 与左右节点比较,插到路径的最后
                    private BinaryNode<AnyType> insert(AnyType x,BinaryNode<AnyType> t){
                        if(t == null){
                            return new BinaryBode<>(x,null,null);
                        }else{
                            int compareResult = x.compareTo(t.element);
                            if(compareResult < 0){
                                t.left = insert(x,t.left);
                            }else if(compareResult > 0){
                                t.right = insert(x,t.right);
                            }else{
                                // Duplicate; do nothing
                            }
                        }
                        return t;
                    }
                    
                    // 1.叶子节点: 直接删除
                    // 2.有子节点
                    // 懒惰删除(优点: 删除高效,节省插入重复元素空间 缺点: 标记为删除的元素越来越多,占用空间越来越大)
                    private BinaryNode<AnyType> remove(AnyType x,BinaryNode<AnyType> t){
                        if(t == null){
                            return t;
                        }else{
                            int compareResult = x.compareTo(t.element);
                            if(compareResult < 0 ){
                                t.right = remove(x,t.right);
                            }else if(compareResult > 0){
                                t.right = remove(x,t.right);
                            }else if(t.left != null && t.right != null){
                                // 有两个孩子时,找一个右子树中最小节点替代删除的节点
                                t.element = findMin(t.right).element;
                                t.right = remove(t.element,t.right);
                            }else{
                                t = (t.left != null) ? t.left : t.right;
                            }
                            return t;
                        }
                    }
                    
                    private void printTree(BinaryNode<AnyType> t){
                        
                    }
                }
        
### **AVL树**
AVL(Adelson-Velskii和Landis)树是带有平衡条件的二叉树查找树.这个平衡条件必须容易保持,而且它保证树的深度必须是O(log N).
1. 平衡条件eg: 
    - 左右子树具有相同高度(容易满足)
    - 每个节点都必须有相同高度的左子树和右子树(严格)
2. 定义: 一颗AVL树是其每个节点的左子树和右子树的高度最多差1的二叉查找树(空树高度定义为-1)
3. 图例:
    [AVL树](https://github.com/mmflys/data-sturcture/blob/master/document/Tree.md)
     <p align="center">
        <img src="https://github.com/mmflys/data-sturcture/blob/master/info/graph/AVLTree.png">
     </p>
3. 性质: 在高度为h的AVL树中,最少节点数S(h): S(h)=S(h-1)+S(h-2)+1 --> 一个AVL树的高度最多为1.44log(N+2)-1.328(实际上的高度只略大于log N).
4. 旋转
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
        