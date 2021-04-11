package com.picpay.desafio.android.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.presentation.model.UserBinding

class UserListDiffCallback : DiffUtil.ItemCallback<UserBinding>() {
    override fun areItemsTheSame(oldItem: UserBinding, newItem: UserBinding): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: UserBinding, newItem: UserBinding): Boolean =  oldItem == newItem
}