package UnitTests;

import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 *
 * @author dzilb and us
 *
 */
public class ReflectionRefractionTests {

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0), 50,
                        new Point3D(0, 0, 50)),
                new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100), 25, new Point3D(0, 0, 50)));

        scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
                0.0004, 0.0000006));

        ImageWriter imageWriter = new ImageWriter("twoSpheres", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(10000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.addGeometries(
                new Sphere(new Color(0, 0, 100), new Material(0.25, 0.25, 20, 0.5, 0), 400, new Point3D(-950, 900, 1000)),
                new Sphere(new Color(100, 20, 20), new Material(0.25, 0.25, 20), 200, new Point3D(-950, 900, 1000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 0.5), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));

        scene.addLights(new SpotLight(new Color(1020, 400, 400),  new Point3D(-750, 750, 150),
                new Vector(-1, 1, 4), 1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored", 2500, 2500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void twoSpheresOnMirrors3lights() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(10000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.addGeometries(
                new Sphere(new Color(0, 0, 100), new Material(0.25, 0.25, 20, 0.5, 0), 400, new Point3D(-950, 900, 1000)),
                new Sphere(new Color(100, 20, 20), new Material(0.25, 0.25, 20), 200, new Point3D(-950, 900, 1000)),
                new Sphere(new Color(200, 22, 20), new Material(0.25, 0.25, 20), 180, new Point3D(-200, 500, 150)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 0.5), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));
        scene.addLights(new DirectionalLight(new Color(500, 300, 0), new Vector(1, -1, 1)));
        scene.addLights(new PointLight(new Color(500, 300, 0), new Point3D(-50, 50, -50), 1, 0.00001, 0.000001));
        scene.addLights(new SpotLight(new Color(1020, 400, 400),  new Point3D(-750, 750, 150),
                new Vector(-1, 1, 4), 1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored-ours", 2500, 2500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }


    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
     *  producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
                        30, new Point3D(60, -50, 50))


        );

        scene.addLights(new SpotLight(new Color(700, 400, 400), //
                new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("shadow with transparency", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void trianglesTransparentSphere2() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 50), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 70, 110), new Point3D(-72, -70, 140), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0.1, 0.1, 20), //
                        new Point3D(-80, 120, 100), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
                        30, new Point3D(60, -50, 50)),
                new Sphere(new Color(java.awt.Color.YELLOW), new Material(0.2, 0.2, 20, 0.2, 0), // )
                        30, new Point3D(40, -50, 50)),
                new Sphere(new Color(java.awt.Color.GREEN), new Material(0.2, 0.2, 40, 0.2, 0), // )
                        30, new Point3D(20, -50, 50)));


        scene.addLights(new SpotLight(new Color(700, 400, 400), //
                new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("shadow with transparency-ours", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void PouTheBear() {
        Scene scene = new Scene("PouTheBear");
        scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(10000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.addGeometries(
                //ears
                new Sphere(new Color(200, 255, 0), new Material(0.25, 0.25, 20, 0.1, 0), 150, new Point3D(-500, 900, 1000)),
                new Sphere(new Color(200, 255, 0), new Material(0.25, 0.25, 20, 0.1, 0), 150, new Point3D(-1100, 400, 1100)),


//body
                new Sphere(new Color(200, 240, 0), new Material(0.25, 0.25, 20, 0.2, 0), 400, new Point3D(-950, 900, 1000)),
                new Sphere(new Color(200, 255, 0), new Material(0.25, 0.25, 20, 0.5, 0), 300, new Point3D(-950, 900, 1000)),
                new Sphere(new Color(200, 240, 0), new Material(0.25, 0.25, 20, 0.8, 0), 200, new Point3D(-950, 900, 1000)),
                new Sphere(new Color(200, 255, 0), new Material(0.25, 0.25, 20, 0.5, 0), 100, new Point3D(-950, 900, 1000)),
                new Sphere(new Color(70, 255, 0), new Material(0.25, 0.25, 20, 0.1, 0), 50, new Point3D(-950, 900, 1000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 0.5), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));

        scene.addLights(new SpotLight(new Color(1020, 400, 400),  new Point3D(-750, 750, 150),
                new Vector(-1, 1, 4), 1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("PouTheBear", 2500, 2500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void sphereWithTransparent() {
        Scene scene = new Scene("wow");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(255,153,0));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.BLACK), 0.15));

        scene.addGeometries(
                new Triangle((Color.BLACK), new Material(0.5, 0.5, 20,1, 0.5), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle((Color.BLACK), new Material(0.5, 0.5, 20,1, 0.5), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
                //גוף
                new Sphere(new Color(200,240,0), new Material(0.2, 0.2, 30, 0.6, 0), 2.4083, new Point3D(0, 0, 0)),
                new Sphere(new Color(200,240,0), new Material(0.2, 0.2, 30, 0.6, 0), 1.5905, new Point3D(0, -4, 0)),
                //גוף
                new Sphere(new Color(java.awt.Color.YELLOW), new Material(0.2, 0.2, 30, 0.6, 0), 0.6, new Point3D(1.79, -5.25, 0)),
                new Sphere(new Color(java.awt.Color.YELLOW), new Material(0.2, 0.2, 30, 0.6, 0), 0.6082, new Point3D(-1.82, -5.19, 0)),
                new Sphere(new Color(java.awt.Color.YELLOW), new Material(0.2, 0.2, 30, 0.6, 0), 0.8485, new Point3D(2.23, 2.32, 0)),
                new Sphere(new Color(java.awt.Color.YELLOW), new Material(0.2, 0.2, 30, 0.6, 0), 0.8360, new Point3D(-2.2, 2.31, 0)),
                new Sphere(new Color(java.awt.Color.YELLOW), new Material(0.2, 0.2, 30, 0.6, 0), .5261, new Point3D(3.48, 2.64, 0)),
                new Sphere(new Color(java.awt.Color.YELLOW), new Material(0.2, 0.2, 30, 0.6, 0), .5, new Point3D(-4.01, -1.39, 0)),
                new Sphere(new Color(java.awt.Color.YELLOW), new Material(0.2, 0.2, 30, 0.6, 0), 0.5477, new Point3D(-3.39, 2.94, 0)),
                new Sphere(new Color(java.awt.Color.YELLOW), new Material(0.2, 0.2, 30, 0.6, 0), .8306, new Point3D(2.74, -1.71, 0)),
                new Sphere(new Color(java.awt.Color.YELLOW), new Material(0.2, 0.2, 30, 0.6, 0), .8306, new Point3D(-2.77, -1.69, 0)),
                //עיניים
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), .5, new Point3D(-.77, -4.47, 1.31)),
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), .5, new Point3D(.67, -4.47, 1.31)),
                new Sphere(new Color(java.awt.Color.YELLOW), new Material(0.2, 0.2, 30, 0.6, 0), .5, new Point3D(4.04, -1.55, 0)));
        scene.addLights(new SpotLight(new Color(400, 240, 0),new Point3D(0,0,0),new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

        ImageWriter imageWriter = new ImageWriter("PouWithMirror", 50, 50, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }


}
