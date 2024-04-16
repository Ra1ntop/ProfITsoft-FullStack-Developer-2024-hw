package service;

import static org.junit.jupiter.api.Assertions.*;

import com.studio.rain.service.CalculateStatisticsService;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class CalculateStatisticsServiceTest {

    @Test
    void testCalculateStatisticsWithValidFolderPath() {
        CalculateStatisticsService service = new CalculateStatisticsService();
        String folderPath = "src/main/resources/";
        String attributeName = "group";
        int numThreads = 2;

        Map<String, Integer> statistics = service.calculateStatistics(folderPath, attributeName, numThreads);

        assertNotNull(statistics);
        assertFalse(statistics.isEmpty());
    }

    @Test
    void testCalculateStatisticsWithInvalidFolderPath() {
        CalculateStatisticsService service = new CalculateStatisticsService();
        String invalidFolderPath = "invalid/path";
        String attributeName = "attributeName";
        int numThreads = 2;

        assertThrows(RuntimeException.class, () -> service.calculateStatistics(invalidFolderPath, attributeName, numThreads));
    }


    @Test
    void testCalculateStatisticsWithNullFolderPath() {
        CalculateStatisticsService service = new CalculateStatisticsService();
        String attributeName = "attributeName";
        int numThreads = 2;

        assertThrows(RuntimeException.class, () -> service.calculateStatistics(null, attributeName, numThreads));
    }

    @Test
    void testCalculateStatisticsWithInvalidNumThreads() {
        CalculateStatisticsService service = new CalculateStatisticsService();
        String folderPath = "src/main/resources";
        String attributeName = "attributeName";
        int invalidNumThreads = -1;

        assertThrows(RuntimeException.class, () -> service.calculateStatistics(folderPath, attributeName, invalidNumThreads));
    }

    @Test
    void testCalculateStatisticsWithNonexistentFolderPath() {
        CalculateStatisticsService service = new CalculateStatisticsService();
        String nonexistentFolderPath = "nonexistent/path";
        String attributeName = "attributeName";
        int numThreads = 2;

        assertThrows(RuntimeException.class, () -> service.calculateStatistics(nonexistentFolderPath, attributeName, numThreads));
    }

}
