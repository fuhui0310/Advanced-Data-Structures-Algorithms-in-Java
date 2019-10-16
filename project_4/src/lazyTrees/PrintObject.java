package lazyTrees;

/**
 * Created by Fu on 17/2/2018.
 */

class PrintObject<E> implements Traverser<E>
    {
        public void visit(E x)
        {
            System.out.print( x + " ");
        }
    }
