import java.util.ArrayList;

public class zheng_BinarySearchTree implements BinarySearchTreeFunctions {
	private Node root;

	public zheng_BinarySearchTree() {
		root = null;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public void insertNode(Node c) {
		if (root == null) {
			root = c;
			c.setCount(c.getCount()+1);
		} else {
			boolean end = false;
			Node node = root;
			while (!end) {
				if (c.getKey() < node.getKey()) {
					if (node.getLeft() == null) {
						node.setLeft(c);
						c.setParent(node);
						c.setCount(c.getCount()+1);
						end = true;
					} else {
						node = node.getLeft();
					}
				} else if (c.getKey() > node.getKey()) {
					if (node.getRight() == null) {
						node.setRight(c);
						c.setParent(node);
						c.setCount(c.getCount()+1);
						end = true;
					} else {
						node = node.getRight();
					}
				} else if (c.getKey() == node.getKey()){
					node.setCount(node.getCount()+1);
					end = true;
				} else {
					end = true;
				}
			}
		}
	}

	public void preOrderWalk(Node a) {
		if (a != null) {
			System.out.println(a.toString());
			preOrderWalk(a.getLeft());
			preOrderWalk(a.getRight());
		}
	}

	public void preOrderWalk(Node a, ArrayList<String> list) {
		if (a != null) {
			list.add(a.toString());
			preOrderWalk(a.getLeft(), list);
			preOrderWalk(a.getRight(), list);
		}
	}

	public void preOrderWalk(Node a, String id, ArrayList<String> result) {
		if (a != null) {
			System.out.println(a.toString() + " " + id);
			result.add(a.getKey() + " " + id);
			preOrderWalk(a.getLeft(), "0" + id, result);
			preOrderWalk(a.getRight(), "1" + id, result);
		}
	}

	public void inOrderWalk(Node a) {
		if (a != null) {
			inOrderWalk(a.getLeft());
			System.out.println(a);
			inOrderWalk(a.getRight());
		}
	}

	public void inOrderWalk(Node a, ArrayList<String> list) {
		if (a != null) {
			inOrderWalk(a.getLeft(), list);
			list.add(a.toString());
			inOrderWalk(a.getRight(), list);
		}
	}

	public void postOrderWalk(Node a) {
		if (a != null) {
			postOrderWalk(a.getLeft());
			postOrderWalk(a.getRight());
			System.out.println(a);
		}
	}

	public void postOrderWalk(Node a, ArrayList<String> list) {
		if (a != null) {
			postOrderWalk(a.getLeft(), list);
			postOrderWalk(a.getRight(), list);
			list.add(a.toString());
		}
	}

	public Node getMax(Node a) {
		if (a.getRight() == null)
			return a;

		return getMax(a.getRight());
	}

	public Node getMin(Node a) {
		if (a.getLeft() == null)
			return a;

		return getMin(a.getLeft());
	}

	public Node getSuccessor(Node a) {
		if (a.getRight() != null)
			return getMin(a.getRight());
		Node b = a.getParent();
		while (b != null && a.equals(b.getRight())) {
			a = b;
			b = b.getParent();
		}
		return b;
	}

	public Node getPredecessor(Node a) {
		if (a.getLeft() != null)
			return getMax(a.getLeft());
		Node b = a.getParent();
		while (b != null && a.equals(b.getLeft())) {
			a = b;
			b = b.getParent();
		}
		return b;
	}

	public Node getNode(Node a, int key) {

		if (a == null || a.getKey() == key)
			return a;
		if (a.getKey() > key)
			return getNode(a.getLeft(), key);
		return getNode(a.getRight(), key);
	}

	private int getHeight(Node a, int height) {
		if (a == null)
			return height;
		return Math.max(getHeight(a.getLeft(), height + 1), getHeight(a.getRight(), height + 1));
	}

	public int getHeight(Node a) {
		return getHeight(a, -1);
	}

	public void shiftNode(Node d, Node e) {

		if (d.getParent() == null)
			setRoot(e);
		else {
			if (d.equals(d.getParent().getLeft()))
				d.getParent().setLeft(e);
			else
				d.getParent().setRight(e);
		}
		if (e != null)
			e.setParent(d.getParent());

	}

	public void deleteNode(Node c) {
		if (c.getCount() > 1) {
			c.setCount(c.getCount()-1);
		}
		else if (c.getLeft() == null) {
			shiftNode(c, c.getRight());
		} else {
			if (c.getRight() == null) {
				shiftNode(c, c.getLeft());
			} else {
				Node b = getSuccessor(c);
				if (b.getParent() != c) {
					shiftNode(b, b.getRight());
					b.setRight(c.getRight());
					b.getRight().setParent(b);
				}
				shiftNode(c, b);
				b.setLeft(c.getLeft());
				b.getLeft().setParent(b);
			}
		}
	}
}
