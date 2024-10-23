package com.almasb.fxglgames;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.UserAction;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.Map;
//import javafx.scene.layout.StackPane;
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

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class AsteroidsApp extends GameApplication {

    private Entity player;
private Entity spielerattribute;
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(750*2);
        settings.setHeight(750);
        settings.setTitle("waterman ice and fire");
        settings.setVersion("0.1");
        settings.setMainMenuEnabled(false);
        settings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                return new AsteroidsMainMenu();
            }
        });
    }

    @Override
    protected void initInput() {
        onKey(KeyCode.A, () -> player.getComponent(spielerkomponent.class).nachwesten());
        onKey(KeyCode.D, () -> player.getComponent(spielerkomponent.class).nachosten());
        onKey(KeyCode.W, () -> player.getComponent(spielerkomponent.class).hoch());
        onKey(KeyCode.S, () -> player.getComponent(spielerkomponent.class).runter());
    //    onKey(KeyCode.R, () -> player.getComponent(spielerkomponent.class).sprinten());
        //
      //  getInput().
        // Detect when the R key is pressed
     /*   getInput().addAction(new UserAction("Press R") {
            @Override
            protected void onActionBegin() {
                // If R is pressed and the player is not sprinting
                if (!isSprinting) {
                    System.out.println("R key pressed! Start sprinting!");
                    player.getComponent(spielerkomponent.class).sprinten();
                    isSprinting = true; // Set the flag to true
                } else { // If R is pressed and the player is already sprinting
                    System.out.println("R key pressed again! Already sprinting!");
                    // Hier kannst du eventuell eine andere Aktion hinzufügen, wenn der Spieler bereits sprintet.
                }
            }
        }, KeyCode.R);*/


   //     onKeyUp(KeyCode.V, () -> player.getComponent(spielerkomponent.class).stoppen());
       // onKeyBuilder(KeyCode.R,)
        onKeyDown(KeyCode.F, () -> player.getComponent(spielerkomponent.class).shoot());
    //    onKey(KeyCode.Q, () -> player.getComponent(spielerkomponent.class).stoppen());
       // isReleaseMode(KeyCode.R)
      //  onKeyUp(KeyCode.R, () -> player.getComponent(spielerkomponent.class).stoppen());
/*
        // Detect key release (for example, the space bar)
        getInput().addAction(new UserAction("Release Space") {
            @Override
            protected void onActionEnd() {
                System.out.println("Spacebar released!");
            }
        }, KeyCode.SPACE);*/

    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
        vars.put("lives", 3);
    }

    @Override
    protected void initGame() {
        getSettings().setGlobalSoundVolume(0.1);

        getGameWorld().addEntityFactory(new GameEntityFactory());

        spawn("background");

        player = spawn("player", getAppWidth() / 2, getAppHeight() / 2);

        run(() -> {

            Entity e = getGameWorld().create("asteroid", new SpawnData(100, 100));

            spawnWithScale(e, Duration.seconds(0.75), Interpolators.BOUNCE.EASE_OUT());

        }, Duration.seconds(2));
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(EntityType.BULLET, EntityType.ASTEROID, (bullet, asteroid) -> {

            var hp = asteroid.getComponent(HealthIntComponent.class);

            if (hp.getValue() > 1) {
                bullet.removeFromWorld();
                hp.damage(1);
                return;
            }

            spawn("scoreText", new SpawnData(asteroid.getX(), asteroid.getY()).put("text", "+100"));

            killAsteroid(asteroid);
            bullet.removeFromWorld();

            inc("score", +100);
        });

        onCollisionBegin(EntityType.PLAYER, EntityType.ASTEROID, (player, asteroid) -> {
            killAsteroid(asteroid);

            player.setPosition(getAppWidth() / 2, getAppHeight() / 2);

            inc("lives", -1);
        });
    }

    private void killAsteroid(Entity asteroid) {
        Point2D explosionSpawnPoint = asteroid.getCenter().subtract(64, 64);

        spawn("explosion", explosionSpawnPoint);

        asteroid.removeFromWorld();
    }

    @Override
    protected void initUI() {
        var text = getUIFactoryService().newText("", 24);
        text.textProperty().bind(getip("score").asString("Score: [%d]"));

        getWorldProperties().addListener("score", (prev, now) -> {
            animationBuilder()
                    .duration(Duration.seconds(0.5))
                    .interpolator(Interpolators.BOUNCE.EASE_OUT())
                    .repeat(2)
                    .autoReverse(true)
                    .scale(text)
                    .from(new Point2D(1, 1))
                    .to(new Point2D(1.2, 1.2))
                    .buildAndPlay();
        });

        addUINode(text, 20, 50);
        addVarText("lives", 20, 70);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
