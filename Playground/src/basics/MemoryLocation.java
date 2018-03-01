package basics;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class MemoryLocation {

    private static int apple = 10;
    private Long orange = 10L;
    private Long orange2 = 10L;

    public static void main(String[] args) throws Exception {
        Unsafe unsafe = getUnsafeInstance();

        Field appleField = MemoryLocation.class.getDeclaredField("apple");
        System.out.println("Location of Apple: "
                + unsafe.staticFieldOffset(appleField));

        Field orangeField = MemoryLocation.class.getDeclaredField("orange");
        System.out.println("Location of Orange: "
                + unsafe.objectFieldOffset(orangeField));

        Field orangeField2 = MemoryLocation.class.getDeclaredField("orange2");
        System.out.println("Location of Orange2: "
                + unsafe.objectFieldOffset(orangeField2));
    }

    private static Unsafe getUnsafeInstance() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }
}

