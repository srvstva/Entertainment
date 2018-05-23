package edu.srvstva.entertainment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.srvstva.entertainment.model.Movie;
import edu.srvstva.entertainment.model.MovieResponse;
import edu.srvstva.entertainment.service.TMDBClient;
import edu.srvstva.entertainment.service.TMDBInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MoviesFragment extends Fragment {
    private static final String TAG = MoviesFragment.class.getSimpleName();
    private static final String TMDB_API_KEY = "d52faf1c3f5a7486d617234325d58aab";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        final RecyclerView rv = view.findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        TMDBInterface tmdbInterface = TMDBClient.getClient(getContext()).create(TMDBInterface.class);
        Call<MovieResponse> call = tmdbInterface.getTopRatedMovies(TMDB_API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                Log.d(TAG, "No of movies received: " + movies.size());
                /*for (Movie movie : movies) {
                    Log.d(TAG, movie.getTitle() + " " + movie.getVoteAverage() + " "
                            + movie.getReleaseDate());
                }*/
                rv.setAdapter(new MoviesAdapter(movies, getContext()));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
        return view;
    }

}
