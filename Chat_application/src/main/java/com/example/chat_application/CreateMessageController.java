package com.example.chat_application;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;



public class CreateMessageController {
    static HelloController lcu = new HelloController();
    static CreateRoomController crc = new CreateRoomController();
    @FXML
    private TextField message;
    @FXML


    private TextArea textarea;
//    @FXML
////    private TextArea<String> textarea;
//    public void initialize()
//
//    {
//        System.out.println("hello");
//        try {
//            refreshMessages();
//        } catch (Exception e) {
//            e.printStackTrace();}
//
//    }
     void refreshMessages() throws SQLException {
        List<String> userlist = ViewMessage.getAllMessages();
        System.out.println("user"+":"+userlist);
        String messagesText = userlist.stream().collect(Collectors.joining("\n"));
        System.out.println("messagesText:"+messagesText);
        textarea.setText(messagesText);
        message.clear();
    }

    @FXML
    public void onMessage()throws Exception
    {
        String message=lcu.ui+": "+this.message.getText();
        String url = "jdbc:mysql://localhost:3306/aadhi";
        String pass = "0000";
        String user = "root";
        System.out.println(crc.cur_room);

        int lcu_id=getId(lcu.ui);
        System.out.println(lcu_id);
        String user1=lcu.ui;



        String statement="insert into messages2 values(?,?,?,?)";
        Connection con = DriverManager.getConnection(url, user, pass);
        Statement st = con.createStatement();
        int id=gethighestid()+1;
        PreparedStatement ps = con.prepareStatement(statement);
        ps.setInt(1,id);
        ps.setString(2,message);
        ps.setString(3,crc.cur_room);
        ps.setInt(4,lcu_id);
        List<String> userlist=ViewMessage.getAllMessages();
//        System.out.println(userlist);



        ps.executeUpdate();
        refreshMessages();

        con.close();


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
    public int gethighestid()throws Exception
    {
        ArrayList <Integer> arr=new ArrayList<>();
        String message=this.message.getText();
        String url = "jdbc:mysql://localhost:3306/aadhi";
        String pass = "0000";
        String user = "root";
        String query="select * from messages2";
        Connection con = DriverManager.getConnection(url, user, pass);
        Statement st= con.createStatement();
        ResultSet rs= st.executeQuery(query);
        while (rs.next())
        {
            arr.add(rs.getInt(1));
        }
         Collections.sort(arr, Collections.reverseOrder());
        System.out.println(arr);
        return arr.get(0);



    }
    @FXML
    public void onLoad() throws SQLException {
        refreshMessages();
    }
    @FXML
    public void onClear()throws Exception
    {
        String url = "jdbc:mysql://localhost:3306/aadhi";
        String pass = "0000";
        String user = "root";
        String query="delete from messages2 where room=?";
        System.out.println(crc.cur_room);
        Connection con = DriverManager.getConnection(url, user, pass);
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1,crc.cur_room);
        ps.executeUpdate();
        refreshMessages();


    }
    @FXML
    public void onExit(){
        HelloApplication.stage_var.setScene(HelloApplication.index_page);
    }
}
