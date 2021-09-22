/**
*Interface that allows data to accessed by Visitor
*
* @author Michael O'Keeffe (116315991)
*/
public interface Visitable<T>{
    /**
     * A method that should visit every object in a collection and
     * call {@code visitor.show()} on that object.
     * @param visitor An object implementing the {@code Visitor} interface
     *                which visits the elements in the visitable collection.
     */
    public void visitAll( Visitor<T> visitor );
}
