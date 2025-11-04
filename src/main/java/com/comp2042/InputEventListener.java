package com.comp2042;

public interface InputEventListener {

    DownData onDownEvent(MoveEvent event); //moves down

    ViewData onLeftEvent(MoveEvent event); //moves left

    ViewData onRightEvent(MoveEvent event); //moves right

    ViewData onRotateEvent(MoveEvent event); //rotates

    void createNewGame(); //creates new game
}
