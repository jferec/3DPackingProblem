package camera;

import guillotine.SystematicSearch;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import naive.Naive;
import naive.NaiveWithSorting;
import shelf.ShelfBestAreaFit;
import util.Algorithm;
import util.Bin;
import util.Cuboid;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class VisualizationApp extends Application {

    private final Group root = new Group();
    private final XformWorld world = new XformWorld();
    private final PerspectiveCamera camera = new PerspectiveCamera(true);
    private final XformCamera cameraXform = new XformCamera();
    private static final double CAMERA_INITIAL_DISTANCE = -1000;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    private double mousePosX, mousePosY, mouseOldX, mouseOldY, mouseDeltaX, mouseDeltaY;
    private double mouseFactorX, mouseFactorY;
    private static Bin bin;

    @Override
    public void start(Stage primaryStage) throws IOException {
        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);
        buildCamera();
        buildBodySystem(bin);
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

    public static void main(String[] args) throws IOException {
        String option = args[0];
        int count = Integer.parseInt(args[1]);
        int maxEdge = Integer.parseInt(args[2]);
        int xBin = Integer.parseInt(args[3]);
        int yBin = Integer.parseInt(args[4]);
        System.out.println(option + count + " " + maxEdge + " " + xBin + " " + yBin );
        Random random = new Random();
        bin = new Bin(xBin, yBin);
        IntStream.range(0, count).forEach((n)->{
            bin.add(new Cuboid(random.nextInt(maxEdge) + 1,random.nextInt(maxEdge) + 1,random.nextInt(maxEdge) + 1, bin));
        });
        if(count <= 0){
           System.out.println("Invalid number of cuboids");
           return;
        }
        if(maxEdge > 1000){
            System.out.println("Max edge size exceeds 1000");
        }
        Algorithm a = null;
        switch (option){
            case "-nn":{
                a = new Naive();
                break;
            }
            case "-ns":{
                a = new NaiveWithSorting();
                break;
            }
            case "-sh":{
                a = new ShelfBestAreaFit();
                break;
            }
            case "-ss":{
                a = new SystematicSearch();
                break;
            }
            default:
                throw new IllegalStateException("??");
        }
            bin = a.solve(bin)   ;
            a.saveResult(bin, "wyniki.txt");
            launch(args);
    }

    private void buildCamera() {
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(camera);
        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
    }

    static Box binBox;
    static boolean ifBinBox = true;
    private void buildBodySystem(Bin bin) throws IOException {


        for(Cuboid cuboid : bin.getCuboids())
        {
            Box box1 = new Box(cuboid.getX(), cuboid.getY(), cuboid.getZ());
            box1.setMaterial(new PhongMaterial(new Color(Math.random(), Math.random(), Math.random(), 1)));
            box1.setTranslateX(cuboid.getBinPosition().getX()  + (double)cuboid.getX()/2);
            box1.setTranslateY(cuboid.getBinPosition().getY()  + (double)cuboid.getY()/2);
            box1.setTranslateZ(cuboid.getBinPosition().getZ()  + (double)cuboid.getZ()/2);
            world.getChildren().addAll(box1);
        }

        PhongMaterial binMaterial = new PhongMaterial();
        binMaterial.setDiffuseColor(Color.web("#ffff0080"));
        //binMaterial.setSpecularColor(Color.INDIANRED);
        binBox = new Box(bin.getX() , bin.getY() , bin.getH() );
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
            if (keycode == KeyCode.P){
                if(ifBinBox == true) {
                    world.getChildren().remove(binBox);
                    ifBinBox = false;
                }
            }
            if (keycode == KeyCode.O){
                if(ifBinBox == false)
                world.getChildren().add(binBox);
                ifBinBox = true;
            }
        });

    }

}






