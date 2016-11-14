package cat.udl.menufinder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.models.Review;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private Context context;
    private List<Review> reviews;

    public ReviewAdapter(Context context, List<Review> reviews) {

        this.context = context;
        this.reviews = reviews;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_review, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Review review = getReview(position);
        holder.review.setText(review.getReview());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    private Review getReview(int position) {
        return reviews.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView review;

        public ViewHolder(View itemView) {
            super(itemView);
            review = (TextView) itemView.findViewById(R.id.review);
        }

    }

    public void addReview(Review review) {
        reviews.add(review);
    }

}
