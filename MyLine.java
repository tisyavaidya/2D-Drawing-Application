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
import java.awt.geom.Line2D;


public class MyLine extends MyShapes{
    
    public MyLine(Point pntA, Point pntB, Paint paint, Stroke strk)
    {
        super(pntA, pntB, paint, strk);
    }
    
    @Override
    public void draw(Graphics2D g2d){
        g2d.setPaint(getPaint());
        g2d.setStroke(getStroke());
        g2d.draw(new Line2D.Double((int)(getStartPoint().getX()), (int)(getStartPoint().getY()), (int)(getEndPoint().getX()), (int)(getEndPoint().getY())));
    }
    
}
