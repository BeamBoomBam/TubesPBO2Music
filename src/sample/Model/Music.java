package sample.Model;

public class Music {
    private int idMusic;
    private String Judul;
    private String Penyanyi;
    private String Album;

    public Music(int idMusic, String Judul, String Penyanyi, String Album){
        this.idMusic = idMusic;
        this.Judul = Judul;
        this.Penyanyi = Penyanyi;
        this.Album = Album;
    }

    @Override
    public String toString() {
        return Judul;
    }

    public int getIdMusic() {
        return idMusic;
    }

    public void setIdMusic(int idMusic) {
        this.idMusic = idMusic;
    }

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String judul) {
        Judul = judul;
    }

    public String getPenyanyi() {
        return Penyanyi;
    }

    public void setPenyanyi(String penyanyi) {
        Penyanyi = penyanyi;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }


}
