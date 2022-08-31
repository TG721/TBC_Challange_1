package net.coremotion.challenge1.ui.users.source

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import net.coremotion.challenge1.databinding.ItemUserBinding
import net.coremotion.challenge1.domain.model.Users
import net.coremotion.challenge1.extensions.setImage


class UsersAdapter(private val itemClick: (Users.Data) -> Unit) :
    PagingDataAdapter<Users.Data, UsersAdapter.UserViewHolder>(
        DiffCallback()
    ) {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(getItem(position)!!, itemClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        ItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: Users.Data, click: (Users.Data) -> Unit) {
            binding.profileImage.setImage(model.avatar)
            binding.tvEmail.text = model.email
            binding.tvName.text = model.firstName.plus(" ").plus(model.lastName)
            binding.root.setOnClickListener { click(model) }
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<Users.Data>() {
        override fun areItemsTheSame(oldItem: Users.Data, newItem: Users.Data) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Users.Data, newItem: Users.Data) =
            oldItem == newItem

    }
}

