import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    @Timeout(22)
    @Disabled
    public void testExecutionTime() throws Exception{
        long startTime = System.currentTimeMillis();
        Main.main(new String[]{});
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        assertTrue(executionTime <= 22000, "Execution time exceeds 22 seconds: " + executionTime);
    }

}