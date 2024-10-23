package com.almasb.fxglgames;

import com.almasb.fxgl.app.GameApplication;
import  com.almasb.fxglgames.spielerkomponent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import static com.almasb.fxgl.dsl.FXGL.*;
import static javafx.scene.input.KeyCode.R;
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

public class rennen extends StackPane {


    //  long Ereigniss;
    public void rennen(Runnable Ereigniss)
    {
        setOnKeyPressed(R -> Ereigniss.run());
    }
}
