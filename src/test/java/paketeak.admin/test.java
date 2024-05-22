package paketeak.admin;

import org.junit.jupiter.api.Test;
import paketak.admin.modeloak.Banatzailea;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class test {

    @Test
    void test() {
        Field[] fields = Banatzailea.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        assertTrue(true);
    }
}
