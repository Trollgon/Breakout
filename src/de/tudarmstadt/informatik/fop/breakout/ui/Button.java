package de.tudarmstadt.informatik.fop.breakout.ui;

import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 * @author Matthias Spörkmann
 */
public class Button extends Entity {

    public Button(String buttonName, int xPos, int yPos, int stateID) {
        super(buttonName);

        this.setPosition(new Vector2f(xPos, yPos));
        try {
            this.addComponent(
                    new ImageRenderComponent(
                        new Image("/images/entry.png")
                    )
            );
        } catch (SlickException e) {
            e.printStackTrace();
        }
        ANDEvent mainEvents = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
        switch (stateID){
            case 1:
                Action newGameAction = new ChangeStateInitAction(Breakout.GAMEPLAY_STATE);
                mainEvents.addAction(newGameAction);
                this.addComponent(mainEvents);
                break;
            default:
                newGameAction = new ChangeStateInitAction(Breakout.MAINMENU_STATE);
                mainEvents.addAction(newGameAction);
                this.addComponent(mainEvents);
                break;
        }
    }
}
