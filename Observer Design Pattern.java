import java.util.ArrayList;
import java.util.List;

interface StockObservable {
    public void add(NotificationAlertObserver observer);
    public void remove(NotificationAlertObserver observer);
    public void notifySubscribers();
    public void setStockCount(int newStockAdded);
    public int getStockCount();
}

class IphoneObservable implements StockObservable {
    public List<NotificationAlertObserver> observerList = new ArrayList<>();
    public int stockCount = 0;

    @Override
    public void add(NotificationAlertObserver observer) {
        this.observerList.add(observer);
    }  
    @Override
    public void remove(NotificationAlertObserver observer){
        this.observerList.remove(observer);
    }
    @Override
    public void notifySubscribers(){
        for(NotificationAlertObserver observer : this.observerList) {
            observer.update();
        }
    }
    @Override
    public void setStockCount(int newStockAdded){
        if(stockCount == 0) {
            notifySubscribers();
        }

        stockCount = stockCount + newStockAdded;
    }
    @Override
    public int getStockCount(){
        return stockCount;
    }
}

interface NotificationAlertObserver {
    public void update();
}

class EmailAlertObserver implements NotificationAlertObserver {
    String emailId;
    StockObservable observable;

    public EmailAlertObserver(String emailId, StockObservable observable) {
        this.observable = observable;
        this.emailId = emailId;
    }

    @Override
    public void update() {
        sendMail(emailId, "Product is in stock!");
    }

    private void sendMail(String emailId, String msg) {
        System.out.println("Mail sent to: " + emailId);
    }
}

class MobileAlertObserver implements NotificationAlertObserver {
    String userName;
    StockObservable observable;

    public MobileAlertObserver(String userName, StockObservable observable) {
        this.observable = observable;
        this.userName = userName;
    }
    @Override
    public void update() {
        sendMsgOnMobile(userName, "Product is in stock!");
    }
    private void sendMsgOnMobile(String userName, String msg) {
        System.out.println("Msg sent to: " + userName);
    }
}
