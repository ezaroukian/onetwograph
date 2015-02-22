package org.arl.onetwograph.dnd;

import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class HasNode {
  protected String text;
  protected Image icon; 
  
  protected Node node;
  protected Label label;
  protected ImageView imageView;
  
  public HasNode(String type, Image icon) {
    this.text = type;
    this.icon = icon;
  }

  public HasNode(String type, String iconName) {
    this.text = type;
    this.icon = new Image("file:"+iconName,true);
  }

  public HasNode(Node n) {
    this.node = n;
  }

  
  protected Node generateNode() {
    imageView = new ImageView(this.icon);
    label = new Label(this.text);
    
    VBox node = new VBox();
    node.setAlignment(Pos.CENTER);
    node.getChildren().add(imageView);
    if (this.text != null) {
      node.getChildren().add(label);
    }
    node.setPrefHeight(100);
    node.setPrefWidth(100);
    
    return node;
  }
  
  public Node getNode() {
    if (node == null) {
      node = generateNode();
    }
    return node;
  }
  
  public void setText(String text) {
    this.text = text;
    label.setText(text);
  }
  
  public void setLocation(double x, double y) {
    Node node = getNode();
    Bounds b = node.getBoundsInLocal();
    double w = b.getWidth();
    double h = b.getHeight();
    node.relocate(x-w/2.0, y-h/2.0);
  }
  
  public Image getIcon() {
    return icon;
  }

}
