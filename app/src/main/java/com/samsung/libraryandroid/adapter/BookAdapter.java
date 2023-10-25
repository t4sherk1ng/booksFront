package com.samsung.libraryandroid.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.samsung.libraryandroid.MainActivity;
import com.samsung.libraryandroid.R;
import com.samsung.libraryandroid.domain.Book;
import com.samsung.libraryandroid.fragment.ChangeBookFragment;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private List<Book> bookList;
    private Context context;

    public BookAdapter(Context context, List<Book> bookList) {

        this.inflater = LayoutInflater.from(context);
        this.bookList = bookList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName,
                tvAuthor,
                tvGenre;
        private final LinearLayout ll_item;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            ll_item = itemView.findViewById(R.id.ll_item);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvGenre = itemView.findViewById(R.id.tv_genre);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_book_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull RecyclerView.ViewHolder holder,
            @SuppressLint("RecyclerView") int position
    ) {

        Book book = bookList.get(position);

        ((MyViewHolder) holder).tvName.setText(book.getName());
        ((MyViewHolder) holder).tvAuthor.setText(book.getAuthor().getName());
        ((MyViewHolder) holder).tvGenre.setText(book.getGenre().getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeBookFragment changeClientFragment = new ChangeBookFragment();

                Bundle bundle = new Bundle();
                bundle.putSerializable(MainActivity.MSG_NAME, bookList.get(position));
                changeClientFragment.setArguments(bundle);

                ((AppCompatActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_main, changeClientFragment)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {

        return bookList.size();
    }
}
