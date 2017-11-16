package fr.polytechnice.caddyhop.views.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.polytechnice.caddyhop.activities.CreateListActivity;
import fr.polytechnice.caddyhop.models.Article;
import fr.polytechnice.caddyhop.views.viewholders.ItemViewHolder;

/**
 * Created by shafiq on 10/06/16.
 *
 * Used for the AutoCompleteTextView
 */

public class ItemAdapter extends BaseAdapter implements Filterable {
    Context context;
    List<Article> articleList;

    public ItemAdapter(Context context, List<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
        filter = new ItemFilter();
    }

    @Override
    public int getCount() {
        if(articleList != null) {
            return articleList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return articleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemView itemView;

        if(convertView == null) {
            itemView = new ItemView(context, articleList.get(position));
        } else {
            itemView = (ItemView) convertView;
            itemView.setName(articleList.get(position).getNom());
        }

        return itemView;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private ItemFilter filter;
    private List<Article> orig;


    private class ItemFilter extends Filter {
        public ItemFilter() {}

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults oReturn = new FilterResults();

            List<Article> results = new ArrayList<Article>();

            if (orig == null)
                orig = articleList;

            if (constraint != null) {
                if (orig != null && orig.size() > 0) {

                    for (Article a : orig) {

                        if (a.getNom().contains(constraint))
                            results.add(a);

                    }
                }
                oReturn.values = results;
            }
            return oReturn;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            articleList = (ArrayList<Article>)results.values;
            notifyDataSetChanged();
        }
    }

    public class ItemView extends LinearLayout {
        TextView txtName;

        public ItemView(final Context context, final Article article) {

            super(context);
            this.setOrientation(VERTICAL);
            this.setDividerPadding(2);
            txtName = new TextView(context);
            txtName.setText(article.getNom());
            txtName.setTextSize(20);

            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10,10,10,10);
            this.setTag(article);

            addView(txtName, layoutParams);
        }

        public void setName(String value) { txtName.setText(value); }

    }
}
