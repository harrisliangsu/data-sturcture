package com.mmflys.datastructure.tree.search;

import com.mmflys.datastructure.tree.binary.BinaryNode;

/**
 * @Author: Shark Chili
 * @Email: sharkchili.su@gmail.com
 * @Date: 2019/1/8 0008
 */
public class SearchNode<AnyType extends Comparable<? super AnyType>> extends BinaryNode<AnyType> {

	SearchNode(AnyType theElement) {
		this(theElement, null, null);
	}

	SearchNode(AnyType theElement, SearchNode<AnyType> lt, SearchNode<AnyType> rt) {
		this.element = theElement;
		this.left = lt;
		this.right = rt;
	}

	public SearchNode() {
		element = null;
	}

	public void makeEmpty() {
		element = null;
		left=null;
		right=null;
	}

	public boolean isEmpty() {
		return left==null&&right==null;
	}

	public boolean contains(AnyType x) {
		return contains(x, this);
	}

	public AnyType findMin() {
		if (isEmpty()) {
			throw new RuntimeException("under flow");
		} else {
			return findMin(this).element;
		}
	}

	public AnyType findMax() {
		if (isEmpty()) {
			throw new RuntimeException("under flow");
		} else {
			return findMax(this).element;
		}
	}

	public SearchNode<AnyType> insert(AnyType x) {
		return insert(x, this);
	}

	public SearchNode<AnyType> remove(AnyType x) {
		return remove(x, this);
	}

	public void printTree() {
		printTree(this);
	}

	private boolean contains(AnyType x, SearchNode<AnyType> t) {
		if (t == null) {
			return false;
		} else {
			int compareResult = x.compareTo(t.element);
			if (compareResult < 0) {
				return contains(x, (SearchNode<AnyType>) t.left);
			} else if (compareResult > 0) {
				return contains(x, (SearchNode<AnyType>) t.right);
			} else {
				return true;
			}
		}
	}

	// 在左子树中搜索
	private SearchNode<AnyType> findMin(SearchNode<AnyType> t) {
		if (t == null) {
			return null;
		} else if (t.left == null) {
			return t;
		} else {
			return findMin((SearchNode<AnyType>) t.left);
		}
	}

	// 在右子树中搜索
	private SearchNode<AnyType> findMax(SearchNode<AnyType> t) {
		if (t != null) {
			if (t.right != null) {
				return findMax((SearchNode<AnyType>) t.right);
			} else {
				return t;
			}
		} else {
			return null;
		}
	}

	// 与左右节点比较,插到路径的最后
	private SearchNode<AnyType> insert(AnyType x, SearchNode<AnyType> t) {
		if (t == null) {
			return new SearchNode<AnyType>(x, null, null);
		} else {
			int compareResult = x.compareTo(t.element);
			if (compareResult < 0) {
				t.left = insert(x, (SearchNode<AnyType>) t.left);
			} else if (compareResult > 0) {
				t.right = insert(x, (SearchNode<AnyType>) t.right);
			} else {
				// Duplicate; do nothing
			}
		}
		return t;
	}

	// 1.叶子节点: 直接删除
	// 2.有子节点
	// 懒惰删除(优点: 删除高效,节省插入重复元素空间 缺点: 标记为删除的元素越来越多,占用空间越来越大)
	private SearchNode<AnyType> remove(AnyType x, SearchNode<AnyType> t) {
		if (t == null) {
			return t;
		} else {
			int compareResult = x.compareTo(t.element);
			if (compareResult < 0) {
				t.right = remove(x, (SearchNode<AnyType>) t.right);
			} else if (compareResult > 0) {
				t.right = remove(x, (SearchNode<AnyType>) t.right);
			} else if (t.left != null && t.right != null) {
				// 有两个孩子时,找一个右子树中最小节点替代删除的节点
				t.element = findMin((SearchNode<AnyType>) t.right).element;
				t.right = remove(t.element, (SearchNode<AnyType>) t.right);
			} else {
				t = (SearchNode<AnyType>) ((t.left != null) ? t.left : t.right);
			}
			return t;
		}
	}

	private void printTree(SearchNode<AnyType> t) {

	}
}
