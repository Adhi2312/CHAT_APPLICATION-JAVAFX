package com.example.chat_application;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateRoomController {
    static HelloController lcu = new HelloController();
    public static String username = lcu.ui;
    public static String cur_room;
    @FXML
    private TextField room_name,user2;
    @FXML
    private Label already;
    @FXML
    private Label already2;
    @FXML
    public void onCreation()throws Exception
    {
        String room_name=this.room_name.getText();
        String user2=this.user2.getText();
        System.out.println(lcu.ui);
        int userId_1=getId(lcu.ui);
         int userId_2=getId(user2);
         cur_room=user2+lcu.ui;

        if(isValid(cur_room))
        {
            already2.setText("Room Already Exists");
        }
        else{
            String url = "jdbc:mysql://localhost:3306/aadhi";
            String pass = "0000";
            String user = "root";

            String statement = "insert into room values(?,?,?,?)";
            Connection con = DriverManager.getConnection(url, user, pass);
//        Statement st = con.createStatement();
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setString(1, lcu.ui + user2);
            ps.setInt(2, userId_1);
            ps.setInt(3, userId_2);
            ps.setString(4, room_name);
            ps.executeUpdate();

            con.close();

            HelloApplication.stage_var.setScene(HelloApplication.message_page);
        }




    }
    protected int getId(String user2) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/aadhi";
        String pass = "0000";
        String user = "root";

        String statement="Select id from user where name= \""+ user2 +"\"";
        Connection con = DriverManager.getConnection(url, user, pass);
//        PreparedStatement ps= con.prepareStatement(statement);
//        ps.setString(1,user2);
//        ResultSet rs=ps.executeQuery();
        Statement st= con.createStatement();
        ResultSet rs=st.executeQuery(statement);
        rs.next();
         return rs.getInt(1);


    }
    @FXML
     private void onJoin() throws Exception
     {
         String room_name=this.room_name.getText();

         if(isValid(room_name))
         {
             System.out.println("room exists");
             cur_room=room_name;
//            CreateMessageController cm=new CreateMessageController();
//             cm.refreshMessages();

             HelloApplication.stage_var.setScene(HelloApplication.message_page);

         }
         else already.setText("Room not Found");
         cur_room=room_name;




     }



    boolean isValid(String room_name) throws SQLException {
         Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/aadhi","root","0000");
         Statement st=con.createStatement();
         System.out.println(room_name);
         String query="SELECT * FROM room WHERE RoomCode=?";
         PreparedStatement ps= con.prepareStatement(query);
         ps.setString(1,room_name);
         ResultSet rs=ps.executeQuery();


         return rs.next();

     }
     @FXML
    void onCancel()
     {
         HelloApplication.stage_var.setScene(HelloApplication.index_page);

     }

}
