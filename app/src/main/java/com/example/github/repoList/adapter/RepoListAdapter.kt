package com.example.github.repoList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.github.R
import com.example.github.repoList.api.Repo

class RepoListAdapter: RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {    private val repoList = mutableListOf<Repo>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.name)
        val typeTextView: TextView = view.findViewById(R.id.type)

        fun bind(repo: Repo) {
            nameTextView.text = repo.name
            typeTextView.text = if (repo.isPrivate) "Private" else "Public"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_repo_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    fun setList(list: List<Repo>) {
        repoList.clear()
        repoList.addAll(list)
        notifyDataSetChanged()
    }

}
