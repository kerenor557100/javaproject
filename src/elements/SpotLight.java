package elements;


import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

//מקור אור זרקור: מקור אור נקודתי עם כיוון ועם מיקום.
//כמו פנס

public class SpotLight extends PointLight {
    Vector _direction;
    double _concentration;

    public Vector getDirection() {
        return _direction;
    }

    public double getConcentration() {
        return _concentration;
    }



    public SpotLight(Color colorIntensity, Point3D position, Vector direction, double kC, double kL, double kQ, double concentration) {
        super(colorIntensity, position, kC, kL, kQ);
        this._direction = new Vector(direction).normalized();
        this._concentration = concentration;
    }
    //with radius
    public SpotLight(Color colorIntensity, Point3D position, Vector direction, double kC, double kL, double kQ, double concentration,double radius) {
        super(colorIntensity, position, kC, kL, kQ,radius);
        //this._radius=;
        this._direction = new Vector(direction).normalized();
        this._concentration = concentration;
    }
    public SpotLight(Color colorIntensity, Point3D position, Vector direction, double kC, double kL, double kQ) {
        this(colorIntensity, position, direction, kC, kL, kQ, 1);
    }


    /**
     * @return spotlight intensity
     */
    @Override
    public Color getIntensity(Point3D p) {
        double projection = _direction.dotProduct(getL(p));

        if (Util.isZero(projection)) {
            return Color.BLACK;
        }
        double factor = Math.max(0, projection);
        Color pointlightIntensity = super.getIntensity(p);

        if (_concentration != 1) {
            factor = Math.pow(factor, _concentration);
        }

        return (pointlightIntensity.scale(factor));
    }


}
