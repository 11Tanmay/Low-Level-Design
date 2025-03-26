// For generating objects based on conditional basis.

interface Shape {
    public void draw();
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rectangle");
    } 
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle");
    }
}

class ShapeFactory {
    Shape getShape(String input) {
        switch (input) {
            case "Circle":
                return new Circle();
            case "Rectangle":
                return new Rectangle();
            default:
                return null;
        }
    }
}
