package org.arl.onetwograph;

import org.arl.onetwograph.dnd.ClipRegistry;
import org.arl.onetwograph.pallette.ThingFactory;
import org.arl.onetwograph.thing.Thing;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Listeners etc for a canvas.
 * 
 * @author jeffhoye
 *
 */
public class OTCanvas {
  ClipRegistry<Thing> registry;

  double dragStartX, dragStartY; // where in the canvas they clicked
  double dragObjStartX, dragObjStartY; // where on the Node they clicked
  Thing dragging;
  
  Pane pane;
  Pane canvas; // todo: change to canvas
  
  public OTCanvas(Pane pane, ClipRegistry<Thing> registry) {
    this.registry = registry;
    this.pane = pane;
    this.canvas = pane;
    
    
    canvas.setOnDragOver(new EventHandler<DragEvent>(){

      @Override
      public void handle(DragEvent event) {
        // event.getGestureSource()
        if (event.getDragboard().hasContent(ThingFactory.format)) {
          event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
          event.consume();          
        }
      }
    });
    
    canvas.setOnDragEntered(new EventHandler<DragEvent>() {
      public void handle(DragEvent event) {
        System.out.println("OnDragEntered");
        if (event.getDragboard().hasContent(ThingFactory.format)) {
          
          event.consume();
        }
      }
    });
    
    canvas.setOnDragDropped(new EventHandler<DragEvent>() {
      public void handle(DragEvent event) {
        System.out.println("OnDragDropped");
        Thing newObj = addThing(registry.getInstance(event.getDragboard()));
        
        newObj.setLocation(event.getX(), event.getY());
        pane.getChildren().add(newObj.getNode());
      }
    });
        
    canvas.setOnDragDetected(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        System.out.println("OnDragDetected");
      }
    });
    
    canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
//        System.out.println("OnMouseDragged");
        if (dragging != null) {
          dragging.setLocation(event.getX(), event.getY());
        }
      }
    });
    
    canvas.setOnMouseDragReleased(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        System.out.println("OnMouseDragReleased");
      }
    });
  }

  public Thing addThing(Thing thing) {
    thing.setCanvas(this);
    return thing;
  }
  
  public void startDrag(MouseEvent event, Thing thing) {
    dragStartX = event.getX();
    dragStartY = event.getY();
    dragging = thing;
  }

  public void stopDrag(MouseEvent event, Thing thing) {
    dragging = null;
  }

}
