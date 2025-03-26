import java.util.ArrayList;
import java.util.List;

enum VehicleType {
    TWO_WHEELER,
    FOUR_WHEELER
}

class Vehicle {
    int vehicleNo;
    VehicleType vehicleType;

    Vehicle(int vehicleNo, VehicleType vehicleType) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
    }
}

class ParkingSpot {
    int id;
    boolean isEmpty;
    Vehicle vehicle;
    int price;

    ParkingSpot(int id, int price) {
        this.id = id;
        this.isEmpty = true;
        this.vehicle = null;
        this.price = price;
    }

    public void parkVehicle(Vehicle v) {
        this.vehicle = v;
        this.isEmpty = false;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isEmpty = true;
    }
}

abstract class ParkingSpotManager {
    List<ParkingSpot> spots;

    ParkingSpotManager(List<ParkingSpot> spots) {
        this.spots = spots;
    }

    abstract ParkingSpot findParkingSpace();

    void parkVehicle(Vehicle v) {
        ParkingSpot spot = findParkingSpace();
        spot.parkVehicle(v);
    }

    void removeVehicle(Vehicle v) {
        for (ParkingSpot spot : spots) {
            if(spot.vehicle != null && spot.vehicle.equals(v)) {
                spot.removeVehicle();
                break;
            }
        }
    }
}

class TwoWheelerManager extends ParkingSpotManager {
    TwoWheelerManager(List <ParkingSpot> spots) {
        super(spots);
    }

    @Override
    ParkingSpot findParkingSpace() {
        // Implementation to find nearest parking spot for Two Wheelers
        return null;
    }
}

class FourWheelerManager extends ParkingSpotManager {
    FourWheelerManager(List<ParkingSpot> spots) {
        super(spots);
    }

    @Override
    ParkingSpot findParkingSpace() {
        // Implementation to find nearest parking space for Four Wheelers
        return null;
    }
}

class ParkingSpotManagerFactory {
    ParkingSpotManager getParkingSpotManager(VehicleType vehicleType, List<ParkingSpot> spots) {
        if(vehicleType == VehicleType.TWO_WHEELER) {
            return new TwoWheelerManager(spots);
        }
        else if(vehicleType == VehicleType.FOUR_WHEELER) {
            return new FourWheelerManager(spots);
        }
        else {
            return null;
        }
    }
}

class Ticket {
    long entryTime;
    ParkingSpot parkingSpot;
    Vehicle vehicle;

    Ticket(long entryTime, ParkingSpot parkingSpot, Vehicle vehicle) {
        this.entryTime = entryTime;
        this.parkingSpot = parkingSpot;
        this.vehicle = vehicle;
    }
}

class EntranceGate {
    ParkingSpotManagerFactory factory;

    EntranceGate(ParkingSpotManagerFactory factory) {
        this.factory = factory;
    }

    ParkingSpot findParkingSpace(VehicleType vehicleType, List<ParkingSpot> spots) {
        ParkingSpotManager manager = factory.getParkingSpotManager(vehicleType, spots);
        return manager.findParkingSpace();
    }

    Ticket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
        // Implementation to generate ticket
        return null;
    }
}

class ExitGate {
    ParkingSpotManagerFactory factory;

    ExitGate(ParkingSpotManagerFactory factory) {
        this.factory = factory;
    }

    void removeVehicle(Ticket ticket, List<ParkingSpot> spots) {
        ParkingSpotManager manager = factory.getParkingSpotManager(ticket.vehicle.vehicleType, spots);
        manager.removeVehicle(ticket.vehicle);
    }
}

class Main {
    public static void main(String[] args) {
        List<ParkingSpot> spots = new ArrayList<>();

        for(int i=1;i<=100;i++) {
            if(i<50) {
                spots.add(new ParkingSpot(i, 10));
            }
            else {
                spots.add(new ParkingSpot(i, 20));
            }
        }

        ParkingSpotManagerFactory factory = new ParkingSpotManagerFactory();

        EntranceGate entranceGate = new EntranceGate(factory);
        ExitGate exitGate = new ExitGate(factory);

        Vehicle vehicle = new Vehicle(123, VehicleType.FOUR_WHEELER);
        ParkingSpot spot = entranceGate.findParkingSpace(vehicle.vehicleType, spots);
        Ticket ticket = entranceGate.generateTicket(vehicle, spot);

        exitGate.removeVehicle(ticket, spots);
    }
}
