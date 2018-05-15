package br.com.duoli.speedrunapp.ui.main.latestruns;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import br.com.duoli.speedrunapp.databinding.LatestRunItemBinding;
import br.com.duoli.sr4j.models.runs.Run;

class LatestRunAdapter extends RecyclerView.Adapter<LatestRunAdapter.ViewHolder> {

    private List<Run> mRuns;
    private LatestRunOnClickListener mClickListener;

    LatestRunAdapter(LatestRunOnClickListener clickListener) {
        mClickListener = clickListener;
        mRuns = Collections.emptyList();
    }

    interface LatestRunOnClickListener {
        void onClickLatestRun(Run run);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LatestRunItemBinding binding = LatestRunItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Run currentRun = mRuns.get(position);
        holder.bind(currentRun);
    }

    @Override
    public int getItemCount() {
        return mRuns.size();
    }

    public void setRuns(List<Run> runs) {
        this.mRuns = runs;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LatestRunItemBinding binding;

        ViewHolder(LatestRunItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        void bind(Run run) {
            binding.setRun(run);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            mClickListener.onClickLatestRun(mRuns.get(getAdapterPosition()));
        }
    }
}
