package com.mmflys.datastructure.tree;

/**
 * @Author: Shark Chili
 * @Email: sharkchili.su@gmail.com
 * @Date: 2019/1/8 0008
 */
public class BinarySearchTree <AnyType extends Comparable<? super AnyType>>{

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
				throw new RuntimeException("under flow");
			}else{
				return findMin(root).element;
			}
		}

		public AnyType findMax(){
			if(isEmpty()){
				throw new RuntimeException("under flow");
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
					return contains(x,t.left);
				}else if(compareResult > 0){
					return contains(x,t.right);
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
				return new BinaryNode<AnyType>(x,null,null);
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
