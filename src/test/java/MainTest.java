import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Disabled;

import java.util.concurrent.TimeUnit;

class MainTest {

    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled("Тест вимкнено. Запустіть вручну при необхідності.")
    void testMainMethodExecutionTime() throws Exception {
        Main.main(new String[]{});
    }
}
