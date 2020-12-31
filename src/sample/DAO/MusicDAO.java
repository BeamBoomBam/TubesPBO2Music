package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Music;
import sample.Utility.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MusicDAO implements daoInterface<Music> {

    @Override
    public int addData(Music data) {
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
        ObservableList<Music> mList = FXCollections.observableArrayList();
        try {
            String query = "select * from Music;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                int id = res.getInt("idMusic");
                String judul = res.getString("Judul");
                String penyanyi = res.getString("Penyanyi");
                String album = res.getString("Album");
                Music m = new Music(id,judul, penyanyi, album);
                mList.add(m);
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return mList;
    }
}
