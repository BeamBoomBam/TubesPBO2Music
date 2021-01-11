package sample.Model;

public class PlayMusic {
    private int idPlayMusic;
    private int idPlaylist;
    private int idMusic;



    public int getIdPlayMusic() { return idPlayMusic; }

    public void setIdPlayMusic(int idPlayMusic) {
        this.idPlayMusic = idPlayMusic;
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

    @Override
    public String toString() {
        return "PlayMusic{" +
                "idPlayMusic=" + idPlayMusic +
                ", idPlaylist=" + idPlaylist +
                ", idMusic=" + idMusic +
                '}';
    }
}
