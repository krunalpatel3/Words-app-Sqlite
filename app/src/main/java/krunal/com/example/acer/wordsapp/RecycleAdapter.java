package krunal.com.example.acer.wordsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 09-02-2018.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    Context context;
    List<Word> mlistword = new ArrayList<>();

    public RecycleAdapter(Context context, List<Word> list){
        this.context = context;
        this.mlistword = list;
    }

    private OnItemClickListener mOnItemClickListener;

    public void SetOnClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item, parent,false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(RecycleAdapter.ViewHolder holder, int position) {
        Word word = mlistword.get(position);
        holder.mTextView.setText(word.getWord());
        holder.Bind(mlistword.get(position),mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mlistword.size();
    }

    Word get(int position){
        return mlistword.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.TextView);
        }

        void Bind(Word word, OnItemClickListener listener){
            itemView.setOnClickListener(v -> {
                listener.OnItemClick(word);
            });
        }
    }
}
