package nonBlocking;
/**
 * OplimisticException.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 03.05.2018
 */
public class OplimisticException extends RuntimeException {
    OplimisticException() {
        super("OplimisticException, версии не совпадают");
    }
}
