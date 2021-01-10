package sample.DAO;

import sample.Model.Playlist;
import sample.Model.User;
import sample.Utility.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements daoInterface<User>{
    @Override
    public int addData(User data) {
        return 0;
    }

    @Override
    public int delData(User data) {
        return 0;
    }

    @Override
    public int updateData(User data) {
        return 0;
    }

    @Override
    public List<User> showData() {
        return null;
    }

    public int Login(String username, String Password){
        int result = 0;
        try {
            String query = "select * from USER where Username = (?) AND Password = (?)";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,Password);
//            result = ps.executeQuery();
            ResultSet res = ps.executeQuery();
            if (res.next()){
                result = 1;
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }
}
