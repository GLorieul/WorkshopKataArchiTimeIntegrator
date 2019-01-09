import java.util.concurrent.TimeUnit;

public class Case3_CoolingOfCoffee {

    private static void wait(int duration)
    {
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void runCase() {
        // Simulation Parameters
        final double timeStep = 1.0; // [minutes]
        final double temperatureDrinkable = 35.0; // [°C]
        final double thermalDissipation = 0.2; // [minutes⁻¹]
        final double roomTemperature = 25.0; // [°C]
        final double initialTemperature = 70.0; // [°C]

        // Integration variables
        double tempN_EE  = initialTemperature; //Position of robot at time t^n
        double tempN_EI  = initialTemperature;
        double tempN_RK2 = initialTemperature;

        // Other variables
        // (None)

        // Print table's header
        System.out.println("Time         Temperature EE/EI/RK2        T - T_drinkable EE/EI/RK2");
        System.out.println("-------------------------------------------------------------------");

        double tN = 0.0; //tN = t^n i.e. current time
        int nbIter = 0;
        while(true) {
            //Display information
            System.out.format("%8.4f ", tN);
            System.out.format("%8.4e %8.4e %8.4e", tempN_EE, tempN_EI, tempN_RK2);
            System.out.format("%8.4f %8.4f %8.4f%n", tempN_EE - temperatureDrinkable, tempN_EI - temperatureDrinkable, tempN_RK2 - temperatureDrinkable);
            wait(1);

            // PERFORM TIME-INTEGRATION
            final double tNp1 = tN + timeStep;
            //Euler Explicit time-integration
            final double rateNp1_EE = tempN_EE + timeStep * (-thermalDissipation * (tempN_EE - roomTemperature));
            //Euler Implicit time-integration
            final double rateNp1_EI = tempN_EI + timeStep * (-thermalDissipation * (tempN_EI  - roomTemperature));
            // Runge-Kutta 2 Midpoint time-integration
            final double htMid = 0.5 * timeStep;
            final double tempMid = tempN_RK2 + htMid * (-thermalDissipation * (tempN_RK2 - roomTemperature)); //Useless in that particular case (RHS is not a function of position)
            final double rateNp1_RK2 = tempN_RK2 + timeStep * (-thermalDissipation * (tempMid - roomTemperature));

            // Moving to the next time step:
            tN += timeStep;
            nbIter++;
            tempN_EE = rateNp1_EE;
            tempN_EI = rateNp1_EI;
            tempN_RK2 = rateNp1_RK2;
        }
    }
}
