// AbstractFactoryDemo.java

// Enum to avoid stringly-typed logic
enum FactoryType {
    PREMIUM,
    ORDINARY
}

// Abstract Factory Producer (factory of factories)
class FactoryProducer {
    public static Factory getFactory(FactoryType type) {
        switch (type) {
            case PREMIUM:
                return new PremiumFactory();
            case ORDINARY:
                return new OrdinaryFactory();
            default:
                throw new IllegalArgumentException("Unknown Factory Type");
        }
    }
}

// Factory Interface
interface Factory {
    Vehicle getVehicle(String brand);
}

// Premium Factory
class PremiumFactory implements Factory {
    @Override
    public Vehicle getVehicle(String brand) {
        switch (brand) {
            case "Tesla":
                return new TeslaVehicle("Premium");
            case "Toyota":
                return new ToyotaVehicle("Premium");
            default:
                throw new IllegalArgumentException("Unsupported brand: " + brand);
        }
    }
}

// Ordinary Factory
class OrdinaryFactory implements Factory {
    @Override
    public Vehicle getVehicle(String brand) {
        switch (brand) {
            case "Tesla":
                return new TeslaVehicle("Ordinary");
            case "Toyota":
                return new ToyotaVehicle("Ordinary");
            default:
                throw new IllegalArgumentException("Unsupported brand: " + brand);
        }
    }
}

// Abstract Product
abstract class Vehicle {
    protected String type;

    public Vehicle(String type) {
        this.type = type;
    }

    public abstract void drive();
}

// Concrete Product - Tesla
class TeslaVehicle extends Vehicle {
    public TeslaVehicle(String type) {
        super(type);
    }

    @Override
    public void drive() {
        System.out.println("This is a " + type + " Tesla Vehicle.");
    }
}

// Concrete Product - Toyota
class ToyotaVehicle extends Vehicle {
    public ToyotaVehicle(String type) {
        super(type);
    }

    @Override
    public void drive() {
        System.out.println("This is a " + type + " Toyota Vehicle.");
    }
}
