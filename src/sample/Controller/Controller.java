package sample.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.DAO.MusicDAO;
import sample.DAO.PlaylistDAO;
import sample.Main.Main;
import sample.Model.Music;
import sample.Model.Playlist;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField SearchBar;
    public Button Search;
    public Button Account;
    public TableView<Music> TabelLagu;
    public TableColumn<Music, String> KolomJudul;
    public TableColumn<Music, String> KolomAlbum;
    public TableColumn<Music, String> KolomArtist;
    public ImageView CoverLagu;
    public Label JudulLagu;
    public Label ArtisLagu;
    public Button ShuffleButton;
    public Button PrevButton;
    public Button PlayButton;
    public Button NextButton;
    public Button RepeatButton;
    public Label ProgressLagu;
    public Label SisaWaktu;
    public Slider VolumeSlider;
    public MenuItem addButton;
    public ListView<Playlist> Playlists;
    public MenuItem delPlaylist;
    private ObservableList<Music> mList;
    public ObservableList<Playlist> pList;
    Stage new_stage;
    private Playlist playlist;
    private PlaylistDAO playlistDAO;
    private Playlist selectedPlaylist;

    public PlaylistDAO getPlaylistDAO() {
        if (playlistDAO == null) {
            playlistDAO = new PlaylistDAO();
        }
        return playlistDAO;
    }

    public ObservableList<Playlist> getpList(){
        if (pList == null){
            pList= FXCollections.observableArrayList();
            pList.addAll(getPlaylistDAO().showData());
        }
        return pList;
    }

    public void initialize (URL location, ResourceBundle resources){
        Playlists.setItems(this.getpList());
        MusicDAO mDao = new MusicDAO();
        ObservableList<Music> mList = (ObservableList<Music>) mDao.showData();
        TabelLagu.setItems(mList);
        KolomJudul.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getJudul()));
        KolomAlbum.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getAlbum()));
        KolomArtist.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getPenyanyi()));
        Music music = TabelLagu.getSelectionModel().getSelectedItem();
        System.out.println(music);
        Playlists.refresh();

    }

    public void addPlaylist(ActionEvent ex) throws IOException {
        Playlist newplay = new Playlist();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setContentText("Playlist name :");
        Optional<String> result = dialog.showAndWait();
        newplay.setNama(dialog.getDefaultValue());
        PlaylistDAO pDao = new PlaylistDAO();
        if (result.isPresent()){
//            newplay.setIdPlaylist(0);
            newplay.setIdUser(1);
            newplay.setNama(result.get());
            newplay.setIdMusic(0);
//            int id = 0;
//            int idmusic = 0;
//            int iduser = 1;
//            String nama = result.get();
            //pDao.addData(new Playlist(id, nama, idmusic, iduser));
        }
        getPlaylistDAO().addData(newplay);
        getpList().add(newplay);
    }

    public void playSound(){
        String filepath = "../Sounds/12DaysOfChristmas.mp3";

        try {
            File musicPath = new File(filepath);

            if (musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else {
                System.out.println("File tidak ditemukan.");
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void deletePlaylist(ActionEvent actionEvent) {
        selectedPlaylist = Playlists.getSelectionModel().getSelectedItem();
        System.out.println(selectedPlaylist.getIdPlaylist());
        if(playlistDAO.delData(selectedPlaylist)==0){
            selectedPlaylist = Playlists.getSelectionModel().getSelectedItem();
            playlistDAO.delData(selectedPlaylist);
        }

        getpList().clear();
        getpList().addAll(playlistDAO.showData());
        Playlists.setItems(this.getpList());
    }

    public void selectplaylist(MouseEvent mouseEvent) {
        selectedPlaylist = Playlists.getSelectionModel().getSelectedItem();
    }

    public void selectPlaylist(WindowEvent windowEvent) {
        selectedPlaylist = Playlists.getSelectionModel().getSelectedItem();
    }
}
