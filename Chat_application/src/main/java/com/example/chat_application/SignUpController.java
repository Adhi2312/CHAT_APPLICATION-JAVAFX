package com.example.chat_application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.*;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {
    @FXML
    private TextField name,first,last,email;
    @FXML
    private PasswordField password;
    @FXML
    private Label already;
    @FXML
    protected  void SignUpButtonClicked() throws Exception
    {
        String first=this.first.getText();
        String last=this.last.getText();
        String name=this.name.getText();
        String email=this.email.getText();
        String password=this.password.getText();
        if(isLoggedIn(name,password))
        {
            System.out.println("user already exists");
            already.setText("User already Exists");
        }
        else{
            String url = "jdbc:mysql://localhost:3306/aadhi";
            String pass = "0000";
            String user = "root";

            String statement = "insert into user values(?,?,?,?,?,?)";
            Connection con = DriverManager.getConnection(url, user, pass);
//        Statement st = con.createStatement();
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, 0);
            ps.setString(2, first);
            ps.setString(3, last);
            ps.setString(4, name);
            ps.setString(5, email);
            ps.setString(6, password);
            int a = ps.executeUpdate();

            con.close();
            HelloApplication.stage_var.setScene(HelloApplication.index_page);
        }







    }
    @FXML
    void onHyper()
    {
        HelloApplication.stage_var.setScene(HelloApplication.login_page);

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
