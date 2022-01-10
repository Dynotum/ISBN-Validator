import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StockManagementTest {

    /**
     * This is a TEST STUB
     * A stub is a replacement for an object that the class we are testing has a dependency on.
     */
    @Test
    public void testCanGetACorrectoLocatorCode() {

        // Simulation of an external third party web service will be doing
        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn, "Of Mice and Men", "J. Steinbeck");
            }
        };

        ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return null;
            }
        };

        final StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);

        final String isbn = "0140177396";
        final String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        when(databaseService.lookup("0140177396"))
                .thenReturn(new Book("0140177396", "abc", "abc"));

        final StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        final String isbn = "0140177396";
        final String locatorCode = stockManager.getLocatorCode(isbn);

        // Expects once that lookup on our database service was called with this value: 0140177396,
        verify(databaseService).lookup("0140177396");

        verify(webService, never()).lookup(anyString());

    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresentInDatabase() {
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        when(databaseService.lookup("0140177396")).thenReturn(null);
        when(webService.lookup("0140177396"))
                .thenReturn(new Book("0140177396", "abc", "abc"));

        final StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        final String isbn = "0140177396";
        final String locatorCode = stockManager.getLocatorCode(isbn);

        // Verify how many times does the method was being called,
        verify(databaseService).lookup("0140177396");

        verify(webService).lookup("0140177396");
    }
}
