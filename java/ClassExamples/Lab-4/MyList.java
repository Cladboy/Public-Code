/**
 * MyList: A visitable LinkedList implementation.
 * Only three methods are defined; a public constructor,
 * a method which can be used to add new data, and a {@code visitAll()}
 * method used to implement the {@code Visitable} interface.
 */
public class MyList<T> implements Visitable<T>{
    // This collection is a LinkedList implementation. It does this by
    // instantiating a "links" instance attribute which implements
    // our definition of a Link. Each Link should contain the next
    // in the set as its "tail"; as such this attribute is similar to
    // a "root" in a Tree.
    private Link<T> links;

    /**
     * Public constructor for an empty MyList.
     * @return A MyList with no contents.
     */
    public MyList(){
        links = null;
    }

    /**
     * Adds new data to the LinkedList by creating a new "links" attribute
     * containing the new data, and the old links as its "tail".
     * @param data Data of the appropriate type to add to this list.
     */
    public void add( final T data ){
        links = new Link<T>(data, links);
    }

    /**
     * VisitAll, a requirement of the {@code Visitable} interface. Allows
     * a {@code Visitor} with a defined {@code show()} method to run this 
     * method for each element of the {@code Visitable} collection in turn.
     * @param visitor An object impementing the {@code Visitor} interface
     */
    @Override
    public void visitAll( Visitor<T> visitor ){
        Link<T> current = links;
        while(current != null){
            visitor.show( current.getData() );
            current = current.getTail();
        }
    }

    /**
     * An implementation of a link within this LinkedList.
     */
    private static class Link<T>{
        /**
         * A {@code Link} stores data, and the next {@code Link} in the
         * list as its "tail".
         */
        private T data;
        private Link<T> tail;

        /**
         * A {@code Link} must be instantiated with data and a tail.
         * @param  data Data to be stored in this {@code Link}.
         * @param  tail The {@code Link} that follows this element in the list
         *              (or {@code null})
         * @return      A new {@code Link} object.
         */
        private Link(T data, Link<T> tail){
            this.data = data;
            this.tail = tail;
        }

        /**
         * Returns the data stored in this element.
         * @return this.data ; data of type T.
         */
        public T getData(){
            return this.data;
        }

        /**
         * Returns the next {@code Link} in this {@code LinkedList}, or 
         * {@code null} if this is the last {@code Link}.
         * @return The next {@code Link} in this {@code LinkedList}.
         */
        public Link<T> getTail(){
            return this.tail;
        }

    }
}