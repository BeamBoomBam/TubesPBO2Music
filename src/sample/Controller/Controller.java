package sample.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import sample.DAO.MusicDAO;
import sample.Model.Music;

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
    private ObservableList<Music> mList;

    public void initialize(){
        MusicDAO mDao = new MusicDAO();
        ObservableList<Music> mList = (ObservableList<Music>) mDao.showData();
        TabelLagu.setItems(mList);
        KolomJudul.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getJudul()));
        KolomAlbum.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getAlbum()));
        KolomArtist.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getPenyanyi()));

        Music music = TabelLagu.getSelectionModel().getSelectedItem();
        System.out.println(music);
    }

    public void addPlaylist(ActionEvent actionEvent) {
    }
}
