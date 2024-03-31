package com.example.chat_application;

import java.sql.*;
import java.util.ArrayList;

public class ViewMessage {
    static CreateRoomController crc=new CreateRoomController();



    public static ArrayList<String> getAllMessages()
    {
        try {
            ArrayList<String> messages = new ArrayList<>();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aadhi", "root", "0000");
            Statement st = con.createStatement();
            String query = "SELECT * FROM messages2 where room=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, crc.cur_room);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                messages.add(rs.getString(2));
            }
            System.out.println(messages);
            return messages;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;




    }
}
