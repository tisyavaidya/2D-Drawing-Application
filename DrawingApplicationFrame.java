/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.drawingapplication5;

/**
 *
 * @author tisyavaidya
 */
//import packages


import javax.swing.*;
//import javax.swing.JFrame;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;


public class DrawingApplicationFrame extends JFrame {
    
    
    public JPanel topPanel;
    
    //widgets for the first line of the topPanel
    public JPanel widget1 = new JPanel();
    public JLabel shape = new JLabel("Shape:");
    public JComboBox shapes;
    public Color color1 = Color.BLACK;
    public Color color2 = Color.BLACK;
    public JButton undo = new JButton("Undo");
    public JButton clear = new JButton("Clear");
    public JButton but1 = new JButton("1st Color...");
    public JButton but2 = new JButton("2nd Color...");
    
    //widgets for the second line of the topPanel
    public JPanel widget2 = new JPanel();
    public JLabel options = new JLabel("Options:");
    public JCheckBox filled = new JCheckBox("Filled");
    public JCheckBox gradient = new JCheckBox("Use Gradient");
    public JCheckBox dashed = new JCheckBox("Dashed");
    public JLabel linewidth = new JLabel("Line Width:");
    public JSpinner width = new JSpinner();
    public JLabel dashlength = new JLabel("Dash Length:");;
    public JSpinner length = new JSpinner();

    //drawing guidelines
    public ArrayList<MyShapes> shapesArray = new ArrayList<>();
    public Point startPoint;
    public Point endPoint;
    public Paint paint;
    public Stroke stroke;
    public DrawPanel drawPanel;
    public MyShapes curShape;
    public MyLine Line;
    public MyOval Oval;
    public MyRectangle Rectangle;
    public MouseHandler mh;


    //label for mouse position
    public JLabel mousepos = new JLabel();
    
    
      // Constructor for DrawingApplicationFrame
    @SuppressWarnings("empty-statement")
    public DrawingApplicationFrame()
    {
        super("Java 2D Drawings");
        this.drawPanel = new DrawPanel();
        
        shapes = new JComboBox();
        shapes.addItem("Line");
        shapes.addItem("Oval");
        shapes.addItem("Rectangle");
        
        drawPanel.setSize(500,500);
        drawPanel.setVisible(true);


        
        //first line of widgets
        widget1.setLayout(new FlowLayout());
        widget1.add(shape);
        widget1.add(shapes);
        widget1.add(but1);
        widget1.add(but2);
        widget1.add(undo);
        widget1.add(clear);
               

        //second line of widgets
        widget2.setLayout(new FlowLayout());
        widget2.add(options);
        widget2.add(filled);
        widget2.add(gradient);
        widget2.add(dashed);
        widget2.add(linewidth);
        widget2.add(width);
        widget2.add(dashlength);
        widget2.add(length);
        

        //combine widget lines to form topPanel
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2,1));
        widget1.setBackground(Color.CYAN);
        widget2.setBackground(Color.CYAN);
        topPanel.add(widget1);
        topPanel.add(widget2);
        
        

        //add topPanel to North, drawPanel to Center, and mousepos to South
        setLayout(new BorderLayout());
        add(topPanel,BorderLayout.NORTH);
        add(drawPanel,BorderLayout.CENTER);
        add(mousepos,BorderLayout.SOUTH);
       
        
        //undo button action listener
        undo.addActionListener((ActionEvent e) -> {
            int size = shapesArray.size();
            if (size > 0)
            {
                shapesArray.remove(size - 1);;
                repaint();
            }
        });
        
        //clear button action listener
        clear.addActionListener((ActionEvent e) -> {
            shapesArray.clear();
            repaint();
        });
        
        ActionListener col1Listener = (ActionEvent e) -> {
            Color initcolor1 = JColorChooser.showDialog(null,"Choose 1st color", color1);
            color1 = initcolor1;
            System.out.println("New Color: "+ color1);
        };
        but1.addActionListener(col1Listener);
        
        ActionListener col2Listener = (ActionEvent e) -> {
            Color initcolor2 = JColorChooser.showDialog(null,"Choose 2nd color", color2);
            color2 = initcolor2;
            System.out.println("New Color: "+ color2);
        };
        but2.addActionListener(col2Listener);

    }

    //Create a private inner class for the DrawPanel.
    private class DrawPanel extends JPanel
    {
        //constructor
        public DrawPanel()
        {
            this.setBackground(Color.white);
            addMouseListener(new MouseHandler());
            addMouseMotionListener(new MouseHandler());
        }

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            //loop through and draw each shape in the shapes arraylist
            for (MyShapes drawing:shapesArray){
                 drawing.draw(g2d);}
                 System.out.println("Abc");

        }


        private class MouseHandler extends MouseAdapter implements MouseMotionListener
        {

            @Override
            public void mousePressed(MouseEvent e)
            {   
                startPoint = e.getPoint();
                endPoint = e.getPoint();
                Float widthvalue = Float.parseFloat(width.getModel().getValue().toString());
                Float lengthvalue = Float.parseFloat(length.getModel().getValue().toString());
                float[] dash = {lengthvalue};
                
                stroke = new BasicStroke(widthvalue,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
                
                if(gradient.isSelected())
                {
                    paint = new GradientPaint(0,0,color1,50,50,color2,true);
                }  
                else
                {
                    paint = color1;
                }
                
                String value = shapes.getSelectedItem().toString();  
                    
                if ("Line".equals(value))
                {
                    curShape = new MyLine(startPoint,endPoint,paint,stroke);
                }
                if("Rectangle".equals(value))
                {
                    curShape = new MyRectangle(startPoint,endPoint,paint,stroke, filled.isSelected());    
                }
                if("Oval".equals(value))
                {
                    curShape = new MyOval(startPoint, endPoint, paint, stroke, filled.isSelected());  
                }
              
               
             
                if (dashed.isSelected())
                {
                    stroke = new BasicStroke(widthvalue, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dash , 0);
                    
                } 
                else
                {
                    stroke = new BasicStroke(widthvalue, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND); 
                }    
                    
                
               
            curShape.setStroke(stroke);
            shapesArray.add(curShape);
            

            }
        

            @Override
            public void mouseReleased(MouseEvent event)
            {
                curShape.setEndPoint(event.getPoint());
                repaint();
           
            };

            @Override
            public void mouseDragged(MouseEvent event)
            {
                curShape.setEndPoint(event.getPoint());
                repaint();
            };

            @Override
            public void mouseMoved(MouseEvent event)
            {
                String position = "(" + event.getPoint().x + "," + event.getPoint().y + ")";
                mousepos.setText(position); 
                repaint();
            }    
        }
    }

}
