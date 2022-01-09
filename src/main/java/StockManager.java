public class StockManager {
    private ExternalISBNDataService webService;
    private ExternalISBNDataService databaseService;


    public String getLocatorCode(String isbn) {
        Book book = databaseService.lookup(isbn);

        if (book == null) {
            book = webService.lookup(isbn);
        }

        final StringBuilder locator = new StringBuilder();
        locator.append(isbn.substring(isbn.length() - 4))
                .append(book.getAuthor().substring(0, 1))
                .append(book.getTitle().split(" ").length);

        return locator.toString();
    }

    public void setWebService(ExternalISBNDataService webService) {
        this.webService = webService;
    }

    public void setDatabaseService(ExternalISBNDataService databaseService) {
        this.databaseService = databaseService;
    }
}
