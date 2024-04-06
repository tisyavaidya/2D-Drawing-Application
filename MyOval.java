/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.drawingapplication5;

/**
 *
 * @author tisyavaidya
 */


import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;


public class MyOval extends MyBoundedShapes{
    
    public MyOval(Point pntA, Point pntB, Paint paint, Stroke strk, boolean filled)
    {
        super(pntA, pntB, paint, strk, filled);
    }
    
    @Override   
    public void draw(Graphics2D g2d)
    {
        g2d.setPaint(getPaint());
        g2d.setStroke(getStroke());
        if(isFilled())
        {
            g2d.fill(new Ellipse2D.Double(getTopLeftX(), getTopLeftY(), getWidth(), getHeight()));
        }
        else
        {
            g2d.draw(new Ellipse2D.Double(getTopLeftX(), getTopLeftY(), getWidth(), getHeight()));
        }
        
    }
    
}
