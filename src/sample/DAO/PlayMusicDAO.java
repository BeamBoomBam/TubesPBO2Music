package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Music;
import sample.Utility.JDBCConnection;

import sample.Model.PlayMusic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PlayMusicDAO implements daoInterface<PlayMusic>{
    @Override
    public int addData(PlayMusic data) {
        int result = 0;
        try {
            Connection connection = JDBCConnection.getConnection();
            String query = "insert into PlayMusic(Music_idMusic, Playlist_idPlaylist) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,data.getIdMusic());
            ps.setInt(2,data.getIdPlaylist());
            if(ps.executeUpdate() != 0)
            {
                connection.commit();
                result = 1;
            }
            else
            {
                connection.rollback();
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delData(PlayMusic data) {
        return 0;
    }

    @Override
    public int updateData(PlayMusic data) {
        return 0;
    }

    @Override
    public List<PlayMusic> showData() {
        return null;
    }
}
