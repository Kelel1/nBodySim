public class NBody {       
        
    /** Return value corresponding to radius of universe from a file */
    public static double readRadius(String fileName) {        
        In in = new In(fileName);
        int totlPlanets = in.readInt();        ;
        double radiusUniverse = in.readDouble();

        return radiusUniverse; 
   }

   /** Return an array of Planets corresponding to the planets in the file */
   public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int totlPlanets = in.readInt();
        Planet[] bodies = new Planet[totlPlanets];
        double radius = in.readDouble(); 
        
        
       for(int j = 0; j < bodies.length; j++ ) {      
       double xxPos = in.readDouble();
       double yyPos = in.readDouble();
       double xxVel = in.readDouble();
       double yyVel = in.readDouble();
       double mass = in.readDouble();
       String planetName = in.readString();
       
       Planet body = new Planet(xxPos, yyPos, xxVel,
                               yyVel, mass, planetName);  

       bodies[j] = body;
  
    } 
       return bodies;
   }


   public static void main(String[] args) {
       
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String fileName = (args[2]);

    NBody.readRadius(fileName);
    Planet[] planets = NBody.readPlanets(fileName);
   
    StdDraw.setScale(-NBody.readRadius(fileName), NBody.readRadius(fileName));
    
    StdDraw.picture(0, 0, "images/starfield.jpg");
    for(int i = 0; i < planets.length; i++) {
        planets[i].draw();
    }

    StdDraw.enableDoubleBuffering();

    
    for(double time = 0.0; time <= T; time += dt) {
        
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];

        for(int j = 0; j < planets.length; j++) {

            xForces[j] = planets[j].calcNetForceExertedByX(planets);
            yForces[j] = planets[j].calcNetForceExertedByY(planets);
        }

        for(int k = 0; k < planets.length; k++) {

            planets[k].update(dt, xForces[k], yForces[k]);
        }
            
        StdDraw.picture(0, 0, "images/starfield.jpg");

        for(int m = 0; m < planets.length; m++) {
            planets[m].draw();
        }

        StdDraw.show(10);
                    
        }
    }

    
   }
    
