package br.com.duoli.speedrunapp.tools;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.com.duoli.speedrunapp.R;

public class BindingAdapters {

    @BindingAdapter({"userName", "runTime"})
    public static void setRunTime(TextView view, String userName, String runTime){
        Context context = view.getContext();
        String text = context.getResources().getString(R.string.latest_run_time_format,
                StringUtils.parseRunTime(runTime),
                userName);
        view.setText(text);
    }

    @BindingAdapter({"cover"})
    public static void setGameCover(ImageView view, String url){
        Context context = view.getContext();
        Glide.with(context)
                .load(url)
                .into(view);
    }
}
