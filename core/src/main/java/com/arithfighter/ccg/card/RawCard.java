package com.arithfighter.ccg.card;

import com.arithfighter.ccg.pojo.Point;
import com.arithfighter.ccg.pojo.Shape;

public class RawCard {
    private Point initPoint;
    private Point point;
    private Shape shape;

    public RawCard(){
        shape = new Shape();
        initPoint = new Point(0,0);
        point = new Point(0,0);
    }

    public RawCard(float initX, float initY){
        shape = new Shape();
        initPoint = new Point(initX,initY);
        point = new Point(initX,initY);
    }

    public void setInitPoint(Point initPoint) {
        this.initPoint = initPoint;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getInitPoint() {
        return initPoint;
    }

    public Point getPoint() {
        return point;
    }

    public void setShape(Shape shape){
        this.shape = shape;
    }

    public Shape getShape(){
        return shape;
    }
}
