package com.durini.cleanarch.business.domain.state

sealed class UIComponent {
    data class Dialog(
        val title: String,
        val description: String
    ) : UIComponent()

    data class None(
        val message: StringBuffer
    ) : UIComponent()
}
