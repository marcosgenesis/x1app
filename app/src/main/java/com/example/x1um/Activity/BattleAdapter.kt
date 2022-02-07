package com.example.x1um.Activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.x1um.Model.Battle
import com.example.x1um.R

class BattleAdapter(battlesList: List<Battle>):
    RecyclerView.Adapter<BattleAdapter.BattleViewHolder>() {
    private val battles:List<Battle> = battlesList;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BattleViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.battle_item,parent,false);
        return BattleViewHolder(view);
    }

    override fun onBindViewHolder(holder: BattleViewHolder, position: Int) {
       val battle = battles.get(position)
        holder.bind(battle)
    }

    override fun getItemCount(): Int {
       return battles.size
    }

    class BattleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtMyName = itemView.findViewById<TextView>(R.id.myName)
        private val txtInitialLetter = itemView.findViewById<TextView>(R.id.profileBoxTextView)
        private val txtInitialLetterOponent = itemView.findViewById<TextView>(R.id.oponentProfileBoxTextView)
        private val txtOponentName = itemView.findViewById<TextView>(R.id.oponentName)
        private val txtMyGoals = itemView.findViewById<TextView>(R.id.txtMyGoals)
        private val txtOponentGoals = itemView.findViewById<TextView>(R.id.txtOponentGoals)
        fun bind(battle: Battle) {
            txtMyName.text = battle.getMyName();
            txtMyGoals.text = battle.getGoalsPro().toString();
            txtOponentName.text = battle.getOponentName();
            txtOponentGoals.text = battle.getGoalsAgainst().toString();
            txtInitialLetter.text = battle.getMyName()?.substring(0, 1);
            txtInitialLetterOponent.text = battle.getOponentName()?.substring(0, 1);
        }

    }

}
