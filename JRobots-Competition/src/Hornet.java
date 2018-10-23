import java.awt.Color;
import jrobots.utils.Scan;

import jrobots.simulation.simulationObjects.JRobot2018_OTH;
import jrobots.utils.Angle;
import jrobots.utils.Vector;

public class Hornet extends JRobot2018_OTH {
    private static final long serialVersionUID = 1L;
    int scanDir = 270;
    int scanAng = 45;
    // add bot state here

    @Override
    protected void init() {
        // called once after instantiation
        setTurretColor(Color.DARK_GRAY);
        setBodyColor(Color.YELLOW);
        setNameColor(Color.RED);
        setScanAperture(new Angle(scanAng, Angle.Units.DEGREES));

    }

    @Override
    protected void actions() {
        setAutopilot(new Angle(scanDir+scanAng/2, Angle.Units.DEGREES),1);
        if(getLastScan().isTargetLocated()) {
            if(getLastScan().distanceToTarget < 10 || getEnergy()> 1.5)
                setLaunchRocket(new Angle(scanDir+scanAng/2, Angle.Units.DEGREES));
            scanDir = scanDir - scanAng/2;
        } else {
            scanDir +=5;
        }
        setScanDirection(new Angle(scanDir, Angle.Units.DEGREES));
        // called every frame - do some thinking here

        // - analyze new sensor data (if any)
        // - maybe fire on enemy
        // - maybe send new target data to rocket(s)
        // - maybe update driving direction
    }

    private int changeDir(int deg) {
        int newDir = scanDir+deg;
        if(newDir<0) {
            newDir = 360-newDir;
        }
        else if(newDir>=360){
            newDir = newDir-360;
        }
        return newDir;
    }
}