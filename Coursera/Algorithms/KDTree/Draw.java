public class Draw {
    public static void main(String[] args) {
        KdTree tree = new KdTree();
//        tree.insert(new Point2D(500, 500));
//        tree.insert(new Point2D(250, 250));
//        tree.insert(new Point2D(750, 750));
//        tree.insert(new Point2D(850, 750));
//        tree.insert(new Point2D(150, 150));

//        tree.insert(new Point2D(700, 200));
//        tree.insert(new Point2D(500, 400));
//        tree.insert(new Point2D(200, 300));
//        tree.insert(new Point2D(400, 700));
//        tree.insert(new Point2D(900, 600));
//        tree.insert(new Point2D(500, 700));

        tree.insert(new Point2D(700, 100));
        tree.insert(new Point2D(200, 300));
        tree.insert(new Point2D(800, 800));
        tree.insert(new Point2D(600, 800));
        tree.draw();
    }
}
