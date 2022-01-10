import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StockManagementTest {
    public static final String ISBN_MAGIC_VALUE = "0140177396";
    ExternalISBNDataService testWebService;
    ExternalISBNDataService testDatabaseService;
    StockManager stockManager;

    @Before
    public void setUp() {
        testWebService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        testDatabaseService = mock(ExternalISBNDataService.class);
        stockManager.setDatabaseService(testDatabaseService);
    }

    /**
     * This is a TEST STUB - Simulation of an external third party web service will be doing
     * A stub is a replacement for an object that the class we are testing has a dependency on.
     */
    @Test
    public void testCanGetACCorrectLocatorCode() {
        when(testWebService.lookup(anyString()))
                .thenReturn(new Book(ISBN_MAGIC_VALUE, "Of Mice and Men", "J. Steinbeck"));

        when(testDatabaseService.lookup(anyString()))
                .thenReturn(null);

        final String locatorCode = stockManager.getLocatorCode(ISBN_MAGIC_VALUE);
        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {

        when(testDatabaseService.lookup(ISBN_MAGIC_VALUE))
                .thenReturn(new Book(ISBN_MAGIC_VALUE, "abc", "abc"));

        final String isbn = ISBN_MAGIC_VALUE;
        final String locatorCode = stockManager.getLocatorCode(isbn);

        // Expects once that lookup on our database service was called with this value: 0140177396,
        verify(testDatabaseService).lookup(ISBN_MAGIC_VALUE);
        verify(testWebService, never()).lookup(anyString());
    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresentInDatabase() {
        when(testDatabaseService.lookup(ISBN_MAGIC_VALUE)).thenReturn(null);
        when(testWebService.lookup(ISBN_MAGIC_VALUE))
                .thenReturn(new Book(ISBN_MAGIC_VALUE, "abc", "abc"));

//        final String isbn = ISBN_MAGIC_VALUE;
        stockManager.getLocatorCode(ISBN_MAGIC_VALUE);

        // Verify how many times does the method was being called,
        verify(testDatabaseService).lookup(ISBN_MAGIC_VALUE);
        verify(testWebService).lookup(ISBN_MAGIC_VALUE);
    }
}
