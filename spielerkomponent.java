package com.almasb.fxglgames;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.input.UserAction;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import static com.almasb.fxgl.dsl.FXGL.*;
import static javax.swing.text.StyleConstants.getComponent;

import java.io.IOException;
import java.util.Scanner;

//package com.almasb.fxglgames;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */




public class spielerkomponent extends Component {
    double geschwindigkeit= 20; public boolean isSprinting = false;
    public void schubfestlegen() {
        double breite = entity.getScaleX();
 double schub = breite-Math.round(breite);
        System.out.println(schub);
    //    geschwindigkeit = 20+(schub*100);
  //      new rennen(sprinten());
// Detect key release (for example, the R key)
/*        getInput().addAction(new UserAction("Release R") {
            @Override
            protected void onActionEnd() {
                System.out.println("R key released!");
                stoppen();
            }
        }, KeyCode.X);*/// Detect when the R key is released

       // getInput().addAction(new UserAction("Release R") {
            //@Override
         //   protected void sprint() {
                // If R is released and the player is currently sprinting
               // if (isSprinting == true)
                {
        /*            System.out.println("R key released! Stop sprinting!");
                  //  player.getComponent(spielerkomponent.class).sprinten();
                    isSprinting = false; // Set the flag to false
                } //else
                { // If R is released and the player is not sprinting
                    System.out.println("R key released! Not sprinting!");
                  //  player.getComponent(spielerkomponent.class).stoppen();
                    // Hier kannst du eventuell eine andere Aktion hinzufügen, wenn der Spieler nicht sprintet.
                }
          //  }
        }, KeyCode.R);
if key.p*/
    }
   // private Entity player;

  //double  geschwindigkeit = 20*(1+(3*mult));
    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(entity.getCenter());
      //  entity.getTransformComponent().translateX();
    }

    public void setGeschwindigkeit(double geschwindigkeit)  {
        this.geschwindigkeit = geschwindigkeit;
    }

    public void rotateLeft() {
        entity.rotateBy(-5);
    }

    public void rotateRight() {
        entity.rotateBy(5);
    }

    public void nachwesten() {
        schubfestlegen();
      entity.translateX(-geschwindigkeit); //setX(-20); //rotateBy(-5);
        System.out.println("Geschwindigkeit: " + geschwindigkeit);
      //  if key

    }

    public void nachosten() {
        schubfestlegen();
        entity.translateX(geschwindigkeit);
        //setX(+20);//rotateBy(5);


    }
    public void hoch() {
        schubfestlegen();
        entity.translateY(-geschwindigkeit);  //setX(-20); //rotateBy(-5);

    }

    public void runter() {
        schubfestlegen();
        entity.translateY(geschwindigkeit);  //setX(+20);//rotateBy(5);

    }
    public void sprinten(){
        if ( geschwindigkeit==20)
        setGeschwindigkeit(80);//  entity.setScaleX(1.4);//*=1.5;
        else
            geschwindigkeit=20;
    }
    public void stoppen(){
        geschwindigkeit=20;//public void

    }
    public void move() {
        Vec2 dir = Vec2.fromAngle(entity.getRotation() - 90)
                .mulLocal(4);
        entity.translate(dir);
    }

    public void shoot() {
        Point2D center = entity.getCenter().subtract(37/2.0, 13/2.0);
//System.out.println(entity.get);
        Vec2 dir = Vec2.fromAngle(entity.getRotation() - 90);

        spawn("bullet", new SpawnData(center.getX(), center.getY()).put("dir", dir.toPoint2D()));
    }


}