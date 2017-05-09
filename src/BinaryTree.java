public class BinaryTree {
    private Node root, current;

    public BinaryTree() {
        root = null;
    }



    public void insert(int n)
    {
        Node current = root;
        Node newNode = new Node();

        newNode.data = n;
        newNode.left = null;
        newNode.right = null;

        if (root == null)
            root = newNode;

        else
            while(true)
                if (newNode.data > current.data)
                    if (current.right == null)
                    {
                        current.right = newNode;
                        break;
                    }
                    else
                        current = current.right;
                else
                if (current.left == null)
                {
                    current.left = newNode;
                    break;
                }
                else
                    current = current.left;
    }
    public Node search(int n)
    {
        Node current = root;

        while (current != null)
            if (current.data == n)
                return current;
            else if (current.data > n)
                current = current.left;
            else
                current = current.right;
        return null;
    }

    public boolean remove(int n)
    {
        // Check empty tree
        if (root == null)
            return false;

        // Prepare search for node
        Node current = root;
        Node parent = root;
        boolean currentIsLeft = true;

        while (current.data != n)
        {
            // currentIsLeft is true when current is finds n
            // and is a “left” child of parent
            parent = current;
            if (current.data > n)
            {
                currentIsLeft = true;
                current = current.left;
            }
            else
            {
                currentIsLeft = false;
                current = current.right;
            }

            // If current is null, node n was not found
            if (current == null)
                return false;
        }

        // At this point, current is the node to delete
        // Now, we check for the situations

        // Situation 1 - leaf node
        if (current.left == null && current.right == null)
            // Check if current node is root
            if (parent == current)
                root = null;

                // Check which child pointer of parent to set
            else if (currentIsLeft)
                parent.left = null;
            else
                parent.right = null;


        // Situation 2 - one child. Parent inherits child
        // or if current is root, root takes child
        else if (current.left == null)

            if (parent == current)
                root = current.right;
            else if (currentIsLeft)
                parent.left = current.right;
            else
                parent.right = current.right;
        else if (current.right == null)
                if (parent == current)
                    root = current.left;
                else if (currentIsLeft)
                    parent.left = current.left;
                else
                    parent.right = current.left;

        // Situation 3: two children
        else
        {
            Node successor = getSuccessor(current);

            // Replace current node with successor
            if (parent == current)
                root = successor;
            else if (currentIsLeft)
                parent.left = successor;
            else
                parent.right = successor;

            // Successor will always come from the right, so
            // it must also take deleted node’s left child
            successor.left = current.left;
        }
        return true;
    }

    private Node getSuccessor(Node removedNode)
    {
        // Prepare successor search by keeping track
        // of parent and current
        Node successorParent = removedNode;
        Node successor = removedNode;
        Node current = successor.right;

        // Starting at the right child of the node to be
        // removed, go down the subtree’s left children
        // until there are no more children on the left
        while (current != null)
        {
            successorParent = successor;
            successor = current;
            current = current.left;
        }

        // if the successor is somewhere down the subtree,
        // the parent’s left child must take the
        // the successor’s right child. Then, the
        // successor’s right child takes the node
        // to delete’s right child (because successor will
        // be replacing it.
        if (successor != removedNode.right)
        {
            successorParent.left = successor.right;
            successor.right = removedNode.right;
        }

        // Note that if the successor is the immediate
        // right child of the node to delete, we just
        // return that node (it has no left children and what
        // ever is on successor’s right stays that way even
        // after successor replaces the removed node.
        return successor;
    }

    public void display()
    {
        displayInOrder(root);
    }

    void displayInOrder(Node current)
    {
        if (current != null)
        {
            displayInOrder(current.left);
            System.out.println(current.data);
            displayInOrder(current.right);
        }
    }

    void displayPreOrder(Node current)
    {
        if (current != null)
        {
            System.out.println(current.data);
            displayPreOrder(current.left);
            displayPreOrder(current.right);
        }
    }

    void displayPostOrder(Node current)
    {
        if (current != null)
        {
            displayPostOrder(current.left);
            displayPostOrder(current.right);
            System.out.println(current.data);
        }
    }



}