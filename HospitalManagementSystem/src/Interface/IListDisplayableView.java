package Interface;

import java.util.ArrayList;

/**
 * The IListDisplayableView interface provides a method for displaying a list of objects of type T.
 * This interface can be implemented by views that display collections of entities, such as lists of medications, staff, or appointments.
 *
 * @param <T> The type of objects to be displayed in a list format.
 */
public interface IListDisplayableView<T> {

    /**
     * Displays the details of a list of specified objects.
     *
     * @param objects An ArrayList of objects to be displayed, where each object is of type T.
     */
    public void display(ArrayList<T> objects);
}
