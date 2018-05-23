package br.com.duoli.speedrunapp.ui.detail;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.duoli.speedrunapp.databinding.LeaderboardItemBinding;
import br.com.duoli.speedrunapp.tools.StringUtils;
import br.com.duoli.sr4j.models.games.GameAssets;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;
import br.com.duoli.sr4j.models.leaderboards.LeaderboardPlace;
import br.com.duoli.sr4j.models.runs.Run;
import br.com.duoli.sr4j.models.users.User;

class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    private LeaderboardOnClickListener mClickListener;
    private Leaderboard mLeaderboard;

    public LeaderboardAdapter(LeaderboardOnClickListener clickListener) {
        mClickListener = clickListener;
    }

    interface LeaderboardOnClickListener {
        void onClickLeaderboard(String videoUrl);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LeaderboardItemBinding binding = LeaderboardItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LeaderboardPlace place = mLeaderboard.getRuns().get(position);

        configurePlaceImage(holder.binding.placeImage, place.getPlace());
        configurePosition(holder.binding.runPlace, place.getPlace());
        configureUsername(holder.binding.userName, position);

        holder.binding.runTime.setText(StringUtils
                .parseRunTime(place.getRun().getTimes().getPrimary()));

    }

    private void configurePosition(TextView view, int position) {
        String positionTxt;

        String format = "{0,ordinal}";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            positionTxt = android.icu.text.MessageFormat.format(format, position);
        } else {
            positionTxt =  com.ibm.icu.text.MessageFormat.format(format, position);
        }
        view.setText(positionTxt);
    }

    private void configurePlaceImage(ImageView view, int position) {
        GameAssets assets = mLeaderboard.getGame().getAssets();
        String positionAsset;
        switch (position) {
            case 1:
                positionAsset = assets.getTrophyFirst().getUri();
                break;
            case 2:
                positionAsset = assets.getTrophySecond().getUri();
                break;
            case 3:
                positionAsset = assets.getTrophyThird().getUri();
                break;
            default:
                positionAsset = "";
        }
        if (!positionAsset.equals("")) {
            Picasso.get().load(positionAsset).into(view);
        } else {
            view.setVisibility(View.INVISIBLE);
        }

    }

    private void configureUsername(TextView view, int position) {
        User user = mLeaderboard.getPlayers().get(position);
        view.setText(user.getName());

    }

    @Override
    public int getItemCount() {
        return mLeaderboard == null ? 0 : mLeaderboard.getRuns().size();
    }

    public void setLeaderboard(Leaderboard leaderboard) {
        this.mLeaderboard = leaderboard;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LeaderboardItemBinding binding;

        ViewHolder(LeaderboardItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Run run = mLeaderboard.getRuns().get(getAdapterPosition()).getRun();
            mClickListener.onClickLeaderboard(run.getVideos().getLinks().get(0).getUri());
        }
    }
}
