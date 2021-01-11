package sample.Model;

public class Playlist {


    private int idPlaylist;
    private String Nama;
    private int idUser;

    public Playlist() {
    }

    public Playlist(int idplaylist, String nama, int iduser) {
        this.idPlaylist = idPlaylist;
        this.Nama = Nama;
        this.idUser = idUser;
    }

    public int getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(int idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return Nama;
    }
}
