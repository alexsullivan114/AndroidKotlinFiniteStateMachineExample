package com.example.alex.statemachineexample

interface SandwichView {
  fun showSandwichList(sandwiches: List<Sandwich>)
  fun hideSandwichList()

  fun showAddSandwichView(predefinedSandwiches: List<Sandwich>)
  fun hideAddSandwichView()

  fun showSandwichInputFields()
  fun hideSandwichInputFields()
}