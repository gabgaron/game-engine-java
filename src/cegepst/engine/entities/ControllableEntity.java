package cegepst.engine.entities;

import cegepst.MovingRectangle.InputHandler;

public abstract class ControllableEntity extends MovableEntity{


    public abstract void update(InputHandler inputHandler);
}
