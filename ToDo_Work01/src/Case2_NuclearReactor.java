import java.util.concurrent.TimeUnit;

public class Case2_NuclearReactor {

    private enum ControlRodPos {
        UP, DOWN;
    }

    private static double reactionGrowthFactor(ControlRodPos rodPos) {
        return (rodPos == ControlRodPos.DOWN) ? +0.1 : -0.1;
    }

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
        final double timeStep = 1.0; // [hours]
        final double targetRate = 1e9; // [reaction/hour]
        final double initialRate = 100.0; // [reaction/hour]

        // Integration variables
        double rateN_EE  = initialRate; //Position of robot at time t^n
        double rateN_EI  = initialRate;
        double rateN_RK2 = initialRate;

        // Other variables
        ControlRodPos rodPos_EE = ControlRodPos.UP; // Upon reactor startup, the control rods are in lifted position
        ControlRodPos rodPos_EI = ControlRodPos.UP;
        ControlRodPos rodPos_RK2 = ControlRodPos.UP;

        // Print table's header
        System.out.println("Time         Reaction Rate EE/EI/RK2        % target rate EE/EI/RK2");
        System.out.println("-------------------------------------------------------------------");

        double tN = 0.0; //tN = t^n i.e. current time
        int nbIter = 0;
        while(true) {
            //Display information
            if(nbIter % 100 == 0) {
                System.out.format("%8.4f ", tN);
                System.out.format("%8.4e %8.4e %8.4e", rateN_EE, rateN_EI, rateN_RK2);
                System.out.format("%8.4f %8.4f %8.4f%n", rateN_EE / targetRate, rateN_EI / targetRate, rateN_RK2 / targetRate);
                wait(1);
            }
            // Nuclear Reactor's control system (implemented as a "Bang-Bang" controler)
            rodPos_EE = (rateN_EE < targetRate) ? ControlRodPos.DOWN /*Lower rods*/ : ControlRodPos.UP /*Raise rods*/;
            rodPos_EI = (rateN_EI < targetRate) ? ControlRodPos.DOWN /*Lower rods*/ : ControlRodPos.UP /*Raise rods*/;
            rodPos_RK2 = (rateN_RK2 < targetRate) ? ControlRodPos.DOWN /*Lower rods*/ : ControlRodPos.UP /*Raise rods*/;

            // PERFORM TIME-INTEGRATION
            final double tNp1 = tN + timeStep;
            //Euler Explicit time-integration
            final double rateNp1_EE = [...]
            //Euler Implicit time-integration
            final double rateNp1_EI = [...]
            // Runge-Kutta 2 Midpoint time-integration
            final double rateNp1_RK2 = [...]

            // Moving to the next time step:
            tN += timeStep;
            nbIter++;
            rateN_EE = rateNp1_EE;
            rateN_EI = rateNp1_EI;
            rateN_RK2 = rateNp1_RK2;
        }
    }
}
