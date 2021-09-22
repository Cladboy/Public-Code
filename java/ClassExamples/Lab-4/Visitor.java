/**
*Interface that allows exictuion of data in the Visitable interface
*
* @author Michael O'Keeffe (116315991)
*/
public interface Visitor<T>{
    public void show( final T data );
}