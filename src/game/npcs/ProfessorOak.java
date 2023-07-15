package game.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ProfessorAction;
import game.utils.GameType;
import game.utils.Status;

public class ProfessorOak extends Actor {

    public ProfessorOak() {
        super("ProfessorOak", '$', 100);
        this.addCapability(Status.IMMUNE);
        this.addCapability(GameType.NPC_OAK);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList list = super.allowableActions(otherActor, direction, map);
        list.add(new ProfessorAction(this));
        return list;
    }
}
