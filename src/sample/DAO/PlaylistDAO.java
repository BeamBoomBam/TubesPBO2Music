package sample.DAO;

import sample.Model.Music;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Utility.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class PlaylistDAO implements daoInterface<Music>{
    @Override
    public int addData(Music data) {
        int result = 0;
        try {
            String query = "insert into Playlist(Playlist) values (?)";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1,data.getJudul());
            result = ps.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    @Override
    public int delData(Music data) {
        return 0;
    }

    @Override
    public int updateData(Music data) {
        return 0;
    }

    @Override
    public List<Music> showData() {
        return null;
    }
}
