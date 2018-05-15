package br.com.duoli.speedrunapp.ui.main.games;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import br.com.duoli.speedrunapp.databinding.GamesItemBinding;
import br.com.duoli.sr4j.models.games.Game;

class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private List<Game> mGames;
    private GamesOnClickListener mClickListener;

    GameAdapter(GamesOnClickListener clickListener) {
        mClickListener = clickListener;
        mGames = Collections.emptyList();
    }

    interface GamesOnClickListener {
        void onClickGame(Game game);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        GamesItemBinding binding = GamesItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Game currentGame = mGames.get(position);
        holder.bind(currentGame);
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    public void setRuns(List<Game> games) {
        this.mGames = games;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private GamesItemBinding binding;

        ViewHolder(GamesItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        void bind(Game game) {
            binding.setGame(game);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            mClickListener.onClickGame(mGames.get(getAdapterPosition()));
        }
    }
}
