package com.durini.cleanarch.business.domain.model

enum class HeroAttackType(val uiValue: String) {
    Melee("Melee"),
    Ranged("Ranged"),
    Unknown("Unknown")
}

fun getHeroAttackType(uiValue: String): HeroAttackType{
    return when(uiValue){
        HeroAttackType.Melee.uiValue -> {
            HeroAttackType.Melee
        }
        HeroAttackType.Ranged.uiValue -> {
            HeroAttackType.Ranged
        }
        else -> {
            HeroAttackType.Unknown
        }
    }
}