package com.example.chat_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
//    public static Stage stage_var;
//    public static Scene register_page;
public static Stage stage_var;
        public static Scene Signup_page;
        public static Scene index_page;
        public  static Scene creation_page;
        public static Scene message_page;
        public static Scene join_page;
        public static Scene login_page;

//    public static Scene login_page;
//    public static Scene main_page;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("index.fxml"));
        FXMLLoader fxmlLoader3 = new FXMLLoader(HelloApplication.class.getResource("CreateRoom.fxml"));
        FXMLLoader fxmlLoader4 = new FXMLLoader(HelloApplication.class.getResource("Messages.fxml"));
        FXMLLoader fxmlLoader5 = new FXMLLoader(HelloApplication.class.getResource("Join.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        Scene scene1=new Scene(fxmlLoader1.load(), 700, 700);
        Scene scene2=new Scene(fxmlLoader2.load(),700,700);
        Scene scene3=new Scene(fxmlLoader3.load(),700,700);
        Scene scene4=new Scene(fxmlLoader4.load(),700,700);
        Scene scene5=new Scene(fxmlLoader5.load(),700,700);
        stage_var=stage;
        Signup_page=scene1;
        index_page=scene2;
        creation_page=scene3;
        message_page=scene4;
        join_page=scene5;
        login_page=scene;

        
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}