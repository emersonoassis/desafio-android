package com.picpay.desafio.android.presentation.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.presentation.model.UserBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    private val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(userBinding: UserBinding) {
        binding.apply {
            name.text = userBinding.name
            username.text = userBinding.username
            progressBar.visibility = View.VISIBLE

            Picasso.get()
                .load(userBinding.img)
                .error(R.drawable.ic_round_account_circle)
                .into(picture, object : Callback {
                    override fun onSuccess() {
                        progressBar.visibility = View.GONE
                    }
                    override fun onError(e: Exception?) {
                        progressBar.visibility = View.GONE
                    }
                })

        }
    }
}