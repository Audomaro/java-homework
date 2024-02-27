package org.audomaro;

public class VehicleData {
    public static void main(String[] args) {
        int vin = 123456;
        String makeModel = "Ford Explorer";
        String color = "Blue";
        boolean hasTowingPackage = true;
        int odometerReading = 50000;
        double price = 25000.00;
        char qualityRating = 'A';

        System.out.println("Vehicle Identification Number (VIN).." + vin);
        System.out.println("Vehicle Make and Model..............." + makeModel);
        System.out.println("Vehicle Color........................" + color);
        System.out.println("Has Towing Package..................." + hasTowingPackage);
        System.out.println("Odometer Reading....................." + odometerReading);
        System.out.println("Price................................" + price);
        System.out.println("Quality Rating......................." + qualityRating);
    }
}
