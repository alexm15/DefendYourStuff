package sdu.group8.interacter;

/**
 * Interactable is used for interacting with entities to perform commands
 * requested by the player.
 * @author Group 8
 */
public interface Interactable {
    /**
     * Creates a new entity for drawing the window sprite containing info 
     * about the interaction. Interact is provided by entities that is
     * interactable.
     * 
     * Precondition: An action method has been called for identifing which 
     * entities are interacting.
     * 
     * Postcondition: An entity is created containing an empty window sprite on
     * the GUI.
     */
    void interact();
   
}
