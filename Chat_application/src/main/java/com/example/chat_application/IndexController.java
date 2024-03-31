package com.example.chat_application;

import javafx.fxml.FXML;

public class IndexController {
    @FXML
    protected void onCreateClick()throws Exception
    {
        HelloApplication.stage_var.setScene(HelloApplication.creation_page);
    }
    @FXML
    protected void onJoinClick()throws Exception
    {
        HelloApplication.stage_var.setScene(HelloApplication.join_page);
    }
    @FXML
    void onLogout()
    {
        HelloApplication.stage_var.setScene(HelloApplication.login_page);
    }

}
