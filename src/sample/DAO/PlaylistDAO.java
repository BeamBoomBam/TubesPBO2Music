package sample.DAO;

import sample.Model.Music;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Playlist;
import sample.Utility.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class PlaylistDAO implements daoInterface<Playlist>{

    @Override
    public int addData(Playlist data) {
        return 0;
    }

    @Override
    public int delData(Playlist data) {
        return 0;
    }

    @Override
    public int updateData(Playlist data) {
        return 0;
    }

    @Override
    public List<Playlist> showData() {
        ObservableList<Playlist> pList = FXCollections.observableArrayList();
        try {
            String query = "select * from Playlist;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                String iduser = res.getString("idUser");
                String playlist = res.getString("Playlist");
                int idmusic = res.getInt("idMusic");
                Playlist p = new Playlist(iduser,playlist, idmusic);
                pList.add(p);
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return pList;
    }
}
