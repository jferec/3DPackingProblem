package app;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import shelf.ShelfBestAreaFit;
import util.Algorithm;
import util.Bin;
import util.Cuboid;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class App extends Application {

    final Group root = new Group();
    final XformWorld world = new XformWorld();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final XformCamera cameraXform = new XformCamera();
    private static final double CAMERA_INITIAL_DISTANCE = -1000;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    double mousePosX, mousePosY, mouseOldX, mouseOldY, mouseDeltaX, mouseDeltaY;
    double mouseFactorX, mouseFactorY;

    @Override
    public void start(Stage primaryStage) throws IOException {
        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);
        buildCamera();
        buildBodySystem();
        Scene scene = new Scene(root, 800, 600, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.BROWN);
        handleMouse(scene);
        primaryStage.setTitle("3D Packing Problem");
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setCamera(camera);
        mouseFactorX = 180.0 / scene.getWidth();
        mouseFactorY = 180.0 / scene.getHeight();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void buildCamera() {
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(camera);
        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
    }

    private void buildBodySystem() throws IOException {

        Bin bin = new Bin(100, 100);
        IntStream.range(0,100).forEach((n)->{
            bin.add(new Cuboid((int)(Math.ceil(Math.random()* 19 + 1)),(int)(Math.ceil(Math.random()* 50 + 1)),(int)(Math.ceil(Math.random()* 50 + 1)), bin));
        });

        Algorithm algorithm2 = new ShelfBestAreaFit();
        algorithm2.solve(bin);
        System.out.println(bin.getH());
        System.out.println((double)Math.round(bin.getFill() * 100.0)/100 + " %");


        PrintWriter printWriter = new PrintWriter("result.txt");
        for (Cuboid cuboid : bin.getCuboids())
           // printWriter.println(cuboid.getBinPosition().getX()+ " " + cuboid.getBinPosition().getY() + " "+  cuboid.getBinPosition().getH());
        printWriter.close();
        ArrayList<Box> boxes = new ArrayList<>();

        for(Cuboid cuboid : bin.getCuboids())
        {
            Box box1 = new Box(cuboid.getX(), cuboid.getY(), cuboid.getZ());
            box1.setMaterial(new PhongMaterial(new Color(Math.random(), Math.random(), Math.random(), 1)));
            box1.setTranslateX(cuboid.getBinPosition().getX()  + (double)cuboid.getX()/2);
            box1.setTranslateY(cuboid.getBinPosition().getY()  + (double)cuboid.getY()/2);
            box1.setTranslateZ(cuboid.getBinPosition().getZ()  + (double)cuboid.getZ()/2);
            boxes.add(box1);
            world.getChildren().addAll(box1);
        }



        PhongMaterial binMaterial = new PhongMaterial();
        binMaterial.setDiffuseColor(Color.web("#ffff0080"));
        //binMaterial.setSpecularColor(Color.INDIANRED);
        Box binBox = new Box(bin.getX(), bin.getY(), bin.getH());
        binBox.setMaterial(binMaterial);
        binBox.setTranslateX((double)bin.getX()/2);
        binBox.setTranslateY((double)bin.getY()/2);
        binBox.setTranslateZ((double)bin.getH()/2);
        world.getChildren().add(binBox);
    }

    private void handleMouse(Scene scene) {
        scene.setOnMousePressed((MouseEvent me) -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();
        });
        scene.setOnMouseDragged((MouseEvent me) -> {
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseDeltaX = (mousePosX - mouseOldX);
            mouseDeltaY = (mousePosY - mouseOldY);
            if (me.isPrimaryButtonDown()) {
                cameraXform.ry(mouseDeltaX * 180.0 / scene.getWidth());
                cameraXform.rx(-mouseDeltaY * 180.0 / scene.getHeight());
            } else if (me.isSecondaryButtonDown()) {
                camera.setTranslateZ(camera.getTranslateZ() + mouseDeltaY);
            }
        });
        scene.setOnKeyPressed( event -> {
            double change = 10.0;
            if (event.isShiftDown()) {
                change = 50.0;
            }
            KeyCode keycode = event.getCode();
            if (keycode == KeyCode.W) {
                camera.setTranslateY(camera.getTranslateY() + change);
            }
            if (keycode == KeyCode.S) {
                camera.setTranslateY(camera.getTranslateY() - change);
            }
            if (keycode == KeyCode.A) {
                camera.setTranslateX(camera.getTranslateX() - change);
            }
            if (keycode == KeyCode.D) {
                camera.setTranslateX(camera.getTranslateX() + change);
            }
        });

    }

}

class XformWorld extends Group {
    final Translate t = new Translate(0.0, 0.0, 0.0);
    final Rotate rx = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
    final Rotate ry = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
    final Rotate rz = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);

    public XformWorld() {
        super();
        this.getTransforms().addAll(t, rx, ry, rz);
    }
}

class XformCamera extends Group {
    Point3D px = new Point3D(1.0, 0.0, 0.0);
    Point3D py = new Point3D(0.0, 1.0, 0.0);
    Rotate r;
    Transform t = new Rotate();

    public XformCamera() {
        super();
    }

    public void rx(double angle) {
        r = new Rotate(angle, px);
        this.t = t.createConcatenation(r);
        this.getTransforms().clear();
        this.getTransforms().addAll(t);
    }

    public void ry(double angle) {
        r = new Rotate(angle, py);
        this.t = t.createConcatenation(r);
        this.getTransforms().clear();
        this.getTransforms().addAll(t);
    }



}
