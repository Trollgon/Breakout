package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.gameevents.NoBallLaunchedEvent;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.NOTEvent;

/**
 * This StopWatch class, as an inheritor of Entity, offers some methods to stop time in the game
 * and will be represented by a string on the Gameplay screen.
 * 
 * @author Lukas Lehmann
 */
public class StopWatch extends Entity implements GameParameters {

	private long startTime;
	private long timePassedBeforePause;
	private boolean running;
	ANDEvent pauseWatchEvent;
	ANDEvent startWatchEvent;
	

	
	/**
	 * The constructor calls the super constructor, sets running to false and timePassedBeforePause to 0.
	 */
	public StopWatch() {
		super(STOP_WATCH_ID);
		this.running = false;
		this.timePassedBeforePause = 0;
		
		pauseWatchEvent = new ANDEvent( new NoBallLaunchedEvent("noBallLaunched"), new Event("watchRunningEvent") {
			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return isRunning();
			}
		});
		
		pauseWatchEvent.addAction(new Action() {
			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				pauseStopWatch();
			}
		});
		
		startWatchEvent = new ANDEvent( new NOTEvent( new NoBallLaunchedEvent("noBallLaunched")), new Event("watchNotRunningEvent") {
			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return !isRunning();
			}
		});
		
		startWatchEvent.addAction(new Action() {
			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				runStopWatch();
			}
		});
		
		this.addComponent(pauseWatchEvent);
		this.addComponent(startWatchEvent);
	}
	
	/**
	 * Running the StopWatch will set the startTime to the actual time of the system and running to true.
	 */
	public void runStopWatch() {
		this.startTime = System.currentTimeMillis();
		this.running = true;
	}
	
	/**
	 * Pausing the StopWatch will increase timePassedBeforePause by the time passed since the las pause and set running to false.
	 */
	public void pauseStopWatch() {
		this.timePassedBeforePause += (System.currentTimeMillis() - this.startTime);
		this.running = false;
	}
	
	/**
	 * gets the actual match time
	 * 
	 * @return the match time in seconds
	 */
	public int getTime() {
		long startTimeSeconds = this.startTime / 1000;
		long timePassedBeforePauseSeconds = this.timePassedBeforePause / 1000;
		long currentTimeSeconds = System.currentTimeMillis() / 1000;
		if (this.isRunning())
			return (int) (timePassedBeforePauseSeconds + currentTimeSeconds - startTimeSeconds);
		else
			return (int) timePassedBeforePauseSeconds;
	}
	
	/**
	 * The String representation of the actual game time.
	 * E.g.: 45 sec -> 0045
	 * 
	 * @return the game time as a String
	 */
	public String toString() {
		StringBuilder display = new StringBuilder();
		if (this.getTime() < 1000)
			display.append(0);
		if (this.getTime() < 100)
			display.append(0);
		if (this.getTime() < 10)
			display.append(0);
		display.append(this.getTime());
		return display.toString();
	}
	
	/**
	 * Returns the actual running status of the stopWatch
	 * 
	 * @return true if running, false if not
	 */
	public boolean isRunning() {
		return this.running;
	}

}
