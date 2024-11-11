package Interface;

/**
 * The IDisplayableView interface provides a method for displaying a specific object of type T.
 * This interface can be implemented by views that display details of various entities, such as appointments, medications, or staff.
 *
 * @param <T> The type of object to be displayed.
 */
public interface IDisplayableView<T> {

    /**
     * Displays the details of the specified object.
     *
     * @param object The object to be displayed, typically an entity like Appointment, Medication, or Staff.
     */
    public void display(T object);
}
