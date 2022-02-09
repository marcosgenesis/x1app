package com.example.x1um

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.x1um.Model.Battle
import com.example.x1um.Model.User

class UserAdapter(usersList: List<User>, var clickListener: OnUserClickListener):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
        var onItemClick: ((User) -> Unit)? = null
        private val users:List<User> = usersList;

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.search_item,parent,false);
            return UserViewHolder(view);
        }

        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            val user = users.get(position)
            holder.bind(user,clickListener);
        }

        override fun getItemCount(): Int {
            return users.size
        }

        inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val txtUsername = itemView.findViewById<TextView>(R.id.searchItemUsername)
            private val txtName = itemView.findViewById<TextView>(R.id.searchItemName)
            private val txtInitialLetterSearchItem = itemView.findViewById<TextView>(R.id.searchItemInitialLetter)
            private val games = itemView.findViewById<TextView>(R.id.games)
            private val points = itemView.findViewById<TextView>(R.id.points)


            fun bind(user: User,action:OnUserClickListener) {
                txtName.text = user.name;
                txtUsername.text = user.username;
                //var lastname = user.name.split(" ")[1]
                //txtInitialLetterSearchItem.text = user.name.substring(0,1) + lastname.substring(0, 1).uppercase();
                if(user.games == 1) {
                    games.text = user.games.toString() + " jogo"
                } else {
                    games.text = user.games.toString() + " jogos"
                }
                if(user.points == 1) {
                    points.text = user.points.toString() + " ponto"
                } else {
                    points.text = user.points.toString() + " pontos"
                }

                itemView.setOnClickListener{
                    action.onItemClick(user,adapterPosition)
                }
            }


        }
}
interface OnUserClickListener{
    fun onItemClick(item:User,position: Int)
}