package org.arl.onetwograph.thing;

import java.io.Serializable;

import org.arl.onetwograph.OTCanvas;
import org.arl.onetwograph.dnd.HasPane;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Thing extends HasPane {
  OTCanvas canvas;

  public Thing(String type, Image icon) {
    super(type,icon);
  }

  public void setCanvas(OTCanvas canvas) {
    this.canvas = canvas;
    canvas.pane.getChildren().add(getNode());
  }
    
}
