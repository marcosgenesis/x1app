package com.example.x1um

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.x1um.Model.Battle
import com.example.x1um.Model.User

class UserAdapter(usersList: List<User>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
        private val users:List<User> = usersList;

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.search_item,parent,false);
            return UserViewHolder(view);
        }

        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            val user = users.get(position)
            holder.bind(user);
        }

        override fun getItemCount(): Int {
            return users.size
        }

        class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val txtUsername = itemView.findViewById<TextView>(R.id.searchItemUsername)
            private val txtName = itemView.findViewById<TextView>(R.id.searchItemName)
            private val txtInitialLetterSearchItem = itemView.findViewById<TextView>(R.id.searchItemInitialLetter)

            fun bind(user: User) {
                txtName.text = user.name;
                txtUsername.text = user.username;
                txtInitialLetterSearchItem.text = user.name.substring(0,1);

            }

        }
}