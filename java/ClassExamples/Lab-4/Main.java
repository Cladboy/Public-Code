/**
 * This is a class that tests our vistable list by creating a few
 * different visitors that perform actions based on the contents of the
 * list.
 *
 * The visitors we create are {@code Printer}, {@code Counter} and
 * {@code Reverser}.
 * A {@code Copier} class is also provided but that is not a visitor
 * itself, it depends on the Reverser class.
 */
public class Main {
    /**
     * Some class-specific constants must be defined such as
     * strings used in the interface and a variable size of the
     * testing array.
     */
    private static final int SIZE_OF_TESTING_ARRAY = 40;
    private static final String BEFORE_1ST_PRINTER = 
                                            "Printing contents:";
    private static final String BEFORE_COUNTER = 
                                            "Size of list: ";
    private static final String BEFORE_2ND_PRINTER = 
                                            "Printing reverse contents:";
    private static final String BEFORE_3RD_PRINTER = 
                                            "Printing copied contents:";


    /**
     * Main; a simple testing program for a number of {@code Visitor}s
     * that we are testing on a {@code MyList}, a visitable collection.
     * These visitors are called statically (e.g. {@code Printer.print(list);}
     * ) and their behaviour is defined in nested classes of this class.
     * @param args command-line arguments, unused.
     */
    public static void main(String[] args) {
        // MyList is a visitable collection we will use to test the
        // visitor classes we have created.
        MyList<Integer> intList = new MyList<Integer>();

        // We add integer items to the MyList.
        for(int i=0; i != SIZE_OF_TESTING_ARRAY; i++){
            intList.add(i);
        }

        // Print a user-facing message and the contents of the initial
        // MyList we have just created.
        System.out.println(BEFORE_1ST_PRINTER);
        Printer.print(intList);
        
        // Prints a newline for decoration.
        System.out.println();

        // Prints a user-facing message plus the returned value
        // of our Counter visitor class.
        System.out.println( BEFORE_COUNTER + Counter.count(intList) );
        
        System.out.println();

        // Prints a user facing message, then prints the output
        // of a method which reverses the list.
        System.out.println(BEFORE_2ND_PRINTER);
        Printer.print( Reverser.reverse(intList) );

        System.out.println();

        // Prints a user facing message, then prints the output
        // of a method which copies the list.
        System.out.println(BEFORE_3RD_PRINTER);
        Printer.print( Copier.copy( intList ) );
    }

    /**
     * Holder is a simple way of storing a variable with getter and
     * setter methods and is generic across all classes.
     */
    private static class Holder<T>{
        // A generic data type to be kept within Holder.
        private T data;

        /**
         * Creates a Holder object that contains null. This should be
         * overwritten.
         * @return A "blank" Holder object.
         */
        public Holder(){
            this.data = null;
        }

        /**
         * Creates a Holder object instantiated with data.
         * @param  data Data to be stored in holder.
         * @return      A new Holder containing that data.
         */
        public Holder(T data){
            this.data = data;
        }

        /**
         * Returns the data currently stored in the Holder.
         * @return holder.data
         */
        public T get(){
            return data;
        }

        /**
         * Overwrites the current data stored in the Holder with new data.
         * @param data Data of the same type as is stored in the holder
         *             with which we are overwriting the current version.
         */
        public void set( final T data ){
            this.data = data;
        }
    }

    /**
     * Reverser: a class that provides the {@code Reverser.reverse( Visitable )}
     * method. Returns a MyList object containing the contents of the
     * original visitable collection in reverse order.
     */
    private static class Reverser<T> implements Visitor<T>{
        private final MyList<T> list;

        /**
         * Private constructor which creates a Reverser that is
         * used as an argument to a visitable collection's 
         * {@code visitAll()} method.
         * @return A Reverser object, instanatiated with an empty MyList.
         */
        private Reverser(){
            list = new MyList<T>();
        }

        /**
         * Public-facing static method which returns a MyList
         * object containing the contents of the original visitable 
         * collection in reverse order.
         * @param  list A visitable collection.
         * @return      A MyList object containing the contents of the
         *              visitable collection in reverse order.
         */
        public static <T> MyList<T> reverse( final Visitable<T> list ){
            Reverser<T> reverser = new Reverser<T>();
            list.visitAll( reverser );
            return reverser.list;
        }

        /**
         * This is called for each element of the visitable collection.
         * Here, it adds the item to a {@code MyList}, acting as a queue.
         * @param data Data provided by the visitable collection.
         */
        @Override
        public void show( final T data ){
            list.add(data);
        }

    }

    /**
     * A class that provides the user-facing staic method
     * that copies a visitable collection by simply reversing it twice.
     * Copier is not a visitor but it depends on visitors.
     */
    private static class Copier<T> {
        /**
         * Copies a visitable collection.
         * @param  list A visitable collection.
         * @return      A MyList object containing the same contents in
         *              the same order.
         */
        public static <T> MyList<T> copy( final Visitable<T> list){
            return Reverser.reverse( Reverser.reverse( list ) );
        }
    }

    /**
     * A class that provides the user-facing static
     * method to allow for the printing of visitable collections.
     */
    private static class Printer<T> implements Visitor<T>{
        // We implement the printer as an extendable string.
        // However, the show() method can only access private attributes.
        // Hence we instantiate a Holder to store the extending string.
        private final Holder<String> stringHolder;

        /**
         * A private constructor to create a visitor Printer object.
         * @return A Visitor for visitable collections that prints the
         *         contents.
         */
        private Printer(){
            stringHolder = new Holder<String>("");
        }

        /**
         * User-facing static method to print the contents of a visitable
         * collection.
         * @param  list A visitable collection.
         */
        public static <T> void print( final Visitable<T> list ){
            Printer<T> printer = new Printer<T>();
            printer.stringHolder.set("{");
            list.visitAll( printer );
            printer.stringHolder.set(printer.stringHolder.get() + " }");
            System.out.println( printer.stringHolder.get() );
        }

        /**
         * Called for each element of a visitable collection. Adds a
         * string representation of that element to an extending string.
         * @param data Content of an element of a visitable collection.
         */
        @Override
        public void show( final T data ){
            stringHolder.set( stringHolder.get() + 
                              " " + data.toString() );
        }
    }

    /**
     * Provides the user-facing static method which
     * returns the number of elements in a visitable collection.
     */
    private static class Counter<T> implements Visitor<T>{
        // The show() method can only access final instance attributes
        // so we create a Holder object that stores the current count
        // value.
        private final Holder<Integer> countHolder;

        /**
         * Private constructor for a Counter object with an initial
         * value of zero.
         * @return A Counter visitor object with an initial value of 0.
         */
        private Counter(){
            countHolder = new Holder<Integer>(0);
        }

        /**
         * User-facing static method which counts the
         * contents of a visitable collection and returns the answer
         * as an integer.
         * @param  list A visitable collection.
         * @return      An integer value representing the number of
         *              elements in the collection.
         */
        public static <T> int count( final Visitable<T> list ){
            Counter<T> counter = new Counter<T>();
            list.visitAll( counter );
            return counter.countHolder.get();
        }

        /**
         * Executed for every element of the visitable collection.
         * It increases the stored count value by one for each element.
         * @param data Data stored in the element of the visitable
         *             collection, here unused but necessary to implement
         *             the {@code Visitor} interface.
         */
        @Override
        public void show( final T data ){
            countHolder.set( countHolder.get()+1 );
        }
    }
}