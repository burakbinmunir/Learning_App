package com.android.learningapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class FAQListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] faqTitles;
    private final String[] faqAnswers;

    public FAQListAdapter(Context context, String[] faqTitles, String[] faqAnswers) {
        super(context, R.layout.list_item_faq, faqTitles);
        this.context = context;
        this.faqTitles = faqTitles;
        this.faqAnswers = faqAnswers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_faq, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.titleTextView = convertView.findViewById(R.id.text_faq_title);
            viewHolder.answerTextView = convertView.findViewById(R.id.text_faq_answer);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.titleTextView.setText(faqTitles[position]);
        viewHolder.answerTextView.setText(faqAnswers[position]);

        return convertView;
    }

    static class ViewHolder {
        TextView titleTextView;
        TextView answerTextView;
    }
}
