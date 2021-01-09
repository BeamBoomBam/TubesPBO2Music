package sample.Model;

public class Playlist {
    private int idUser;
    private int idMusic;
    private String playlist;

    public Playlist(int idMusic,int idUser, String playlist){
        this.idUser = idUser;
        this.idMusic = idMusic;
        this.playlist = playlist;
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

}
