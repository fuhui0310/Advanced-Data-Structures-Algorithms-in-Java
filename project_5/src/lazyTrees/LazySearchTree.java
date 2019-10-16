package lazyTrees;
import java.util.*;

/**
 *  BST with lazy deletion to keep track of total inventory ("deleted" + non deleted)
 * Created by Fu on 16/2/2018.
 */

public class LazySearchTree<E extends Comparable< ? super E > >
        implements Cloneable
{

    /**
     * Inner node class contains data and two children node.
     */
    private class LazySTNode
    {
        public boolean deleted;
        public LazySTNode lftChild, rtChild;
        public E data;
        public LazySTNode myRoot;  // needed to test for certain error

        public LazySTNode( E d, LazySTNode lft, LazySTNode rt )
        {
            deleted = false;
            lftChild = lft;
            rtChild = rt;
            data = d;
        }

        public LazySTNode()
        {
            this(null, null, null);
            deleted = false;
        }

        // function stubs -- for use only with AVL Trees when we extend
        public int getHeight() { return 0; }
        boolean setHeight(int height) { return true; }
    }

    protected int mSizeHard;
    protected int mSize;
    protected LazySTNode mRoot;
    protected LazySTNode minFound;
    protected LazySTNode maxFound;
    public LazySearchTree() { clear(); }
    public boolean empty() { return (mSize == 0); }
    public int size() { return mSize; }
    public int sizeHard() { return mSizeHard;}
    public void clear() { mSize = 0; mRoot = null; }
    public int showHeight() { return findHeight(mRoot, -1); }

    /**
     * finds the minimum node in the tree
     * @return the found minimum node in the tree.
     */
    public E findMin()
    {
        if (mRoot == null)
            throw new NoSuchElementException();
        return findMin(mRoot).data;
    }

    /**
     * finds the maximum node in the tree
     * @return the found maximum node in the tree.
     */
    public E findMax()
    {
        if (mRoot == null)
            throw new NoSuchElementException();
        return findMax(mRoot).data;
    }

    /**
     * finds the target node in the tree
     * @param x the target node
     * @return the result node
     */
    public E find( E x )
    {
        LazySTNode resultNode;
        resultNode = find(mRoot, x);
        if (resultNode == null)
            throw new NoSuchElementException();
        return resultNode.data;
    }

    /**
     * checks if the node is contained in the tree.
     * @param x the target node
     * @return boolean
     */
    public boolean contains(E x)  { return find(mRoot, x) != null && !find(mRoot, x).deleted; }

    /**
     * inserts node in the tree
     * @param x the target node
     * @return boolean
     */
    public boolean insert( E x )
    {
        int oldSize = mSize;
        mRoot = insert(mRoot, x);
        return (mSize != oldSize);
    }

    /**
     * removes node in the tree
     * @param x the target node
     * @return boolean
     */
    public boolean remove( E x )
    {
        int oldSize = mSize;
        remove(mRoot, x);
        return (mSize != oldSize);
    }

    /**
     * traverses the whole tree.
     * @param func
     * @param <F>
     */
    public < F extends Traverser<? super E > >
    void traverseHard(F func)
    {
        traverseHard(func, mRoot);
    }

    /**
     * traverses the whole tree without the deleted node.
     * @param func
     * @param <F>
     */
    public < F extends Traverser<? super E > >
    void traverseSoft(F func)
    {
        traverseSoft(func, mRoot);
    }

    public Object clone() throws CloneNotSupportedException
    {
        LazySearchTree newObject = (LazySearchTree)super.clone();
        newObject.clear();  // can't point to other's data

        newObject.mRoot = cloneSubtree(mRoot);
        newObject.mSize = mSize;

        return newObject;
    }

    /**
     * truly remove all deleted (stale) nodes from the tree.
     * @return true
     */
    public boolean collectGarbage() {
        int oldSize = mSizeHard;
        collectGarbage(mRoot);
        return (mSizeHard != oldSize);
    }

    /**
     * private helper methods that finds the minimum node in the tree
     */
    protected LazySTNode findMin( LazySTNode root )
    {
        if (root == null)
            return null;
        minFound = findMin(root.lftChild);
        if (minFound != null){
            return minFound;
        }else {
            if(!root.deleted){
                minFound = root;
                return minFound;
            }
            return findMin(root.rtChild);
        }
    }

    /**
     * private helper methods that finds the maximum node in the tree
     */
    protected LazySTNode findMax( LazySTNode root )
    {
        if (root == null)
            return null;
        maxFound = findMax(root.rtChild);
        if (maxFound != null){
            return maxFound;
        }else {
            if(!root.deleted){
                maxFound = root;
                return maxFound;
            }
            return findMin(root.lftChild);
        }
    }

    /**
     * private helper methods that finds the real minimum node in the whole tree
     */
    protected LazySTNode findMinHard(LazySTNode root) {
            if (root == null) {
                return null;
            }
            if (root.lftChild == null) {
                return root;
            }
            return findMinHard(root.lftChild);
        }

    /**
     * private helper methods that finds the real maximum node in the whole tree
     */
    protected LazySTNode findMaxHard(LazySTNode root) {
        if (root == null) {
            return null;
        }
        if (root.rtChild == null) {
            return root;
        }
        return findMaxHard(root.rtChild);
    }

    /**
     * private helper methods that inserts node in the tree
     */
    protected LazySTNode insert( LazySTNode root, E x ) {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null) {
            mSize++;
            mSizeHard++;
            return new LazySTNode(x,null,null);
        }
        compareResult = x.compareTo(root.data);
        if (compareResult < 0)
            root.lftChild = insert(root.lftChild, x);
        else if (compareResult > 0)
            root.rtChild = insert(root.rtChild, x);
        else if (root.deleted) {
            root.deleted = false;
            mSize++;
        }
        return root;
    }

    /**
     * private helper methods that lazy remove node from tree.
     */
    protected void remove( LazySTNode root, E x  )
    {
        int compareResult;  // avoid multiple calls to compareTo()
        if(root == null){
            return;
        }
        compareResult = x.compareTo(root.data);
        if ( compareResult < 0 )
            remove(root.lftChild, x);
        else if ( compareResult > 0 )
            remove(root.rtChild, x);

            // found the node
        else
        {
            root.deleted = true;
            mSize--;
        }
    }

    /**
     * private helper methods that truly remove the deleted node from the tree.
     */
    protected LazySTNode removeHard( LazySTNode root, E x  )
    {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null)
            return null;

        compareResult = x.compareTo(root.data);
        if ( compareResult < 0 )
            root.lftChild = removeHard(root.lftChild, x);
        else if ( compareResult > 0 )
            root.rtChild = removeHard(root.rtChild, x);

            // found the node
        else if (root.lftChild != null && root.rtChild != null)
        {
            root.data = findMinHard(root.rtChild).data;
            root.rtChild = removeHard(root.rtChild, root.data);
        }
        else
        {
            root =
                    (root.lftChild != null)? root.lftChild : root.rtChild;
            mSizeHard--;
        }
        return root;
    }

    /**
     * private helper methods
     */
    protected <F extends Traverser<? super E>>
    void traverseHard(F func, LazySTNode treeNode)
    {
        if (treeNode == null)
            return;

        traverseHard(func, treeNode.lftChild);
        func.visit(treeNode.data);
        traverseHard(func, treeNode.rtChild);
    }

    /**
     * private helper methods
     */
    protected <F extends Traverser<? super E>>
    void traverseSoft(F func, LazySTNode treeNode)
    {
        if (treeNode == null)
            return;

        traverseSoft(func, treeNode.lftChild);
        if (treeNode.deleted == false) {
            func.visit(treeNode.data);
        }
        traverseSoft(func, treeNode.rtChild);
    }

    /**
     * private helper methods
     */
    protected LazySTNode find( LazySTNode root, E x )
    {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null)
            return null;

        compareResult = x.compareTo(root.data);
        if (compareResult < 0)
            return find(root.lftChild, x);
        if (compareResult > 0)
            return find(root.rtChild, x);
        if (root.deleted == true) {
            return null;
        }else {
            return root;   // found
        }
    }

    /**
     * private helper methods that
     */
    protected void collectGarbage(LazySTNode root) {
        if (root == null) {
            return;
        }
        collectGarbage(root.lftChild);
        collectGarbage(root.rtChild);
            if (root.deleted) {
                mRoot = removeHard(mRoot, root.data);
            }

    }


    /**
     * private helper methods that truly remove all deleted (stale) nodes from the tree.
     */
    protected LazySTNode cloneSubtree(LazySTNode root)
    {
        LazySTNode newNode;
        if (root == null)
            return null;

        // does not set myRoot which must be done by caller
        newNode = new LazySTNode
                (
                        root.data,
                        cloneSubtree(root.lftChild),
                        cloneSubtree(root.rtChild)
                );
        return newNode;
    }

    /**
     * private helper methods
     */
    protected int findHeight( LazySTNode treeNode, int height )
    {
        int leftHeight, rightHeight;
        if (treeNode == null)
            return height;
        height++;
        leftHeight = findHeight(treeNode.lftChild, height);
        rightHeight = findHeight(treeNode.rtChild, height);
        return (leftHeight > rightHeight)? leftHeight : rightHeight;
    }

}