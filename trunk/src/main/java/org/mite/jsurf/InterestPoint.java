package org.mite.jsurf;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

/**
 * Interest_Point has a 64 components Descriptor. 
 * @author  Alessandro Martini, Claudio Fantacci
 */
public class InterestPoint {

    private Random r = new Random();
 
    private float x;
    private float y;
    private float scale;
    private int orientation_x;
    private int orientation_y;
    private double orientation_radius;
    private float[] descriptorOfTheInterestPoint;
    private Image parent;

    public InterestPoint(float x, float y, float _scale, int orientation_x, int orientation_y, float[] descriptorOfTheInterestPoint, Image parent) {
        this.x = x;
        this.y = y;
        this.scale = _scale;
        this.orientation_x = orientation_x;
        this.orientation_y = orientation_y;
        this.orientation_radius = Math.atan2(orientation_y - y, orientation_x - x);
        this.descriptorOfTheInterestPoint = descriptorOfTheInterestPoint;
        this.parent = parent;
    }

    public InterestPoint(float x, float y, float _scale, int orientation_x, int orientation_y, Image parent) {
        this.x = x;
        this.y = y;
        this.scale = _scale;
        this.orientation_x = orientation_x;
        this.orientation_y = orientation_y;
        this.orientation_radius = Math.atan2(orientation_y - y, orientation_x - x);
        this.parent = parent;
    }

    /* Costruttore utilizzato */
    public InterestPoint(float x, float y, float _scale, Image parent) {
        this.x = x;
        this.y = y;
        this.scale = _scale;
        this.orientation_x = 0;
        this.orientation_y = 0;
        this.orientation_radius = 0;
        this.parent = parent;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getScale() {
        return scale;
    }

    public int getOrientation_x() {
        return orientation_x;
    }

    public int getOrientation_y() {
        return orientation_y;
    }

    public double getOrientation_radius() {
        return orientation_radius;
    }

    public void setOrientation(int orientation_x, int orientation_y) {
        this.orientation_x = orientation_x;
        this.orientation_y = orientation_y;
        this.orientation_radius = Math.atan2(orientation_y - y, orientation_x - x);
    }

    public float[] getDescriptorOfTheInterestPoint() {
        return descriptorOfTheInterestPoint;
    }

    public void setDescriptorOfTheInterestPoint(float[] descriptor) {
        descriptorOfTheInterestPoint = new float[descriptor.length];
        for (int i = 0; i < descriptor.length; i++) {

            this.descriptorOfTheInterestPoint[i] = descriptor[i];

        }
    }

    public String getInterestPointInformationAsAString() {
        String information = new String();
        information += x + " " + y + " ";
        for (int i = 0; i < descriptorOfTheInterestPoint.length; i++) {

            information += (descriptorOfTheInterestPoint[i] + " ");

        }
        return information;
    }

    public void drawPosition() {

        //               parent.getGraphics().drawOval((int)x, (int)y, 5, 5);
                Graphics2D g2d = (Graphics2D) parent.getGraphics();
        g2d.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        g2d.fillOval((int) x, (int) y, 6, 6);

//		parent.stroke(255,105,180);
//		parent.fill(255,192,203);
//		parent.ellipseMode(PApplet.CENTER);
//		parent.ellipse(x, y, 4, 4);

//            System.out.println("TOTO DRAW POsition");
    }

    public void drawDescriptor() {


//		parent.stroke(0, 0, 255);
//		parent.smooth();
//		parent.rectMode(PImage.CENTER);
//		parent.noFill();
//		parent.rect(x, y, scale * 20, scale * 20);
        System.out.println("TOTO DRAW Descriptor");
    }
}
