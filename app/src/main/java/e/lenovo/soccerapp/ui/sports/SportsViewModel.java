package e.lenovo.soccerapp.ui.sports;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import e.lenovo.soccerapp.data.Sports;
import e.lenovo.soccerapp.data.SportsRepository;

public class SportsViewModel extends AndroidViewModel {

    private SportsRepository sRepo;

    private final LiveData<List<Sports>> aSports;

    public SportsViewModel(@NonNull Application application) {
        super(application);
        sRepo = new SportsRepository(application);
        aSports = sRepo.getAllSports();
    }

    public void insert(Sports spo) { sRepo.insert(spo); }

    public void update(Sports spo) { sRepo.update(spo); }

    public void delete(Sports spo) { sRepo.delete(spo); }

    LiveData<List<Sports>> getAllSports() { return aSports; }
}