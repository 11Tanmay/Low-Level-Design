interface DriveStrategy {
    public void drive();
}

class NormalDriveStrategy implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Normal Drive Strategy");
    }
}

class SportsDriveStrategy implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Sports Drive Strategy");
    }
}

class Vehicle {
    DriveStrategy driveStrategyObject;

    Vehicle(DriveStrategy driveStrategyObject) {                // strategy will be passed in the constructor -> constructor injection
        this.driveStrategyObject = driveStrategyObject;
    }

    public void drive() {
        this.driveStrategyObject.drive();                       // call the drive method of the driveStrategy
    }
}

class OffRoadVehicle extends Vehicle {
    OffRoadVehicle() {
        super(new SportsDriveStrategy());
    }
}

class SportsVehicle extends Vehicle {
    SportsVehicle() {
        super(new SportsDriveStrategy());
    }
}

class GoodsVehicle extends Vehicle {
    GoodsVehicle() {
        super(new NormalDriveStrategy());
    }
}
