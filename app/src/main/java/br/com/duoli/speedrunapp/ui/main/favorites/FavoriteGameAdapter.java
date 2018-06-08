package br.com.duoli.speedrunapp.ui.main.favorites;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import br.com.duoli.speedrunapp.databinding.FavoriteGamesItemBinding;
import br.com.duoli.speedrunapp.model.FavoriteGame;

class FavoriteGameAdapter extends RecyclerView.Adapter<FavoriteGameAdapter.ViewHolder> {

    private List<FavoriteGame> mGames;
    private GamesOnClickListener mClickListener;

    FavoriteGameAdapter(GamesOnClickListener clickListener) {
        mClickListener = clickListener;
        mGames = Collections.emptyList();
    }

    interface GamesOnClickListener {
        void onClickGame(FavoriteGame game);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FavoriteGamesItemBinding binding = FavoriteGamesItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavoriteGame currentGame = mGames.get(position);
        holder.bind(currentGame);
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    public void setRuns(List<FavoriteGame> games) {
        this.mGames = games;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private FavoriteGamesItemBinding binding;

        ViewHolder(FavoriteGamesItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        void bind(FavoriteGame game) {
            binding.setGame(game);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            mClickListener.onClickGame(mGames.get(getAdapterPosition()));
        }
    }
}
