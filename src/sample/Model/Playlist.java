package sample.Model;

public class Playlist {


    private int idPlaylist;
    private int idMusic;
    private String Nama;
    private int idUser;

    public Playlist(int idplaylist, String nama, int idmusic, int iduser) {
        this.idPlaylist = idPlaylist;
        this.Nama = Nama;
        this.idMusic = idMusic;
        this.idUser = idUser;
    }

    public int getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(int idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public int getIdMusic() {
        return idMusic;
    }

    public void setIdMusic(int idMusic) {
        this.idMusic = idMusic;
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
        return "Playlist : " + Nama;
    }
}
