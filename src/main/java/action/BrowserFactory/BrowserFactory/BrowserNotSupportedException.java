package action.BrowserFactory.BrowserFactory;

public class BrowserNotSupportedException extends IllegalStateException{
    public BrowserNotSupportedException(String browserName){
        super(String.format("Browser not supported: %s",browserName));
    }
}
