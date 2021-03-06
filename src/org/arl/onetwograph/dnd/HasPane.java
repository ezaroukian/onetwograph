package org.arl.onetwograph.dnd;

import java.io.InputStream;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class HasPane implements HasNode {
  public static final Background selectedBG = new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5.0), Insets.EMPTY));

  protected String text;
  protected Image icon; 
  
  protected Pane node;
  protected Label label;
  protected ImageView imageView;
  
  public static Image loadImage(String iconName) {
    InputStream is = HasPane.class.getResourceAsStream(iconName);
    if (is == null) {
      System.out.println("Cant load image "+iconName);
      
      double w = 50.0;
      double h = 50.0;
      Canvas c = new Canvas(w,h); // yellow arrow
      GraphicsContext g = c.getGraphicsContext2D();
      g.setStroke(Color.BLACK);
      g.setFill(Color.YELLOW);
      g.setLineWidth(3.0);
      double[] dx = new double[]{ 6, w/2.0, w-6 };
      double[] dy = new double[]{ 11, h-5, 11 };
      g.fillPolygon(dx, dy, 3);
      g.strokePolygon(dx, dy, 3);
      
      SnapshotParameters sp = new SnapshotParameters();
      sp.setFill(Color.TRANSPARENT);
      WritableImage ret = c.snapshot(sp, new WritableImage((int)w, (int)h));
      return ret;
    }
    return new Image(is);
  }
  
  public HasPane(String type, Image icon) {
    this.text = type;
    this.icon = icon;
  }

  public HasPane(String type, String iconName) {
    this.text = type;
    this.icon = loadImage(iconName);
  }

  public HasPane(Pane n) {
    this.node = n;
  }
  
  public double getWidth() {
    return 70.0;
  }

  public double getHeight() {
    return 50.0;
  }

  public double getIconWidth() {
    return 50.0;
  }
  
  public double getIconHeight() {
    return 50.0;
  }
  
  public Pane getBaseNode() {
    VBox ret = new VBox();
    ret.setAlignment(Pos.CENTER);
    return ret;
  }

  /**
   * used when generating the node to override special for palette mode
   * 
   * @return
   */
  public Image getNodeIcon() {
    return this.icon;
  }
  
  protected Pane generateNode() {
    imageView = new ImageView(getNodeIcon());
    imageView.setPreserveRatio(true);
    imageView.setFitWidth(getIconWidth());
    imageView.setFitHeight(getIconHeight());
    
    label = new Label(this.text);
    
    Pane node = getBaseNode();
    node.getChildren().add(imageView);
    if (this.text != null) {
      node.getChildren().add(label);
    }
//    node.setPrefWidth(getWidth());
    node.setPrefHeight(getHeight());
    
    return node;
  }
  
  public Pane getPane() {
    if (node == null) {
      node = generateNode();
    }
    return node;
  }
  
  public void setText(String text) {
    this.text = text;
    label.setText(text);
  }

  public Point2D getLocation() {
    Bounds b = node.getBoundsInParent();
    Point2D ret = new Point2D((b.getMinX()+b.getMaxX())/2.0, (b.getMinY()+b.getMaxY())/2.0);
    return ret;
  }
  
  public void setLocation(double x, double y) {
    Node node = getPane();
    Bounds b = node.getBoundsInLocal();
    double w = b.getWidth();
    double h = b.getHeight();
    node.relocate(x-w/2.0, y-h/2.0);
  }
  
  public Image getIcon() {
    return icon;
  }

  @Override
  public Node getNode() {
    return getPane();
  }

  public String toString() {
    return getClass().getName()+" "+text;
  }
   
  public boolean selected = false;
  public void setSelected(boolean selected) {
    if (selected == this.selected) return;
    this.selected = selected;
    if (selected) {
      node.setBackground(selectedBG);
    } else {
      node.setBackground(null);
    }
  }
}
