package com.example.mangareader.BookRecycler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mangareader.Models.Book;
import com.example.mangareader.R;

import java.util.ArrayList;
import java.util.List;

public class BookRecyclerFragment extends Fragment {

    private RecyclerView bookRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_recycler, container, false);

        //Fetch UI elements.
        bookRecycler = view.findViewById(R.id.bookRecycler);

        //Specify how it should be laid out.
        bookRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Fetch Data.
        ArrayList<Book> bookList = fetchBookList();

        bookList.add(new Book("aswsddassdasd"));
        bookList.add(new Book("aswwsddwddwassdasd"));
        bookList.add(new Book("asdwwsddassdasd"));
        bookList.add(new Book("aswsddassdasd"));
        bookList.add(new Book("aswsddassasdasddasd"));
        bookList.add(new Book("aswwsddaassdasd"));
        bookList.add(new Book("aswsddassdasd"));
        bookList.add(new Book("asdwsdsadsdasddassdasd"));
        bookList.add(new Book("aswsddassdasd"));
        bookList.add(new Book("aswsdaasdddassdasd"));
        bookList.add(new Book("aswsddassdasd"));
        bookList.add(new Book("aswsasdsadddassdasd"));
        bookList.add(new Book("aswsassdaddassdasd"));
        bookList.add(new Book("a"));


        BookAdapter adapter = new BookAdapter(bookList);

        //Connect adapter to recycler view.
        bookRecycler.setAdapter(adapter);

        return view;
    }

    private class BookAdapter extends RecyclerView.Adapter<BookDataViewHolder>
    {
        private ArrayList<Book> bookList;

        public BookAdapter(ArrayList<Book> inBookList)
        {
            bookList = inBookList;
        }

        @Override
        public int getItemCount()
        {
            return bookList.size();
        }

        @NonNull
        public BookDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new BookDataViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(BookDataViewHolder viewHolder, int index)
        {
            viewHolder.bind(bookList.get(index));
        }
    }

    private class BookDataViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title;

        public BookDataViewHolder(LayoutInflater layoutInflater, ViewGroup parent)
        {
            super(layoutInflater.inflate(R.layout.fragment_book_item, parent, false));

            //Find UI elements.
            title = itemView.findViewById(R.id.bookTitleTextView);
        }

        public void bind(Book book)
        {
            title.setText(book.getTitle());
        }
    }

    private ArrayList<Book> fetchBookList()
    {
        ArrayList<Book> bookList = new ArrayList<Book>();

        return bookList;
    }
}