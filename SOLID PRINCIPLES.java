import java.util.List;
import java.util.ArrayList;

// S-Single Responsibility Principle
// Open / Closed Principle
// Liskov Substitution Principle
// Interface Segmented Principle
// Dependency Inversion Principle

// S - Single Responsibility Principle -> A class should have only one reason to change and only one responsibility.

class Marker {
    public String name;
    public String color;
    public int year;
    public int price;

    public Marker() {}

    public Marker(String name, String color, int year, int price) {
        this.name = name;
        this.color = color;
        this.year = year;
        this.price = price;
    }
};

// class Invoice {
//     private Marker marker;
//     private int quantity;
    
//     public Invoice(Marker marker, int quantity) {
//         this.marker = marker;
//         this.quantity = quantity;
//     }

//     public int calculateTotal() {                               REASON TO CHANGE if price calculation changes
//         int price = marker.price * this.quantity;
//         return price;
//     }

//     public void printInvoice() {                                REASON TO CHANGE if printing method changes
//         // print the invoice
//     }

//     public void saveToDB() {                                    REASON TO CHANGE if saving method changes
//         // save the data into DB
//     }
// };

//---------------SOLUTION---------------//

class Invoice {
    private Marker marker;
    private int quantity;
    
    public Invoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    public int calculateTotal() {
        int price = marker.price * this.quantity;
        return price;
    }
};

class InvoiceDao {
    private Invoice invoice;
    
    public InvoiceDao(Invoice invoice) {
        this.invoice = invoice;
    }

    public void saveToDB() {
        // Save into the DB
    }
};

class InvoicePrinter {
    private Invoice invoice;
    
    public InvoicePrinter(Invoice invoice) {
        this.invoice = invoice;
    }

    public void printInvoice() {
        // print the invoice
    }
};

// Open Close Principle -> Open for extension but closed for modification.

// class InvoiceDao {
//     private Invoice invoice;
    
//     public InvoiceDao(Invoice invoice) {
//         this.invoice = invoice;
//     }

//     public void saveToDB() {
//         // Save into the DB
//     }
// };

// class InvoiceDao {
//     private Invoice invoice;
    
//     public InvoiceDao(Invoice invoice) {
//         this.invoice = invoice;
//     }

//     public void saveToDB() {
//         // Save into the DB
//     }

//     public void saveToFile() {                      // Here the original class was modified which violates open close principle
//         // Save to file
//     }
// };

//---------------SOLUTION---------------//


interface InvoiceDaoInterface {
    public void save(Invoice invoice);
};

class DatabaseInvoiceDao implements InvoiceDaoInterface {
    @Override
    public void save(Invoice invoice) {
        // Save to DB
    }
};

class FileInvoiceDao implements InvoiceDaoInterface {
    @Override
    public void save(Invoice invoice) {
        // Save to file
    }
}


// Liskov Substitution Principle -> If Class B is a subtype of Class A, then we should bea able to replace object of A with B without breaking the behaviour of the program. Subclass should extend the capability of parent class not narrow it down.

// interface Bike {
//     void turnOnEngine();
//     void accelerate();
// };

// class MotorCycle implements Bike {
//     boolean isEngineOn;
//     int speed;

//     public void turnOnEngine(){
//         // turn on the engine!
//         isEngineOn = true;
//     }

//     public void accelerate() {
//         // increase the speed
//         speed = speed + 10;
//     }
// }

// class Bicycle implements Bike {
//     public void turnOnEngine() {
//         throw new AssertionError("There is no engine");         // here we changed the behaviour, if we replace an object of Bike with bicycle then the turnOnEngine capability will break.
//     }

//     public void accelerate() {
//         // do something
//     }
// }

class Vehicle {
    public Integer getNumberOfWheels () {                       // only keep generic methods in parent class
        return 2;
    }
}

class Bicycle extends Vehicle {

}

class EngineVehicle extends Vehicle {
    public boolean hasEngine() {
        return true;
    }
}

class Car extends EngineVehicle {

}

class MotorCycle extends EngineVehicle {

}

class Main {
    public static void main(String args[]) {
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new MotorCycle());
        vehicleList.add(new Car());
        vehicleList.add(new Bicycle());

        for(Vehicle vehicle : vehicleList) {
            System.out.println(vehicle.getNumberOfWheels().toString());     // here you can only call methouds visible at vehicle level and so all the child objects will be able to call this getNumberOfWheels function and the code will not break
        }
    }
}

// Interface Segmented Principle -> Interface should be such, that the client should not implement unnecessary functions they do not need

// interface RestaurantEmployee {
//     void washDishes();
//     void serveCustomers();
//     void cookFood();
// }

// class waiter implements RestaurantEmployee {
//     public void washDishes() {                               // here waiter class has to implement unnecessary functions
//         // not my job
//     }

//     public void serveCustomers() {
//         System.out.println("Serving the customer");
//     }

//     public void cookFood() {
//         // not my job
//     }
// }

interface WaiterInterface {
    void serveCustomers();
    void takeOrder();
}

interface ChefInterface {
    void cookFood();
    void decideMenu();
}

class waiter implements WaiterInterface {
    public void serveCustomers() {
        System.out.println("Serving the customers");
    }

    public void takeOrder() {
        System.out.println("Taking orders");
    }
}

// Dependency Inversion Principle -> Class should depend on interfaces rather than concrete classes.

// class MacBook {
//     private final WiredKeyboard keyboard;           // here in future if i have to assign new keyboard to macbook i can't do as i have assigned object of concrete classes here.
//     private final WiredMouse mouse;                 // here also i have assigned objects of concrete classes.

//     public MacBook() {
//         keyboard = new WiredKeyboard();             // here also i have assigned concrete objects
//         mouse = new WiredMouse();                   // here also i have assigned concrete objects
//     }
// }

// class MacBook {
//     private final Keyboard keyboard;                         // here interface objects are passed.
//     private final Mouse mouse;                               // here interface objects are passed.

//     public Macbook(Keyboard keyboard, Mouse mouse) {         // here interface objects are passed in constructor.
//         this.keyboard = keyboard;
//         this.mouse = mouse;
//     }
// }
