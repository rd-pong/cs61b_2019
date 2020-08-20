/**
 * The goal of this class is to simulate a universe specified in one of the data
 * files.
 */

public class NBody {
    /*
     * all methods are static, it is meaning less to have instance variable. Cannot
     * make a static reference to the non-static field R.
     */

    // public int N;
    // public double R;

    public static double readRadius(String fileName) {
        final In in = new In(fileName);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String fileName) {
        final In in = new In(fileName);
        int num = in.readInt();
        double radius = in.readDouble();
        Body[] bodies = new Body[num];

        for (int i = 0; i < num; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();

            bodies[i] = new Body(xP, yP, xV, yV, m, img);

        }

        /** for each is not working probably because bodies is not iterable */
        // for (Body bodies_element : bodies) {
        // double xP = in.readDouble();
        // double yP = in.readDouble();
        // double xV = in.readDouble();
        // double yV = in.readDouble();
        // double m = in.readDouble();
        // String img = in.readString();

        // bodies_element = new Body(xP, yP, xV, yV, m, img);
        // }

        // Body[] bodies_test = bodies;

        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double in_radius = readRadius(filename);
        Body[] in_bodies = readBodies(filename);

    }

}