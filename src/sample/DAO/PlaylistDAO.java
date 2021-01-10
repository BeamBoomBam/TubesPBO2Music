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
        int result = 0;
        try {
            String query = "insert into Playlist(Nama) values (?)";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1,data.getNama());
            result = ps.executeUpdate();
            System.out.println(data.getNama());
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
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
                int idplaylist = res.getInt("idPlaylist");
                String nama = res.getString("Nama");
                int idmusic = res.getInt("Music_idMusic");
                int iduser = res.getInt("User_idUser");
                Playlist p = new Playlist(idplaylist,nama, idmusic, iduser);
                pList.add(p);
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return pList;
    }
}
