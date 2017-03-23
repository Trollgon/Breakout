package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import eea.engine.action.Action;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.basicevents.TimeEvent;

/**
 * 
 * @author Peter Franke
 * 
 * a Countdown timer that executes the startAction on startup and the endAction after the time ran out
 *
 */
public class Countdown extends Entity implements GameParameters {
	private long length;
	private long endTime;
	
	private boolean isRunning;
	
	private Event timeOver;
	private Event cancelCondition;
	private Action startAction;
	private Action endAction;
	private boolean startActionExecuted;
	private Event startEvent;
	
	private static int idnumber = 0;
	
	/**
	 * Countdown Constructor
	 * 
	 * @param timeInms the desired length of the Countdown
	 * @param startAction the Action to execute at the start of the Countdown
	 * @param endAction the Action to execute at the end of the Countdown
	 * @param cancelCondition an Event that will make the running Countdown destroy itself
	 */
	public Countdown(long timeInms, Action startAction, Action endAction, Event cancelCondition) {
		super(COUNTDOWN_ID + idnumber);
		idnumber++;
		
		length = timeInms;
		this.cancelCondition = cancelCondition;
		this.startAction = startAction;
		this.endAction = endAction;
		start();
		configureEvents();
		
		System.out.println("Countdown created");
	}
	private void start() {
		setEndTime();
		isRunning = true;
		startActionExecuted = false;
		System.out.println("Countdown started");
	}
	private void setEndTime(){
		endTime = System.currentTimeMillis() + length;
	}
	
	private void configureEvents(){
		
		timeOver = new Event("timeIsOver") {
			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return isRunning() && startActionExecuted && (System.currentTimeMillis() > getEndTime());
			}
		};
		
		startEvent = new Event("start") {
			
			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return !startActionExecuted;
			}
		};
		
		timeOver.addAction(new Action() {
			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				if(endAction != null) endAction.update(arg0, arg1, arg2, arg3); //execute the end Action
				stop();	//stop the countdown
				System.out.println("countdown over");
				System.out.println(System.currentTimeMillis() - (endTime - length)); 
			}
		});
		
		timeOver.addAction(new DestroyEntityAction()); //destroy the countdown after the time is over
		
		startEvent.addAction(new Action() {
			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3){
				if(startAction!= null) startAction.update(arg0, arg1, arg2, arg3);	//execute the start action
				startActionExecuted = true;			
				System.out.println("start action executed");
				
			}
		});
	
		if(cancelCondition != null){
			cancelCondition.addAction(new DestroyEntityAction());
			this.addComponent(cancelCondition);
		}
		this.addComponent(timeOver);
		this.addComponent(startEvent);
	}
	/**
	 * Used to get the ending time of the countdown
	 * @return the ending time in milliseconds
	 */
	public long getEndTime(){
		return endTime;
	}
	/**
	 * Used to find out if the countdown is running
	 * @return true if the countdown is running, else false
	 */
	public boolean isRunning(){
		return isRunning;
	}
	private void stop(){
		this.isRunning = false;
	}
}
