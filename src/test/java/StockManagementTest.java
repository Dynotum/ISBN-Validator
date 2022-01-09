import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresentInDatabase() {

    }
}
