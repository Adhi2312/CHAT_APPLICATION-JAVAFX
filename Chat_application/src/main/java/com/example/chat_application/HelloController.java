package com.example.chat_application;
import java.sql.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class HelloController {
    public static String ui;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label invalid;

    @FXML
    public void onHelloButtonClick() throws Exception {
        String username1=this.username.getText();
        String password1=this.password.getText();
//        System.out.println(username);

        ui=username1;
//        System.out.println(ui);

        if(isLoggedIn(username1,password1))
        {
            System.out.println(0);
            HelloApplication.stage_var.setScene(HelloApplication.index_page);
            username.clear();
            password.clear();

        }
        else {

            System.out.println("invalid credentials");
            invalid.setText("Invalid Credentials");
            username.clear();
            password.clear();

        }

    }
    @FXML
    protected void SignUp()throws Exception
    {
        HelloApplication.stage_var.setScene(HelloApplication.Signup_page);

    }
    protected  boolean isLoggedIn(String username,String password)throws  Exception
    {
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/aadhi","root","0000");
        Statement st=con.createStatement();
        String query="SELECT * FROM USER WHERE NAME=?";
        PreparedStatement ps= con.prepareStatement(query);
        ps.setString(1,username);
        ResultSet rs=ps.executeQuery();
        PreparedStatement ps2=con.prepareStatement("Select *from user where Password=?");
        ps2.setString(1,password);
        ResultSet rs2=ps2.executeQuery();


        return rs.next()&&rs2.next();
    }
}