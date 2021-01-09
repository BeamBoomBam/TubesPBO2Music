package sample.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
import java.util.Optional;

public class Controller {
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
    public ListView PlaylistView;
    private ObservableList<Music> mList;
    Stage new_stage;

    public void initialize (){
        MusicDAO mDao = new MusicDAO();
        ObservableList<Music> mList = (ObservableList<Music>) mDao.showData();
        TabelLagu.setItems(mList);
        KolomJudul.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getJudul()));
        KolomAlbum.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getAlbum()));
        KolomArtist.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getPenyanyi()));

        Music music = TabelLagu.getSelectionModel().getSelectedItem();
        System.out.println(music);
    }

    public void addPlaylist(ActionEvent ex) throws IOException {
        PlaylistDAO pDao = new PlaylistDAO();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setContentText("input a club name :");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            int id = 0;
            String playlist = result.get();
            pDao.addData(new Playlist(playlist));
        }
        ObservableList<Playlist> pList = (ObservableList<Playlist>) pDao.showData();
        PlaylistView.setItems(pList);
        PlaylistView.refresh();
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
}
