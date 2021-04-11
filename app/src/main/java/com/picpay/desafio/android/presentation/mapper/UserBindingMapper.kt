package com.picpay.desafio.android.presentation.mapper

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.presentation.model.UserBinding
import com.picpay.desafio.android.presentation.util.PresentationMapper

object UserBindingMapper: PresentationMapper<List<User>, List<UserBinding>>() {
    override fun toPresentationBinding(domain: List<User>): List<UserBinding> = domain.map { user ->
        UserBinding(
            id = user.id,
            img = user.img,
            name = user.name,
            username = user.username
        )
    }
}