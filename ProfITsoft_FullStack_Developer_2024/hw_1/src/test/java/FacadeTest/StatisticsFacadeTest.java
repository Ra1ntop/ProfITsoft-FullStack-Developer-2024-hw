package FacadeTest;

import static org.junit.jupiter.api.Assertions.*;

import com.studio.rain.facade.StatisticsFacade;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class StatisticsFacadeTest {
    String folderPath = "src/main/resources/";

    @Test
    void testStartCalculateStatistics() {
        StatisticsFacade facade = new StatisticsFacade();
        String attributeName = "attributeName";
        String input = "2\n";
        BufferedReader reader = new BufferedReader(new StringReader(input));

        assertDoesNotThrow(() -> facade.startCalculateStatistics(reader, folderPath, attributeName));
    }

    @Test
    void testSaveStatisticToFile() {
        StatisticsFacade facade = new StatisticsFacade();
        Map<String, Integer> statistics = new HashMap<>();
        statistics.put("value1", 10);
        statistics.put("value2", 5);
        String attributeName = "attributeName";

        assertDoesNotThrow(() -> facade.saveStatisticToFile(statistics, folderPath, attributeName));
    }

    @Test
    void testCreateFileWithRandomInputStatistics() {
        StatisticsFacade facade = new StatisticsFacade();

        assertDoesNotThrow(() -> facade.createFileWithRandomInputStatistics(folderPath));
    }


    @Test
    void testStartCalculateStatisticsWithInvalidPath() {
        StatisticsFacade facade = new StatisticsFacade();
        String invalidFolderPath = "invalid/path";
        String attributeName = "attributeName";
        String input = "2\n";
        BufferedReader reader = new BufferedReader(new StringReader(input));

        assertThrows(IllegalArgumentException.class, () -> facade.startCalculateStatistics(reader, invalidFolderPath, attributeName));
    }

    @Test
    void testSaveStatisticToFileWithInvalidStatistics() {
        StatisticsFacade facade = new StatisticsFacade();
        Map<String, Integer> invalidStatistics = null;
        String attributeName = "attributeName";

        assertThrows(IllegalArgumentException.class, () -> facade.saveStatisticToFile(invalidStatistics, folderPath, attributeName));
    }

    @Test
    void testCreateFileWithRandomInputStatisticsAndCheckIfExistsWithInvalidFolderPath() {
        StatisticsFacade facade = new StatisticsFacade();
        String invalidFolderPath = "invalid/path";

        assertThrows(IllegalArgumentException.class, () -> facade.createFileWithRandomInputStatistics(invalidFolderPath));
    }

    @Test
    void testStartCalculateStatisticsWithNullFolderPath() {
        StatisticsFacade facade = new StatisticsFacade();
        String attributeName = "attributeName";
        BufferedReader reader = new BufferedReader(new StringReader("2\n"));

        assertThrows(IllegalArgumentException.class, () -> facade.startCalculateStatistics(reader, null, attributeName));
    }

    @Test
    void testStartCalculateStatisticsWithInvalidThreadInput() {
        StatisticsFacade facade = new StatisticsFacade();
        String folderPath = "src/main/resources";
        String attributeName = "attributeName";
        BufferedReader reader = new BufferedReader(new StringReader("abc\n"));

        assertThrows(NumberFormatException.class, () -> facade.startCalculateStatistics(reader, folderPath, attributeName));
    }

    @Test
    void testCreateFileWithRandomInputStatisticsWithInvalidFolderPath() {
        StatisticsFacade facade = new StatisticsFacade();
        String invalidFolderPath = "invalid/path";

        assertThrows(IllegalArgumentException.class, () -> facade.createFileWithRandomInputStatistics(invalidFolderPath));
    }

    @Test
    void testSaveStatisticToFileWithNullStatistics() {
        StatisticsFacade facade = new StatisticsFacade();
        String folderPath = "src/main/resources";
        String attributeName = "attributeName";

        assertThrows(IllegalArgumentException.class, () -> facade.saveStatisticToFile(null, folderPath, attributeName));
    }


}
