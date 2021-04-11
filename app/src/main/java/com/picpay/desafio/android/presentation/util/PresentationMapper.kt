package com.picpay.desafio.android.presentation.util

abstract class PresentationMapper<in Domain, out Presentation> {
    abstract fun toPresentationBinding(domain: Domain): Presentation
}