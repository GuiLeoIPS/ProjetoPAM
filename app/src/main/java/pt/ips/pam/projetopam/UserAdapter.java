package pt.ips.pam.projetopam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {


    Context context;
    Activity activity;
    ArrayList user_id, user_name, user_password, user_email, user_number;

    UserAdapter(Activity activity,Context context, ArrayList user_id, ArrayList user_name, ArrayList user_password, ArrayList user_email, ArrayList user_number){
        this.activity = activity;
        this.context = context;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_email = user_email;
        this.user_number = user_number;


    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new UserAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
        holder.user_id_text.setText(String.valueOf(user_id.get(position)));
        holder.user_name_text.setText(String.valueOf(user_name.get(position)));
        holder.user_password_text.setText(String.valueOf(user_password.get(position)));
        holder.user_email_text.setText(String.valueOf(user_email.get(position)));
        holder.user_number_text.setText(String.valueOf(user_number.get(position)));


    }

    /*@Override
    public void onBindViewHolder(@NonNull final UserAdapter.MyViewHolder holder, final int position) {
        holder.user_id_text.setText(String.valueOf(user_id.get(position)));
        holder.user_name_text.setText(String.valueOf(user_name.get(position)));
        holder.user_password_text.setText(String.valueOf(user_password.get(position)));
        holder.user_email_text.setText(String.valueOf(user_email.get(position)));
        holder.user_number_text.setText(String.valueOf(user_number.get(position)));


    }*/

    @Override
    public int getItemCount() {
        return user_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user_id_text, user_name_text, user_password_text, user_email_text, user_number_text;
        LinearLayout mainlayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            user_id_text = itemView.findViewById(R.id.book_id_txt);
            user_name_text = itemView.findViewById(R.id.book_title_txt);
            user_password_text = itemView.findViewById(R.id.book_author_txt);
            user_email_text = itemView.findViewById(R.id.book_pages_txt);
            user_number_text = itemView.findViewById(R.id.book_pages_txt);
            mainlayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}


