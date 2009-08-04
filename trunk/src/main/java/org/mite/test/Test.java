/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mite.test;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.mite.jsurf.IDescriptor;
import org.mite.jsurf.IDetector;
import org.mite.jsurf.ISURFfactory;
import org.mite.jsurf.InterestPoint;
import org.mite.jsurf.SURF;

/**
 *
 * @author mite
 */
public class Test {

    static ArrayList interest_points;
    static float threshold = 600;
    static float balanceValue = (float) 0.9;
    static int octaves = 10;

    public static void main(String argv[]) {


        BufferedImage img = null;


        try {
            File file = new File("M:/IMAGE_DATABASE/Car/1.jpg");
            img = ImageIO.read(file);
            BufferedImage parent = img;
            ISURFfactory mySURF = SURF.createInstance(img, balanceValue, threshold, octaves, img);
            IDetector detector = mySURF.createDetector();
            interest_points = detector.generateInterestPoints();
            IDescriptor descriptor = mySURF.createDescriptor(interest_points);
            descriptor.generateAllDescriptors();

        } catch (Exception ex) {


            ex.printStackTrace();
        }
        drawInterestPoints();


        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(1);
        JLabel label = new JLabel(new ImageIcon(img));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);


    }

    static void drawInterestPoints() {

        System.out.println("Drawing Interest Points...");

        for (int i = 0; i < interest_points.size(); i++) {

            InterestPoint IP = (InterestPoint) interest_points.get(i);
            IP.drawPosition();

        }

    }

    void drawDescriptors() {

        System.out.println("Drawing Descriptors...");

        for (int i = 0; i < interest_points.size(); i++) {

            InterestPoint IP = (InterestPoint) interest_points.get(i);
            IP.drawDescriptor();

        }

    }
}
