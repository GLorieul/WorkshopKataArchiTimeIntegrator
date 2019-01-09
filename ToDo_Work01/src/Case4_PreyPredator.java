import java.util.concurrent.TimeUnit;

public class Case4_PreyPredator {
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
        final double timeStep = 0.1; // [month]
        final double rabbitMating = 1.1; // Rate at which rabbits mate ("multiply")
        final double rabbitPreyed = 0.0004; // Rate at which rabbits are eaten by foxes
        final double foxesStarvation = 0.4; // Rate at which foxes die if not finding food
        final double foxesPreying = 0.0001; // Rate at which foxes mate, if they find food easily enough
        final double initialRabbits = 10000.0;
        final double initialFoxes = 10000.0;

        // Integration variables
        double rabbitsN_EE  = initialRabbits; // We use floating values because the ODE form of our Prey-Predator model
        double rabbitsN_EI  = initialRabbits; // assumes it (hypothesis) and requires it.
        double rabbitsN_RK2 = initialRabbits;
        double foxesN_EE  = initialFoxes;
        double foxesN_EI  = initialFoxes;
        double foxesN_RK2 = initialFoxes;

        // Other variables
        // (None)

        // Print table's header
        System.out.println("Time         Rabbits EE/EI/RK2        Foxes EE/EI/RK2");
        System.out.println("-------------------------------------------------------------------");

        double tN = 0.0; //tN = t^n i.e. current time
        int nbIter = 0;
        while(true) {
            if(nbIter % 5 == 0) { // Every half-month
                //Display information
                System.out.format("%8.4f ", tN);
                System.out.format("%8.0f %8.0f %8.0f", rabbitsN_EE, rabbitsN_EI, rabbitsN_RK2);
                System.out.format("%8.0f %8.0f %8.0f%n", foxesN_EE, foxesN_EI, foxesN_RK2);
                wait(1);
            }

            // PERFORM TIME-INTEGRATION
            //Euler Explicit time-integration
            final double rabbitsNp1_EE = [...]
            final double foxesNp1_EE = [...]
            //Euler Implicit time-integration
            final double rabbitsNp1_EI = [...]
            final double foxesNp1_EI = [...]
            // Runge-Kutta 2 Midpoint time-integration
            final double rabbitsNp1_RK2 = [...]
            final double foxesNp1_RK2 = [...]

            // Moving to the next time step:
            tN += timeStep;
            nbIter++;
            rabbitsN_EE = rabbitsNp1_EE;
            rabbitsN_EI = rabbitsNp1_EI;
            rabbitsN_RK2 = rabbitsNp1_RK2;
            foxesN_EE = foxesNp1_EE;
            foxesN_EI = foxesNp1_EI;
            foxesN_RK2 = foxesNp1_RK2;
        }
    }
}
