package edu.srvstva.entertainment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class TopRatedFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] topMovies = new String[TopRatedMovie.topRatedMovies.length];
        for (int i = 0; i < TopRatedMovie.topRatedMovies.length; i++) {
            topMovies[i] = TopRatedMovie.topRatedMovies[i].getName()
                    + ", "
                    + TopRatedMovie.topRatedMovies[i].getYear();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                topMovies
        );
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private static class TopRatedMovie {
        private String name;
        private String year;

        private TopRatedMovie(String name, String year) {
            this.name = name;
            this.year = year;
        }

        public String getName() {
            return name;
        }

        String getYear() {
            return year;
        }

        static final TopRatedMovie[] topRatedMovies = {
                new TopRatedMovie("Black Panther", "2018"),
                new TopRatedMovie("Avengers Infinity War", "2018"),
                new TopRatedMovie("Thor Ragnorak", "2017")
        };
    }
}
