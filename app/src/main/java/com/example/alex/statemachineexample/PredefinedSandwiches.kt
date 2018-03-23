package com.example.alex.statemachineexample

import com.example.alex.statemachineexample.SandwichType.GRINDER
import com.example.alex.statemachineexample.SandwichType.WRAP

val predefinedSandwichList = listOf(
  Sandwich("Meatball", GRINDER),
  Sandwich("Italian", GRINDER),
  Sandwich("Caesar", WRAP),
  Sandwich("Bacon", WRAP),
  Sandwich("Egg", WRAP),
  Sandwich("Beef", GRINDER),
  Sandwich("Chacarero", GRINDER),
  Sandwich("Ham and Cheese", GRINDER),
  Sandwich("Kebab", WRAP)
)