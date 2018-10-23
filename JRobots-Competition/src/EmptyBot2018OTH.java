import java.awt.Color;

import jrobots.simulation.simulationObjects.JRobot2018_OTH;
import jrobots.utils.Angle;
import jrobots.utils.Vector;

public class EmptyBot2018OTH extends JRobot2018_OTH {
	private static final long serialVersionUID = 1L;
    boolean a = false;
    int i=0;

	// add bot state here
	
	@Override
	protected void init() {
        setAutopilot(new Angle(rand(), Angle.Units.DEGREES), 1);
		// called once after instantiation
		setTurretColor(Color.RED);
		setBodyColor(Color.BLUE);
		setNameColor(Color.GREEN);
	}
	
	@Override
	protected void actions() {

	    if(i==50) {
            setAutopilot(new Angle(rand(), Angle.Units.DEGREES), 1);
            i=0;
        }
        else i++;

        if(getEnergy() > 2)
            a = true;

        if(a)
            setLaunchRocket(new Angle(0, Angle.Units.DEGREES));

        if(getEnergy()<0.5)
            a = false;
		// called every frame - do some thinking here
		
		// - analyze new sensor data (if any)
		// - maybe fire on enemy
		// - maybe send new target data to rocket(s)
		// - maybe update driving direction
	}

	private int rand() {
	    return (int) Math.floor(Math.random() * 360);
    }
}
