package com.tbrey1gmail.this_sucks;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        // Initialize contacts
        ArrayList<Contact> contacts = Contact.createContactsList(20);
        // Create adapter passing in the sample user data
        ContactsAdapter adapter = new ContactsAdapter(this, contacts);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }

    // Create the basic adapter extending from RecyclerView.Adapter
    // Note that we specify the custom ViewHolder which gives us access to our views
    public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {


        // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        public class ViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
            public TextView nameTextView;
            public Button messageButton;




            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);

                nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
                messageButton = (Button) itemView.findViewById(R.id.message_button);
            }

        }

//        Every adapter has three primary methods:
//            onCreateViewHolder to inflate the item layout and create the holder,
//            onBindViewHolder to set the view attributes based on the data and
//            getItemCount to determine the number of items. We need to implement all three to finish the adapter:
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View contactView = inflater.inflate(R.layout.item_contact, parent, false);

            // Return a new holder instance
            ViewHolder viewHolder = new ViewHolder(contactView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            // Get the data model based on position
            Contact contact = mContacts.get(position);

            // Set item views based on your views and data model
            TextView textView = viewHolder.nameTextView;
            textView.setText(contact.getName());
            Button button = viewHolder.messageButton;
            button.setText(contact.isOnline() ? "Message" : "Offline");
            button.setEnabled(contact.isOnline());
        }

        @Override
        public int getItemCount() {
            return mContacts.size();
        }

        // Store a member variable for the contacts
        private List<Contact> mContacts;
        // Store the context for easy access
        private Context mContext;
        // Pass in the contact array into the constructor
        public ContactsAdapter(Context context, List<Contact> contacts) {
            mContacts = contacts;
            mContext = context;
        }

        // Easy access to the context object in the recyclerview
        private Context getContext() {
            return mContext;
        }


    }





}
