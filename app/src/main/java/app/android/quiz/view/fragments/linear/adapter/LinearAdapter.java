package app.android.quiz.view.fragments.linear.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.android.quiz.R;
import app.android.quiz.data.storage.database.entities.Linear;
import app.android.quiz.view.fragments.linear.FragmentLinear;
import io.github.kbiakov.codeview.CodeView;
import io.github.kbiakov.codeview.adapters.Format;
import io.github.kbiakov.codeview.adapters.Options;
import io.github.kbiakov.codeview.highlight.ColorTheme;
import io.github.kbiakov.codeview.highlight.Font;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;


/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */
public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.MyViewHolder> {

    private Context mContext;
    private List<Linear> mGroupLinears;
    private FragmentLinear mFragment;

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvQuestion;
        CodeView answera, answerb, answerc, answerd;

        MyViewHolder(View view) {
            super(view);
            tvQuestion = view.findViewById(R.id.tvQuestion);
            answera = view.findViewById(R.id.answera);
            answerb = view.findViewById(R.id.answerb);
            answerc = view.findViewById(R.id.answerc);
            answerd = view.findViewById(R.id.answerd);
        }
    }

    public LinearAdapter(Context context, FragmentLinear fragment, List<Linear> items) {
        mContext = context;
        mFragment = fragment;
        mGroupLinears = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_linear_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Linear item = mGroupLinears.get(position);
        holder.tvQuestion.setText(item.getQuestion());
        holder.answera.setCode(item.getA());
        holder.answera.updateOptions(new Function1<Options, Unit>() {
            @Override
            public Unit invoke(Options options) {
                options.withFont(Font.Consolas)
                        .withTheme(ColorTheme.SOLARIZED_LIGHT)
                        .withShadows()
                        .setShortcut(false);
                return null;
            }
        });
        holder.answerb.setCode(item.getB());
        holder.answerb.updateOptions(new Function1<Options, Unit>() {
            @Override
            public Unit invoke(Options options) {
                options.withFont(Font.Consolas)
                        .withTheme(ColorTheme.SOLARIZED_LIGHT)
                        .withShadows()
                        .setShortcut(false);
                return null;
            }
        });
        holder.answerc.setCode(item.getB());
        holder.answerc.updateOptions(new Function1<Options, Unit>() {
            @Override
            public Unit invoke(Options options) {
                options.withFont(Font.Consolas)
                        .withTheme(ColorTheme.SOLARIZED_LIGHT)
                        .withShadows()
                        .setShortcut(false);
                return null;
            }
        });
        holder.answerd.setCode(item.getB());
        holder.answerd.updateOptions(new Function1<Options, Unit>() {
            @Override
            public Unit invoke(Options options) {
                options.withFont(Font.Consolas)
                        .withLanguage("java")
                        .withTheme(ColorTheme.SOLARIZED_LIGHT)
                        .withShadows()
                        .setShortcut(false);
                return null;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGroupLinears.size();
    }

    public void updateLinears(List<Linear> items) {
        mGroupLinears = items;
        notifyDataSetChanged();
    }
}
