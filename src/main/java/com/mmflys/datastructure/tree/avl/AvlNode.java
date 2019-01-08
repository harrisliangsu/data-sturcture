package com.mmflys.datastructure.tree.avl;

/**
 * @Author: Shark Chili
 * @Email: sharkchili.su@gmail.com
 * @Date: 2019/1/8 0008
 */
public class AvlNode<AnyType extends Comparable<? super AnyType>> {

	private static final int ALLOWED_IMBALANCE = 1;

	AnyType element;
	AvlNode<AnyType> left;
	AvlNode<AnyType> right;
	int height;

	public AvlNode(AnyType theElement) {
		this(theElement, null, null);
	}

	public AvlNode(AnyType element, AvlNode<AnyType> left, AvlNode<AnyType> right) {
		this.element = element;
		this.left = left;
		this.right = right;
		this.height = 0;
	}

	private int height(AvlNode<AnyType> t) {
		return t == null ? -1 : t.height;
	}

	private AvlNode<AnyType> insert(AnyType x, AvlNode<AnyType> t) {
		if (t == null) {
			return new AvlNode<AnyType>(x, null, null);
		} else {
			int compareResult = x.compareTo(t.element);
			if (compareResult < 0) {
				t.left = insert(x, t.left);
			} else if (compareResult > 0) {
				t.right = insert(x, t.right);
			} else {
				// Duplicate; do nothing
			}
		}
		return balance(t);
	}

	/**
	 * 平衡树
	 *
	 * @param t 根节点
	 * @return 新树
	 */
	private AvlNode<AnyType> balance(AvlNode<AnyType> t) {
		if (t == null) {
			return null;
		} else if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
			if (height(t.left.left) >= height(t.left.right)) {
				t = rotateWithLeftChild(t);
			} else {
				t = rotateWithRightChild(t);
			}
		} else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
			if (height(t.right.right) >= height(t.right.left)) {
				t = rotateWithRightChild(t);
			} else {
				t = doubleWithRightChild(t);
			}
		}
		t.height = Math.max(height(t.left), height(t.right)) + 1;
		return t;
	}

	/**
	 * 右左-双旋
	 *
	 * @param k3 根节点
	 * @return 新树
	 */
	private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k3) {
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}

	/**
	 * 右-单旋转
	 *
	 * @param k2 根节点
	 * @return 新树
	 */
	private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k2) {
		AvlNode<AnyType> k1 = k2.right;
		k2.right = k1.left;
		k1.left = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.right), k2.height) + 1;
		return k1;
	}

	/**
	 * 左-单旋转
	 *
	 * @param k2 根节点
	 * @return 新树
	 */
	private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2) {
		AvlNode<AnyType> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}

	private AvlNode<AnyType> remove(AnyType x, AvlNode<AnyType> t) {
		if (t == null) {
			return null;
		} else {
			int compareResult = x.compareTo(t.element);
			if (compareResult < 0) {
				t.left = remove(x, t.left);
			} else if (compareResult > 0) {
				t.right = remove(x, t.right);
			} else if (t.left != null && t.right != null) {
				t.element = findMin(t.right).element;
				t.right = remove(t.element, t.right);
			} else {
				t = (t.left != null) ? t.left : t.right;
			}
			return balance(t);
		}
	}

	// 在左子树中搜索
	private AvlNode<AnyType> findMin(AvlNode<AnyType> t) {
		if (t == null) {
			return null;
		} else if (t.left == null) {
			return t;
		} else {
			return findMin(t.left);
		}
	}
}
