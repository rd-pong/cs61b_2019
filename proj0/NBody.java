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
    public static String imageToDraw = "images/starfield.jpg";

    public static double readRadius(String fileName) {
        final In in = new In(fileName);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String fileName) {
        final In in = new In(fileName);
        int num_of_body = in.readInt();
        double radius = in.readDouble();
        Body[] bodies = new Body[num_of_body];

        for (int i = 0; i < num_of_body; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = "images/" + in.readString();

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

    // Use "javac -encoding utf-8 NBody.java" to compile the method
    public static void main(String[] args) {
        // Collecting All Needed Input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double in_radius = readRadius(filename);
        Body[] in_bodies = readBodies(filename);

        // Drawing the Background
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-in_radius, in_radius);
        StdDraw.clear();
        // StdDraw.picture(0, 0, imageToDraw);

        // // Drawing More than One Body
        // for (Body bodies_elem : in_bodies) {
        // bodies_elem.draw();
        // }
        // StdDraw.show();
        // StdDraw.pause(2);

        // Creating an Animation
        for (double time = 0; time < T; time++) {
            double[] xForces = new double[in_bodies.length];
            double[] yForces = new double[in_bodies.length];

            for (int i = 0; i < in_bodies.length; i++) {
                xForces[i] = in_bodies[i].calcNetForceExertedByX(in_bodies);
                yForces[i] = in_bodies[i].calcNetForceExertedByY(in_bodies);
            }

            // After calculating the net forces for every Body, call update
            for (int i = 0; i < in_bodies.length; i++) {
                in_bodies[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, imageToDraw);
            for (Body in_bodies_elem : in_bodies) {
                in_bodies_elem.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", in_bodies.length);
        StdOut.printf("%.2e\n", in_radius);
        for (int i = 0; i < in_bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", in_bodies[i].xxPos, in_bodies[i].yyPos,
                    in_bodies[i].xxVel, in_bodies[i].yyVel, in_bodies[i].mass, in_bodies[i].imgFileName);
        }

    }

}