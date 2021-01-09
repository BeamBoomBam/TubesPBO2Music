package sample.Model;

public class Playlist {
    private int idUser;
    private int idMusic;
    private String playlist;

    public Playlist() {
        this.idUser = idUser;
        this.idMusic = idMusic;
        this.playlist = playlist;
    }

    public Playlist(String iduser, String playlist, int idmusic) {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdMusic() {
        return idMusic;
    }

    public void setIdMusic(int idMusic) {
        this.idMusic = idMusic;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "idUser=" + idUser +
                ", idMusic=" + idMusic +
                ", playlist='" + playlist + '\'' +
                '}';
    }
}
