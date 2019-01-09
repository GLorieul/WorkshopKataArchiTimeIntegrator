import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Case1_Robot
{
    private static double velocity(double time)
    { return 1.0 + sin(time); }
    //{ return 1.0; }
    //{ return time; }

    private static double positionAnalytical(double time)
    { return time - cos(time) + 1.0; }
    //{ return time; }
    //{ return time*time*0.5; }

    public static void runCase()
    {
        // Simulation Parameters
        final double finalTime = 10.0; // [seconds]
        final double timeStep = 1.0; // [seconds]
        final double initialPosition = 0.0; // [centimeters]

        // Integration variables
        double posN_EE = initialPosition; //Position of robot at time t^n
        double posN_EI = initialPosition;
        double posN_RK2 = initialPosition;

        // Other variables
        // (None)

        // Print table's header
        // (Note: In general in numerical simulation, there is too much data
        // for it to be stored. Hence you can't normally store the data while
        // performing the computations and only then print the stored data)
        System.out.print("TimePoint | Velocity | ");
        System.out.print("Pos Ana   Pos EE   Pos EI   Pos RK2 |  ");
        System.out.println("Err EE   Err EI   Err RK2");
        System.out.println("---------------------------------------------------------------------------------------");

        for(double tN=0.0; tN <= finalTime; tN+=timeStep) { //tN = t^n i.e. current time
            //Display information
            final double posAna = positionAnalytical(tN);
            final double errEE  = posN_EE - posAna;
            final double errEI  = posN_EI - posAna;
            final double errRK2 = posN_RK2 - posAna;
            System.out.format("%8.4f  | %8.4f |", tN, velocity(tN));
            System.out.format("%8.4f %8.4f %8.4f  %8.4f |", posAna, posN_EE, posN_EI, posN_RK2);
            System.out.format("%8.4f %8.4f  %8.4f%n", errEE, errEI, errRK2);

            // PERFORM TIME-INTEGRATION
            final double tNp1 = tN + timeStep;
            //Euler Explicit time-integration
            final double posNp1_EE = posN_EE + timeStep*velocity(tN);
            //Euler Implicit time-integration
            final double posNp1_EI = posN_EI + timeStep*velocity(tNp1);
            // Runge-Kutta 2 Midpoint time-integration
            final double tMid = 0.5*(tN + tNp1);
//          final double posMid = posN_RK2 + (tMid-tN)*velocity(tN); //Useless in that particular case (RHS is not a function of position)
            final double posNp1_RK2 = posN_RK2 + timeStep*velocity(tMid);

            // Moving to the next time step:
            posN_EE = posNp1_EE;
            posN_EI = posNp1_EI;
            posN_RK2 = posNp1_RK2;
        }
        System.out.println("Youhou!");
    }
}
