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
        
3. 代码实现:

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
            
4. 遍历

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
3. 例子:
    - 表达式树: 树叶是操作数,其他节点是操作符
    - 查找树ADT(二叉查找树): 
        - 定义: 对于二叉树中每个节点X,它的左子树中所有项的值都小于X中的项,它的右子树中所有项的值都大于X中的项
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
                    
                    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t){
                        
                    }
                    
                    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t){
                        
                    }
                    
                    private BinaryNode<AnyType> insert(AnyType x,BinaryNode<AnyType> t){
                        
                    }
                    
                    private BinaryNode<AnyType> remove(AnyType x,BinaryNode<AnyType> t){
                        
                    }
                    
                    private void printTree(BinaryNode<AnyType> t){
                        
                    }
                }
        