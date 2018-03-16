package com.example.alex.statemachineexample

data class Sandwich(val name: String, val type: SandwichType)

enum class SandwichType {
  GRINDER,
  WRAP
}