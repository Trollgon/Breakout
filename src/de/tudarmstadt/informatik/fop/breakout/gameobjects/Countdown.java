package de.tudarmstadt.informatik.fop.breakout.gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import eea.engine.action.Action;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.event.Event;





public class Countdown extends Entity implements GameParameters {
	private long length;
	private long endTime;
	private boolean isRunning;
	private Event timeOver;
	private Action startAction;
	private Action endAction;
	private boolean initialStart;
	private Event startEvent;
	public Countdown(long timeInms, Action startAction, Action endAction) {
		// TODO Auto-generated constructor stub
		super(COUNTDOWN_ID);
		length = timeInms;
		setEndTime();
		isRunning = true;
		configureEvents();
		this.startAction = startAction;
		this.endAction = endAction;
		initialStart = true;
	}
	private void setEndTime(){
		endTime = System.currentTimeMillis() + length;
	}
	
	private void configureEvents(){
		timeOver = new Event("timeIsOver") {
			@Override
			protected boolean performAction(GameContainer arg0, StateBasedGame arg1, int arg2) {
				return isRunning() && (System.currentTimeMillis() > getEndTime());
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
				endAction.update(arg0, arg1, arg2, arg3);
				stop();
			}
		});
		timeOver.addAction(new DestroyEntityAction());
		startEvent.addAction(new Action() {
			@Override
			public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3){
				startAction.update(arg0, arg1, arg2, arg3);
				initialStart = false;
				
			}
		});
		
		this.addComponent(timeOver);
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
