package e.lenovo.soccerapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import e.lenovo.soccerapp.HomeActivity;

public class SportsRepository {

    private SportsDAO sportsDao;
    private LiveData<List<Sports>> allSports;

    public SportsRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        sportsDao = db.sportsDao();
        allSports = sportsDao.getAllSports();
    }

    public void insert(Sports spo) {
        new InsertSportsAsyncTask(sportsDao).execute(spo);
    }

    public void update(Sports spo) {
        new UpdateSportsAsyncTask(sportsDao).execute(spo);
    }

    public void delete(Sports spo) {
        new DeleteSportsAsyncTask(sportsDao).execute(spo);
    }

    public LiveData<List<Sports>> getAllSports() {
        return allSports;
    }

    private static class InsertSportsAsyncTask extends AsyncTask<Sports, Void, Void> {
        private SportsDAO sportsDao;

        private InsertSportsAsyncTask(SportsDAO sportsDao) {
            this.sportsDao = sportsDao;
        }

        @Override
        protected Void doInBackground(Sports... sports) {
            sportsDao.insertSport(sports[0]);
            return null;
        }
    }

    private static class UpdateSportsAsyncTask extends AsyncTask<Sports, Void, Void> {
        private SportsDAO sportsDao;

        private UpdateSportsAsyncTask(SportsDAO sportsDao) {
            this.sportsDao = sportsDao;
        }

        @Override
        protected Void doInBackground(Sports... sports) {
            sportsDao.updateSport(sports[0]);
            return null;
        }
    }

    private static class DeleteSportsAsyncTask extends AsyncTask<Sports, Void, Void> {
        private SportsDAO sportsDao;

        private DeleteSportsAsyncTask(SportsDAO sportsDao) {
            this.sportsDao = sportsDao;
        }

        @Override
        protected Void doInBackground(Sports... sports) {
            sportsDao.deleteSport(sports[0].getSportId());
            return null;
        }
    }

}
