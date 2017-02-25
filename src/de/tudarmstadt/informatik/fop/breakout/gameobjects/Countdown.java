package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import eea.engine.action.Action;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.event.Event;

/**
 * 
 * @author Peter Franke
 * 
 * a Countdown timer that executes the startACtion on startup and the endAction after the time ran out
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
	private boolean initialStart;
	private Event startEvent;
	
	
	public Countdown(long timeInms, Action startAction, Action endAction/*, Event cancelCondition*/) {
		super(COUNTDOWN_ID);
		
		length = timeInms;
		this.cancelCondition = cancelCondition;
		this.startAction = startAction;
		this.endAction = endAction;
		initialStart = false;
		start();
		configureEvents();
		
		System.out.println("Countdown created");
	}
	public void start() {
		setEndTime();
		isRunning = true;
		initialStart = true;
		System.out.println("Countdown started");
	}
	private void setEndTime(){
		endTime = System.currentTimeMillis() + length;
	}
	
	private void configureEvents(){
		timeOver = new Event("timeIsOver") {
			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return isRunning() && !initialStart && (System.currentTimeMillis() > getEndTime())  ;
			}
		};
		startEvent = new Event("start") {
			
			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return initialStart;
			}
		};
		
		timeOver.addAction(new Action() {
			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
				if(endAction != null) endAction.update(arg0, arg1, arg2, arg3);
				stop();
				System.out.println("countdown over");
			}
		});
		
		timeOver.addAction(new DestroyEntityAction());
		
		startEvent.addAction(new Action() {
			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3){
				if(startAction!= null) startAction.update(arg0, arg1, arg2, arg3);
				initialStart = false;
				System.out.println("start action executed");
				
			}
		});
		//startEvent.addAction(startAction);
		//timeOver.addAction(endAction);
		/*if(!cancelCondition.equals(null)){
			cancelCondition.addAction(new DestroyEntityAction());
			this.addComponent(cancelCondition);
		}*/
		this.addComponent(timeOver);
		this.addComponent(startEvent);
	}
	public long getEndTime(){
		return endTime;
	}
	public boolean isRunning(){
		return isRunning;
	}
	private void stop(){
		this.isRunning = false;
	}
}
